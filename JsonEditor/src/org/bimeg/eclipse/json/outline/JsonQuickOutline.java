package org.bimeg.eclipse.json.outline;

import org.bimeg.eclipse.json.Container;
import org.bimeg.eclipse.json.SelectionsFinder;
import org.bimeg.eclipse.json.editor.JsonEditor;
import org.bimeg.eclipse.json.model.JsonElement;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.text.AbstractInformationControl;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonQuickOutline extends AbstractInformationControl implements ISelectionChangedListener, IInformationControlExtension2, IDoubleClickListener, FocusListener, KeyListener
{
	private final ListenerList mSelectionChangedListeners = new ListenerList();
	private TreeViewer mTreeViewer;
	private final JsonEditor mEditor;
	private JsonElement mJsonElement;
	private Text mFilter;
	private final SelectionsFinder mFinder = new SelectionsFinder();

	public JsonQuickOutline(Shell parentShell, ToolBarManager toolBarManager, JsonEditor editor)
	{
		super(parentShell, toolBarManager);
		mEditor = editor;

		create();

		final Rectangle bounds = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getBounds();

		final int width = bounds.width / 3;
		final int height = bounds.height / 2;

		final int x = bounds.x + (bounds.width - width) / 2;
		final int y = bounds.y + (bounds.height - height) / 2;

		setLocation(new Point(x, y));
		setSize(width, height);
	}

	@Override
	public void setInput(Object input)
	{
		mTreeViewer.setInput(new Container(input));

		final ISelection selection = mEditor.getSelectionProvider().getSelection();

		if (selection instanceof ITextSelection)
		{
			final ITextSelection textSelection = (ITextSelection) selection;
			final int start = textSelection.getOffset();

			final JsonElement found = mFinder.findSelectedScope(start, (JsonElement) input);

			if (found != null)
			{
				final TreeViewer treeViewer = getTreeViewer();

				final TreeSelection treeSelection = new TreeSelection(new TreePath(new Object[] { found }));

				treeViewer.reveal(found);
				treeViewer.setSelection(treeSelection);
			}
		}

		mTreeViewer.expandAll();
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener)
	{
		mSelectionChangedListeners.add(listener);
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener)
	{
		mSelectionChangedListeners.remove(listener);
	}

	@Override
	public boolean hasContents()
	{
		return true;
	}

	private void findSelection()
	{
		final String filter = mFilter.getText().trim().toLowerCase();
		final JsonElement input = (JsonElement) ((Container) mTreeViewer.getInput()).object;

		final JsonElement element = Checker.find(filter, input);
		mTreeViewer.setSelection(new TreeSelection(new TreePath(new Object[] { element })));
	}

	@Override
	protected void createContent(Composite parent)
	{
		parent.setLayout(new FormLayout());

		mFilter = new Text(parent, SWT.BORDER);
		final FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100, 0);
		fd_text.top = new FormAttachment(0);
		fd_text.left = new FormAttachment(0);
		mFilter.setLayoutData(fd_text);

		mFilter.addModifyListener(new ModifyListener()
		{

			@Override
			public void modifyText(ModifyEvent e)
			{
				mTreeViewer.refresh();
				mTreeViewer.expandAll();

				findSelection();
			}
		});

		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new TreeColumnLayout());
		final FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(mFilter, 0);
		fd_composite.left = new FormAttachment(0, 0);
		fd_composite.right = new FormAttachment(100, 0);
		fd_composite.bottom = new FormAttachment(100, 0);
		composite.setLayoutData(fd_composite);

		mTreeViewer = new TreeViewer(composite, getTreeStyle());
		mTreeViewer.setContentProvider(new JsonOutlineContentProvider());
		mTreeViewer.setLabelProvider(new JsonLabelProvider());
		mTreeViewer.setFilters(new ViewerFilter[] { new QuickOutlineFilter(mFilter) });

		mTreeViewer.addSelectionChangedListener(this);
		mTreeViewer.addDoubleClickListener(this);
		mTreeViewer.setComparer(new IElementComparer()
		{

			@Override
			public int hashCode(Object element)
			{
				return System.identityHashCode(element);
			}

			@Override
			public boolean equals(Object a, Object b)
			{
				return a == b;
			}
		});

		final Control control = mTreeViewer.getControl();

		mFilter.addKeyListener(this);
		control.addKeyListener(this);
		addFocusListener(this);
	}

	private int getTreeStyle()
	{
		return SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL;
	}

	public TreeViewer getTreeViewer()
	{
		return mTreeViewer;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event)
	{
		final ISelection selection = event.getSelection();

		if (selection.isEmpty())
		{
			mJsonElement = null;
		}
		else
		{
			mJsonElement = (JsonElement) ((IStructuredSelection) selection).getFirstElement();
		}
	}

	@Override
	public void doubleClick(DoubleClickEvent event)
	{
		if (mJsonElement != null)
		{
			showJsonElement();
		}
	}

	@Override
	public void dispose()
	{
		mTreeViewer.removeDoubleClickListener(this);

		final Control control = mTreeViewer.getControl();

		control.removeKeyListener(this);
		control.removeFocusListener(this);

		super.dispose();
	}

	@Override
	public void focusGained(FocusEvent e)
	{
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		setVisible(false);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR)
		{
			if (mJsonElement != null)
			{
				showJsonElement();
			}
		}
	}

	private void showJsonElement()
	{
		try
		{
			mEditor.selectAndReveal(mJsonElement.getStart(), mJsonElement.getLength());
		}
		catch (final IllegalArgumentException x)
		{
			mEditor.resetHighlightRange();
		}

		setVisible(false);
		dispose();
	}
}
