package org.sweetlemonade.eclipse.json.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
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
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.sweetlemonade.eclipse.json.Constants;
import org.sweetlemonade.eclipse.json.JsonPlugin;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.outline.JsonOutlinePage;
import org.sweetlemonade.eclipse.json.outline.JsonQuickOutline;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.ColorType;

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

		action.setActionDefinitionId(Constants.JSON_EDITOR_FORMAT_ACTION);
		action.setText("Format Json");
		setAction(Constants.JSON_EDITOR_FORMAT_ACTION, action);

		action = new Action()
		{
			@Override
			public void run()
			{
				if (mElement != null)
				{
					final JsonQuickOutline outline = new JsonQuickOutline(getSite().getShell(), null, JsonEditor.this);
					outline.setInput(mElement);
					outline.setVisible(true);
					outline.setFocus();
				}
			}
		};

		action.setActionDefinitionId(Constants.JSON_EDITOR_QUICK_OUTLINE_ACTION);
		action.setText("Quick outline");
		setAction(Constants.JSON_EDITOR_QUICK_OUTLINE_ACTION, action);
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
		super.doSave(null);
	}

	@Override
	protected void editorContextMenuAboutToShow(IMenuManager menu)
	{
		super.editorContextMenuAboutToShow(menu);

		addAction(menu, ITextEditorActionConstants.GROUP_EDIT, Constants.JSON_EDITOR_FORMAT_ACTION);
		addAction(menu, ITextEditorActionConstants.GROUP_OPEN, Constants.JSON_EDITOR_QUICK_OUTLINE_ACTION);
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

		final ColorType[] values = ColorType.values();
		final String property = event.getProperty();

		for (final ColorType colorType : values)
		{
			if (colorType.getKey().equals(property) || colorType.getEnabledKey().equals(property))
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

	public void setJsonInput(JsonElement element)
	{
		mAnnotationer.update(element);

		mElement = element;

		getOutlinePage().setInput(element);
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
		super.dispose();

		mAnnotationer.dispose();
	}
}
