/**
 * InsertionMutationFunction.java
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

import jmona.random.RandomUtils;

/**
 * Chooses an element in the List at random and inserts it at a new random
 * location in the List.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in the List to reinsert.
 * @since 0.4
 */
public class InsertionMutationFunction<E, L extends List<E>> implements
    OrderedListMutationFunction<E, L> {

  /**
   * Choose an element in the specified List at random and move it to a new
   * random location in the List.
   * 
   * @param list
   *          The List from which to choose an element for reinsertion.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final L list) {
    // choose a random index of a city to remove from the tour
    final int source = RandomUtils.randomData().nextInt(0, list.size() - 1);

    // remove that city from the tour
    final E city = list.remove(source);

    // choose a random index for reinsertion of that city into the tour
    final int target = RandomUtils.randomData().nextInt(0, list.size() - 1);

    // reinsert the city into the tour
    list.add(target, city);
  }

}
