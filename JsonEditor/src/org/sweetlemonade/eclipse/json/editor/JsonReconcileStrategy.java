package org.sweetlemonade.eclipse.json.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.swt.widgets.Display;
import org.sweetlemonade.eclipse.json.model.antlr.IllegalParseStateException;
import org.sweetlemonade.eclipse.json.model.antlr.ParseUtils;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonReconcileStrategy implements IReconcilingStrategy, IReconcilingStrategyExtension
{
	private IDocument mDocument;
	private final JsonEditor mEditor;

	public JsonReconcileStrategy(JsonEditor editor)
	{
		mEditor = editor;
	}

	@Override
	public void setDocument(IDocument document)
	{
		mDocument = document;
	}

	@Override
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion)
	{
		reconcile();
	}

	@Override
	public void reconcile(IRegion partition)
	{
		reconcile();
	}

	private void reconcile()
	{
		try
		{
			final Object parse = ParseUtils.parse(mDocument);

			Display.getDefault().asyncExec(new Runnable()
			{
				@Override
				public void run()
				{
					mEditor.setJsonInput(ParseUtils.tree(parse));
				}

			});

			return;
		}
		catch (IllegalParseStateException e)
		{
			e.getClass();

			return;
		}

/*		final JsonElement parse = parse();

		Display.getDefault().asyncExec(new Runnable()
		{
			@Override
			public void run()
			{
				mEditor.setJsonInput(parse);
			}

		});
*/	}

/*	private JsonElement parse()
	{
		return new JsonParserMy(mDocument).parse();
	}
*/
	@Override
	public void setProgressMonitor(IProgressMonitor monitor)
	{
	}

	@Override
	public void initialReconcile()
	{
		reconcile();
	}
}
