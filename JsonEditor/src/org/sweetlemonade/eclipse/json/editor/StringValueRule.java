package org.sweetlemonade.eclipse.json.editor;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.sweetlemonade.eclipse.json.model.JsonGrammar;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class StringValueRule extends SingleLineRule
{

	public StringValueRule(String startSequence, String endSequence, IToken token, char escapeCharacter)
	{
		super(startSequence, endSequence, token, escapeCharacter);
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner)
	{
		scanner.read();

		int read;
		int count = -1;

		do
		{
			scanner.unread();
			scanner.unread();

			read = scanner.read();
			count++;
		}
		while (JsonGrammar.isWhitespace((char) read));

		for (int i = 0; i < count; i++)
		{
			scanner.read();
		}

		if (read != ':')
		{
			return Token.UNDEFINED;
		}

		return super.evaluate(scanner);
	}
}
