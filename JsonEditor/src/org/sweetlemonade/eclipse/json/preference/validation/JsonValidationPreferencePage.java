package org.sweetlemonade.eclipse.json.preference.validation;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.sweetlemonade.eclipse.json.JsonPlugin;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer;

/**
 * 11 мая 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonValidationPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, SelectionListener, ModifyListener
{
    private Spinner mMaxErrors;

    public JsonValidationPreferencePage()
    {
    }

    @Override
    public void init(IWorkbench workbench)
    {
        setPreferenceStore(JsonPlugin.getDefault().getPreferenceStore());
    }

    @Override
    protected Control createContents(Composite parent)
    {
        final Composite validationComposite = new Composite(parent, SWT.NONE);
        validationComposite.setLayout(new FormLayout());

        Button enableValidation = new Button(validationComposite, SWT.CHECK);
        enableValidation.addSelectionListener(this);
        enableValidation.setLayoutData(new FormData());
        enableValidation.setText("Validate");

        Label lblNewLabel = new Label(validationComposite, SWT.NONE);
        FormData fd_lblNewLabel = new FormData();
        fd_lblNewLabel.top = new FormAttachment(enableValidation, 15);
        lblNewLabel.setLayoutData(fd_lblNewLabel);
        lblNewLabel.setText("Max errors:");

        mMaxErrors = new Spinner(validationComposite, SWT.BORDER);
        mMaxErrors.addModifyListener(this);
        FormData fd_spinner = new FormData();
        fd_spinner.bottom = new FormAttachment(lblNewLabel, 0, SWT.BOTTOM);
        fd_spinner.left = new FormAttachment(lblNewLabel, 10);
        mMaxErrors.setLayoutData(fd_spinner);

        Label lblNewLabel_1 = new Label(validationComposite, SWT.NONE);
        FormData fd_lblNewLabel_1 = new FormData();
        fd_lblNewLabel_1.bottom = new FormAttachment(lblNewLabel, 0, SWT.BOTTOM);
        fd_lblNewLabel_1.left = new FormAttachment(mMaxErrors, 10);
        lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
        lblNewLabel_1.setText("(0 - no limit)");

        IPreferenceStore store = getPreferenceStore();

        boolean validate = store.getBoolean(JsonPreferencesInitializer.PREF_VALIDATE);
        enableValidation.setSelection(validate);
        mMaxErrors.setSelection(store.getInt(JsonPreferencesInitializer.PREF_MAX_ERROR));
        mMaxErrors.setEnabled(validate);

        return validationComposite;
    }

    @Override
    public boolean performOk()
    {
        return true;
    }

    @Override
    public void modifyText(ModifyEvent e)
    {
        getPreferenceStore().setValue(JsonPreferencesInitializer.PREF_MAX_ERROR, ((Spinner) e.widget).getSelection());
    }

    @Override
    public void widgetSelected(SelectionEvent e)
    {
        boolean selection = ((Button) e.widget).getSelection();

        mMaxErrors.setEnabled(selection);

        getPreferenceStore().setValue(JsonPreferencesInitializer.PREF_VALIDATE, selection);
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e)
    {
    }
}
