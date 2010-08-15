/**
 * DeepCopyUtils.java
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
import java.util.Vector;

import jmona.CopyingException;
import jmona.DeepCopyable;

/**
 * Utility class which provides static methods for deep copying objects which
 * implement the DeepCopyable interface.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public final class DeepCopyUtils {

  /**
   * Perform a deep copy on the specified Collection of deep copyable elements.
   * 
   * This method runs in <em>O(n)</em> time.
   * 
   * @param <E>
   *          The type of deep copyable element in the Collection to be deep
   *          copied.
   * @param collection
   *          The Collection of elements on which to perform a deep copy.
   * @return A List of the copied elements from the specified Collection.
   * @throws CopyingException
   *           If there is a problem copying the elements of the Collection.
   */
  public static <E extends DeepCopyable<E>> List<E> deepCopy(
      final Iterable<? extends E> iterable) throws CopyingException {

    final List<E> result = new Vector<E>();

    for (final E element : iterable) {
      result.add((E) element.deepCopy());
    }

    return result;
  }

  /** Instantiation disallowed except by subclasses. */
  protected DeepCopyUtils() {
    // intentionally unimplemented
  }
}
