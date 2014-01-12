package org.bimeg.eclipse.json.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonObject extends JsonElement implements Map<String, JsonElement>
{
	private final HashMap<String, JsonElement> mMap = new LinkedHashMap<>();

	public JsonObject(JsonElement parent)
	{
		super(parent);
	}

	@Override
	public void clear()
	{
		mMap.clear();
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
	public Set<java.util.Map.Entry<String, JsonElement>> entrySet()
	{
		return mMap.entrySet();
	}

	@Override
	public boolean equals(Object arg0)
	{
		return mMap.equals(arg0);
	}

	@Override
	public JsonElement get(Object key)
	{
		return mMap.get(key);
	}

	@Override
	public int hashCode()
	{
		return mMap.hashCode();
	}

	@Override
	public boolean isEmpty()
	{
		return mMap.isEmpty();
	}

	@Override
	public Set<String> keySet()
	{
		return mMap.keySet();
	}

	@Override
	public JsonElement put(String key, JsonElement value)
	{
		return mMap.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends JsonElement> m)
	{
		mMap.putAll(m);
	}

	@Override
	public JsonElement remove(Object key)
	{
		return mMap.remove(key);
	}

	@Override
	public int size()
	{
		return mMap.size();
	}

	@Override
	public String toString()
	{
		return mMap.toString();
	}

	@Override
	public Collection<JsonElement> values()
	{
		return mMap.values();
	}

	@Override
	public boolean hasChilds()
	{
		return !isEmpty();
	}
}
