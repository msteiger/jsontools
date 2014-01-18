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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;

		result = prime * result + ((mType == null) ? 0 : mType.hashCode());
		result = prime * result + ((mValue == null) ? 0 : mValue.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof JsonPrimitive))
		{
			return false;
		}

		final JsonPrimitive other = (JsonPrimitive) obj;

		if (mType != other.mType)
		{
			return false;
		}

		if (mValue == null)
		{
			if (other.mValue != null)
			{
				return false;
			}
		}
		else if (!mValue.equals(other.mValue))
		{
			return false;
		}

		return true;
	}

}
