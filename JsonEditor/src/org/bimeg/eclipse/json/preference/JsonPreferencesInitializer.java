package org.bimeg.eclipse.json.preference;

import org.bimeg.eclipse.json.JsonPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonPreferencesInitializer extends AbstractPreferenceInitializer
{
	public static final String PREF_AUTO_FORMAT_ON_SAVE = "autoFormatOnSave";

	public static final String PREF_WRAP_ARRAY = "wrapArray";
	public static final String PREF_WRAP_OBJECT = "wrapObject";

	public static final String PREF_ARRAY_BRACKETS_NEW_LINE = "arrayBrackets";
	public static final String PREF_OBJECT_BRACKETS_NEW_LINE = "objectBrackets";

	public static final String PREF_SPACE_AFTER_COMMA = "spaceAfterComma";
	public static final String PREF_SPACE_AFTER_COLON = "spaceAfterColon";
	public static final String PREF_SPACE_BEFORE_COLON = "spaceBeforeColon";

	public static final String PREF_BLANK_LINE_AFTER_COMPLEX = "newLineAfterComplex";

	public static final String PREF_SPACE_AFTER_OBJECT_OPEN = "spaceAfterObjectOpen";
	public static final String PREF_SPACE_BEFORE_OBJECT_CLOSE = "spaceAfterObjectClose";

	public static final String PREF_SPACE_AFTER_ARRAY_OPEN = "spaceAfterArrayOpen";
	public static final String PREF_SPACE_BEFORE_ARRAY_CLOSE = "spaceAfterArrayClose";

	public static final String PREF_TAB_WIDTH = "tabWidth";

	/*@formatter:off*/
	
	public static final String[] FORMAT_KEYS = {
		PREF_AUTO_FORMAT_ON_SAVE,
		PREF_WRAP_ARRAY,
		PREF_WRAP_OBJECT,
		PREF_ARRAY_BRACKETS_NEW_LINE,
		PREF_OBJECT_BRACKETS_NEW_LINE,
		PREF_SPACE_AFTER_COMMA,
		PREF_SPACE_AFTER_COLON,
		PREF_SPACE_BEFORE_COLON,
		PREF_BLANK_LINE_AFTER_COMPLEX,
		PREF_SPACE_AFTER_OBJECT_OPEN,
		PREF_SPACE_BEFORE_OBJECT_CLOSE,
		PREF_SPACE_AFTER_ARRAY_OPEN,
		PREF_SPACE_BEFORE_ARRAY_CLOSE,
		PREF_TAB_WIDTH
	};

	/*@formatter:on*/

	public static final String PREF_COLOR_MATCH_BRACKET = "colorMatchBracket";
	public static final String PREF_COLOR_BRACKET = "colorBracket";
	public static final String PREF_COLOR_OBJECT_BRACKET = "colorObjectBracket";
	public static final String PREF_COLOR_ARRAY_BRACKET = "colorArrayBracket";
	public static final String PREF_COLOR_KEY = "colorKey";
	public static final String PREF_COLOR_STRING = "colorString";
	public static final String PREF_COLOR_NUMBER = "colorNumber";
	public static final String PREF_COLOR_BOOLEAN = "colorBoolean";
	public static final String PREF_COLOR_NULL = "colorNull";
	public static final String PREF_COLOR_DEFAULT = "colorDefault";

	public static final String PREF_ENABLED_MATCH_BRACKET = "colorMatchBracketEnabled";
	public static final String PREF_ENABLED_BRACKET = "colorBracketEnabled";
	public static final String PREF_ENABLED_OBJECT_BRACKET = "colorObjectBracketEnabled";
	public static final String PREF_ENABLED_ARRAY_BRACKET = "colorArrayBracketEnabled";
	public static final String PREF_ENABLED_KEY = "colorKeyEnabled";
	public static final String PREF_ENABLED_STRING = "colorStringEnabled";
	public static final String PREF_ENABLED_NUMBER = "colorNumberEnabled";
	public static final String PREF_ENABLED_BOOLEAN = "colorBooleanEnabled";
	public static final String PREF_ENABLED_NULL = "colorNullEnabled";
	public static final String PREF_ENABLED_DEFAULT = "colorDefaultEnabled";

	public enum ColorType
	{
		MATCHED_BRACKET(PREF_COLOR_MATCH_BRACKET, PREF_ENABLED_MATCH_BRACKET, new RGB(0, 0, 0), true),
		BRACKETS(PREF_COLOR_BRACKET, PREF_ENABLED_BRACKET, new RGB(0, 0, 0), false),
		OBJECT_BRACKETS(PREF_COLOR_OBJECT_BRACKET, PREF_ENABLED_OBJECT_BRACKET, new RGB(0, 0, 0), false)
		{
			@Override
			public RGB getColor(IPreferenceStore store)
			{
				if (!isEnabled(store))
				{
					return BRACKETS.getColor(store);
				}

				return super.getColor(store);
			}
		},
		ARRAY_BRACKETS(PREF_COLOR_ARRAY_BRACKET, PREF_ENABLED_ARRAY_BRACKET, new RGB(0, 0, 0), false)
		{
			@Override
			public RGB getColor(IPreferenceStore store)
			{
				if (!isEnabled(store))
				{
					return BRACKETS.getColor(store);
				}

				return super.getColor(store);
			}
		},
		KEYS(PREF_COLOR_KEY, PREF_ENABLED_KEY, new RGB(0, 128, 0), true)
		{
			@Override
			public RGB getColor(IPreferenceStore store)
			{
				if (!isEnabled(store))
				{
					return STRINGS.getColor(store);
				}

				return super.getColor(store);
			}
		},
		STRINGS(PREF_COLOR_STRING, PREF_ENABLED_STRING, new RGB(0, 0, 255), true),
		NUMBERS(PREF_COLOR_NUMBER, PREF_ENABLED_NUMBER, new RGB(128, 64, 64), true),
		BOOLEANS(PREF_COLOR_BOOLEAN, PREF_ENABLED_BOOLEAN, new RGB(0, 0, 0), false),
		NULL(PREF_COLOR_NULL, PREF_ENABLED_NULL, new RGB(0, 0, 0), false),
		DEFAULT(PREF_COLOR_DEFAULT, PREF_ENABLED_DEFAULT, new RGB(0, 0, 0), true);

		private final String mKey;
		private final RGB mDef;
		private final boolean mEnabledDef;
		private final String mEnabledKey;

		private ColorType(String key, String enabledKey, RGB def, boolean enabledDef)
		{
			mKey = key;
			mEnabledKey = enabledKey;
			mDef = def;
			mEnabledDef = enabledDef;
		}

		public RGB getColor(IPreferenceStore store)
		{
			if (!isEnabled(store))
			{
				return DEFAULT.getColor(store);
			}

			return StringConverter.asRGB(store.getString(mKey));
		}

		public RGB getOwnColor(IPreferenceStore store)
		{
			return StringConverter.asRGB(store.getString(mKey));
		}

		public boolean isEnabled(IPreferenceStore store)
		{
			return store.getBoolean(mEnabledKey);
		}

		public String getKey()
		{
			return mKey;
		}

		public String getEnabledKey()
		{
			return mEnabledKey;
		}
	}

	@Override
	public void initializeDefaultPreferences()
	{
		final IPreferenceStore store = JsonPlugin.getDefault().getPreferenceStore();

		store.setDefault(PREF_AUTO_FORMAT_ON_SAVE, false);

		store.setDefault(PREF_WRAP_ARRAY, true);
		store.setDefault(PREF_WRAP_OBJECT, true);

		store.setDefault(PREF_ARRAY_BRACKETS_NEW_LINE, true);
		store.setDefault(PREF_OBJECT_BRACKETS_NEW_LINE, true);

		store.setDefault(PREF_SPACE_AFTER_COMMA, false);
		store.setDefault(PREF_SPACE_AFTER_COLON, true);
		store.setDefault(PREF_SPACE_BEFORE_COLON, false);

		store.setDefault(PREF_BLANK_LINE_AFTER_COMPLEX, true);

		store.setDefault(PREF_SPACE_AFTER_ARRAY_OPEN, false);
		store.setDefault(PREF_SPACE_BEFORE_ARRAY_CLOSE, false);

		store.setDefault(PREF_SPACE_AFTER_OBJECT_OPEN, false);
		store.setDefault(PREF_SPACE_BEFORE_OBJECT_CLOSE, false);

		store.setDefault(PREF_TAB_WIDTH, 4);

		final ColorType[] values = ColorType.values();

		for (final ColorType colorType : values)
		{
			store.setDefault(colorType.mKey, StringConverter.asString(colorType.mDef));
			store.setDefault(colorType.mEnabledKey, StringConverter.asString(colorType.mEnabledDef));
		}
	}
}
