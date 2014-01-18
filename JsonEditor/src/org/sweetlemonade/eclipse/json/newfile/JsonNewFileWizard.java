package org.sweetlemonade.eclipse.json.newfile;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

public class JsonNewFileWizard extends BasicNewResourceWizard
{
	public static final String WIZARD_ID = "org.sweetlemonade.eclipse.json.new";

	private WizardNewFileCreationPage mainPage;

	public JsonNewFileWizard()
	{
		super();
	}

	@Override
	public void addPages()
	{
		super.addPages();
		mainPage = new WizardNewFileCreationPage("newFilePage1", getSelection());
		mainPage.setTitle("Create JSON File");
		mainPage.setFileName("file.json");
		addPage(mainPage);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection)
	{
		super.init(workbench, currentSelection);
		setWindowTitle("New JSON File");
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean performFinish()
	{
		final IFile file = mainPage.createNewFile();
		if (file == null)
		{
			return false;
		}

		selectAndReveal(file);

		final IWorkbenchWindow dw = getWorkbench().getActiveWorkbenchWindow();

		try
		{
			if (dw != null)
			{
				final IWorkbenchPage page = dw.getActivePage();
				if (page != null)
				{
					IDE.openEditor(page, file, true);
				}
			}
		}
		catch (final PartInitException e)
		{
		}

		return true;
	}
}
