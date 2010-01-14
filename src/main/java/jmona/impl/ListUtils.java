/**
 * ListUtils.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
 * 
 * This file is part of jmona.
 * 
 * jmona is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * jmona is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jmona. If not, see <http://www.gnu.org/licenses/>.
 */
package jmona.impl;

import java.util.List;

import jmona.functional.Range;

/**
 * Utility class which provides static methods for operations on Lists.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public final class ListUtils {

  /**
   * Swap the element at the specified index between the two specified Lists.
   * 
   * @param list2
   *          A List.
   * @param list1
   *          Another List.
   * @param index
   *          The index of the element to swap.
   * @param <E>
   *          The type of element in the List to swap.
   * @param <L>
   *          The type of Lists to swap between.
   */
  public static <E, L extends List<E>> void swap(final L list1, final L list2,
      final int index) {

    final E temp = list1.get(index);
    list1.set(index, list2.get(index));
    list2.set(index, temp);

  }

  /**
   * Swap each element in the specified Lists between the specified start and
   * end indices.
   * 
   * @param list1
   *          A List.
   * @param list2
   *          Another List.
   * @param start
   *          The index of the first element to swap.
   * @param end
   *          The index of the last element to swap.
   * @param <E>
   *          The type of elements in the List to swap.
   * @param <L>
   *          The type of Lists to swap between.
   */
  public static <E, L extends List<E>> void swap(final L list1, final L list2,
      final int start, final int end) {
    for (final int i : new Range(start, end)) {
      swap(list1, list2, i);
    }
  }

  /** Instantiation disallowed except by subclasses. */
  protected ListUtils() {
    // intentionally unimplemented
  }
}
