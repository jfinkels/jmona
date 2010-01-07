/**
 * HashSetFromIterable.java
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
package jmona.functional;

import java.util.HashSet;

/**
 * A HashSet whose initial elements are added from an Iterable.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in this HashSet.
 * @since 0.5
 */
public class HashSetFromIterable<E> extends HashSet<E> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -3184644489341066116L;

  /**
   * Instantiates this HashSet by adding the elements from the specified
   * iterable to this object.
   * 
   * @param iterable
   *          The iterable from which to add all elements.
   */
  public HashSetFromIterable(final Iterable<E> iterable) {
    for (final E element : iterable) {
      this.add(element);
    }
  }
}
