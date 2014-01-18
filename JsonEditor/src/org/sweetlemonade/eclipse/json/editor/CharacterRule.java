package org.sweetlemonade.eclipse.json.editor;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class CharacterRule implements IRule
{
	private final IToken mToken;
	private final char[] mC;

	public CharacterRule(IToken token, char... c)
	{
		mToken = token;
		mC = c;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner)
	{
		final int read = scanner.read();

		for (int i = 0; i < mC.length; i++)
		{
			if (mC[i] == read)
			{
				return mToken;
			}
		}

		scanner.unread();

		return Token.UNDEFINED;
	}
}
