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
import org.sweetlemonade.eclipse.json.preference.JavaColorPrefsCopier;
import org.sweetlemonade.eclipse.json.preference.JsonPreferences;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.TokenType;
import org.sweetlemonade.eclipse.json.preference.PseudoPreferenceStore;

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
	private Button mBoldButton;
	private Button mItalicButton;
	private Button mUnderButton;
	private Button mStrikeButton;
	private Button mCopyJavaPrefsButton;

	public JsonSyntaxColorsPreferencePage()
	{
		final IPreferenceStore store = JsonPlugin.getDefault().getPreferenceStore();
		final TokenType[] types = TokenType.values();

		for (final TokenType colorType : types)
		{
			mPreferenceStore.setValue(colorType.getKey(), StringConverter.asString(colorType.getOwnColor(store)));
			mPreferenceStore.setValue(colorType.getEnabledKey(), colorType.isEnabled(store));
			mPreferenceStore.setValue(colorType.getStyleKey(), colorType.getOwnStyle(store));
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

		mBoldButton = new Button(appearanceComposite, SWT.CHECK);
		final FormData fd_btnBold = new FormData();
		fd_btnBold.top = new FormAttachment(colorButton, 10);
		fd_btnBold.left = new FormAttachment(list, 10);
		mBoldButton.setLayoutData(fd_btnBold);
		mBoldButton.setText("Bold");
		mBoldButton.addSelectionListener(this);

		mItalicButton = new Button(appearanceComposite, SWT.CHECK);
		final FormData fd_btnCheckButton0 = new FormData();
		fd_btnCheckButton0.top = new FormAttachment(mBoldButton, 10);
		fd_btnCheckButton0.left = new FormAttachment(list, 10);
		mItalicButton.setLayoutData(fd_btnCheckButton0);
		mItalicButton.setText("Italic");
		mItalicButton.addSelectionListener(this);

		mUnderButton = new Button(appearanceComposite, SWT.CHECK);
		final FormData fd_btnCheckButton_1 = new FormData();
		fd_btnCheckButton_1.top = new FormAttachment(colorButton, 10);
		fd_btnCheckButton_1.left = new FormAttachment(mItalicButton, 10);
		mUnderButton.setLayoutData(fd_btnCheckButton_1);
		mUnderButton.setText("Underline");
		mUnderButton.addSelectionListener(this);

		mStrikeButton = new Button(appearanceComposite, SWT.CHECK);
		final FormData fd_btnCheckButton_2 = new FormData();
		fd_btnCheckButton_2.top = new FormAttachment(mUnderButton, 10);
		fd_btnCheckButton_2.left = new FormAttachment(mItalicButton, 10);
		mStrikeButton.setLayoutData(fd_btnCheckButton_2);
		mStrikeButton.setText("Strikethrough");
		mStrikeButton.addSelectionListener(this);

		if (JavaColorPrefsCopier.mayCopyJavaColorPrefs())
		{
			mCopyJavaPrefsButton = new Button(appearanceComposite, SWT.NONE);
			FormData fd_btnCopyJavaPrefs = new FormData();
			fd_btnCopyJavaPrefs.top = new FormAttachment(0, 10);
			fd_btnCopyJavaPrefs.right = new FormAttachment(100, -10);
			mCopyJavaPrefsButton.setLayoutData(fd_btnCopyJavaPrefs);
			mCopyJavaPrefsButton.setText("Copy Java prefs");
			mCopyJavaPrefsButton.addSelectionListener(this);
		}

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
		final int style = mSelection.getOwnStyle(mPreferenceStore);

		mSelector.setColorValue(mSelection.getOwnColor(mPreferenceStore));
		mBoldButton.setSelection(JsonPreferences.isBold(style));
		mItalicButton.setSelection(JsonPreferences.isItalic(style));
		mUnderButton.setSelection(JsonPreferences.isUnderline(style));
		mStrikeButton.setSelection(JsonPreferences.isStrike(style));

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
			store.setValue(type.getStyleKey(), type.getOwnStyle(mPreferenceStore));
		}

		return true;
	}

	@Override
	protected void performDefaults()
	{
		super.performDefaults();

		final IPreferenceStore store = JsonPlugin.getDefault().getPreferenceStore();
		final TokenType[] types = TokenType.values();

		for (final TokenType type : types)
		{
			mPreferenceStore.setValue(type.getEnabledKey(), store.getDefaultBoolean(type.getEnabledKey()));
			mPreferenceStore.setValue(type.getKey(), store.getDefaultString(type.getKey()));
			mPreferenceStore.setValue(type.getStyleKey(), store.getDefaultInt(type.getStyleKey()));
		}

		updateSelection();
		mHighlighter.update();
	}

	@Override
	public void init(IWorkbench workbench)
	{
		setPreferenceStore(JsonPlugin.getDefault().getPreferenceStore());
	}

	private int collectStyle()
	{
		final boolean bold = mBoldButton.getSelection();
		final boolean italic = mItalicButton.getSelection();
		final boolean under = mUnderButton.getSelection();
		final boolean strike = mStrikeButton.getSelection();

		return JsonPreferences.mergeStyles(bold, italic, under, strike);
	}

	@Override
	public void widgetSelected(SelectionEvent e)
	{
		if (e.widget == mEnabledButton)
		{
			mPreferenceStore.setValue(mSelection.getEnabledKey(), mEnabledButton.getSelection());
		}
		else if (mCopyJavaPrefsButton != null && e.widget == mCopyJavaPrefsButton)
		{
			JavaColorPrefsCopier.copyPrefs(mPreferenceStore);
			updateSelection();
		}
		else
		{
			mPreferenceStore.setValue(mSelection.getStyleKey(), collectStyle());
		}

		mHighlighter.update();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e)
	{
		if (e.widget == mEnabledButton)
		{
			mPreferenceStore.setValue(mSelection.getEnabledKey(), mEnabledButton.getSelection());
		}
		else if (mCopyJavaPrefsButton != null && e.widget == mCopyJavaPrefsButton)
		{
			//XXX ???
		}
		else
		{
			mPreferenceStore.setValue(mSelection.getStyleKey(), collectStyle());
		}

		mHighlighter.update();
	}
}
