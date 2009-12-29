/**
 * CompleteDeepCopyableListFactory.java
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

import jmona.DeepCopyable;
import jmona.DeepCopyableList;
import jmona.InitializationException;

/**
 * A ListFactory which aggregates object created from a Factory which creates
 * objects of type E.
 * 
 * This factory creates a List which can be deep copied, and all the elements in
 * the list can also be deep copied.
 * 
 * @param <E>
 *          The type of element in the List to create.
 * @author jfinkels
 */
public class CompleteDeepCopyableListFactory<E extends DeepCopyable<E>> extends
    PartialDeepCopyableListFactory<E> {

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws InitializationException
   *           {@inheritDoc}
   * @see jmona.ListFactory#createObject()
   */
  @Override
  public DeepCopyableList<E> createObject() throws InitializationException {
    if (this.elementFactory() == null) {
      throw new InitializationException("Element factory has not been set.");
    }

    final DeepCopyableList<E> result = new CompleteDeepCopyableVector<E>();

    for (int i = 0; i < this.size(); ++i) {
      result.add(this.elementFactory().createObject());
    }

    return result;
  }

}
