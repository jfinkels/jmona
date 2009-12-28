/**
 * CompleteDeepCopyableVector.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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

import jmona.DeepCopyable;
import jmona.exceptions.CopyingException;

/**
 * A vector which can be deep copied, and which contains elements which can be
 * deep copied as well.
 * 
 * @param <E>
 *          The type of element contained in this Vector.
 * @author jfinkels
 */
public class CompleteDeepCopyableVector<E extends DeepCopyable<E>> extends
    PartialDeepCopyableVector<E> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 2375384227473517083L;

  /** Instantiate this Vector as an empty List. */
  public CompleteDeepCopyableVector() {
    super();
  }

  /**
   * Instantiate this Vector and perform a deep copy on the specified
   * Collection, adding those copied elements to this Vector.
   * 
   * @param collection
   *          The collection on which to perform a deep copy, then add the
   *          copied elements to this Vector.
   * @throws CopyingException
   *           If there is a problem deep copying the elements of the specified
   *           collection.
   */
  public CompleteDeepCopyableVector(final Collection<E> collection)
      throws CopyingException {
    this.addAll(Util.deepCopy(collection));
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public CompleteDeepCopyableVector<E> deepCopy() throws CopyingException {
    return new CompleteDeepCopyableVector<E>(this);
  }

}
