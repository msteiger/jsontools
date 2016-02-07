package org.sweetlemonade.eclipse.json.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 09 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonArray extends JsonElement implements List<JsonElement>
{
    private final ArrayList<JsonElement> mList = new ArrayList<>();

    @Override
    public boolean hasChilds()
    {
        return !isEmpty();
    }

    public JsonArray(JsonElement parent)
    {
        super(parent);
    }

    @Override
    public void add(int index, JsonElement element)
    {
        mList.add(index, element);
    }

    @Override
    public boolean add(JsonElement e)
    {
        return mList.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends JsonElement> c)
    {
        return mList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends JsonElement> c)
    {
        return mList.addAll(index, c);
    }

    @Override
    public void clear()
    {
        mList.clear();
    }

    @Override
    public boolean contains(Object o)
    {
        return mList.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> arg0)
    {
        return mList.containsAll(arg0);
    }

    public void ensureCapacity(int minCapacity)
    {
        mList.ensureCapacity(minCapacity);
    }

    @Override
    public boolean equals(Object arg0)
    {
        return mList.equals(arg0);
    }

    @Override
    public JsonElement get(int index)
    {
        return mList.get(index);
    }

    @Override
    public int hashCode()
    {
        return mList.hashCode();
    }

    @Override
    public int indexOf(Object o)
    {
        return mList.indexOf(o);
    }

    @Override
    public boolean isEmpty()
    {
        return mList.isEmpty();
    }

    @Override
    public Iterator<JsonElement> iterator()
    {
        return mList.iterator();
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return mList.lastIndexOf(o);
    }

    @Override
    public ListIterator<JsonElement> listIterator()
    {
        return mList.listIterator();
    }

    @Override
    public ListIterator<JsonElement> listIterator(int index)
    {
        return mList.listIterator(index);
    }

    @Override
    public JsonElement remove(int index)
    {
        return mList.remove(index);
    }

    @Override
    public boolean remove(Object o)
    {
        return mList.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return mList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return mList.retainAll(c);
    }

    @Override
    public JsonElement set(int index, JsonElement element)
    {
        return mList.set(index, element);
    }

    @Override
    public int size()
    {
        return mList.size();
    }

    @Override
    public List<JsonElement> subList(int fromIndex, int toIndex)
    {
        return mList.subList(fromIndex, toIndex);
    }

    @Override
    public String toString()
    {
        return mList.toString();
    }

    @Override
    public Object[] toArray()
    {
        return mList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return mList.toArray(a);
    }
}
