package org.sweetlemonade.eclipse.json.preference;

import java.util.HashMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class PseudoPreferenceStore implements IPreferenceStore
{
    private final HashMap<String, Object> mMap = new HashMap<>();

    @Override
    public void addPropertyChangeListener(IPropertyChangeListener listener)
    {
    }

    @Override
    public boolean contains(String name)
    {
        return mMap.containsKey(name);
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
        if (!mMap.containsKey(name))
        {
            return false;
        }

        return (Boolean) mMap.get(name);
    }

    @Override
    public double getDouble(String name)
    {
        if (!mMap.containsKey(name))
        {
            return 0.0;
        }

        return (Double) mMap.get(name);
    }

    @Override
    public float getFloat(String name)
    {
        if (!mMap.containsKey(name))
        {
            return 0.0f;
        }

        return (Float) mMap.get(name);
    }

    @Override
    public int getInt(String name)
    {
        if (!mMap.containsKey(name))
        {
            return 0;
        }

        return (Integer) mMap.get(name);
    }

    @Override
    public long getLong(String name)
    {
        if (!mMap.containsKey(name))
        {
            return 0L;
        }

        return (Long) mMap.get(name);
    }

    @Override
    public String getString(String name)
    {
        if (!mMap.containsKey(name))
        {
            return "";
        }

        return (String) mMap.get(name);
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
        mMap.put(name, value);
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
        mMap.put(name, value);
    }

    @Override
    public void setValue(String name, float value)
    {
        mMap.put(name, value);
    }

    @Override
    public void setValue(String name, int value)
    {
        mMap.put(name, value);
    }

    @Override
    public void setValue(String name, long value)
    {
        mMap.put(name, value);
    }

    @Override
    public void setValue(String name, String value)
    {
        mMap.put(name, value);
    }

    @Override
    public void setValue(String name, boolean value)
    {
        mMap.put(name, value);
    }

}
