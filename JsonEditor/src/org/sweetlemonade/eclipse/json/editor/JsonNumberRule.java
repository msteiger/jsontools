package org.sweetlemonade.eclipse.json.editor;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonNumberRule implements IRule
{
    private int mCount = 0;
    private ICharacterScanner mScanner;
    private final IToken mToken;
    private boolean mValid;

    public JsonNumberRule(IToken token)
    {
        mToken = token;
    }

    private void begin(ICharacterScanner scanner)
    {
        mCount = 0;
        mScanner = scanner;
    }

    private void end()
    {
        mScanner = null;
    }

    private int read()
    {
        mCount++;
        return mScanner.read();
    }

    private void unread()
    {
        mCount--;
        mScanner.unread();
    }

    private void unreadAll()
    {
        for (int i = 0; i < mCount; i++)
        {
            mScanner.unread();
        }

        mCount = 0;
    }

    @Override
    public IToken evaluate(ICharacterScanner scanner)
    {
        boolean start = true;
        boolean startZero = false;
        boolean wasMinus = false;
        boolean wasESign = false;
        boolean wasDot = false;
        boolean wasE = false;
        boolean prevE = false;
        mValid = false;

        begin(scanner);

        do
        {
            final int c = read();

            if (c == ICharacterScanner.EOF)
            {
                return stop();
            }

            if (start)
            {
                if (c == '-')
                {
                    if (wasMinus)
                    {
                        return stop();
                    }

                    mValid = false;
                    wasMinus = true;
                }
                else if (c == '0')
                {
                    mValid = true;
                    startZero = true;
                    start = false;
                }
                else if (Character.isDigit(c))
                {
                    mValid = true;
                    startZero = false;
                    start = false;
                }
                else
                {
                    mValid = false;
                    return stop();
                }
            }
            else
            {
                if (Character.isDigit(c))
                {
                    if (startZero && !wasDot)
                    {
                        mValid = false;
                        return stop();
                    }

                    if (wasDot || wasE)
                    {
                        mValid = true;
                    }

                    prevE = false;
                }
                else if (c == '.')
                {
                    if (wasDot || !mValid)
                    {
                        return stop();
                    }

                    prevE = false;
                    wasDot = true;
                    mValid = false;
                }
                else if (c == 'e' || c == 'E')
                {
                    if (wasE || !mValid)
                    {
                        return stop();
                    }

                    prevE = true;
                    wasE = true;
                    mValid = false;
                }
                else if (c == '-' || c == '+')
                {
                    if (wasESign || !prevE)
                    {
                        return stop();
                    }

                    wasESign = true;
                    mValid = false;
                    prevE = false;
                }
                else
                {
                    return stop();
                }
            }
        }
        while (true);
    }

    private IToken stop()
    {
        if (mValid)
        {
            unread();
            end();
            return mToken;
        }

        unreadAll();
        end();

        return Token.UNDEFINED;
    }

}
