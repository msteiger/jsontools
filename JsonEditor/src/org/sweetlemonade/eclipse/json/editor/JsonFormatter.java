package org.sweetlemonade.eclipse.json.editor;

import java.util.Set;

import org.eclipse.jface.preference.IPreferenceStore;
import org.sweetlemonade.eclipse.json.model.JsonArray;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.model.JsonObject;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive.PrimitiveType;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonFormatter
{
	private final JsonElement mElement;

	private boolean mWrapArray;
	private boolean mWrapObject;

	private boolean mArrayBracketsNewLine;
	private boolean mObjectBracketsNewLine;

	private boolean mAfterComma;
	private boolean mAfterColon;
	private boolean mBeforeColon;

	private boolean mBlankLineAfterComplex;

	private boolean mSpaceAfterObjectOpen;
	private boolean mSpaceBeforeObjectClose;

	private boolean mSpaceAfterArrayOpen;
	private boolean mSpaceBeforeArrayClose;

	private final IPreferenceStore mStore;

	public JsonFormatter(JsonElement element, IPreferenceStore store)
	{
		mElement = element;
		mStore = store;

	}

	public String format(int initialCapacity)
	{
		mWrapArray = mStore.getBoolean(JsonPreferencesInitializer.PREF_WRAP_ARRAY);
		mWrapObject = mStore.getBoolean(JsonPreferencesInitializer.PREF_WRAP_OBJECT);
		mArrayBracketsNewLine = mStore.getBoolean(JsonPreferencesInitializer.PREF_ARRAY_BRACKETS_NEW_LINE);
		mObjectBracketsNewLine = mStore.getBoolean(JsonPreferencesInitializer.PREF_OBJECT_BRACKETS_NEW_LINE);
		mAfterComma = mStore.getBoolean(JsonPreferencesInitializer.PREF_SPACE_AFTER_COMMA);
		mAfterColon = mStore.getBoolean(JsonPreferencesInitializer.PREF_SPACE_AFTER_COLON);
		mBeforeColon = mStore.getBoolean(JsonPreferencesInitializer.PREF_SPACE_BEFORE_COLON);
		mBlankLineAfterComplex = mStore.getBoolean(JsonPreferencesInitializer.PREF_BLANK_LINE_AFTER_COMPLEX);
		mSpaceAfterObjectOpen = mStore.getBoolean(JsonPreferencesInitializer.PREF_SPACE_AFTER_OBJECT_OPEN);
		mSpaceBeforeObjectClose = mStore.getBoolean(JsonPreferencesInitializer.PREF_SPACE_BEFORE_OBJECT_CLOSE);
		mSpaceAfterArrayOpen = mStore.getBoolean(JsonPreferencesInitializer.PREF_SPACE_AFTER_ARRAY_OPEN);
		mSpaceBeforeArrayClose = mStore.getBoolean(JsonPreferencesInitializer.PREF_SPACE_BEFORE_ARRAY_CLOSE);

		final StringBuilder builder = new StringBuilder(initialCapacity);

		toFormatedString(mElement, builder, 0);

		return builder.toString();
	}

	private void toFormatedString(JsonElement element, StringBuilder builder, int indent)
	{
		if (element.isArray())
		{
			final JsonArray array = (JsonArray) element;

			builder.append('[');

			if (mSpaceAfterArrayOpen)
			{
				builder.append(' ');
			}

			if (mWrapArray)
			{
				builder.append('\n');
				indent(builder, indent + 1);
			}

			boolean first = true;
			boolean wasComplex = false;

			for (final JsonElement jsonElement : array)
			{
				if (!first)
				{
					builder.append(',');

					if (mAfterComma)
					{
						builder.append(' ');
					}

					if (wasComplex && mBlankLineAfterComplex && mWrapArray)
					{
						builder.append('\n');
					}

					if (mWrapArray)
					{
						builder.append('\n');
						indent(builder, indent + 1);
					}
				}

				wasComplex = false;
				first = false;

				toFormatedString(jsonElement, builder, indent + 1);

				if (jsonElement.isObject() || jsonElement.isArray())
				{
					wasComplex = true;
				}
			}

			if (mWrapArray)
			{
				builder.append('\n');
				indent(builder, indent);
			}

			if (mSpaceBeforeArrayClose)
			{
				builder.append(' ');
			}

			builder.append(']');
		}
		else if (element.isObject())
		{
			final JsonObject object = (JsonObject) element;

			builder.append('{');

			if (mSpaceAfterObjectOpen)
			{
				builder.append(' ');
			}

			if (mWrapObject)
			{
				builder.append('\n');
				indent(builder, indent + 1);
			}

			final Set<String> keySet = object.keySet();

			boolean first = true;
			boolean wasComplex = true;

			for (final String key : keySet)
			{
				if (!first)
				{
					builder.append(',');

					if (mAfterComma)
					{
						builder.append(' ');
					}

					if (wasComplex && mBlankLineAfterComplex && mWrapObject)
					{
						builder.append('\n');
					}

					if (mWrapObject)
					{
						builder.append('\n');
						indent(builder, indent + 1);
					}
				}

				wasComplex = false;
				first = false;

				builder.append('\"');
				builder.append(key);
				builder.append('\"');

				if (mBeforeColon)
				{
					builder.append(' ');
				}

				builder.append(':');

				if (mAfterColon)
				{
					builder.append(' ');
				}

				final JsonElement jsonElement = object.get(key);

				if (jsonElement.isArray() && mArrayBracketsNewLine)
				{
					builder.append('\n');
					indent(builder, indent + 1);
				}
				else if (jsonElement.isObject() && mObjectBracketsNewLine)
				{
					builder.append('\n');
					indent(builder, indent + 1);
				}

				toFormatedString(jsonElement, builder, indent + 1);

				if (jsonElement.isArray() || jsonElement.isObject())
				{
					wasComplex = true;
				}
			}

			if (mWrapObject)
			{
				builder.append('\n');
				indent(builder, indent);
			}

			if (mSpaceBeforeObjectClose)
			{
				builder.append(' ');
			}

			builder.append('}');
		}
		else
		{
			final JsonPrimitive primitive = (JsonPrimitive) element;

			if (primitive.getType() == PrimitiveType.STRING)
			{
				builder.append('\"');
			}

			builder.append(primitive.getValue());

			if (primitive.getType() == PrimitiveType.STRING)
			{
				builder.append('\"');
			}
		}
	}

	private static void indent(StringBuilder builder, int indent)
	{
		for (int i = 0; i < indent; i++)
		{
			builder.append('\t');
		}
	}
}
