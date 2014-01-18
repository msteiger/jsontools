package org.sweetlemonade.eclipse.json.editor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

class StringStorage implements IStorage
{
	public StringStorage()
	{
	}

	@Override
	public InputStream getContents() throws CoreException
	{
		return new ByteArrayInputStream(new byte[0]);
	}

	@Override
	public IPath getFullPath()
	{
		return null;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter)
	{
		return null;
	}

	@Override
	public String getName()
	{
		return "JSON";
	}

	@Override
	public boolean isReadOnly()
	{
		return false;
	}
}