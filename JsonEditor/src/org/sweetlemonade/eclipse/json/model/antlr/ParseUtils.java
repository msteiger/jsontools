package org.sweetlemonade.eclipse.json.model.antlr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.jface.text.IDocument;
import org.sweetlemonade.eclipse.json.model.JsonArray;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.model.JsonObject;
import org.sweetlemonade.eclipse.json.model.JsonObject.Key;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive.PrimitiveType;

/**
 * 20 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class ParseUtils
{
	public static class ParseError
	{
		public RecognitionException error;
		public String text;
		public int line;
		public int start = -1;
		public int stop = -1;
	}

	public static class ParseResult
	{
		public Object tree;
		public Collection<ParseError> errors;
	}

	private static ParseError fillError(RecognitionException e, BaseRecognizer recognizer, IDocument document)
	{
		final ParseError error = new ParseError();

		error.error = e;
		error.line = e.line;

		final CommonToken token = (CommonToken) e.token;

		if (token != null)
		{
			error.start = token.getStartIndex();
			error.stop = token.getStopIndex();

			if (error.stop == error.start)
			{
				error.stop++;
			}
		}
		else
		{
			error.start = e.index;
		}

		error.text = recognizer.getErrorMessage(e, recognizer.getTokenNames());

		return error;
	}

	public static ParseResult parse(final IDocument document) throws IllegalParseStateException
	{
		final ParseResult result = new ParseResult();

		final ArrayList<ParseError> errors = new ArrayList<>();

		result.errors = errors;

		final JsonLexer lexer = new JsonLexer(new DocumentCharStream(document))
		{
			@Override
			public void reportError(RecognitionException e)
			{
				super.reportError(e);

				errors.add(fillError(e, this, document));
			}
		};
		final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		final JsonParser parser = new JsonParser(tokenStream)
		{
			@Override
			public void reportError(RecognitionException e)
			{
				super.reportError(e);

				errors.add(fillError(e, this, document));
			}
		};

		result.tree = parser.jsonText().getTree();

		return result;
	}

	public static JsonElement tree(Object t)
	{
		return element((CommonTree) t, null);
	}

	private static JsonElement element(CommonTree tree, JsonElement parent)
	{
		if (tree instanceof CommonErrorNode)
		{
			return null;
		}

		final Token token = tree.getToken();

		if (token == null)
		{
			tree.getClass();
		}

		final int type = token.getType();
		String text = token.getText();
		PrimitiveType type2 = null;

		switch (type)
		{
			case JsonParser.OBJECT:
				return object(tree, parent);

			case JsonParser.ARRAY:
				return array(tree, parent);

			case JsonParser.FALSE:
			case JsonParser.TRUE:
				type2 = PrimitiveType.BOOLEAN;
				break;

			case JsonParser.NULL:
				type2 = PrimitiveType.NULL;
				break;

			case JsonParser.NUMBER:
				type2 = PrimitiveType.NUMBER;
				break;

			case JsonParser.STRING:
				text = dequote(text);
				type2 = PrimitiveType.STRING;
				break;

			default:
				return null;
		}

		return fill(new JsonPrimitive(parent, text, type2), tree);
	}

	private static String dequote(String text)
	{
		return text.substring(0, text.length());
	}

	private static JsonArray array(CommonTree t, JsonElement parent)
	{
		final JsonArray array = fill(new JsonArray(parent), t);

		final List<? extends Object> children = t.getChildren();

		for (final Object object : children)
		{
			final CommonTree child = (CommonTree) object;

			final JsonElement element = element(child, array);

			if (element == null)
			{
				continue;
			}

			array.add(element);
		}

		return array;
	}

	public static JsonObject object(CommonTree t, JsonElement parent)
	{
		final JsonObject object = fill(new JsonObject(parent), t);

		final List<? extends Object> children = t.getChildren();

		for (final Object object2 : children)
		{
			final CommonTree child = (CommonTree) object2;

			if (child.getType() != JsonParser.STRING)
			{
				continue;
			}

			final CommonToken token = (CommonToken) child.getToken();
			String text = token.getText();
			text = dequote(text);

			final Key key = new Key(text);
			key.setStart(token.getStartIndex());
			key.setStop(token.getStopIndex() + 1);
			key.setLine(token.getLine());

			final JsonElement element = element((CommonTree) child.getChild(0), object);

			if (element == null)
			{
				continue;
			}

			object.put(key, element);
		}

		return object;
	}

	private static <T extends JsonElement> T fill(T element, CommonTree tree)
	{
		final CommonToken startToken = (CommonToken) tree.getToken();
		CommonToken lastToken = startToken;

		final int childCount = tree.getChildCount();

		if (childCount > 0)
		{
			lastToken = (CommonToken) ((CommonTree) tree.getChild(childCount - 1)).getToken();
		}

		element.setStart(startToken.getStartIndex());
		element.setEnd(lastToken.getStopIndex() + 1);
		element.setStartLine(startToken.getLine());
		element.setEndLine(lastToken.getLine());

		return element;
	}
}
/*if (e instanceof NoViableAltException)
{
	NoViableAltException exception = (NoViableAltException) e;

	exception.getClass();
	//token
}
else if (e instanceof MismatchedTreeNodeException)
{
	MismatchedTreeNodeException exception = (MismatchedTreeNodeException) e;

	exception.getClass();
}
else if (e instanceof MissingTokenException)
{
	MissingTokenException exception = (MissingTokenException) e;

	exception.getClass();
	//token
}
else if (e instanceof UnwantedTokenException)
{
	UnwantedTokenException exception = (UnwantedTokenException) e;

	exception.getClass();
	//token
}
else if (e instanceof MismatchedTokenException)
{
	MismatchedTokenException exception = (MismatchedTokenException) e;

	exception.getClass();
	//token
}
else if (e instanceof MismatchedNotSetException)
{
	MismatchedNotSetException exception = (MismatchedNotSetException) e;

	exception.getClass();
	//token
}
else if (e instanceof MismatchedSetException)
{
	MismatchedSetException exception = (MismatchedSetException) e;

	exception.getClass();
	//token
}
else if (e instanceof MismatchedRangeException)
{
	MismatchedRangeException exception = (MismatchedRangeException) e;

	exception.getClass();
}
else if (e instanceof FailedPredicateException)
{
	FailedPredicateException exception = (FailedPredicateException) e;

	exception.getClass();
}
else if (e instanceof EarlyExitException)
{
	EarlyExitException exception = (EarlyExitException) e;

	exception.getClass();
	//token
}
*/