package org.sweetlemonade.eclipse.json.model;

import java.io.IOException;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.sweetlemonade.eclipse.json.model.JsonPrimitive.PrimitiveType;

/**
 * 10 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonParserMy
{
    private final IDocument mDoc;
    private int mPosition;

    public JsonParserMy(IDocument document)
    {
        mDoc = document;
    }

    public JsonElement parse()
    {
        try
        {
            mPosition = 0;

            final int read = read(true);

            if (read == '{')
            {
                return readObject(null);
            }
            else if (read == '[')
            {
                return readArray(null);
            }

            return null;
        }
        catch (final IOException e)
        {
        }

        return null;
    }

    private int getPos()
    {
        return mPosition;
    }

    private int getPrevPos()
    {
        return mPosition - 1;
    }

    private int getLine() throws IOException
    {
        try
        {
            return mDoc.getLineOfOffset(mPosition);
        }
        catch (final BadLocationException e)
        {
            throw new IOException();
        }
    }

    private int getPrevLine() throws IOException
    {
        try
        {
            return mDoc.getLineOfOffset(mPosition - 1);
        }
        catch (final BadLocationException e)
        {
            throw new IOException();
        }
    }

    private JsonArray readArray(JsonElement parent) throws IOException
    {
        //no opening [

        final JsonArray array = new JsonArray(parent);
        array.setStart(getPrevPos());
        array.setStartLine(getPrevLine());

        do
        {
            int read = read(true);

            if (read == ']')
            {
                array.setEnd(getPos());
                array.setEndLine(getLine());

                return array;
            }

            unread();
            final JsonElement value = readValue(array);

            read = read(true);

            if (read != ',' && read != ']')
            {
                throw new IOException();
            }

            if (read == ']')
            {
                unread();
            }

            array.add(value);
        }
        while (true);
    }

    private JsonObject readObject(JsonElement parent) throws IOException
    {
        //no opening {

        final JsonObject object = new JsonObject(parent);

        object.setStart(getPrevPos());
        object.setStartLine(getPrevLine());

        int read;

        do
        {
            read = read(true);

            if (read == '}')
            {
                object.setEnd(getPos());
                object.setEndLine(getLine());

                return object;
            }

            final String key = readStringValue();

            read = read(true);

            if (read != ':')
            {
                throw new IOException();
            }

            final JsonElement value = readValue(object);

            read = read(true);

            if (read != ',' && read != '}')
            {
                throw new IOException();
            }

            if (read == '}')
            {
                unread();
            }

            object.put(key, value);
        }
        while (true);
    }

    private JsonPrimitive readPrimitive(JsonElement parent) throws IOException
    {
        final int read = read(true);
        final int start = getPrevPos();
        final int startLine = getPrevLine();

        if (read == '\"')
        {
            final String until = readStringValue();

            readPrimitiveValue();

            return makePrimitive(parent, start, startLine, until, PrimitiveType.STRING);
        }
        else
        {
            unread();

            final String until = readPrimitiveValue();

            if (JsonGrammar.Keyword.NULL.isEquals(until))
            {
                return makePrimitive(parent, start, startLine, null, PrimitiveType.NULL);
            }
            else if (JsonGrammar.Keyword.TRUE.isEquals(until) || JsonGrammar.Keyword.FALSE.isEquals(until))
            {
                return makePrimitive(parent, start, startLine, until, PrimitiveType.BOOLEAN);
            }
            else
            {
                return makePrimitive(parent, start, startLine, until, PrimitiveType.NUMBER);
            }
        }
    }

    private JsonPrimitive makePrimitive(JsonElement parent, int start, int startLine, String until, PrimitiveType type) throws IOException
    {
        final JsonPrimitive primitive = new JsonPrimitive(parent, until, type);

        primitive.setStart(start);
        primitive.setStartLine(startLine);
        primitive.setEnd(getPos());
        primitive.setEndLine(getLine());

        return primitive;
    }

    private JsonElement readValue(JsonElement parent) throws IOException
    {
        final int read = read(true);

        if (read == '[')
        {
            return readArray(parent);
        }
        else if (read == '{')
        {
            return readObject(parent);
        }

        unread();

        return readPrimitive(parent);
    }

    private boolean contains(int ch, int... endChar)
    {
        for (int i = 0; i < endChar.length; i++)
        {
            if (ch == endChar[i])
            {
                return true;
            }
        }

        return false;
    }

    private String readPrimitiveValue() throws IOException
    {
        final StringBuilder builder = new StringBuilder();

        int pos = getPos();
        int read = read(true);

        while (!contains(read, ',', '}', ']'))
        {
            builder.append((char) read);
            pos = getPos();
            read = read(true);
        }

        unreadTo(pos);

        return builder.toString();
    }

    private String readStringValue() throws IOException
    {
        //no opening "

        final StringBuilder builder = new StringBuilder();

        int read = read();
        boolean escapeStart = false;

        while (read != '\"' || escapeStart)
        {
            if (read == '\\')
            {
                escapeStart = true;
            }
            else
            {
                escapeStart = false;
            }

            builder.append((char) read);
            read = read();
        }

        return builder.toString();
    }

    private void unreadTo(int pos) throws IOException
    {
        mPosition = pos;
    }

    private void unread() throws IOException
    {
        mPosition--;

        if (mPosition < 0)
        {
            throw new IOException();
        }
    }

    private char read() throws IOException
    {
        return read(false);
    }

    private char read(boolean ignoreWhitespace) throws IOException
    {
        char read;

        do
        {
            try
            {
                read = mDoc.getChar(mPosition++);
            }
            catch (final BadLocationException e)
            {
                throw new IOException();
            }
        }
        while (JsonGrammar.isWhitespace(read) && ignoreWhitespace);

        return read;
    }
}
