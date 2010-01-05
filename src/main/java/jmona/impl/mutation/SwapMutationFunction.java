/**
 * SwapMutationFunction.java
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

import java.util.Collections;
import java.util.List;

import jmona.random.RandomUtils;

/**
 * Swap two elements chosen at random from a List.
 * 
 * This MutationFunction is only useful for a List (representing an individual)
 * in which order matters.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in the List to swap.
 * @since 0.4
 */
public class SwapMutationFunction<E> implements OrderedListMutationFunction<E> {

  /**
   * Swap two element chosen at random from a List.
   * 
   * @param list
   *          The List of elements in which to swap a random pair.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final List<E> list) {

    // get two random indices into the tour
    final int source = RandomUtils.RANDOM.nextInt(0, list.size() - 1);
    final int target = RandomUtils.RANDOM.nextInt(0, list.size() - 1);

    // swap the cities at the source index and target index in the tour
    Collections.swap(list, source, target);
  }

}
