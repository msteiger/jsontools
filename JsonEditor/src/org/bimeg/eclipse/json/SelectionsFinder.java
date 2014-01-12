package org.bimeg.eclipse.json;

import java.util.Collection;

import org.bimeg.eclipse.json.model.JsonElement;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class SelectionsFinder
{
	private int minStart;
	private int minEnd;
	private JsonElement minElement;

	public JsonElement findSelectedScope(int start, JsonElement element)
	{
		minStart = -1;
		minEnd = Integer.MAX_VALUE / 2;
		minElement = null;

		if (element != null)
		{
			checkElementScope(element, start);
		}

		return minElement;
	}

	private void checkElementScope(JsonElement element, int start)
	{
		final int s = element.getStart();
		final int e = element.getEnd();

		if (s <= start && e >= start)
		{
			if (e - s < minEnd - minStart)
			{
				minElement = element;
				minEnd = e;
				minStart = s;

				if (element.hasChilds())
				{
					final Collection<JsonElement> children = element.getChilds();

					for (final JsonElement jsonElement : children)
					{
						checkElementScope(jsonElement, start);
					}
				}
			}
		}
	}
}
