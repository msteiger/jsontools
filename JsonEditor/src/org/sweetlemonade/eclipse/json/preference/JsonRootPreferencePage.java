package org.sweetlemonade.eclipse.json.preference;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.sweetlemonade.eclipse.json.JsonPlugin;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonRootPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, SelectionListener
{
	public JsonRootPreferencePage()
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
		Composite rootComposite = new Composite(parent, SWT.NONE);
		rootComposite.setLayout(new FormLayout());

		Button saveAsOnSave = new Button(rootComposite, SWT.CHECK);
		FormData fd_btnCheckButton = new FormData();
		fd_btnCheckButton.top = new FormAttachment(0);
		fd_btnCheckButton.left = new FormAttachment(0);
		saveAsOnSave.setLayoutData(fd_btnCheckButton);
		saveAsOnSave.setText("Do \"Save As\" when saving \"pseudo\" Json editor");
		saveAsOnSave.addSelectionListener(this);

		saveAsOnSave.setSelection(getPreferenceStore().getBoolean(JsonPreferencesInitializer.PREF_SAVE_AS_ON_SAVE));

		return rootComposite;
	}

	@Override
	public void widgetSelected(SelectionEvent e)
	{
		getPreferenceStore().setValue(JsonPreferencesInitializer.PREF_SAVE_AS_ON_SAVE, ((Button) e.widget).getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e)
	{
	}

}
