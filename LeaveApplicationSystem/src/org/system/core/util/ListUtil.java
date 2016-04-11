package org.system.core.util;

import java.util.Iterator;
import java.util.List;

public class ListUtil {

	public static <T> void removeTargetObjectFromList(List<T> list, T targetObject) {
		for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
			if (targetObject.equals(iterator.next())) {
				// Remove the current element from the iterator and the list.
				iterator.remove();
			}
		}
	}
}
