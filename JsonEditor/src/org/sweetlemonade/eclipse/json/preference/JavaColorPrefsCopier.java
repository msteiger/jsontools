package org.sweetlemonade.eclipse.json.preference;

import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_BRACKET_BOLD;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_BRACKET_COLOR;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_BRACKET_ITALIC;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_BRACKET_STRIKETHROUGH;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_BRACKET_UNDERLINE;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_DEFAULT_BOLD;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_DEFAULT_COLOR;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_DEFAULT_ITALIC;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_DEFAULT_STRIKETHROUGH;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_DEFAULT_UNDERLINE;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_KEYWORD_BOLD;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_KEYWORD_COLOR;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_KEYWORD_ITALIC;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_KEYWORD_STRIKETHROUGH;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_JAVA_KEYWORD_UNDERLINE;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_STRING_BOLD;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_STRING_COLOR;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_STRING_ITALIC;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_STRING_STRIKETHROUGH;
import static org.eclipse.jdt.ui.PreferenceConstants.EDITOR_STRING_UNDERLINE;

import java.util.EnumMap;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.internal.ui.javaeditor.SemanticHighlighting;
import org.eclipse.jdt.internal.ui.javaeditor.SemanticHighlightings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.TokenType;

/**
 * Feb 25, 2015
 *
 * @author denis.mirochnik
 */
@SuppressWarnings("restriction")
public class JavaColorPrefsCopier
{
	public static final String JDT_UI_ID = "org.eclipse.jdt.ui";

	private static class JdtKeys
	{
		public final String colorKey;
		public final String boldKey;
		public final String italicKey;
		public final String strikeKey;
		public final String underlineKey;
		public final String enabledKey;

		public JdtKeys(String colorKey, String boldKey, String italicKey, String strikeKey, String underlineKey, String enabledKey)
		{
			this.colorKey = colorKey;
			this.boldKey = boldKey;
			this.italicKey = italicKey;
			this.strikeKey = strikeKey;
			this.underlineKey = underlineKey;
			this.enabledKey = enabledKey;
		}
	}

	private final EnumMap<TokenType, JdtKeys> KEYS;

	{
		KEYS = new EnumMap<>(TokenType.class);

		/*@formatter:off*/
		KEYS.put(TokenType.DEFAULT, 		new JdtKeys(EDITOR_JAVA_DEFAULT_COLOR, EDITOR_JAVA_DEFAULT_BOLD, EDITOR_JAVA_DEFAULT_ITALIC, EDITOR_JAVA_DEFAULT_STRIKETHROUGH, EDITOR_JAVA_DEFAULT_UNDERLINE, null));
		KEYS.put(TokenType.BOOLEANS, 		new JdtKeys(EDITOR_JAVA_KEYWORD_COLOR, EDITOR_JAVA_KEYWORD_BOLD, EDITOR_JAVA_KEYWORD_ITALIC, EDITOR_JAVA_KEYWORD_STRIKETHROUGH, EDITOR_JAVA_KEYWORD_UNDERLINE, null));
		KEYS.put(TokenType.NULL, 			new JdtKeys(EDITOR_JAVA_KEYWORD_COLOR, EDITOR_JAVA_KEYWORD_BOLD, EDITOR_JAVA_KEYWORD_ITALIC, EDITOR_JAVA_KEYWORD_STRIKETHROUGH, EDITOR_JAVA_KEYWORD_UNDERLINE, null));
		KEYS.put(TokenType.BRACKETS, 		new JdtKeys(EDITOR_JAVA_BRACKET_COLOR, EDITOR_JAVA_BRACKET_BOLD, EDITOR_JAVA_BRACKET_ITALIC, EDITOR_JAVA_BRACKET_STRIKETHROUGH, EDITOR_JAVA_BRACKET_UNDERLINE, null));
		KEYS.put(TokenType.OBJECT_BRACKETS, new JdtKeys(EDITOR_JAVA_BRACKET_COLOR, EDITOR_JAVA_BRACKET_BOLD, EDITOR_JAVA_BRACKET_ITALIC, EDITOR_JAVA_BRACKET_STRIKETHROUGH, EDITOR_JAVA_BRACKET_UNDERLINE, null));
		KEYS.put(TokenType.ARRAY_BRACKETS, 	new JdtKeys(EDITOR_JAVA_BRACKET_COLOR, EDITOR_JAVA_BRACKET_BOLD, EDITOR_JAVA_BRACKET_ITALIC, EDITOR_JAVA_BRACKET_STRIKETHROUGH, EDITOR_JAVA_BRACKET_UNDERLINE, null));
		KEYS.put(TokenType.STRINGS, 		new JdtKeys(EDITOR_STRING_COLOR, EDITOR_STRING_BOLD, EDITOR_STRING_ITALIC, EDITOR_STRING_STRIKETHROUGH, EDITOR_STRING_UNDERLINE, null));
		KEYS.put(TokenType.MATCHED_BRACKET, new JdtKeys(EDITOR_MATCHING_BRACKETS_COLOR, EDITOR_JAVA_DEFAULT_BOLD, EDITOR_JAVA_DEFAULT_ITALIC, EDITOR_JAVA_DEFAULT_STRIKETHROUGH, EDITOR_JAVA_DEFAULT_UNDERLINE, null));
		/*@formatter:on*/

		try
		{
			SemanticHighlighting[] highlightings = SemanticHighlightings.getSemanticHighlightings();

			for (SemanticHighlighting semanticHighlighting : highlightings)
			{
				TokenType type;

				switch (semanticHighlighting.getPreferenceKey())
				{
					case SemanticHighlightings.NUMBER:
						type = TokenType.NUMBERS;
						break;

					case SemanticHighlightings.CLASS:
						type = TokenType.KEYS;
						break;

					default:
						continue;
				}

				/*@formatter:off*/
				KEYS.put(type, new JdtKeys(
						SemanticHighlightings.getColorPreferenceKey(semanticHighlighting), 
						SemanticHighlightings.getBoldPreferenceKey(semanticHighlighting), 
						SemanticHighlightings.getItalicPreferenceKey(semanticHighlighting), 
						SemanticHighlightings.getStrikethroughPreferenceKey(semanticHighlighting), 
						SemanticHighlightings.getUnderlinePreferenceKey(semanticHighlighting), 
						SemanticHighlightings.getEnabledPreferenceKey(semanticHighlighting)));
				/*@formatter:on*/
			}
		}
		catch (Throwable e)
		{
			KEYS.remove(TokenType.NUMBERS);
			KEYS.remove(TokenType.KEYS);

			/*@formatter:off*/
			KEYS.put(TokenType.KEYS, new JdtKeys(EDITOR_STRING_COLOR, EDITOR_STRING_BOLD, EDITOR_STRING_ITALIC, EDITOR_STRING_STRIKETHROUGH, EDITOR_STRING_UNDERLINE, null));
			KEYS.put(TokenType.NUMBERS, new JdtKeys(EDITOR_JAVA_DEFAULT_COLOR, EDITOR_JAVA_DEFAULT_BOLD, EDITOR_JAVA_DEFAULT_ITALIC, EDITOR_JAVA_DEFAULT_STRIKETHROUGH, EDITOR_JAVA_DEFAULT_UNDERLINE, null));
			/*@formatter:on*/
		}

	}

	private JavaPreferenceStore mJavaStore = new JavaPreferenceStore();

	public void copyPrefs(IPreferenceStore targetStore)
	{
		final TokenType[] types = TokenType.values();

		for (final TokenType colorType : types)
		{
			JdtKeys jdtKeys = KEYS.get(colorType);

			targetStore.setValue(colorType.getKey(), mJavaStore.getString(jdtKeys.colorKey));

			String enabledKey = jdtKeys.enabledKey;
			targetStore.setValue(colorType.getEnabledKey(), enabledKey == null ? true : mJavaStore.getBoolean(enabledKey));

			boolean bold = mJavaStore.getBoolean(jdtKeys.boldKey);
			boolean italic = mJavaStore.getBoolean(jdtKeys.italicKey);
			boolean under = mJavaStore.getBoolean(jdtKeys.underlineKey);
			boolean strike = mJavaStore.getBoolean(jdtKeys.strikeKey);

			targetStore.setValue(colorType.getStyleKey(), JsonPreferences.mergeStyles(bold, italic, under, strike));
		}
	}

	public static boolean mayCopyJavaColorPrefs()
	{
		return Platform.getBundle(JDT_UI_ID) != null;
	}
}
