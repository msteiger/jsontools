package org.sweetlemonade.eclipse.json.editor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IStorage;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.sweetlemonade.eclipse.json.Constants;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class OpenJsonEditorHandler extends AbstractHandler
{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		final IWorkbench wb = PlatformUI.getWorkbench();
		final IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
		final IStorage storage = new StringStorage();
		final IStorageEditorInput input = new StringInput(storage);
		final IWorkbenchPage page = window.getActivePage();

		if (page != null)
		{
			try
			{
				page.openEditor(input, Constants.JSON_EDITOR_ID);
			}
			catch (final PartInitException e)
			{
			}
		}

		return null;
	}

}
