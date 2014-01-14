package org.bimeg.eclipse.json.outline;

import org.bimeg.eclipse.json.SelectionsFinder;
import org.bimeg.eclipse.json.editor.JsonEditor;
import org.bimeg.eclipse.json.model.JsonElement;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonOutlinePage extends ContentOutlinePage implements ISelectionListener
{
	private final JsonEditor mEditor;
	private JsonTreeExpander mExpander;
	private JsonElement mElement;
	private JsonElement mSelectedElement;
	private final SelectionsFinder mFinder = new SelectionsFinder();

	public JsonOutlinePage(JsonEditor editor)
	{
		mEditor = editor;
	}

	@Override
	public void createControl(Composite parent)
	{
		super.createControl(parent);

		final TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new JsonOutlineContentProvider());
		viewer.setLabelProvider(new JsonLabelProvider());
		getSite().getPage().addPostSelectionListener(this);

		mExpander = new JsonTreeExpander(viewer);

		if (mElement != null)
		{
			mExpander.update(mElement);
		}
	}

	@Override
	public void dispose()
	{
		super.dispose();

		getSite().getPage().removePostSelectionListener(this);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event)
	{
		final ISelection selection = event.getSelection();

		if (selection.isEmpty())
		{
			mEditor.resetHighlightRange();
		}
		else
		{
			final JsonElement jsonElement = (JsonElement) ((IStructuredSelection) selection).getFirstElement();

			if (jsonElement == mSelectedElement)
			{
				mSelectedElement = null;

				return;
			}

			try
			{
				mEditor.selectAndReveal(jsonElement.getStart(), jsonElement.getLength());
			}
			catch (final IllegalArgumentException x)
			{
				mEditor.resetHighlightRange();
			}
		}
	}

	public void setInput(JsonElement element)
	{
		mElement = element;

		final TreeViewer treeViewer = getTreeViewer();

		if (treeViewer != null)
		{
			mExpander.update(mElement);
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection)
	{
		if (selection instanceof ITextSelection && part instanceof JsonEditor)
		{
			final ITextSelection textSelection = (ITextSelection) selection;
			final int start = textSelection.getOffset();

			mSelectedElement = mFinder.findSelectedScope(start, mElement);

			if (mSelectedElement != null)
			{
				final TreeViewer treeViewer = getTreeViewer();

				final TreeSelection treeSelection = new TreeSelection(new TreePath(new Object[] { mSelectedElement }));

				treeViewer.reveal(mSelectedElement);
				treeViewer.setSelection(treeSelection);
			}
		}
	}
}