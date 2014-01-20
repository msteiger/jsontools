package org.sweetlemonade.eclipse.json.model.antlr;

import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.jface.text.IDocument;
import org.sweetlemonade.eclipse.json.model.JsonArray;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.model.JsonObject;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive.PrimitiveType;

/**
 * 20 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class ParseUtils
{
	public static Object parse(IDocument document) throws IllegalParseStateException
	{
		JsonLexer lexer = new JsonLexer(new DocumentCharStream(document));
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		JsonParser parser = new JsonParser(tokenStream);

		return parser.jsonText().getTree();
	}

	public static JsonElement tree(Object t)
	{
		return element((CommonTree) t, null);
	}

	private static JsonElement element(CommonTree tree, JsonElement parent)
	{
		if (tree instanceof CommonErrorNode)
		{
			CommonErrorNode error = (CommonErrorNode) tree;

			return null;
		}

		Token token = tree.getToken();

		int type = token.getType();
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
		JsonArray array = fill(new JsonArray(parent), t);

		List<? extends Object> children = t.getChildren();

		for (Object object : children)
		{
			CommonTree child = (CommonTree) object;

			JsonElement element = element(child, array);

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
		JsonObject object = fill(new JsonObject(parent), t);

		List<? extends Object> children = t.getChildren();

		for (Object object2 : children)
		{
			CommonTree child = (CommonTree) object2;

			if (child.getType() != JsonParser.STRING)
			{
				continue;
			}

			String text = child.getToken().getText();
			text = dequote(text);

			JsonElement element = element((CommonTree) child.getChild(0), object);

			if (element == null)
			{
				continue;
			}

			object.put(text, element);
		}

		return object;
	}

	private static <T extends JsonElement> T fill(T element, CommonTree tree)
	{
		CommonToken startToken = (CommonToken) tree.getToken();
		CommonToken lastToken = startToken;

		int childCount = tree.getChildCount();

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
