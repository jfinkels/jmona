/**
 * ListMutationFunction.java
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

import java.util.List;

import jmona.MutationException;
import jmona.MutationFunction;

/**
 * A class which mutates elements in a List by mutating one element at random.
 * 
 * @param <E>
 *          The type of element contained in the List to mutate.
 * @author jfinkels
 */
public class ListMutationFunction<E> implements MutationFunction<List<E>> {

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
   * @see jmona.IndividualMutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final List<E> list) throws MutationException {
    if (this.elementMutationFunction == null) {
      throw new MutationException(
          "No ListElementMutationFunction has been set.");
    }

    this.elementMutationFunction.mutate(Util.randomFromCollection(list));
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
