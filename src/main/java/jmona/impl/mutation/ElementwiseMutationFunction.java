/**
 * ElementwiseMutationFunction.java
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
package jmona.impl.mutation;

import jmona.DeepCopyableList;
import jmona.MutationException;
import jmona.MutationFunction;
import jmona.random.RandomUtils;

/**
 * A class which mutates elements in a List by mutating one element at random.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element contained in the List to mutate.
 * @since 0.1
 */
public class ElementwiseMutationFunction<E> implements ListMutationFunction<E> {

  /** The mutation function on elements of the List. */
  private MutationFunction<E> elementMutationFunction = null;

  /**
   * Mutate a random element in the specified List.
   * 
   * @param list
   *          The List in which to mutate a random element.
   * @throws MutationException
   *           If the mutation of the random element throws an Exception, or if
   *           the ListElementMutationException has not been set.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final DeepCopyableList<E> list) throws MutationException {
    if (this.elementMutationFunction == null) {
      throw new MutationException(
          Messages.getString("ElementwiseMutationFunction.0")); //$NON-NLS-1$
    }

    this.elementMutationFunction.mutate(RandomUtils.choice(list));
  }

  /**
   * Set the mutation function on elements of the List.
   * 
   * @param newFunction
   *          The mutation function on elements of the List.
   */
  public void setElementMutationFunction(final MutationFunction<E> newFunction) {
    this.elementMutationFunction = newFunction;
  }
}
