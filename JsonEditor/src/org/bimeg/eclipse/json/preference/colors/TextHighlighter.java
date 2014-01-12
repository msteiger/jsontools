package org.bimeg.eclipse.json.preference.colors;

import java.util.ArrayList;
import java.util.List;

import org.bimeg.eclipse.json.ColorManager;
import org.bimeg.eclipse.json.JsonPlugin;
import org.bimeg.eclipse.json.preference.JsonPreferencesInitializer.ColorType;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.graphics.Color;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class TextHighlighter
{
	private static final String TEXT =

	/*@formatter:off*/
	
	"\"key\" : \"string\",\n" +
	"\"key\" : -12.0e+13,\n" +
	"\"key\" : true,\n" +
	"\"key\" : null,\n" +
	"\"key\" : {\"key\" : \"string\"},\n" +
	"\"key\" : [1, 2, 3]";
	
	/*@formatter:on*/

	private final TextViewer mViewer;
	private final IPreferenceStore mStore;
	private final ColorManager mManager;

	public TextHighlighter(TextViewer viewer, IPreferenceStore store)
	{
		mStore = store;
		mManager = JsonPlugin.getColorManager();

		mViewer = viewer;
		mViewer.setInput(new Document(TEXT));
	}

	@SuppressWarnings("incomplete-switch")
	public void update()
	{
		final ColorType[] types = ColorType.values();

		mViewer.setTextColor(mManager.getColor(ColorType.DEFAULT.getColor(mStore)));

		for (final ColorType type : types)
		{
			final Color color = mManager.getColor(type.getColor(mStore));
			List<Integer> inds = null;

			switch (type)
			{
				case KEYS:
					inds = calcInds("\"key\"");
					break;

				case ARRAY_BRACKETS:
					inds = calcInds("[", "]");
					break;

				case BOOLEANS:
					inds = calcInds("true", "false");
					break;

				case NULL:
					inds = calcInds("null");
					break;

				case NUMBERS:
					inds = calcInds("-12.0e+13", "1", "2", "3");
					break;

				case OBJECT_BRACKETS:
					inds = calcInds("{", "}");
					break;

				case STRINGS:
					inds = calcInds("\"string\"");
					break;
			}

			if (inds == null)
			{
				continue;
			}

			for (int i = 0; i < inds.size(); i += 2)
			{
				mViewer.setTextColor(color, inds.get(i), inds.get(i + 1), true);
			}
		}
	}

	private List<Integer> calcInds(String... str)
	{
		final List<Integer> list = new ArrayList<>();

		for (final String string : str)
		{
			int start = 0;
			int of;

			while ((of = TEXT.indexOf(string, start)) != -1)
			{
				list.add(of);
				list.add(string.length());
				start = of + string.length();
			}
		}

		return list;
	}
}
