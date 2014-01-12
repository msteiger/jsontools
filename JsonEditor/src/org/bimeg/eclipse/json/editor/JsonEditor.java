package org.bimeg.eclipse.json.editor;

import org.bimeg.eclipse.json.Constants;
import org.bimeg.eclipse.json.JsonPlugin;
import org.bimeg.eclipse.json.model.JsonElement;
import org.bimeg.eclipse.json.outline.JsonOutlinePage;
import org.bimeg.eclipse.json.outline.JsonQuickOutline;
import org.bimeg.eclipse.json.preference.JsonPreferencesInitializer;
import org.bimeg.eclipse.json.preference.JsonPreferencesInitializer.ColorType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

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
/*				ISourceViewer sourceViewer = getSourceViewer();

				sourceViewer.getTextWidget().setRedraw(true);

				if (getSourceViewer() != null)
				{
					int offset = 5;//getReplacementOffset() + getCursorPosition();
					int exit = 7;//getReplacementOffset() + getReplacementString().length();

					IDocument document = getDocumentProvider().getDocument(getEditorInput());

					try
					{
						document.addPositionCategory(toString());
						document.addPositionUpdater(new IPositionUpdater()
						{

							@Override
							public void update(DocumentEvent event)
							{
							}
						});
						document.addPosition(toString(), new Position(offset, 2));

						LinkedModeModel model = new LinkedModeModel();

						LinkedPositionGroup group = new LinkedPositionGroup();
						group.addPosition(new LinkedPosition(document, offset, 2, LinkedPositionGroup.NO_STOP));
						model.addGroup(group);

						group = new LinkedPositionGroup();
						group.addPosition(new LinkedPosition(getDocumentProvider().getDocument(getEditorInput()), offset + 5, 2, LinkedPositionGroup.NO_STOP));
						model.addGroup(group);

						model.forceInstall();

						LinkedModeUI ui = new EditorLinkedModeUI(model, getSourceViewer());
						ui.setSimpleMode(true);
						ui.setExitPosition(getSourceViewer(), offset + 4, 0, Integer.MAX_VALUE);
						ui.setCyclingMode(LinkedModeUI.CYCLE_NEVER);
						ui.enter();
					}
					catch (BadLocationException x)
					{
					}
					catch (BadPositionCategoryException e)
					{
					}
				}
*/
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
//					((SourceViewer) getSourceViewer()).doOperation(SourceViewer.INFORMATION);

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
