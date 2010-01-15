/**
 * CompleteDeepCopyableListFactory.java
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

import jmona.DeepCopyable;
import jmona.DeepCopyableList;
import jmona.InitializationException;
import jmona.functional.Range;

/**
 * A factory which creates a DeepCopyableList of elements generated by the
 * {@link #elementFactory} property, which can themselves be deep copied.
 * 
 * @param <E>
 *          The type of element in the List to create.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class CompleteDeepCopyableListFactory<E extends DeepCopyable<E>> extends
    DeepCopyableListFactory<E> {

  /**
   * Creates a DeepCopyableList of elements generated by the
   * {@link #elementFactory} property, which can themselves be deep copied.
   * 
   * The created DeepCopyableList can be deep copied, and the elements contained
   * in the List will also be deep copied.
   * 
   * @return A DeepCopyableList of elements generated by the
   *         {@link #elementFactory} property.
   * @throws InitializationException
   *           If an Exception is thrown when generating an element, or if no
   *           element factory has been set.
   * @throws PropertyNotSetException
   *           If any of the necessary properties have not been set.
   * @see jmona.impl.PartialDeepCopyableListFactory#createObject()
   */
  @Override
  public DeepCopyableList<E> createObject() throws InitializationException {
    this.sanityCheck();

    final DeepCopyableList<E> result = new CompleteDeepCopyableVector<E>();

    for (final int i : new Range(this.size())) {
      result.add(this.elementFactory().createObject());
    }

    return result;
  }

}
