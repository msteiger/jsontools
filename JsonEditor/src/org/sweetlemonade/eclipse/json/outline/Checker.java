package org.sweetlemonade.eclipse.json.outline;

import java.util.Collection;

import org.sweetlemonade.eclipse.json.model.JsonElement;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class Checker
{
	public static JsonElement find(String text, JsonElement jsonElement)
	{
		final String key = jsonElement.getKey();

		if (include(key, text))
		{
			return jsonElement;
		}

		if (jsonElement.isPrimitive())
		{
			if (checkPrimitive(text, jsonElement))
			{
				return jsonElement;
			}
		}
		else if (jsonElement.hasChilds())
		{
			final Collection<JsonElement> childs = jsonElement.getChilds();

			for (final JsonElement child : childs)
			{
				final JsonElement find = find(text, child);

				if (find != null)
				{
					return find;
				}
			}
		}

		return null;
	}

	public static boolean check(String text, JsonElement jsonElement)
	{
		return find(text, jsonElement) != null;
	}

	private static boolean checkPrimitive(String text, JsonElement jsonElement)
	{
		return include(jsonElement.asPrimitive().getValue(), text);
	}

	private static boolean include(String original, String filter)
	{
		if (original == null)
		{
			return false;
		}

		return original.toLowerCase().contains(filter);
	}
}
