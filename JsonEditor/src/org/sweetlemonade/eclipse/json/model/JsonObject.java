package org.sweetlemonade.eclipse.json.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.sweetlemonade.eclipse.json.model.JsonObject.Key;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonObject extends JsonElement implements Map<Key, JsonElement>
{
	public static class Key
	{
		private final String mValue;
		private int mStart = -1;
		private int mStop = -1;
		private int mLine;

		public Key(String value)
		{
			mValue = value;
		}

		public String getValue()
		{
			return mValue;
		}

		public void setLine(int line)
		{
			mLine = line;
		}

		public int getLine()
		{
			return mLine;
		}

		public void setStart(int start)
		{
			mStart = start;
		}

		public void setStop(int stop)
		{
			mStop = stop;
		}

		public int getStart()
		{
			return mStart;
		}

		public int getStop()
		{
			return mStop;
		}

		@Override
		public String toString()
		{
			return mValue;
		}
	}

	private final ArrayList<Key> mKeys = new ArrayList<>();
	private final ArrayList<JsonElement> mValues = new ArrayList<>();

	public JsonObject(JsonElement parent)
	{
		super(parent);
	}

	@Override
	public void clear()
	{
		mKeys.clear();
		mValues.clear();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return mKeys.contains(key);
	}

	@Override
	public boolean containsValue(Object value)
	{
		return mValues.contains(value);
	}

	@Override
	public Set<java.util.Map.Entry<Key, JsonElement>> entrySet()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public JsonElement get(Object key)
	{
		int indexOf = mKeys.indexOf(key);

		return indexOf == -1 ? null : mValues.get(indexOf);
	}

	@Override
	public boolean isEmpty()
	{
		return mKeys.isEmpty();
	}

	public Collection<Key> keys()
	{
		return Collections.unmodifiableList(mKeys);
	}

	@Override
	public Set<Key> keySet()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public JsonElement put(Key key, JsonElement value)
	{
		JsonElement was = get(key);

		mKeys.add(key);
		mValues.add(value);

		return was;
	}

	public JsonElement put(String key, JsonElement value)
	{
		return put(new Key(key), value);
	}

	@Override
	public void putAll(Map<? extends Key, ? extends JsonElement> m)
	{
		Set<? extends Key> keySet = m.keySet();

		for (Key key : keySet)
		{
			put(key, m.get(key));
		}
	}

	@Override
	public JsonElement remove(Object key)
	{
		int indexOf = mKeys.indexOf(key);

		if (indexOf == -1)
		{
			return null;
		}

		mKeys.remove(key);

		return mValues.remove(indexOf);
	}

	@Override
	public int size()
	{
		return mKeys.size();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append('[');

		for (int i = 0; i < mKeys.size(); i++)
		{
			if (i > 0)
			{
				builder.append(", ");
			}

			Key key = mKeys.get(i);
			JsonElement value = mValues.get(i);

			builder.append(key.getValue());
			builder.append('=');

			if (value == this)
			{
				builder.append("this object");
			}
			else
			{
				builder.append(value);
			}
		}

		builder.append(']');

		return builder.toString();
	}

	@Override
	public Collection<JsonElement> values()
	{
		return Collections.unmodifiableList(mValues);
	}

	@Override
	public boolean hasChilds()
	{
		return !isEmpty();
	}
}
