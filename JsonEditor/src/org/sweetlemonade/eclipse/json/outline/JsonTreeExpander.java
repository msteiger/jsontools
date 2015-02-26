package org.sweetlemonade.eclipse.json.outline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.TreeViewer;
import org.sweetlemonade.eclipse.json.Container;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.model.JsonObject;
import org.sweetlemonade.eclipse.json.model.JsonObject.Key;

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

	private boolean check(JsonElement elementWas, JsonElement elementNow, List<JsonElement> expanded, List<JsonElement> expand)
	{
		if (elementWas.isPrimitive() || elementNow.isPrimitive())
		{
			return false;
		}

		if (elementWas.getClass() != elementNow.getClass())
		{
			return false;
		}

		boolean result = false;

		if (expanded.contains(elementWas))
		{
			result = true;

			expand.add(elementNow);
		}

		if (!elementWas.hasChilds())
		{
			return result;
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

			final Set<Key> wasSet = wasObj.keySet();
			final Set<Key> nowSet = nowObj.keySet();

			outer: for (final Key wasKey : wasSet)
			{
				for (final Key nowKey : nowSet)
				{
					if (nowKey.getValue().equals(wasKey.getValue()))
					{
						if (check(wasObj.get(wasKey), nowObj.get(nowKey), expanded, expand))
						{
							continue outer;
						}
					}
				}
			}
		}

		return result;
	}
}
