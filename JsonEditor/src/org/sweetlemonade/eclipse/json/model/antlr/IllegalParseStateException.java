package org.sweetlemonade.eclipse.json.model.antlr;

/**
 * 20 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class IllegalParseStateException extends RuntimeException
{

	private static final long serialVersionUID = -4654924590698264699L;

	public IllegalParseStateException()
	{
		super();
	}

	public IllegalParseStateException(String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
	}

	public IllegalParseStateException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public IllegalParseStateException(String arg0)
	{
		super(arg0);
	}

	public IllegalParseStateException(Throwable arg0)
	{
		super(arg0);
	}

}
