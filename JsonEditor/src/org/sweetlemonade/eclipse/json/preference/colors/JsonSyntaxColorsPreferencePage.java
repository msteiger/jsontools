package org.sweetlemonade.eclipse.json.preference.colors;

import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.sweetlemonade.eclipse.json.Container;
import org.sweetlemonade.eclipse.json.JsonPlugin;
import org.sweetlemonade.eclipse.json.preference.PseudoPreferenceStore;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.TokenType;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonSyntaxColorsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, ISelectionChangedListener, IPropertyChangeListener, SelectionListener
{
	private final PseudoPreferenceStore mPreferenceStore = new PseudoPreferenceStore();
	private Button mEnabledButton;
	private TokenType mSelection;
	private ColorSelector mSelector;
	private TextHighlighter mHighlighter;

	public JsonSyntaxColorsPreferencePage()
	{
		final IPreferenceStore store = JsonPlugin.getDefault().getPreferenceStore();
		final TokenType[] types = TokenType.values();

		for (final TokenType colorType : types)
		{
			mPreferenceStore.setValue(colorType.getKey(), StringConverter.asString(colorType.getOwnColor(store)));
			mPreferenceStore.setValue(colorType.getEnabledKey(), colorType.isEnabled(store));
		}
	}

	@Override
	protected Control createContents(Composite parent)
	{
		final Composite appearanceComposite = new Composite(parent, SWT.NONE);
		appearanceComposite.setLayout(new FormLayout());

		final ListViewer listViewer = new ListViewer(appearanceComposite, SWT.BORDER | SWT.V_SCROLL);
		final List list = listViewer.getList();

		final FormData fd_list = new FormData();
		fd_list.top = new FormAttachment(0, 0);
		fd_list.left = new FormAttachment(0, 0);

		list.setLayoutData(fd_list);

		mEnabledButton = new Button(appearanceComposite, SWT.CHECK);
		final FormData fd_btnCheckButton = new FormData();
		fd_btnCheckButton.top = new FormAttachment(0, 0);
		fd_btnCheckButton.left = new FormAttachment(list, 10);
		mEnabledButton.setLayoutData(fd_btnCheckButton);
		mEnabledButton.setText("Enabled");
		mEnabledButton.addSelectionListener(this);

		mSelector = new ColorSelector(appearanceComposite);
		final Button colorButton = mSelector.getButton();
		final FormData fd_btnColorButton = new FormData();
		fd_btnColorButton.top = new FormAttachment(mEnabledButton, 10);
		fd_btnColorButton.left = new FormAttachment(list, 10);
		colorButton.setLayoutData(fd_btnColorButton);
		mSelector.addListener(this);

		final TextViewer textViewer = new TextViewer(appearanceComposite, SWT.BORDER);
		final StyledText styledText = textViewer.getTextWidget();
		final FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(100, -0);
		fd_styledText.right = new FormAttachment(100, -0);
		fd_styledText.top = new FormAttachment(list, 10);
		fd_styledText.left = new FormAttachment(0, 0);
		styledText.setLayoutData(fd_styledText);

		textViewer.setEditable(false);

		mHighlighter = new TextHighlighter(textViewer, mPreferenceStore);

		listViewer.setContentProvider(new ColorsContentProvider());
		listViewer.setLabelProvider(new ColorsLabelProvider());
		listViewer.addSelectionChangedListener(this);

		final TokenType[] values = TokenType.values();
		listViewer.setInput(new Container(values));

		if (mSelection == null)
		{
			listViewer.setSelection(new StructuredSelection(values[0]));
		}

		mHighlighter.update();

		return appearanceComposite;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event)
	{
		mSelection = (TokenType) ((IStructuredSelection) event.getSelection()).getFirstElement();

		updateSelection();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		if (ColorSelector.PROP_COLORCHANGE.equals(event.getProperty()))
		{
			mPreferenceStore.setValue(mSelection.getKey(), StringConverter.asString((RGB) event.getNewValue()));

			mHighlighter.update();
		}
	}

	private void updateSelection()
	{
		mSelector.setColorValue(mSelection.getOwnColor(mPreferenceStore));

		if (mSelection == TokenType.DEFAULT)
		{
			mEnabledButton.setSelection(true);
			mEnabledButton.setEnabled(false);
		}
		else
		{
			mEnabledButton.setEnabled(true);
			mEnabledButton.setSelection(mSelection.isEnabled(mPreferenceStore));
		}

		mHighlighter.update();
	}

	@Override
	public boolean performOk()
	{
		final IPreferenceStore store = JsonPlugin.getDefault().getPreferenceStore();
		final TokenType[] types = TokenType.values();

		for (final TokenType type : types)
		{
			store.setValue(type.getEnabledKey(), type.isEnabled(mPreferenceStore));
			store.setValue(type.getKey(), StringConverter.asString(type.getOwnColor(mPreferenceStore)));
		}

		return true;
	}

	@Override
	public void init(IWorkbench workbench)
	{
		setPreferenceStore(JsonPlugin.getDefault().getPreferenceStore());
	}

	@Override
	public void widgetSelected(SelectionEvent e)
	{
		mPreferenceStore.setValue(mSelection.getEnabledKey(), mEnabledButton.getSelection());

		mHighlighter.update();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e)
	{
		mPreferenceStore.setValue(mSelection.getEnabledKey(), mEnabledButton.getSelection());

		mHighlighter.update();
	}
}
