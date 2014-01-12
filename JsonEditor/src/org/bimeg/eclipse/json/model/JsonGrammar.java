package org.bimeg.eclipse.json.model;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonGrammar
{
	public enum Keyword
	{
		NULL("null"),
		TRUE("true"),
		FALSE("false");

		private final String mKeyword;

		private Keyword(String keyword)
		{
			mKeyword = keyword;
		}

		public String keyword()
		{
			return mKeyword;
		}

		public boolean isEquals(String str)
		{
			return mKeyword.equals(str);
		}
	}

	public static boolean isWhitespace(int c)
	{
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f');
	}
}
