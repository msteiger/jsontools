package org.sweetlemonade.eclipse.json.model;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.sweetlemonade.eclipse.json.model.JsonObject.Key;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public abstract class JsonElement
{
    private final JsonElement mParent;
    private int mStart;
    private int mEnd;
    private int mStartLine;
    private int mEndLine;

    public JsonElement(JsonElement parent)
    {
        mParent = parent;
    }

    public boolean isArray()
    {
        return this instanceof JsonArray;
    }

    public boolean isObject()
    {
        return this instanceof JsonObject;
    }

    public boolean isPrimitive()
    {
        return this instanceof JsonPrimitive;
    }

    public JsonArray asArray()
    {
        if (isArray())
        {
            return (JsonArray) this;
        }

        throw new UnsupportedOperationException();
    }

    public JsonObject asObject()
    {
        if (isObject())
        {
            return (JsonObject) this;
        }

        throw new UnsupportedOperationException();
    }

    public JsonPrimitive asPrimitive()
    {
        if (isPrimitive())
        {
            return (JsonPrimitive) this;
        }

        throw new UnsupportedOperationException();
    }

    public abstract boolean hasChilds();

    public Collection<JsonElement> getChilds()
    {
        if (isArray())
        {
            return asArray();
        }
        else if (isObject())
        {
            return asObject().values();
        }

        throw new UnsupportedOperationException();
    }

    public boolean hasParent()
    {
        return mParent != null;
    }

    public JsonElement getParent()
    {
        return mParent;
    }

    public void setStart(int start)
    {
        mStart = start;
    }

    public void setEnd(int end)
    {
        mEnd = end;
    }

    public int getStart()
    {
        return mStart;
    }

    public int getEnd()
    {
        return mEnd;
    }

    public int getLength()
    {
        return mEnd - mStart;
    }

    public void setStartLine(int startLine)
    {
        mStartLine = startLine;
    }

    public void setEndLine(int endLine)
    {
        mEndLine = endLine;
    }

    public int getEndLine()
    {
        return mEndLine;
    }

    public int getStartLine()
    {
        return mStartLine;
    }

    public int getIndex()
    {
        final JsonElement parent = getParent();

        if (parent == null || !parent.isArray())
        {
            return -1;
        }

        return parent.asArray().indexOf(this);
    }

    public String getKey()
    {
        final JsonElement parent = getParent();

        if (parent == null || !parent.isObject())
        {
            return null;
        }

        final JsonObject object = parent.asObject();

        Set<Entry<Key, JsonElement>> entrySet = object.entrySet();

        for (Entry<Key, JsonElement> entry : entrySet)
        {
            if (entry.getValue() == this)
            {
                return entry.getKey().getValue();
            }
        }

        return null;
    }
}
