package org.sweetlemonade.eclipse.json.preference;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.sweetlemonade.eclipse.json.preference.colors.JavaColorPrefsCopier;

/**
 * Feb 25, 2015
 *
 * @author denis.mirochnik
 */
public class JavaPreferenceStore implements IPreferenceStore
{
    private final IPreferencesService mService;

    public JavaPreferenceStore()
    {
        mService = Platform.getPreferencesService();
    }

    @Override
    public void addPropertyChangeListener(IPropertyChangeListener listener)
    {
    }

    @Override
    public boolean contains(String name)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void firePropertyChangeEvent(String name, Object oldValue, Object newValue)
    {
    }

    @Override
    public boolean getDefaultBoolean(String name)
    {
        return false;
    }

    @Override
    public double getDefaultDouble(String name)
    {
        return 0;
    }

    @Override
    public float getDefaultFloat(String name)
    {
        return 0;
    }

    @Override
    public int getDefaultInt(String name)
    {
        return 0;
    }

    @Override
    public long getDefaultLong(String name)
    {
        return 0;
    }

    @Override
    public String getDefaultString(String name)
    {
        return null;
    }

    @Override
    public boolean getBoolean(String name)
    {
        return mService.getBoolean(JavaColorPrefsCopier.JDT_UI_ID, name, false, null);
    }

    @Override
    public double getDouble(String name)
    {
        return mService.getDouble(JavaColorPrefsCopier.JDT_UI_ID, name, 0.0, null);
    }

    @Override
    public float getFloat(String name)
    {
        return mService.getFloat(JavaColorPrefsCopier.JDT_UI_ID, name, 0.0f, null);
    }

    @Override
    public int getInt(String name)
    {
        return mService.getInt(JavaColorPrefsCopier.JDT_UI_ID, name, 0, null);
    }

    @Override
    public long getLong(String name)
    {
        return mService.getLong(JavaColorPrefsCopier.JDT_UI_ID, name, 0L, null);
    }

    @Override
    public String getString(String name)
    {
        return mService.getString(JavaColorPrefsCopier.JDT_UI_ID, name, "", null);
    }

    @Override
    public boolean isDefault(String name)
    {
        return false;
    }

    @Override
    public boolean needsSaving()
    {
        return false;
    }

    @Override
    public void putValue(String name, String value)
    {
    }

    @Override
    public void removePropertyChangeListener(IPropertyChangeListener listener)
    {
    }

    @Override
    public void setDefault(String name, double value)
    {
    }

    @Override
    public void setDefault(String name, float value)
    {
    }

    @Override
    public void setDefault(String name, int value)
    {
    }

    @Override
    public void setDefault(String name, long value)
    {
    }

    @Override
    public void setDefault(String name, String defaultObject)
    {
    }

    @Override
    public void setDefault(String name, boolean value)
    {
    }

    @Override
    public void setToDefault(String name)
    {
    }

    @Override
    public void setValue(String name, double value)
    {
    }

    @Override
    public void setValue(String name, float value)
    {
    }

    @Override
    public void setValue(String name, int value)
    {
    }

    @Override
    public void setValue(String name, long value)
    {
    }

    @Override
    public void setValue(String name, String value)
    {
    }

    @Override
    public void setValue(String name, boolean value)
    {
    }

}
