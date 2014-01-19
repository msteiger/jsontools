package org.sweetlemonade.eclipse.json.model.antlr;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
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
		JsonLexer lexer = new JsonLexer(new ANTLRStringStream(document.get()));
//		JsonLexer lexer = new JsonLexer(new DocumentCharStream(document));
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		JsonParser parser = new JsonParser(tokenStream);

		return parser.jsonText().getTree();
	}

	public static JsonElement tree(Object t)
	{
		return element((CommonTree) t, null);
	}

	private static JsonElement element(CommonTree t, JsonElement parent)
	{
		CommonTree tree = t;

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
		}

		if (type2 == null)
		{
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

			array.add(element(child, array));
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

			String text = child.getToken().getText();
			text = dequote(text);

			object.put(text, element((CommonTree) child.getChild(0), object));
		}

		return object;
	}

	private static <T extends JsonElement> T fill(T element, CommonTree tree)
	{
		element.setStart(tree.getTokenStartIndex());
		element.setEnd(tree.getTokenStopIndex());
//		element.setStart(tree.getTokenStartIndex());
//		element.setEnd(tree.getTokenStopIndex());
		element.setStartLine(tree.getLine());
		element.setEndLine(tree.getLine());

		return element;
	}
}
