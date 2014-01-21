package org.sweetlemonade.eclipse.json.preference;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.sweetlemonade.eclipse.json.ColorManager;
import org.sweetlemonade.eclipse.json.JsonPlugin;
import org.sweetlemonade.eclipse.json.preference.JsonPreferencesInitializer.TokenType;

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

	public RGB getRgb(TokenType type)
	{
		return type.getColor(mStore);
	}

	public Color getColor(TokenType type)
	{
		return mManager.getColor(getRgb(type));
	}
}
