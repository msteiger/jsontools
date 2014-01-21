package org.sweetlemonade.eclipse.json.editor;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.sweetlemonade.eclipse.json.JsonPlugin;
import org.sweetlemonade.eclipse.json.preference.JsonPreferences;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.TokenType;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonScanner extends RuleBasedScanner
{
	public JsonScanner()
	{
		updateRules();
	}

	public void updateRules()
	{
		final JsonPreferences preferences = JsonPlugin.getPreferences();

		final IToken objectBrackets = new Token(new TextAttribute(preferences.getColor(TokenType.OBJECT_BRACKETS)));
		final IToken arrayBrackets = new Token(new TextAttribute(preferences.getColor(TokenType.ARRAY_BRACKETS)));
		final IToken keys = new Token(new TextAttribute(preferences.getColor(TokenType.KEYS)));
		final IToken strings = new Token(new TextAttribute(preferences.getColor(TokenType.STRINGS)));
		final IToken numbers = new Token(new TextAttribute(preferences.getColor(TokenType.NUMBERS)));
		final IToken booleans = new Token(new TextAttribute(preferences.getColor(TokenType.BOOLEANS)));
		final IToken nulls = new Token(new TextAttribute(preferences.getColor(TokenType.NULL)));

		final IRule[] rules = new IRule[6];

		final WordRule keywords = new WordRule(new IWordDetector()
		{
			final String start = "nft";
			final String part = "nullfalsetrue";

			@Override
			public boolean isWordStart(char c)
			{
				return start.indexOf(c) != -1;
			}

			@Override
			public boolean isWordPart(char c)
			{
				return part.indexOf(c) != -1;
			}
		});

		keywords.addWord("null", nulls);
		keywords.addWord("false", booleans);
		keywords.addWord("true", booleans);

		rules[0] = new StringValueRule("\"", "\"", strings, '\\');
		rules[1] = new SingleLineRule("\"", "\"", keys, '\\');
		rules[2] = new CharacterRule(objectBrackets, '{', '}');
		rules[3] = new CharacterRule(arrayBrackets, '[', ']');
		rules[4] = keywords;
		rules[5] = new JsonNumberRule(numbers);

		setRules(rules);

		setDefaultReturnToken(new Token(new TextAttribute(JsonPlugin.getPreferences().getColor(TokenType.DEFAULT))));
	}
}
