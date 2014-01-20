package org.sweetlemonade.eclipse.json.model.antlr;

// $ANTLR 3.5 D:\\Documents\\grammars\\Json.g 2014-01-20 02:30:21

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
		return "D:\\Documents\\grammars\\Json.g";
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
	// D:\\Documents\\grammars\\Json.g:8:1: jsonText : ( jsonObject | jsonArray );
	public final JsonParser.jsonText_return jsonText()
	{
		JsonParser.jsonText_return retval = new JsonParser.jsonText_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope jsonObject1 = null;
		ParserRuleReturnScope jsonArray2 = null;

		try
		{
			// D:\\Documents\\grammars\\Json.g:9:9: ( jsonObject | jsonArray )
			int alt1 = 2;
			int LA1_0 = input.LA(1);
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
				NoViableAltException nvae = new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1)
			{
				case 1:
				// D:\\Documents\\grammars\\Json.g:9:11: jsonObject
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonObject_in_jsonText32);
					jsonObject1 = jsonObject();
					state._fsp--;

					adaptor.addChild(root_0, jsonObject1.getTree());

				}
					break;
				case 2:
				// D:\\Documents\\grammars\\Json.g:10:11: jsonArray
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonArray_in_jsonText44);
					jsonArray2 = jsonArray();
					state._fsp--;

					adaptor.addChild(root_0, jsonArray2.getTree());

				}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re)
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
	// D:\\Documents\\grammars\\Json.g:13:1: jsonValue : ( FALSE | TRUE | NULL | jsonObject | jsonArray | jsonNumber | jsonString );
	public final JsonParser.jsonValue_return jsonValue()
	{
		JsonParser.jsonValue_return retval = new JsonParser.jsonValue_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token FALSE3 = null;
		Token TRUE4 = null;
		Token NULL5 = null;
		ParserRuleReturnScope jsonObject6 = null;
		ParserRuleReturnScope jsonArray7 = null;
		ParserRuleReturnScope jsonNumber8 = null;
		ParserRuleReturnScope jsonString9 = null;

		Object FALSE3_tree = null;
		Object TRUE4_tree = null;
		Object NULL5_tree = null;

		try
		{
			// D:\\Documents\\grammars\\Json.g:14:9: ( FALSE | TRUE | NULL | jsonObject | jsonArray | jsonNumber | jsonString )
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
					NoViableAltException nvae = new NoViableAltException("", 2, 0, input);
					throw nvae;
			}
			switch (alt2)
			{
				case 1:
				// D:\\Documents\\grammars\\Json.g:14:11: FALSE
				{
					root_0 = adaptor.nil();

					FALSE3 = (Token) match(input, FALSE, FOLLOW_FALSE_in_jsonValue61);
					FALSE3_tree = adaptor.create(FALSE3);
					adaptor.addChild(root_0, FALSE3_tree);

				}
					break;
				case 2:
				// D:\\Documents\\grammars\\Json.g:15:11: TRUE
				{
					root_0 = adaptor.nil();

					TRUE4 = (Token) match(input, TRUE, FOLLOW_TRUE_in_jsonValue73);
					TRUE4_tree = adaptor.create(TRUE4);
					adaptor.addChild(root_0, TRUE4_tree);

				}
					break;
				case 3:
				// D:\\Documents\\grammars\\Json.g:16:11: NULL
				{
					root_0 = adaptor.nil();

					NULL5 = (Token) match(input, NULL, FOLLOW_NULL_in_jsonValue85);
					NULL5_tree = adaptor.create(NULL5);
					adaptor.addChild(root_0, NULL5_tree);

				}
					break;
				case 4:
				// D:\\Documents\\grammars\\Json.g:17:11: jsonObject
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonObject_in_jsonValue97);
					jsonObject6 = jsonObject();
					state._fsp--;

					adaptor.addChild(root_0, jsonObject6.getTree());

				}
					break;
				case 5:
				// D:\\Documents\\grammars\\Json.g:18:11: jsonArray
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonArray_in_jsonValue109);
					jsonArray7 = jsonArray();
					state._fsp--;

					adaptor.addChild(root_0, jsonArray7.getTree());

				}
					break;
				case 6:
				// D:\\Documents\\grammars\\Json.g:19:11: jsonNumber
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonNumber_in_jsonValue121);
					jsonNumber8 = jsonNumber();
					state._fsp--;

					adaptor.addChild(root_0, jsonNumber8.getTree());

				}
					break;
				case 7:
				// D:\\Documents\\grammars\\Json.g:20:11: jsonString
				{
					root_0 = adaptor.nil();

					pushFollow(FOLLOW_jsonString_in_jsonValue133);
					jsonString9 = jsonString();
					state._fsp--;

					adaptor.addChild(root_0, jsonString9.getTree());

				}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re)
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
	// D:\\Documents\\grammars\\Json.g:23:1: jsonNumber : NUMBER ;
	public final JsonParser.jsonNumber_return jsonNumber()
	{
		JsonParser.jsonNumber_return retval = new JsonParser.jsonNumber_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token NUMBER10 = null;

		Object NUMBER10_tree = null;

		try
		{
			// D:\\Documents\\grammars\\Json.g:24:9: ( NUMBER )
			// D:\\Documents\\grammars\\Json.g:24:11: NUMBER
			{
				root_0 = adaptor.nil();

				NUMBER10 = (Token) match(input, NUMBER, FOLLOW_NUMBER_in_jsonNumber150);
				NUMBER10_tree = adaptor.create(NUMBER10);
				adaptor.addChild(root_0, NUMBER10_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re)
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
	// D:\\Documents\\grammars\\Json.g:27:1: jsonString : STRING ;
	public final JsonParser.jsonString_return jsonString()
	{
		JsonParser.jsonString_return retval = new JsonParser.jsonString_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STRING11 = null;

		Object STRING11_tree = null;

		try
		{
			// D:\\Documents\\grammars\\Json.g:28:9: ( STRING )
			// D:\\Documents\\grammars\\Json.g:28:11: STRING
			{
				root_0 = adaptor.nil();

				STRING11 = (Token) match(input, STRING, FOLLOW_STRING_in_jsonString167);
				STRING11_tree = adaptor.create(STRING11);
				adaptor.addChild(root_0, STRING11_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re)
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
	// D:\\Documents\\grammars\\Json.g:31:1: member : STRING ^ ':' ! jsonValue ;
	public final JsonParser.member_return member()
	{
		JsonParser.member_return retval = new JsonParser.member_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STRING12 = null;
		Token char_literal13 = null;
		ParserRuleReturnScope jsonValue14 = null;

		Object STRING12_tree = null;
		Object char_literal13_tree = null;

		try
		{
			// D:\\Documents\\grammars\\Json.g:32:9: ( STRING ^ ':' ! jsonValue )
			// D:\\Documents\\grammars\\Json.g:32:11: STRING ^ ':' ! jsonValue
			{
				root_0 = adaptor.nil();

				STRING12 = (Token) match(input, STRING, FOLLOW_STRING_in_member184);
				STRING12_tree = adaptor.create(STRING12);
				root_0 = adaptor.becomeRoot(STRING12_tree, root_0);

				char_literal13 = (Token) match(input, 18, FOLLOW_18_in_member187);
				pushFollow(FOLLOW_jsonValue_in_member190);
				jsonValue14 = jsonValue();
				state._fsp--;

				adaptor.addChild(root_0, jsonValue14.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re)
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
	// D:\\Documents\\grammars\\Json.g:35:1: jsonObject : OBJECT ^ ( member ( ',' ! member )* )? '}' ;
	public final JsonParser.jsonObject_return jsonObject()
	{
		JsonParser.jsonObject_return retval = new JsonParser.jsonObject_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token OBJECT15 = null;
		Token char_literal17 = null;
		Token char_literal19 = null;
		ParserRuleReturnScope member16 = null;
		ParserRuleReturnScope member18 = null;

		Object OBJECT15_tree = null;
		Object char_literal17_tree = null;
		Object char_literal19_tree = null;

		try
		{
			// D:\\Documents\\grammars\\Json.g:36:9: ( OBJECT ^ ( member ( ',' ! member )* )? '}' )
			// D:\\Documents\\grammars\\Json.g:36:11: OBJECT ^ ( member ( ',' ! member )* )? '}'
			{
				root_0 = adaptor.nil();

				OBJECT15 = (Token) match(input, OBJECT, FOLLOW_OBJECT_in_jsonObject207);
				OBJECT15_tree = adaptor.create(OBJECT15);
				root_0 = adaptor.becomeRoot(OBJECT15_tree, root_0);

				// D:\\Documents\\grammars\\Json.g:36:19: ( member ( ',' ! member )* )?
				int alt4 = 2;
				int LA4_0 = input.LA(1);
				if ((LA4_0 == STRING))
				{
					alt4 = 1;
				}
				switch (alt4)
				{
					case 1:
					// D:\\Documents\\grammars\\Json.g:36:20: member ( ',' ! member )*
					{
						pushFollow(FOLLOW_member_in_jsonObject211);
						member16 = member();
						state._fsp--;

						adaptor.addChild(root_0, member16.getTree());

						// D:\\Documents\\grammars\\Json.g:36:27: ( ',' ! member )*
						loop3: while (true)
						{
							int alt3 = 2;
							int LA3_0 = input.LA(1);
							if ((LA3_0 == 17))
							{
								alt3 = 1;
							}

							switch (alt3)
							{
								case 1:
								// D:\\Documents\\grammars\\Json.g:36:28: ',' ! member
								{
									char_literal17 = (Token) match(input, 17, FOLLOW_17_in_jsonObject214);
									pushFollow(FOLLOW_member_in_jsonObject217);
									member18 = member();
									state._fsp--;

									adaptor.addChild(root_0, member18.getTree());

								}
									break;

								default:
									break loop3;
							}
						}

					}
						break;

				}

				char_literal19 = (Token) match(input, 20, FOLLOW_20_in_jsonObject223);
				char_literal19_tree = adaptor.create(char_literal19);
				adaptor.addChild(root_0, char_literal19_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re)
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
	// D:\\Documents\\grammars\\Json.g:40:1: jsonArray : ARRAY ^ ( jsonValue ( ',' ! jsonValue )* )? ']' ;
	public final JsonParser.jsonArray_return jsonArray()
	{
		JsonParser.jsonArray_return retval = new JsonParser.jsonArray_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ARRAY20 = null;
		Token char_literal22 = null;
		Token char_literal24 = null;
		ParserRuleReturnScope jsonValue21 = null;
		ParserRuleReturnScope jsonValue23 = null;

		Object ARRAY20_tree = null;
		Object char_literal22_tree = null;
		Object char_literal24_tree = null;

		try
		{
			// D:\\Documents\\grammars\\Json.g:41:9: ( ARRAY ^ ( jsonValue ( ',' ! jsonValue )* )? ']' )
			// D:\\Documents\\grammars\\Json.g:41:11: ARRAY ^ ( jsonValue ( ',' ! jsonValue )* )? ']'
			{
				root_0 = adaptor.nil();

				ARRAY20 = (Token) match(input, ARRAY, FOLLOW_ARRAY_in_jsonArray241);
				ARRAY20_tree = adaptor.create(ARRAY20);
				root_0 = adaptor.becomeRoot(ARRAY20_tree, root_0);

				// D:\\Documents\\grammars\\Json.g:41:18: ( jsonValue ( ',' ! jsonValue )* )?
				int alt6 = 2;
				int LA6_0 = input.LA(1);
				if ((LA6_0 == ARRAY || LA6_0 == FALSE || (LA6_0 >= NULL && LA6_0 <= TRUE)))
				{
					alt6 = 1;
				}
				switch (alt6)
				{
					case 1:
					// D:\\Documents\\grammars\\Json.g:41:19: jsonValue ( ',' ! jsonValue )*
					{
						pushFollow(FOLLOW_jsonValue_in_jsonArray245);
						jsonValue21 = jsonValue();
						state._fsp--;

						adaptor.addChild(root_0, jsonValue21.getTree());

						// D:\\Documents\\grammars\\Json.g:41:29: ( ',' ! jsonValue )*
						loop5: while (true)
						{
							int alt5 = 2;
							int LA5_0 = input.LA(1);
							if ((LA5_0 == 17))
							{
								alt5 = 1;
							}

							switch (alt5)
							{
								case 1:
								// D:\\Documents\\grammars\\Json.g:41:30: ',' ! jsonValue
								{
									char_literal22 = (Token) match(input, 17, FOLLOW_17_in_jsonArray248);
									pushFollow(FOLLOW_jsonValue_in_jsonArray251);
									jsonValue23 = jsonValue();
									state._fsp--;

									adaptor.addChild(root_0, jsonValue23.getTree());

								}
									break;

								default:
									break loop5;
							}
						}

					}
						break;

				}

				char_literal24 = (Token) match(input, 19, FOLLOW_19_in_jsonArray257);
				char_literal24_tree = adaptor.create(char_literal24);
				adaptor.addChild(root_0, char_literal24_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re)
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

	public static final BitSet FOLLOW_jsonObject_in_jsonText32 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonArray_in_jsonText44 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FALSE_in_jsonValue61 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TRUE_in_jsonValue73 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NULL_in_jsonValue85 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonObject_in_jsonValue97 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonArray_in_jsonValue109 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonNumber_in_jsonValue121 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_jsonString_in_jsonValue133 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_NUMBER_in_jsonNumber150 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_STRING_in_jsonString167 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_STRING_in_member184 = new BitSet(new long[] { 0x0000000000040000L });
	public static final BitSet FOLLOW_18_in_member187 = new BitSet(new long[] { 0x0000000000007C90L });
	public static final BitSet FOLLOW_jsonValue_in_member190 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_OBJECT_in_jsonObject207 = new BitSet(new long[] { 0x0000000000102000L });
	public static final BitSet FOLLOW_member_in_jsonObject211 = new BitSet(new long[] { 0x0000000000120000L });
	public static final BitSet FOLLOW_17_in_jsonObject214 = new BitSet(new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_member_in_jsonObject217 = new BitSet(new long[] { 0x0000000000120000L });
	public static final BitSet FOLLOW_20_in_jsonObject223 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ARRAY_in_jsonArray241 = new BitSet(new long[] { 0x0000000000087C90L });
	public static final BitSet FOLLOW_jsonValue_in_jsonArray245 = new BitSet(new long[] { 0x00000000000A0000L });
	public static final BitSet FOLLOW_17_in_jsonArray248 = new BitSet(new long[] { 0x0000000000007C90L });
	public static final BitSet FOLLOW_jsonValue_in_jsonArray251 = new BitSet(new long[] { 0x00000000000A0000L });
	public static final BitSet FOLLOW_19_in_jsonArray257 = new BitSet(new long[] { 0x0000000000000002L });
}
