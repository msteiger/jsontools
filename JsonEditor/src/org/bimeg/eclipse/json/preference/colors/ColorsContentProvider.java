package org.bimeg.eclipse.json.preference.colors;

import org.bimeg.eclipse.json.Container;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ColorsContentProvider implements IStructuredContentProvider
{
	@Override
	public void dispose()
	{

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
	}

	@Override
	public Object[] getElements(Object inputElement)
	{
		return (Object[]) ((Container) inputElement).object;
	}
}