package org.sweetlemonade.eclipse.json.outline;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.TextStyle;
import org.sweetlemonade.eclipse.json.JsonPlugin;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive.PrimitiveType;
import org.sweetlemonade.eclipse.json.preference.JsonPreferences;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.TokenType;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider
{
	@Override
	public String getText(Object element)
	{
		return getStyledText(element).toString();
	}

	@Override
	public StyledString getStyledText(Object element)
	{
		JsonElement jsonElement = (JsonElement) element;

		StyledString string = new StyledString();

		String key = jsonElement.getKey();

		if (key != null && key.length() > 0)
		{
			string.append(key, new SimpleStyler(TokenType.KEYS));
			string.append(" : ", new SimpleStyler(TokenType.DEFAULT));
		}

		int index = jsonElement.getIndex();

		if (index != -1)
		{
			string.append("[" + index + "] : ", new SimpleStyler(TokenType.DEFAULT));
		}

		if (jsonElement.isObject())
		{
			string.append("{}", new SimpleStyler(TokenType.OBJECT_BRACKETS));
		}
		else if (jsonElement.isArray())
		{
			string.append("[" + jsonElement.asArray().size() + "]", new SimpleStyler(TokenType.OBJECT_BRACKETS));
		}
		else if (jsonElement.isPrimitive())
		{
			JsonPrimitive primitive = (JsonPrimitive) jsonElement;

			PrimitiveType type = primitive.getType();
			SimpleStyler styler = null;

			switch (type)
			{
				case NULL:
					styler = new SimpleStyler(TokenType.NULL);
					break;

				case BOOLEAN:
					styler = new SimpleStyler(TokenType.BOOLEANS);
					break;

				case NUMBER:
					styler = new SimpleStyler(TokenType.NUMBERS);
					break;

				case STRING:
					styler = new SimpleStyler(TokenType.STRINGS);
					break;
			}

			string.append(primitive.getValue(), styler);
		}

		return string;
	}

	private static class SimpleStyler extends Styler
	{
		private TokenType mType;

		public SimpleStyler(TokenType type)
		{
			mType = type;
		}

		@Override
		public void applyStyles(TextStyle textStyle)
		{
			JsonPreferences preferences = JsonPlugin.getPreferences();
			int style = preferences.getStyle(mType);

			if (textStyle instanceof StyleRange)
			{
				StyleRange range = (StyleRange) textStyle;

				range.fontStyle = JsonPreferences.extractBoldItalic(style);
			}

			textStyle.foreground = preferences.getColor(mType);
			textStyle.underline = JsonPreferences.isUnderline(style);
			textStyle.strikeout = JsonPreferences.isStrike(style);
		}
	}
}
