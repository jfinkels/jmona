/**
 * PrimitiveListMutationFunction.java
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

import java.util.List;

import jmona.MutationException;
import jmona.impl.Util;

/**
 * A ElementwiseMutationFunction which operates on Lists of immutable elements, by
 * inserting a mutated copy of a random element of the List back into the List.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in the List.
 * @since 0.1
 */
public abstract class ImmutableElementsListMutationFunction<E> implements
    ListMutationFunction<E> {

  /**
   * Mutate the specified List by getting a random element from the List, then
   * inserting a mutated copy of that element back into the List where the
   * original element was.
   * 
   * @param list
   *          A List containing immutable elements.
   * @throws MutationException
   *           If there is a problem getting a mutated copy of an element of the
   *           List.
   */
  @Override
  public void mutate(final List<E> list) throws MutationException {
    // choose a random index into the list
    final int index = Util.RANDOM.nextInt(list.size());

    // get the element at that index
    final E element = list.get(index);

    // get the mutated element
    final E mutatedElement = this.mutated(element);

    // put the mutated element back into the list where the original element was
    list.set(index, mutatedElement);
  }

  /**
   * Get a mutated copy of the specified element.
   * 
   * @param element
   *          The element to mutate.
   * @return A mutated copy of the specified element.
   * @throws MutationException
   *           If there is a problem mutating the specified element.
   */
  protected abstract E mutated(final E element) throws MutationException;
}
