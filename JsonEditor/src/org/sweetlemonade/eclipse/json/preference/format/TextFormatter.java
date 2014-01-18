package org.sweetlemonade.eclipse.json.preference.format;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextViewer;
import org.sweetlemonade.eclipse.json.editor.JsonFormatter;
import org.sweetlemonade.eclipse.json.model.JsonArray;
import org.sweetlemonade.eclipse.json.model.JsonObject;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive.PrimitiveType;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class TextFormatter
{
	private final TextViewer mViewer;
	private final JsonObject mElement;
	private final IPreferenceStore mStore;

	public TextFormatter(TextViewer viewer, IPreferenceStore store)
	{
		mViewer = viewer;
		mStore = store;

		mElement = new JsonObject(null);
		mElement.put("k1", new JsonPrimitive(mElement, "val", PrimitiveType.STRING));

		JsonObject childObject = new JsonObject(mElement);
		childObject.put("k", new JsonPrimitive(childObject, "val", PrimitiveType.STRING));

		mElement.put("k2", childObject);

		final JsonArray childArray = new JsonArray(mElement);
		childArray.add(new JsonPrimitive(childArray, "11", PrimitiveType.NUMBER));
		childArray.add(new JsonPrimitive(childArray, "22", PrimitiveType.NUMBER));

		childObject = new JsonObject(childArray);
		childObject.put("k", new JsonPrimitive(childObject, "val", PrimitiveType.STRING));

		childArray.add(childObject);

		childArray.add(new JsonPrimitive(childArray, "44", PrimitiveType.NUMBER));

		mElement.put("k3", childArray);
	}

	public void update()
	{
		final IDocument document = mViewer.getDocument();
		final int length = document == null ? 50 : document.getLength();
		final String format = new JsonFormatter(mElement, mStore).format(length);

		if (document != null)
		{
			try
			{
				document.replace(0, length, format);
			}
			catch (final BadLocationException e)
			{
			}
		}
		else
		{
			mViewer.setDocument(new Document(format));
		}
	}
}
