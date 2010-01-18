/**
 * DisplacementMutationFunction.java
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
import java.util.Vector;

import jmona.DeepCopyableList;
import jmona.random.RandomUtils;

/**
 * Displaces a random sublist within a List to a new random location in the
 * List.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in the List to mutate.
 * @since 0.4
 */
public class DisplacementMutationFunction<E> implements
    OrderedListMutationFunction<E> {

  /**
   * Select a random sublist from the specified List and displace it to a random
   * new location in the List.
   * 
   * @param list
   *          The List to mutate.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final DeepCopyableList<E> list) {
    // get a random start and end of the sublist (one can be at list.size())
    final int number1 = RandomUtils.randomData().nextInt(0, list.size() - 1);
    final int number2 = RandomUtils.randomData().nextInt(0, list.size());

    final int start = Math.min(number1, number2);
    final int end = Math.max(number1, number2);

    // only do the displacement if we haven't selected the entire list!
    if (start != 0 || end != list.size()) {

      // make a list of references to elements in the sublist
      final List<E> sublist = new Vector<E>(list.subList(start, end));

      // remove all elements from the tour which are in the sublist
      list.removeAll(sublist);

      // choose a random index for reinsertion of the sublist into the list
      final int insertionPoint = RandomUtils.randomData().nextInt(0,
          list.size());

      // add the sublist back into the tour
      list.addAll(insertionPoint, sublist);

    }
  }
}
