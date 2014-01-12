package org.bimeg.eclipse.json.preference.colors;

import org.bimeg.eclipse.json.preference.JsonPreferencesInitializer.ColorType;
import org.eclipse.jface.viewers.LabelProvider;

public class ColorsLabelProvider extends LabelProvider
{
	@Override
	public String getText(Object element)
	{
		final ColorType type = (ColorType) element;

		switch (type)
		{
			case NULL:
				return "Null";
			case ARRAY_BRACKETS:
				return "Array brackets";
			case BOOLEANS:
				return "Booleans";
			case BRACKETS:
				return "Brackets";
			case DEFAULT:
				return "Default";
			case KEYS:
				return "Keys";
			case MATCHED_BRACKET:
				return "Matched bracket";
			case NUMBERS:
				return "Numbers";
			case OBJECT_BRACKETS:
				return "Object brackets";
			case STRINGS:
				return "Strings";
		}

		return type.name();
	}
}