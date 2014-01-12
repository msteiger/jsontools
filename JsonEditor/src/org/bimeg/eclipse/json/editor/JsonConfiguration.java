package org.bimeg.eclipse.json.editor;

import org.bimeg.eclipse.json.JsonPlugin;
import org.bimeg.eclipse.json.preference.JsonPreferencesInitializer;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonConfiguration extends SourceViewerConfiguration
{
	private JsonScanner mJsonScanner;
	private final JsonEditor mEditor;

	public JsonConfiguration(JsonEditor editor)
	{
		mEditor = editor;
	}

	@Override
	public int getTabWidth(ISourceViewer sourceViewer)
	{
		return JsonPlugin.getDefault().getPreferenceStore().getInt(JsonPreferencesInitializer.PREF_TAB_WIDTH);
	}

	private ITokenScanner getJsonScanner()
	{
		if (mJsonScanner == null)
		{
			mJsonScanner = new JsonScanner();
		}

		return mJsonScanner;
	}

	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer)
	{
		return new JsonContentFormatter(mEditor);
	}

	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer)
	{
		return new MonoReconciler(new JsonReconcileStrategy(mEditor), false);
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
	{
		final PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr;

		dr = new DefaultDamagerRepairer(getJsonScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		return reconciler;
	}

	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType)
	{
		return new IAutoEditStrategy[] { new DefaultIndentLineAutoEditStrategy() };
	}

	public void updatePreferences()
	{
		if (mJsonScanner != null)
		{
			mJsonScanner.updateRules();
		}
	}
}
