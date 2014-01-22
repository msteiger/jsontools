package org.sweetlemonade.eclipse.json.editor;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Set;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.ForwardingDocumentProvider;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.MarkerAnnotation;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.sweetlemonade.eclipse.json.Constants;
import org.sweetlemonade.eclipse.json.JsonPlugin;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.model.JsonObject;
import org.sweetlemonade.eclipse.json.model.JsonObject.Key;
import org.sweetlemonade.eclipse.json.model.antlr.ParseUtils.ParseError;
import org.sweetlemonade.eclipse.json.outline.JsonOutlinePage;
import org.sweetlemonade.eclipse.json.outline.JsonQuickOutline;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.TokenType;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonEditor extends TextEditor
{
	private JsonOutlinePage mOutlinePage;
	private JsonElement mElement;
	private JsonAnnotationer mAnnotationer;
	private boolean mDirty;

	public JsonEditor()
	{
		setSourceViewerConfiguration(new JsonConfiguration(this));
	}

	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support)
	{
		support.setCharacterPairMatcher(new DefaultCharacterPairMatcher(new char[] { '{', '}', '[', ']' }));
		support.setMatchingCharacterPainterPreferenceKeys(JsonPreferencesInitializer.PREF_ENABLED_MATCH_BRACKET, JsonPreferencesInitializer.PREF_COLOR_MATCH_BRACKET);

		super.configureSourceViewerDecorationSupport(support);
	}

	@Override
	protected void initializeKeyBindingScopes()
	{
		setKeyBindingScopes(new String[] { Constants.JSON_EDITOR_CONTEXT });
	}

	private void format()
	{
		mDirty = isDirty();

		mAnnotationer.store();

		((ProjectionViewer) getSourceViewer()).doOperation(ISourceViewer.FORMAT);
	}

	@Override
	protected void createActions()
	{
		super.createActions();

		Action action = new Action()
		{
			@Override
			public void run()
			{
				format();
			}
		};

		action.setActionDefinitionId(Constants.COMMAND_FORMAT_ID);
		action.setText("Format Json");
		setAction(Constants.COMMAND_FORMAT_ID, action);

		action = new Action()
		{
			@Override
			public void run()
			{
				if (mElement != null)
				{
					final JsonQuickOutline outline = new JsonQuickOutline(getSite().getShell(), JsonEditor.this);
					outline.setInput(mElement);
					outline.setVisible(true);
					outline.setFocus();
				}
			}
		};

		action.setActionDefinitionId(Constants.COMMAND_QUICK_OUTLINE_ID);
		action.setText("Quick outline");
		setAction(Constants.COMMAND_QUICK_OUTLINE_ID, action);
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor)
	{
		super.doSave(progressMonitor);

		if (JsonPlugin.getDefault().getPreferenceStore().getBoolean(JsonPreferencesInitializer.PREF_AUTO_FORMAT_ON_SAVE))
		{
			format();
		}
	}

	public void doSaveAfterFormat()
	{
		if (!mDirty)
		{
			super.doSave(null);
		}
	}

	@Override
	protected void editorContextMenuAboutToShow(IMenuManager menu)
	{
		super.editorContextMenuAboutToShow(menu);

		addAction(menu, ITextEditorActionConstants.GROUP_EDIT, Constants.COMMAND_FORMAT_ID);
		addAction(menu, ITextEditorActionConstants.GROUP_OPEN, Constants.COMMAND_QUICK_OUTLINE_ID);
	}

	@Override
	protected void initializeEditor()
	{
		super.initializeEditor();

		final IPreferenceStore store = getPreferenceStore();
		final IPreferenceStore myStore = JsonPlugin.getDefault().getPreferenceStore();

		if (store != null)
		{
			setPreferenceStore(new ChainedPreferenceStore(new IPreferenceStore[] { store, myStore }));
		}
		else
		{
			setPreferenceStore(myStore);
		}
	}

	@Override
	protected void handlePreferenceStoreChanged(PropertyChangeEvent event)
	{
		super.handlePreferenceStoreChanged(event);

		final TokenType[] values = TokenType.values();
		final String property = event.getProperty();

		for (final TokenType colorType : values)
		{
			if (colorType.getKey().equals(property) || colorType.getEnabledKey().equals(property) || colorType.getStyleKey().equals(property))
			{
				updatePresentation();

				break;
			}
		}

		for (int i = 0; i < JsonPreferencesInitializer.FORMAT_KEYS.length; i++)
		{
			final String key = JsonPreferencesInitializer.FORMAT_KEYS[i];

			if (key.equals(property))
			{
				updatePresentation();

				break;
			}
		}
	}

	private void updatePresentation()
	{
		((JsonConfiguration) getSourceViewerConfiguration()).updatePreferences();

		getSourceViewer().invalidateTextPresentation();
	}

	@Override
	public void createPartControl(Composite parent)
	{
		super.createPartControl(parent);

		final SourceViewerDecorationSupport support = getSourceViewerDecorationSupport(getSourceViewer());
		support.install(getPreferenceStore());

		final ProjectionViewer sourceViewer = (ProjectionViewer) getSourceViewer();
		final ProjectionSupport projectionSupport = new ProjectionSupport(sourceViewer, getAnnotationAccess(), getSharedColors());
		projectionSupport.install();

		sourceViewer.doOperation(ProjectionViewer.TOGGLE);

		mAnnotationer = new JsonAnnotationer(sourceViewer.getProjectionAnnotationModel(), this, getSourceViewer());
	}

	@Override
	public IDocumentProvider getDocumentProvider()
	{
		IDocumentProvider provider = super.getDocumentProvider();

		if (provider == null)
		{
			return provider;
		}

		if (provider.getAnnotationModel(getEditorInput()) == null)
		{
			return new ForwardingDocumentProvider(null, new IDocumentSetupParticipant()
			{

				@Override
				public void setup(IDocument document)
				{
				}
			}, provider)
			{
				@Override
				public IAnnotationModel getAnnotationModel(Object element)
				{
					return new AnnotationModel();
				}
			};
		}

		return provider;
	}

	@Override
	protected ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles)
	{
		fAnnotationAccess = getAnnotationAccess();
		fOverviewRuler = createOverviewRuler(getSharedColors());

		final ProjectionViewer viewer = new ProjectionViewer(parent, ruler, getOverviewRuler(), isOverviewRulerVisible(), styles);

		getSourceViewerDecorationSupport(viewer);

		return viewer;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class required)
	{
		if (IContentOutlinePage.class.equals(required))
		{
			return getOutlinePage();
		}

		return super.getAdapter(required);
	}

	private void findDupKeys(JsonElement element, IResource resource, boolean hasResource)
	{
		if (element == null)
		{
			return;
		}

		if (element.isObject())
		{
			JsonObject object = element.asObject();

			Collection<Key> keys = object.keys();
			IAnnotationModel annotationModel = getSourceViewer().getAnnotationModel();

			for (Key key1 : keys)
			{
				for (Key key2 : keys)
				{
					if (key1 != key2 && key1.getValue().equals(key2.getValue()))
					{
						try
						{
							IMarker marker = resource.createMarker(Constants.MARKER_ERROR);

							marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
							marker.setAttribute(IMarker.LOCATION, "Line " + key1.getLine());
							marker.setAttribute(IMarker.MESSAGE, "Duplicate keys");

							int start = key1.getStart();
							int stop = key1.getStop();

							marker.setAttribute(IMarker.CHAR_START, start);
							marker.setAttribute(IMarker.CHAR_END, stop);

							if (!hasResource)
							{
								MarkerAnnotation annotation = new MarkerAnnotation(marker);

								annotationModel.addAnnotation(annotation, new Position(start, stop - start));

								mProblems.put(marker, annotation);
							}
						}
						catch (CoreException e)
						{
							e.printStackTrace();
						}
					}
				}

				findDupKeys(object.get(key1), resource, hasResource);
			}
		}
		else if (element.isArray())
		{
			Collection<JsonElement> childs = element.getChilds();

			for (JsonElement jsonElement : childs)
			{
				findDupKeys(jsonElement, resource, hasResource);
			}
		}
	}

	private IdentityHashMap<IMarker, Annotation> mProblems = new IdentityHashMap<>();

	public void setJsonInput(JsonElement element, Collection<ParseError> errors)
	{
		IResource resource = ResourceUtil.getResource(getEditorInput());
		boolean hasResource = true;

		if (resource == null)
		{
			hasResource = false;
			resource = ResourcesPlugin.getWorkspace().getRoot();
		}

		if (resource != null)
		{
			try
			{
				if (hasResource)
				{
					resource.deleteMarkers(Constants.MARKER_ERROR, false, 0);
				}

				IAnnotationModel annotationModel = getSourceViewer().getAnnotationModel();

				clearAnnos();

				for (ParseError parseError : errors)
				{
					IMarker marker = resource.createMarker(Constants.MARKER_ERROR);

					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					marker.setAttribute(IMarker.LOCATION, "Line " + parseError.line);
					marker.setAttribute(IMarker.MESSAGE, parseError.text);

					if (parseError.start != -1)
					{
						marker.setAttribute(IMarker.CHAR_START, parseError.start);

						if (parseError.stop != -1)
						{
							marker.setAttribute(IMarker.CHAR_END, parseError.stop);
						}
						else
						{
							marker.setAttribute(IMarker.CHAR_END, parseError.start + 1);
						}
					}

					if (!hasResource)
					{
						MarkerAnnotation annotation = new MarkerAnnotation(marker);

						annotationModel.addAnnotation(annotation, new Position(parseError.start, 1));
						mProblems.put(marker, annotation);
					}
				}
			}
			catch (CoreException e)
			{
				e.printStackTrace();
			}

			findDupKeys(element, resource, hasResource);
		}

		mAnnotationer.update(element);

		mElement = element;

		getOutlinePage().setInput(element);
	}

	private void clearAnnos()
	{
		IAnnotationModel annotationModel = getSourceViewer().getAnnotationModel();

		Set<IMarker> keySet = mProblems.keySet();

		for (IMarker iMarker : keySet)
		{
			try
			{
				iMarker.delete();
			}
			catch (CoreException e)
			{
				e.printStackTrace();
			}

			if (annotationModel != null)
			{
				annotationModel.removeAnnotation(mProblems.get(iMarker));
			}
		}

		mProblems.clear();
	}

	private JsonOutlinePage getOutlinePage()
	{
		if (mOutlinePage == null)
		{
			mOutlinePage = new JsonOutlinePage(this);
		}

		return mOutlinePage;
	}

	public JsonElement getJsonElement()
	{
		return mElement;
	}

	@Override
	public void dispose()
	{
		clearAnnos();

		super.dispose();

		mAnnotationer.dispose();
	}
}
