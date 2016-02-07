package org.sweetlemonade.eclipse.json.model;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonPrimitive extends JsonElement
{
    public enum PrimitiveType
    {
        NULL,
        STRING,
        NUMBER,
        BOOLEAN;
    }

    private final String mValue;
    private final PrimitiveType mType;

    public JsonPrimitive(JsonElement parent, String value, PrimitiveType type)
    {
        super(parent);

        mValue = value;
        mType = type;
    }

    public String getValue()
    {
        return mValue;
    }

    public PrimitiveType getType()
    {
        return mType;
    }

    @Override
    public String toString()
    {
        return mValue;
    }

    @Override
    public boolean hasChilds()
    {
        return false;
    }
}
