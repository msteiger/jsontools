package org.bimeg.eclipse.json.preference;

import org.bimeg.eclipse.json.ColorManager;
import org.bimeg.eclipse.json.JsonPlugin;
import org.bimeg.eclipse.json.preference.JsonPreferencesInitializer.ColorType;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonPreferences
{
	private final IPreferenceStore mStore;
	private final ColorManager mManager;

	public JsonPreferences()
	{
		mStore = JsonPlugin.getDefault().getPreferenceStore();
		mManager = JsonPlugin.getColorManager();
	}

	public RGB getRgb(ColorType type)
	{
		return type.getColor(mStore);
	}

	public Color getColor(ColorType type)
	{
		return mManager.getColor(getRgb(type));
	}
}
