package org.sweetlemonade.eclipse.json.model.antlr;

import java.util.LinkedList;

import org.antlr.runtime.CharStream;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class DocumentCharStream implements CharStream
{
	private IDocument mDocument;
	private int mPos = 0;
	private LinkedList<Integer> mMarks = new LinkedList<>();

	public DocumentCharStream(IDocument document)
	{
		mDocument = document;
	}

	@Override
	public int LA(int i)
	{
		try
		{
			if (i == 0)
			{
				return mDocument.getChar(mPos);
			}

			if (i < 0)
			{
				i++;

				if ((mPos + i) - 1 < 0)
				{
					return -1;
				}
			}

			if ((mPos + i) - 1 >= mDocument.getLength())
			{
				return -1;
			}
			else
			{
				return mDocument.getChar((mPos + i) - 1);
			}
		}
		catch (BadLocationException e)
		{
			return -1;
		}
	}

	@Override
	public void consume()
	{
		if (mPos < mDocument.getLength())
		{
			mPos++;
		}
	}

	@Override
	public String getSourceName()
	{
		return null;
	}

	@Override
	public int index()
	{
		return mPos;
	}

	@Override
	public int mark()
	{
		int index = index();

		mMarks.addLast(index);

		return mMarks.size() - 1;
	}

	@Override
	public void release(int i)
	{
		int size = mMarks.size();

		for (int j = size - 1; j > i; j++)
		{
			mMarks.removeLast();
		}
	}

	@Override
	public void rewind()
	{
		seek(mMarks.removeLast());
	}

	@Override
	public void rewind(int i)
	{
		release(i);
		rewind();
	}

	@Override
	public void seek(int i)
	{
		mPos = i;

		int length = mDocument.getLength();

		if (mPos >= length)
		{
			mPos = length - 1;
		}
	}

	@Override
	public int size()
	{
		return mDocument.getLength();
	}

	@Override
	public int LT(int i)
	{
		return LA(i);
	}

	@Override
	public int getCharPositionInLine()
	{
		try
		{
			return mPos - mDocument.getLineInformationOfOffset(mPos).getOffset();
		}
		catch (BadLocationException e)
		{
			throw new IllegalParseStateException();
		}
	}

	@Override
	public int getLine()
	{
		try
		{
			return mDocument.getLineOfOffset(mPos);
		}
		catch (BadLocationException e)
		{
			throw new IllegalParseStateException();
		}
	}

	@Override
	public void setCharPositionInLine(int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLine(int i)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public String substring(int start, int stop)
	{
		try
		{
			return mDocument.get(start, stop - start + 1);
		}
		catch (BadLocationException e)
		{
			throw new IllegalParseStateException();
		}
	}
}