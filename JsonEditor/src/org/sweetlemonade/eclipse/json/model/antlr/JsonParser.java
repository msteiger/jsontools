package org.sweetlemonade.eclipse.json.model.antlr;

// $ANTLR 3.5 C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g
// 2014-01-21 12:06:11

import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

@SuppressWarnings("all")
public class JsonParser extends Parser
{
	public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARRAY", "ESC_SEQ", "EXPONENT", "FALSE", "HEX_DIGIT", "INT", "NULL", "NUMBER", "OBJECT", "STRING",
			"TRUE", "UNICODE_ESC", "WS", "','", "':'", "']'", "'}'" };
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
	public Parser[] getDelegates()
	{
		return new Parser[] {};
	}

	// delegators

	public JsonParser(TokenStream input)
	{
		this(input, new RecognizerSharedState());
	}

	public JsonParser(TokenStream input, RecognizerSharedState state)
	{
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor)
	{
		this.adaptor = adaptor;
	}

	public TreeAdaptor getTreeAdaptor()
	{
		return adaptor;
	}

	@Override
	public String[] getTokenNames()
	{
		return JsonParser.tokenNames;
	}

	@Override
	public String getGrammarFileName()
	{
		return "C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g";
	}

	public static class jsonText_return extends ParserRuleReturnScope
	{
		Object tree;

		@Override
		public Object getTree()
		{
			return tree;
		}
	};

	// $ANTLR start "jsonText"
	// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:8:1: jsonText : ( jsonObject | jsonArray ) EOF !;
	public final JsonParser.jsonText_return jsonText()
	{
		final JsonParser.jsonText_return retval = new JsonParser.jsonText_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EOF3 = null;
		ParserRuleReturnScope jsonObject1 = null;
		ParserRuleReturnScope jsonArray2 = null;

		final Object EOF3_tree = null;

		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:9:9: ( ( jsonObject | jsonArray ) EOF !)
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:9:11: ( jsonObject | jsonArray ) EOF !
			{
				root_0 = adaptor.nil();

				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:9:11: ( jsonObject | jsonArray )
				int alt1 = 2;
				final int LA1_0 = input.LA(1);
				if ((LA1_0 == OBJECT))
				{
					alt1 = 1;
				}
				else if ((LA1_0 == ARRAY))
				{
					alt1 = 2;
				}

				else
				{
					final NoViableAltException nvae = new NoViableAltException("", 1, 0, input);
					throw nvae;
				}

				switch (alt1)
				{
					case 1:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:9:12: jsonObject
					{
						pushFollow(FOLLOW_jsonObject_in_jsonText33);
						jsonObject1 = jsonObject();
						state._fsp--;

						adaptor.addChild(root_0, jsonObject1.getTree());

					}
						break;
					case 2:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:10:11: jsonArray
					{
						pushFollow(FOLLOW_jsonArray_in_jsonText45);
						jsonArray2 = jsonArray();
						state._fsp--;

						adaptor.addChild(root_0, jsonArray2.getTree());

					}
						break;

				}

				EOF3 = (Token) match(input, EOF, FOLLOW_EOF_in_jsonText49);
			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (final RecognitionException re)
		{
			reportError(re);
			recover(input, re);
			retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally
		{
			// do for sure before leaving
		}
		return retval;
	}

	// $ANTLR end "jsonText"

	public static class jsonValue_return extends ParserRuleReturnScope
	{
		Object tree;

		@Override
		public Object getTree()
		{
			return tree;
		}
	};

	// $ANTLR start "jsonValue"
	// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:13:1: jsonValue : ( FALSE | TRUE | NULL | jsonObject | jsonArray | jsonNumber | jsonString );
	public final JsonParser.jsonValue_return jsonValue()
	{
		final JsonParser.jsonValue_return retval = new JsonParser.jsonValue_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token FALSE4 = null;
		Token TRUE5 = null;
		Token NULL6 = null;
		ParserRuleReturnScope jsonObject7 = null;
		ParserRuleReturnScope jsonArray8 = null;
		ParserRuleReturnScope jsonNumber9 = null;
		ParserRuleReturnScope jsonString10 = null;

		Object FALSE4_tree = null;
		Object TRUE5_tree = null;
		Object NULL6_tree = null;

		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:14:9: ( FALSE | TRUE | NULL | jsonObject | jsonArray | jsonNumber | jsonString )
			int alt2 = 7;
			switch (input.LA(1))
			{
				case FALSE:
				{
					alt2 = 1;
				}
					break;
				case TRUE:
				{
					alt2 = 2;
				}
					break;
				case NULL:
				{
					alt2 = 3;
				}
					break;
				case OBJECT:
				{
					alt2 = 4;
				}
					break;
				case ARRAY:
				{
					alt2 = 5;
				}
					break;
				case NUMBER:
				{
					alt2 = 6;
				}
					break;
				case STRING:
				{
					alt2 = 7;
				}
					break;
				default:
					final NoViableAltException nvae = new NoViableAltException("", 2, 0, input);
					throw nvae;
			}
			switch (alt2)
			{
				case 1:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:14:11: FALSE
				{
					root_0 = adaptor.nil();

					FALSE4 = (Token) match(input, FALSE, FOLLOW_FALSE_in_jsonValue67);
					FALSE4_tree = adaptor.create(FALSE4);
					adaptor.addChild(root_0, FALSE4_tree);

				}
					break;
				case 2:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:15:11: TRUE
				{
					root_0 = adaptor.nil();

					TRUE5 = (Token) match(input, TRUE, FOLLOW_TRUE_in_jsonValue79);
					TRUE5_tree = adaptor.create(TRUE5);
					adaptor.addChild(root_0, TRUE5_tree);

				}
					break;
				case 3:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:16:11: NULL
				{
					root_0 = adaptor.nil();

					NULL6 = (Token) match(input, NULL, FOLLOW_NULL_in_jsonValue91);
					NULL6_tree = adaptor.create(NULL6);
					adaptor.addChild(root_0, NULL6_tree);

				}
					break;
				case 4:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:17:11: jsonObject
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonObject_in_jsonValue103);
					jsonObject7 = jsonObject();
					state._fsp--;

					adaptor.addChild(root_0, jsonObject7.getTree());

				}
					break;
				case 5:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:18:11: jsonArray
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonArray_in_jsonValue115);
					jsonArray8 = jsonArray();
					state._fsp--;

					adaptor.addChild(root_0, jsonArray8.getTree());

				}
					break;
				case 6:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:19:11: jsonNumber
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonNumber_in_jsonValue127);
					jsonNumber9 = jsonNumber();
					state._fsp--;

					adaptor.addChild(root_0, jsonNumber9.getTree());

				}
					break;
				case 7:
				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:20:11: jsonString
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonString_in_jsonValue139);
					jsonString10 = jsonString();
					state._fsp--;

					adaptor.addChild(root_0, jsonString10.getTree());

				}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (final RecognitionException re)
		{
			reportError(re);
			recover(input, re);
			retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally
		{
			// do for sure before leaving
		}
		return retval;
	}

	// $ANTLR end "jsonValue"

	public static class jsonNumber_return extends ParserRuleReturnScope
	{
		Object tree;

		@Override
		public Object getTree()
		{
			return tree;
		}
	};

	// $ANTLR start "jsonNumber"
	// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:23:1: jsonNumber : NUMBER ;
	public final JsonParser.jsonNumber_return jsonNumber()
	{
		final JsonParser.jsonNumber_return retval = new JsonParser.jsonNumber_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NUMBER11 = null;

		Object NUMBER11_tree = null;

		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:24:9: ( NUMBER )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:24:11: NUMBER
			{
				root_0 = adaptor.nil();

				NUMBER11 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_jsonNumber156);
				NUMBER11_tree = adaptor.create(NUMBER11);
				adaptor.addChild(root_0, NUMBER11_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (final RecognitionException re)
		{
			reportError(re);
			recover(input, re);
			retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally
		{
			// do for sure before leaving
		}
		return retval;
	}

	// $ANTLR end "jsonNumber"

	public static class jsonString_return extends ParserRuleReturnScope
	{
		Object tree;

		@Override
		public Object getTree()
		{
			return tree;
		}
	};

	// $ANTLR start "jsonString"
	// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:27:1: jsonString : STRING ;
	public final JsonParser.jsonString_return jsonString()
	{
		final JsonParser.jsonString_return retval = new JsonParser.jsonString_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STRING12 = null;

		Object STRING12_tree = null;

		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:28:9: ( STRING )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:28:11: STRING
			{
				root_0 = adaptor.nil();

				STRING12 = (Token) match(input, STRING, FOLLOW_STRING_in_jsonString173);
				STRING12_tree = adaptor.create(STRING12);
				adaptor.addChild(root_0, STRING12_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (final RecognitionException re)
		{
			reportError(re);
			recover(input, re);
			retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally
		{
			// do for sure before leaving
		}
		return retval;
	}

	// $ANTLR end "jsonString"

	public static class member_return extends ParserRuleReturnScope
	{
		Object tree;

		@Override
		public Object getTree()
		{
			return tree;
		}
	};

	// $ANTLR start "member"
	// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:31:1: member : STRING ^ ':' ! jsonValue ;
	public final JsonParser.member_return member()
	{
		final JsonParser.member_return retval = new JsonParser.member_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STRING13 = null;
		Token char_literal14 = null;
		ParserRuleReturnScope jsonValue15 = null;

		Object STRING13_tree = null;
		final Object char_literal14_tree = null;

		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:32:9: ( STRING ^ ':' ! jsonValue )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:32:11: STRING ^ ':' ! jsonValue
			{
				root_0 = adaptor.nil();

				STRING13 = (Token) match(input, STRING, FOLLOW_STRING_in_member190);
				STRING13_tree = adaptor.create(STRING13);
				root_0 = adaptor.becomeRoot(STRING13_tree, root_0);

				char_literal14 = (Token) match(input, 18, FOLLOW_18_in_member193);
				pushFollow(FOLLOW_jsonValue_in_member196);
				jsonValue15 = jsonValue();
				state._fsp--;

				adaptor.addChild(root_0, jsonValue15.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (final RecognitionException re)
		{
			reportError(re);
			recover(input, re);
			retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally
		{
			// do for sure before leaving
		}
		return retval;
	}

	// $ANTLR end "member"

	public static class jsonObject_return extends ParserRuleReturnScope
	{
		Object tree;

		@Override
		public Object getTree()
		{
			return tree;
		}
	};

	// $ANTLR start "jsonObject"
	// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:35:1: jsonObject : OBJECT ^ ( member ( ',' ! member )* )? '}' ;
	public final JsonParser.jsonObject_return jsonObject()
	{
		final JsonParser.jsonObject_return retval = new JsonParser.jsonObject_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token OBJECT16 = null;
		Token char_literal18 = null;
		Token char_literal20 = null;
		ParserRuleReturnScope member17 = null;
		ParserRuleReturnScope member19 = null;

		Object OBJECT16_tree = null;
		final Object char_literal18_tree = null;
		Object char_literal20_tree = null;

		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:36:9: ( OBJECT ^ ( member ( ',' ! member )* )? '}' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:36:11: OBJECT ^ ( member ( ',' ! member )* )? '}'
			{
				root_0 = adaptor.nil();

				OBJECT16 = (Token) match(input, OBJECT, FOLLOW_OBJECT_in_jsonObject213);
				OBJECT16_tree = adaptor.create(OBJECT16);
				root_0 = adaptor.becomeRoot(OBJECT16_tree, root_0);

				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:36:19: ( member ( ',' ! member )* )?
				int alt4 = 2;
				final int LA4_0 = input.LA(1);
				if ((LA4_0 == STRING))
				{
					alt4 = 1;
				}
				switch (alt4)
				{
					case 1:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:36:20: member ( ',' ! member )*
					{
						pushFollow(FOLLOW_member_in_jsonObject217);
						member17 = member();
						state._fsp--;

						adaptor.addChild(root_0, member17.getTree());

						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:36:27: ( ',' ! member )*
						loop3: while (true)
						{
							int alt3 = 2;
							final int LA3_0 = input.LA(1);
							if ((LA3_0 == 17))
							{
								alt3 = 1;
							}

							switch (alt3)
							{
								case 1:
								// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:36:28: ',' ! member
								{
									char_literal18 = (Token) match(input, 17, FOLLOW_17_in_jsonObject220);
									pushFollow(FOLLOW_member_in_jsonObject223);
									member19 = member();
									state._fsp--;

									adaptor.addChild(root_0, member19.getTree());

								}
									break;

								default:
									break loop3;
							}
						}

					}
						break;

				}

				char_literal20 = (Token) match(input, 20, FOLLOW_20_in_jsonObject229);
				char_literal20_tree = adaptor.create(char_literal20);
				adaptor.addChild(root_0, char_literal20_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (final RecognitionException re)
		{
			reportError(re);
			recover(input, re);
			retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally
		{
			// do for sure before leaving
		}
		return retval;
	}

	// $ANTLR end "jsonObject"

	public static class jsonArray_return extends ParserRuleReturnScope
	{
		Object tree;

		@Override
		public Object getTree()
		{
			return tree;
		}
	};

	// $ANTLR start "jsonArray"
	// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:40:1: jsonArray : ARRAY ^ ( jsonValue ( ',' ! jsonValue )* )? ']' ;
	public final JsonParser.jsonArray_return jsonArray()
	{
		final JsonParser.jsonArray_return retval = new JsonParser.jsonArray_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ARRAY21 = null;
		Token char_literal23 = null;
		Token char_literal25 = null;
		ParserRuleReturnScope jsonValue22 = null;
		ParserRuleReturnScope jsonValue24 = null;

		Object ARRAY21_tree = null;
		final Object char_literal23_tree = null;
		Object char_literal25_tree = null;

		try
		{
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:41:9: ( ARRAY ^ ( jsonValue ( ',' ! jsonValue )* )? ']' )
			// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:41:11: ARRAY ^ ( jsonValue ( ',' ! jsonValue )* )? ']'
			{
				root_0 = adaptor.nil();

				ARRAY21 = (Token) match(input, ARRAY, FOLLOW_ARRAY_in_jsonArray247);
				ARRAY21_tree = adaptor.create(ARRAY21);
				root_0 = adaptor.becomeRoot(ARRAY21_tree, root_0);

				// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:41:18: ( jsonValue ( ',' ! jsonValue )* )?
				int alt6 = 2;
				final int LA6_0 = input.LA(1);
				if ((LA6_0 == ARRAY || LA6_0 == FALSE || (LA6_0 >= NULL && LA6_0 <= TRUE)))
				{
					alt6 = 1;
				}
				switch (alt6)
				{
					case 1:
					// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:41:19: jsonValue ( ',' ! jsonValue )*
					{
						pushFollow(FOLLOW_jsonValue_in_jsonArray251);
						jsonValue22 = jsonValue();
						state._fsp--;

						adaptor.addChild(root_0, jsonValue22.getTree());

						// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:41:29: ( ',' ! jsonValue )*
						loop5: while (true)
						{
							int alt5 = 2;
							final int LA5_0 = input.LA(1);
							if ((LA5_0 == 17))
							{
								alt5 = 1;
							}

							switch (alt5)
							{
								case 1:
								// C:\\Users\\bimeg\\workspaceRCP\\JsonToolsRepo\\JsonEditor\\Json.g:41:30: ',' ! jsonValue
								{
									char_literal23 = (Token) match(input, 17, FOLLOW_17_in_jsonArray254);
									pushFollow(FOLLOW_jsonValue_in_jsonArray257);
									jsonValue24 = jsonValue();
									state._fsp--;

									adaptor.addChild(root_0, jsonValue24.getTree());

								}
									break;

								default:
									break loop5;
							}
						}

					}
						break;

				}

				char_literal25 = (Token) match(input, 19, FOLLOW_19_in_jsonArray263);
				char_literal25_tree = adaptor.create(char_literal25);
				adaptor.addChild(root_0, char_literal25_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (final RecognitionException re)
		{
			reportError(re);
			recover(input, re);
			retval.tree = adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally
		{
			// do for sure before leaving
		}
		return retval;
	}

	// $ANTLR end "jsonArray"

	// Delegated rules

	public static final BitSet FOLLOW_jsonObject_in_jsonText33 = new BitSet(new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_jsonArray_in_jsonText45 = new BitSet(new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_jsonText49 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FALSE_in_jsonValue67 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TRUE_in_jsonValue79 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NULL_in_jsonValue91 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonObject_in_jsonValue103 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonArray_in_jsonValue115 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonNumber_in_jsonValue127 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonString_in_jsonValue139 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NUMBER_in_jsonNumber156 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_STRING_in_jsonString173 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_STRING_in_member190 = new BitSet(new long[] { 0x0000000000040000L });
	public static final BitSet FOLLOW_18_in_member193 = new BitSet(new long[] { 0x0000000000007C90L });
	public static final BitSet FOLLOW_jsonValue_in_member196 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_OBJECT_in_jsonObject213 = new BitSet(new long[] { 0x0000000000102000L });
	public static final BitSet FOLLOW_member_in_jsonObject217 = new BitSet(new long[] { 0x0000000000120000L });
	public static final BitSet FOLLOW_17_in_jsonObject220 = new BitSet(new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_member_in_jsonObject223 = new BitSet(new long[] { 0x0000000000120000L });
	public static final BitSet FOLLOW_20_in_jsonObject229 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ARRAY_in_jsonArray247 = new BitSet(new long[] { 0x0000000000087C90L });
	public static final BitSet FOLLOW_jsonValue_in_jsonArray251 = new BitSet(new long[] { 0x00000000000A0000L });
	public static final BitSet FOLLOW_17_in_jsonArray254 = new BitSet(new long[] { 0x0000000000007C90L });
	public static final BitSet FOLLOW_jsonValue_in_jsonArray257 = new BitSet(new long[] { 0x00000000000A0000L });
	public static final BitSet FOLLOW_19_in_jsonArray263 = new BitSet(new long[] { 0x0000000000000002L });
}
