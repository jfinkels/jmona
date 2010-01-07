/**
 * PartialDeepCopyableVector.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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

import java.util.Collection;
import java.util.Vector;

import jmona.CopyingException;
import jmona.DeepCopyableList;

/**
 * A Vector which can be deep copied, but whose elements are assumed to be
 * immutable.
 * 
 * Therefore, a deep copy of this Vector will only copy references to the
 * elements of the original Vector into the cloned vector.
 * 
 * @param <E>
 *          The type of element contained in this Vector.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class PartialDeepCopyableVector<E> extends Vector<E> implements
    DeepCopyableList<E> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -6350548246821886002L;

  /** Instantiate this Vector as an empty List. */
  public PartialDeepCopyableVector() {
    super();
  }

  /**
   * Instantiate this vector and copy references of the elements contained in
   * the specified Collection to this Vector.
   * 
   * @param collection
   *          The Collection from which to copy references of elements.
   */
  public PartialDeepCopyableVector(final Collection<E> collection) {
    super(collection);
  }

  /**
   * Instantiates this vector and copies references of the elements returned by
   * the specified Iterable into this Vector in order.
   * 
   * @param iterable
   *          The Iterable from which to copy references of elements.
   */
  public PartialDeepCopyableVector(final Iterable<E> iterable) {
    for (final E element : iterable) {
      this.add(element);
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public DeepCopyableList<E> deepCopy() throws CopyingException {
    return new PartialDeepCopyableVector<E>(this);
  }

}
