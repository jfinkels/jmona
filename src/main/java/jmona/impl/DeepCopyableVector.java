/**
 * DeepCopyableVector.java
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

import java.util.Vector;

import jmona.CopyingException;
import jmona.DeepCopyable;
import jmona.DeepCopyableList;

/**
 * A vector which can be deep copied, and which contains elements which can be
 * deep copied as well.
 * 
 * @param <E>
 *          The type of element contained in this Vector.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class DeepCopyableVector<E extends DeepCopyable<E>> extends Vector<E>
    implements DeepCopyableList<E> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 2375384227473517083L;

  /** Instantiate this Vector as an empty List. */
  public DeepCopyableVector() {
    // intentionally unimplemented
  }

  /**
   * Instantiate this Vector and perform a deep copy on the specified Iterable,
   * adding those copied elements to this Vector.
   * 
   * @param iterable
   *          The iterable on which to perform a deep copy, then add the copied
   *          elements to this Vector.
   * @throws CopyingException
   *           If there is a problem deep copying the elements of the specified
   *           Iterable.
   */
  public DeepCopyableVector(final Iterable<E> iterable) throws CopyingException {
    this.addAll(DeepCopyUtils.deepCopy(iterable));
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public DeepCopyableVector<E> deepCopy() throws CopyingException {
    return new DeepCopyableVector<E>(this);
  }

}
