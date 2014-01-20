package org.sweetlemonade.eclipse.json.editor;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.services.IDisposable;
import org.sweetlemonade.eclipse.json.SelectionsFinder;
import org.sweetlemonade.eclipse.json.model.JsonElement;
import org.sweetlemonade.eclipse.json.model.JsonObject;
import org.sweetlemonade.eclipse.json.model.JsonObject.Key;

/**
 * 11 янв. 2014 г.
 * 
 * @author denis.mirochnik
 */
public class JsonAnnotationer implements ISelectionListener, IDisposable
{
	private final ProjectionAnnotationModel mProjectionModel;
	private JsonElement mElement;
	private final JsonEditor mEditor;
	private final SelectionsFinder mFinder = new SelectionsFinder();
	private Map<JsonElement, ProjectionAnnotation> mAnnos;
	private final Map<ProjectionAnnotation, Boolean> mState = new IdentityHashMap<>();
	private ISourceViewer mViewer;

	public JsonAnnotationer(ProjectionAnnotationModel projectionModel, JsonEditor editor, ISourceViewer viewer)
	{
		mProjectionModel = projectionModel;
		mEditor = editor;
		mViewer = viewer;
		mEditor.getSite().getPage().addPostSelectionListener(this);
	}

	public void store()
	{
		if (mAnnos == null)
		{
			return;
		}

		final Collection<ProjectionAnnotation> values = mAnnos.values();

		mState.clear();

		for (final ProjectionAnnotation projectionAnnotation : values)
		{
			mState.put(projectionAnnotation, projectionAnnotation.isCollapsed());
		}
	}

	public void update(int start)
	{
		if (mElement != null)
		{
			JsonElement selected = mFinder.findSelectedScope(start, mElement);

			if (selected == null)
			{
				mViewer.setRangeIndicator(null);

				return;
			}

			if (selected.isPrimitive())
			{
				selected = selected.getParent();
			}

			mViewer.setRangeIndication(selected.getStart(), selected.getLength(), false);
		}
	}

	public void update(JsonElement element)
	{
		if (element != null)
		{
			final IdentityHashMap<JsonElement, ProjectionAnnotation> annos = new IdentityHashMap<>();
			final IdentityHashMap<ProjectionAnnotation, Position> target = new IdentityHashMap<>();

			collect(element, target, annos);

			if (mElement == null)
			{
				mProjectionModel.modifyAnnotations(null, target, null);
			}
			else
			{
				if (!mState.isEmpty())
				{
					final Collection<ProjectionAnnotation> values = mAnnos.values();

					for (final ProjectionAnnotation projectionAnnotation : values)
					{
						if (mState.get(projectionAnnotation))
						{
							projectionAnnotation.markCollapsed();
						}
						else
						{
							projectionAnnotation.markExpanded();
						}
					}

					mState.clear();
				}

				check(mElement, element, mAnnos, annos);

				mProjectionModel.modifyAnnotations(mAnnos.values().toArray(new ProjectionAnnotation[mAnnos.size()]), target, null);
			}

			mAnnos = annos;
			mElement = element;
		}
	}

	private boolean check(JsonElement elementWas, JsonElement elementNow, Map<JsonElement, ProjectionAnnotation> oldAnnos, Map<JsonElement, ProjectionAnnotation> newAnnos)
	{
		if (elementWas.isPrimitive() || elementNow.isPrimitive())
		{
			return false;
		}

		if (elementWas.getClass() != elementNow.getClass())
		{
			return false;
		}

		boolean result = false;

		if (oldAnnos.containsKey(elementWas))
		{
			result = true;

			final ProjectionAnnotation annotation = oldAnnos.get(elementWas);

			if (annotation.isCollapsed())
			{
				newAnnos.get(elementNow).markCollapsed();
			}
			else
			{
				newAnnos.get(elementNow).markExpanded();
			}
		}

		if (!elementWas.hasChilds())
		{
			return result;
		}

		if (elementWas.isArray())
		{
			final List<JsonElement> wasChilds = elementWas.asArray();
			final List<JsonElement> nowChilds = elementNow.asArray();

			final int len = Math.min(wasChilds.size(), nowChilds.size());

			for (int i = 0; i < len; i++)
			{
				final JsonElement was = wasChilds.get(i);
				final JsonElement now = nowChilds.get(i);

				check(was, now, oldAnnos, newAnnos);
			}
		}
		else if (elementWas.isObject())
		{
			final JsonObject wasObj = elementWas.asObject();
			final JsonObject nowObj = elementNow.asObject();

			final Collection<Key> wasSet = wasObj.keys();
			final Collection<Key> nowSet = nowObj.keys();

			outer: for (final Key wasKey : wasSet)
			{
				for (Key nowKey : nowSet)
				{
					if (nowKey.getValue().equals(wasKey.getValue()))
					{
						if (check(wasObj.get(wasKey), nowObj.get(nowKey), oldAnnos, newAnnos))
						{
							continue outer;
						}
					}
				}
			}
		}

		return result;
	}

	private void collect(JsonElement element, Map<ProjectionAnnotation, Position> target, IdentityHashMap<JsonElement, ProjectionAnnotation> annos)
	{
		if (element.isPrimitive())
		{
			return;
		}

		if (element.getParent() != null && element.getStartLine() != element.getEndLine())
		{
			final ProjectionAnnotation annotation = new ProjectionAnnotation();
			target.put(annotation, pos(element));
			annos.put(element, annotation);
		}

		final Collection<JsonElement> childs = element.getChilds();

		for (final JsonElement jsonElement : childs)
		{
			collect(jsonElement, target, annos);
		}
	}

	private Position pos(JsonElement element)
	{
		return new Position(element.getStart(), element.getLength());
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection)
	{
		if (part == mEditor && selection instanceof ITextSelection)
		{
			update(((ITextSelection) selection).getOffset());
		}
	}

	@Override
	public void dispose()
	{
		mEditor.getSite().getPage().removeSelectionListener(this);
	}
}
