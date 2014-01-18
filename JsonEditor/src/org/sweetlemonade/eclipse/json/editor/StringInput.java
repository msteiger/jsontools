package org.sweetlemonade.eclipse.json.editor;

import org.eclipse.core.resources.IStorage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;

public class StringInput implements IStorageEditorInput
{
	private final IStorage mStorage;

	public StringInput(IStorage storage)
	{
		mStorage = storage;
	}

	@Override
	public boolean exists()
	{
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return mStorage.getName();
	}

	@Override
	public IPersistableElement getPersistable()
	{
		return null;
	}

	@Override
	public IStorage getStorage()
	{
		return mStorage;
	}

	@Override
	public String getToolTipText()
	{
		return "";
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter)
	{
		return null;
	}
}