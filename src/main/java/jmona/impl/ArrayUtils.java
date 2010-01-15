/**
 * ArrayUtils.java
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

import jmona.functional.Range;

/**
 * A utility class which provides methods for operating on arrays.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public final class ArrayUtils {

  /**
   * Returns an array containing references to the Objects in the specified
   * array between the specified start and end indices.
   * 
   * The length of the returned array is {@code endIndex - startIndex}.
   * 
   * @param array
   *          The array from which to get the slice.
   * @param startIndex
   *          The start index of the slice.
   * @param endIndex
   *          The end index of the slice.
   * @return An array containing references to the Objects in the specified
   *         array between the specified start and end indices.
   */
  public static Object[] slice(final Object[] array, final int startIndex,
      final int endIndex) {
    final int length = endIndex - startIndex;
    final Object[] result = new Object[length];

    for (final int i : new Range(0, length)) {
      result[i] = array[startIndex + i];
    }

    return result;
  }

  /** Instantiation disallowed except by subclasses. */
  protected ArrayUtils() {
    // intentionally unimplemented
  }
}
