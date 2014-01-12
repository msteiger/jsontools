package org.bimeg.eclipse.json.outline;

import org.bimeg.eclipse.json.model.JsonElement;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Text;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class QuickOutlineFilter extends ViewerFilter
{
	private final Text mFilter;

	public QuickOutlineFilter(Text filter)
	{
		mFilter = filter;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element)
	{
		final String text = mFilter.getText().trim().toLowerCase();

		if (text.length() == 0)
		{
			return true;
		}

		return Checker.check(text, (JsonElement) element);
	}
}
