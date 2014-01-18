package org.sweetlemonade.eclipse.json.outline;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.sweetlemonade.eclipse.json.Container;
import org.sweetlemonade.eclipse.json.model.JsonElement;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonOutlineContentProvider implements ITreeContentProvider
{
	public JsonOutlineContentProvider()
	{
	}

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
		final Object object = ((Container) inputElement).object;

		if (object != null)
		{
			return new Object[] { object };
		}

		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement)
	{
		if (parentElement instanceof JsonElement)
		{
			if (((JsonElement) parentElement).hasChilds())
			{
				return ((JsonElement) parentElement).getChilds().toArray();
			}
		}

		return null;
	}

	@Override
	public Object getParent(Object element)
	{
		if (element instanceof JsonElement)
		{
			return ((JsonElement) element).getParent();
		}

		return null;
	}

	@Override
	public boolean hasChildren(Object element)
	{
		if (element instanceof JsonElement)
		{
			return ((JsonElement) element).hasChilds();
		}

		return false;
	}
}
