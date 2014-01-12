package org.bimeg.eclipse.json.outline;

import org.bimeg.eclipse.json.model.JsonElement;
import org.bimeg.eclipse.json.model.JsonPrimitive;
import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonLabelProvider extends ColumnLabelProvider
{
	@Override
	public String getText(Object element)
	{
		final JsonElement jsonElement = (JsonElement) element;

		String key = jsonElement.getKey();

		if (key == null)
		{
			key = "";
		}

		if (key.length() > 0)
		{
			key += " : ";
		}

		if (jsonElement.isObject())
		{
			return key + "{}";
		}
		else if (jsonElement.isArray())
		{
			return key + "[]";

		}
		else if (jsonElement.isPrimitive())
		{
			final JsonPrimitive primitive = (JsonPrimitive) element;

			return key + primitive.getValue();
		}

		return super.getText(element);
	}
}
