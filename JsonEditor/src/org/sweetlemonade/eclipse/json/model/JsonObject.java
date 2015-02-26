package org.sweetlemonade.eclipse.json.model;

import java.util.Collection;
import java.util.LinkedHashMap;
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

	//FIXME do LinkedIdentityHashMap
	private final Map<Key, JsonElement> mMap = new LinkedHashMap<>();

	public JsonObject(JsonElement parent)
	{
		super(parent);
	}

	@Override
	public int size()
	{
		return mMap.size();
	}

	@Override
	public boolean isEmpty()
	{
		return mMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return mMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value)
	{
		return mMap.containsValue(value);
	}

	@Override
	public JsonElement get(Object key)
	{
		return mMap.get(key);
	}

	@Override
	public JsonElement put(Key key, JsonElement value)
	{
		return mMap.put(key, value);
	}

	@Override
	public JsonElement remove(Object key)
	{
		return mMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends Key, ? extends JsonElement> m)
	{
		mMap.putAll(m);
	}

	@Override
	public void clear()
	{
		mMap.clear();
	}

	@Override
	public Set<Key> keySet()
	{
		return mMap.keySet();
	}

	@Override
	public Collection<JsonElement> values()
	{
		return mMap.values();
	}

	@Override
	public Set<java.util.Map.Entry<Key, JsonElement>> entrySet()
	{
		return mMap.entrySet();
	}

	@Override
	public boolean equals(Object o)
	{
		return mMap.equals(o);
	}

	@Override
	public int hashCode()
	{
		return mMap.hashCode();
	}

	public JsonElement put(String key, JsonElement value)
	{
		return put(new Key(key), value);
	}

	@Override
	public String toString()
	{
		return mMap.toString();
	}

	@Override
	public boolean hasChilds()
	{
		return !isEmpty();
	}
}
