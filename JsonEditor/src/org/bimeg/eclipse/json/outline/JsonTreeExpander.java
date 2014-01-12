package org.bimeg.eclipse.json.outline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bimeg.eclipse.json.Container;
import org.bimeg.eclipse.json.model.JsonElement;
import org.bimeg.eclipse.json.model.JsonObject;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonTreeExpander
{
	private TreeViewer mViewer;
	private JsonElement mElement;

	public JsonTreeExpander(TreeViewer viewer)
	{
		mViewer = viewer;
		mViewer.setComparer(new IElementComparer()
		{

			@Override
			public int hashCode(Object element)
			{
				return System.identityHashCode(element);
			}

			@Override
			public boolean equals(Object a, Object b)
			{
				return a == b;
			}
		});
	}

	public void update(JsonElement element)
	{
		if (mElement == null)
		{
			mViewer.setInput(new Container(element));
			mViewer.expandToLevel(2);
			mElement = element;

			return;
		}

		final Object[] objects = mViewer.getExpandedElements();
		final JsonElement[] elements = new JsonElement[objects.length];
		System.arraycopy(objects, 0, elements, 0, objects.length);

		mViewer.setInput(new Container(element));

		if (element == null)
		{
			return;
		}

		final List<JsonElement> expanded = Arrays.<JsonElement> asList(elements);
		final List<JsonElement> expand = new ArrayList<>();

		check(mElement, element, expanded, expand);

		mViewer.setExpandedElements(expand.toArray());

		mElement = element;
	}

	private void check(JsonElement elementWas, JsonElement elementNow, List<JsonElement> expanded, List<JsonElement> expand)
	{
		if (elementWas.isPrimitive() || elementNow.isPrimitive())
		{
			return;
		}

		if (elementWas.getClass() != elementNow.getClass())
		{
			return;
		}

		if (expanded.contains(elementWas))
		{
			expand.add(elementNow);
		}

		if (!elementWas.hasChilds())
		{
			return;
		}

		if (elementWas.isArray())
		{
			final List<JsonElement> wasChilds = elementWas.asArray();
			final List<JsonElement> nowChilds = elementNow.asArray();

			final int len = Math.min(wasChilds.size(), nowChilds.size());

			for (int i = 0; i < len; i++)
			{
				final JsonElement was = wasChilds.get(i);
				final JsonElement now = nowChilds.get(i);

				check(was, now, expanded, expand);
			}
		}
		else if (elementWas.isObject())
		{
			final JsonObject wasObj = elementWas.asObject();
			final JsonObject nowObj = elementNow.asObject();

			final Set<String> wasSet = wasObj.keySet();
			final Set<String> nowSet = nowObj.keySet();

			for (final String wasKey : wasSet)
			{
				if (nowSet.contains(wasKey))
				{
					check(wasObj.get(wasKey), nowObj.get(wasKey), expanded, expand);
				}
			}
		}
	}
}
