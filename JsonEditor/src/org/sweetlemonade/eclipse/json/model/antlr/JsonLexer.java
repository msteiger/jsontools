package org.sweetlemonade.eclipse.json.model.antlr;

// $ANTLR 3.5 C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g
// 2014-01-21 12:06:11

import org.antlr.runtime.CharStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

@SuppressWarnings("all")
public class JsonLexer extends Lexer
{
	public static final int EOF = -1;
	public static final int T__17 = 17;
	public static final int T__18 = 18;
	public static final int T__19 = 19;
	public static final int T__20 = 20;
	public static final int ARRAY = 4;
	public static final int ESC_SEQ = 5;
	public static final int EXPONENT = 6;
	public static final int FALSE = 7;
	public static final int HEX_DIGIT = 8;
	public static final int INT = 9;
	public static final int NULL = 10;
	public static final int NUMBER = 11;
	public static final int OBJECT = 12;
	public static final int STRING = 13;
	public static final int TRUE = 14;
	public static final int UNICODE_ESC = 15;
	public static final int WS = 16;

	// delegates
	// delegators
	public Lexer[] getDelegates()
	{
		return new Lexer[] {};
	}

	public JsonLexer()
	{
	}

	public JsonLexer(CharStream input)
	{
		this(input, new RecognizerSharedState());
	}

	public JsonLexer(CharStream input, RecognizerSharedState state)
	{
		super(input, state);
	}

	@Override
	public String getGrammarFileName()
	{
		return "C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g";
	}

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException
	{
		try
		{
			final int _type = T__17;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:2:7: ( ',' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:2:9: ','
			{
				match(',');
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException
	{
		try
		{
			final int _type = T__18;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:3:7: ( ':' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:3:9: ':'
			{
				match(':');
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException
	{
		try
		{
			final int _type = T__19;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:4:7: ( ']' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:4:9: ']'
			{
				match(']');
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException
	{
		try
		{
			final int _type = T__20;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:5:7: ( '}' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:5:9: '}'
			{
				match('}');
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "T__20"

	// $ANTLR start "ARRAY"
	public final void mARRAY() throws RecognitionException
	{
		try
		{
			final int _type = ARRAY;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:45:2: ( '[' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:45:4: '['
			{
				match('[');
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "ARRAY"

	// $ANTLR start "OBJECT"
	public final void mOBJECT() throws RecognitionException
	{
		try
		{
			final int _type = OBJECT;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:48:2: ( '{' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:48:4: '{'
			{
				match('{');
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "OBJECT"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException
	{
		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:53:9: ( ( '0' .. '9' )+ )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:53:11: ( '0' .. '9' )+
			{
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:53:11: ( '0' .. '9' )+
				int cnt1 = 0;
				loop1: while (true)
				{
					int alt1 = 2;
					final int LA1_0 = input.LA(1);
					if (((LA1_0 >= '0' && LA1_0 <= '9')))
					{
						alt1 = 1;
					}

					switch (alt1)
					{
						case 1:
						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:
						{
							if ((input.LA(1) >= '0' && input.LA(1) <= '9'))
							{
								input.consume();
							}
							else
							{
								final MismatchedSetException mse = new MismatchedSetException(null, input);
								recover(mse);
								throw mse;
							}
						}
							break;

						default:
							if (cnt1 >= 1)
							{
								break loop1;
							}
							final EarlyExitException eee = new EarlyExitException(1, input);
							throw eee;
					}
					cnt1++;
				}

			}

		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "INT"

	// $ANTLR start "NULL"
	public final void mNULL() throws RecognitionException
	{
		try
		{
			final int _type = NULL;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:56:2: ( 'null' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:56:4: 'null'
			{
				match("null");

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "NULL"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException
	{
		try
		{
			final int _type = TRUE;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:59:2: ( 'true' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:59:4: 'true'
			{
				match("true");

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "TRUE"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException
	{
		try
		{
			final int _type = FALSE;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:62:2: ( 'false' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:62:4: 'false'
			{
				match("false");

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "FALSE"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException
	{
		try
		{
			final int _type = NUMBER;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:9: ( ( '-' )? ( '0' | ( '1' .. '9' ( INT )* ) ) ( '.' ( INT )+ )? ( EXPONENT )? )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:11: ( '-' )? ( '0' | ( '1' .. '9' ( INT )* ) ) ( '.' ( INT )+ )? ( EXPONENT )?
			{
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:11: ( '-' )?
				int alt2 = 2;
				final int LA2_0 = input.LA(1);
				if ((LA2_0 == '-'))
				{
					alt2 = 1;
				}
				switch (alt2)
				{
					case 1:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:11: '-'
					{
						match('-');
					}
						break;

				}

				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:16: ( '0' | ( '1' .. '9' ( INT )* ) )
				int alt4 = 2;
				final int LA4_0 = input.LA(1);
				if ((LA4_0 == '0'))
				{
					alt4 = 1;
				}
				else if (((LA4_0 >= '1' && LA4_0 <= '9')))
				{
					alt4 = 2;
				}

				else
				{
					final NoViableAltException nvae = new NoViableAltException("", 4, 0, input);
					throw nvae;
				}

				switch (alt4)
				{
					case 1:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:17: '0'
					{
						match('0');
					}
						break;
					case 2:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:23: ( '1' .. '9' ( INT )* )
					{
						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:23: ( '1' .. '9' ( INT )* )
						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:25: '1' .. '9' ( INT )*
						{
							matchRange('1', '9');
							// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:34: ( INT )*
							loop3: while (true)
							{
								int alt3 = 2;
								final int LA3_0 = input.LA(1);
								if (((LA3_0 >= '0' && LA3_0 <= '9')))
								{
									alt3 = 1;
								}

								switch (alt3)
								{
									case 1:
									// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:34: INT
									{
										mINT();

									}
										break;

									default:
										break loop3;
								}
							}

						}

					}
						break;

				}

				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:42: ( '.' ( INT )+ )?
				int alt6 = 2;
				final int LA6_0 = input.LA(1);
				if ((LA6_0 == '.'))
				{
					alt6 = 1;
				}
				switch (alt6)
				{
					case 1:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:43: '.' ( INT )+
					{
						match('.');
						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:47: ( INT )+
						int cnt5 = 0;
						loop5: while (true)
						{
							int alt5 = 2;
							final int LA5_0 = input.LA(1);
							if (((LA5_0 >= '0' && LA5_0 <= '9')))
							{
								alt5 = 1;
							}

							switch (alt5)
							{
								case 1:
								// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:47: INT
								{
									mINT();

								}
									break;

								default:
									if (cnt5 >= 1)
									{
										break loop5;
									}
									final EarlyExitException eee = new EarlyExitException(5, input);
									throw eee;
							}
							cnt5++;
						}

					}
						break;

				}

				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:54: ( EXPONENT )?
				int alt7 = 2;
				final int LA7_0 = input.LA(1);
				if ((LA7_0 == 'E' || LA7_0 == 'e'))
				{
					alt7 = 1;
				}
				switch (alt7)
				{
					case 1:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:65:54: EXPONENT
					{
						mEXPONENT();

					}
						break;

				}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "NUMBER"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException
	{
		try
		{
			final int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:69:9: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:69:11: ( ' ' | '\\t' | '\\n' | '\\r' )
			{
				if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || input.LA(1) == '\r' || input.LA(1) == ' ')
				{
					input.consume();
				}
				else
				{
					final MismatchedSetException mse = new MismatchedSetException(null, input);
					recover(mse);
					throw mse;
				}
				_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "WS"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException
	{
		try
		{
			final int _type = STRING;
			final int _channel = DEFAULT_TOKEN_CHANNEL;
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:77:9: ( '\"' ( ESC_SEQ |~ ( '\\\\' | '\"' ) )* '\"' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:77:11: '\"' ( ESC_SEQ |~ ( '\\\\' | '\"' ) )* '\"'
			{
				match('\"');
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:77:15: ( ESC_SEQ |~ ( '\\\\' | '\"' ) )*
				loop8: while (true)
				{
					int alt8 = 3;
					final int LA8_0 = input.LA(1);
					if ((LA8_0 == '\\'))
					{
						alt8 = 1;
					}
					else if (((LA8_0 >= '\u0000' && LA8_0 <= '!') || (LA8_0 >= '#' && LA8_0 <= '[') || (LA8_0 >= ']' && LA8_0 <= '\uFFFF')))
					{
						alt8 = 2;
					}

					switch (alt8)
					{
						case 1:
						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:77:17: ESC_SEQ
						{
							mESC_SEQ();

						}
							break;
						case 2:
						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:77:27: ~ ( '\\\\' | '\"' )
						{
							if ((input.LA(1) >= '\u0000' && input.LA(1) <= '!') || (input.LA(1) >= '#' && input.LA(1) <= '[') || (input.LA(1) >= ']' && input.LA(1) <= '\uFFFF'))
							{
								input.consume();
							}
							else
							{
								final MismatchedSetException mse = new MismatchedSetException(null, input);
								recover(mse);
								throw mse;
							}
						}
							break;

						default:
							break loop8;
					}
				}

				match('\"');
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "STRING"

	// $ANTLR start "EXPONENT"
	public final void mEXPONENT() throws RecognitionException
	{
		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:83:9: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:83:11: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
			{
				if (input.LA(1) == 'E' || input.LA(1) == 'e')
				{
					input.consume();
				}
				else
				{
					final MismatchedSetException mse = new MismatchedSetException(null, input);
					recover(mse);
					throw mse;
				}
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:83:21: ( '+' | '-' )?
				int alt9 = 2;
				final int LA9_0 = input.LA(1);
				if ((LA9_0 == '+' || LA9_0 == '-'))
				{
					alt9 = 1;
				}
				switch (alt9)
				{
					case 1:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:
					{
						if (input.LA(1) == '+' || input.LA(1) == '-')
						{
							input.consume();
						}
						else
						{
							final MismatchedSetException mse = new MismatchedSetException(null, input);
							recover(mse);
							throw mse;
						}
					}
						break;

				}

				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:83:32: ( '0' .. '9' )+
				int cnt10 = 0;
				loop10: while (true)
				{
					int alt10 = 2;
					final int LA10_0 = input.LA(1);
					if (((LA10_0 >= '0' && LA10_0 <= '9')))
					{
						alt10 = 1;
					}

					switch (alt10)
					{
						case 1:
						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:
						{
							if ((input.LA(1) >= '0' && input.LA(1) <= '9'))
							{
								input.consume();
							}
							else
							{
								final MismatchedSetException mse = new MismatchedSetException(null, input);
								recover(mse);
								throw mse;
							}
						}
							break;

						default:
							if (cnt10 >= 1)
							{
								break loop10;
							}
							final EarlyExitException eee = new EarlyExitException(10, input);
							throw eee;
					}
					cnt10++;
				}

			}

		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "EXPONENT"

	// $ANTLR start "HEX_DIGIT"
	public final void mHEX_DIGIT() throws RecognitionException
	{
		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:88:9: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:
			{
				if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'F') || (input.LA(1) >= 'a' && input.LA(1) <= 'f'))
				{
					input.consume();
				}
				else
				{
					final MismatchedSetException mse = new MismatchedSetException(null, input);
					recover(mse);
					throw mse;
				}
			}

		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "HEX_DIGIT"

	// $ANTLR start "ESC_SEQ"
	public final void mESC_SEQ() throws RecognitionException
	{
		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:93:9: ( '\\\\' ( '\\\"' | '\\\\' | '/' | 'b' | 'f' | 'n' | 'r' | 't' ) | UNICODE_ESC )
			int alt11 = 2;
			final int LA11_0 = input.LA(1);
			if ((LA11_0 == '\\'))
			{
				final int LA11_1 = input.LA(2);
				if ((LA11_1 == '\"' || LA11_1 == '/' || LA11_1 == '\\' || LA11_1 == 'b' || LA11_1 == 'f' || LA11_1 == 'n' || LA11_1 == 'r' || LA11_1 == 't'))
				{
					alt11 = 1;
				}
				else if ((LA11_1 == 'u'))
				{
					alt11 = 2;
				}

				else
				{
					final int nvaeMark = input.mark();
					try
					{
						input.consume();
						final NoViableAltException nvae = new NoViableAltException("", 11, 1, input);
						throw nvae;
					}
					finally
					{
						input.rewind(nvaeMark);
					}
				}

			}

			else
			{
				final NoViableAltException nvae = new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11)
			{
				case 1:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:93:11: '\\\\' ( '\\\"' | '\\\\' | '/' | 'b' | 'f' | 'n' | 'r' | 't' )
				{
					match('\\');
					if (input.LA(1) == '\"' || input.LA(1) == '/' || input.LA(1) == '\\' || input.LA(1) == 'b' || input.LA(1) == 'f' || input.LA(1) == 'n' || input.LA(1) == 'r' || input.LA(1) == 't')
					{
						input.consume();
					}
					else
					{
						final MismatchedSetException mse = new MismatchedSetException(null, input);
						recover(mse);
						throw mse;
					}
				}
					break;
				case 2:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:94:11: UNICODE_ESC
				{
					mUNICODE_ESC();

				}
					break;

			}
		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "ESC_SEQ"

	// $ANTLR start "UNICODE_ESC"
	public final void mUNICODE_ESC() throws RecognitionException
	{
		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:99:9: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:99:11: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
			{
				match('\\');
				match('u');
				mHEX_DIGIT();

				mHEX_DIGIT();

				mHEX_DIGIT();

				mHEX_DIGIT();

			}

		}
		finally
		{
			// do for sure before leaving
		}
	}

	// $ANTLR end "UNICODE_ESC"

	@Override
	public void mTokens() throws RecognitionException
	{
		// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:8: ( T__17 | T__18 | T__19 | T__20 | ARRAY | OBJECT | NULL | TRUE | FALSE | NUMBER | WS | STRING )
		int alt12 = 12;
		switch (input.LA(1))
		{
			case ',':
			{
				alt12 = 1;
			}
				break;
			case ':':
			{
				alt12 = 2;
			}
				break;
			case ']':
			{
				alt12 = 3;
			}
				break;
			case '}':
			{
				alt12 = 4;
			}
				break;
			case '[':
			{
				alt12 = 5;
			}
				break;
			case '{':
			{
				alt12 = 6;
			}
				break;
			case 'n':
			{
				alt12 = 7;
			}
				break;
			case 't':
			{
				alt12 = 8;
			}
				break;
			case 'f':
			{
				alt12 = 9;
			}
				break;
			case '-':
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			{
				alt12 = 10;
			}
				break;
			case '\t':
			case '\n':
			case '\r':
			case ' ':
			{
				alt12 = 11;
			}
				break;
			case '\"':
			{
				alt12 = 12;
			}
				break;
			default:
				final NoViableAltException nvae = new NoViableAltException("", 12, 0, input);
				throw nvae;
		}
		switch (alt12)
		{
			case 1:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:10: T__17
			{
				mT__17();

			}
				break;
			case 2:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:16: T__18
			{
				mT__18();

			}
				break;
			case 3:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:22: T__19
			{
				mT__19();

			}
				break;
			case 4:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:28: T__20
			{
				mT__20();

			}
				break;
			case 5:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:34: ARRAY
			{
				mARRAY();

			}
				break;
			case 6:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:40: OBJECT
			{
				mOBJECT();

			}
				break;
			case 7:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:47: NULL
			{
				mNULL();

			}
				break;
			case 8:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:52: TRUE
			{
				mTRUE();

			}
				break;
			case 9:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:57: FALSE
			{
				mFALSE();

			}
				break;
			case 10:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:63: NUMBER
			{
				mNUMBER();

			}
				break;
			case 11:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:70: WS
			{
				mWS();

			}
				break;
			case 12:
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:1:73: STRING
			{
				mSTRING();

			}
				break;

		}
	}

}
