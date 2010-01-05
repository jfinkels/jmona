/**
 * InversionMutationFunction.java
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

import jmona.impl.Util;

/**
 * A MutationFunction which mutates a List by inverting the order of a sublist
 * of elements in place.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in the List to mutate.
 * @since 0.4
 */
public class InversionMutationFunction<E> implements
    OrderedListMutationFunction<E> {

  /**
   * Invert a random sublist of elements in the specified sublist.
   * 
   * @param list
   *          The List in which to invert a random sublist.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final List<E> list) {
    // get a random start and end of the sublist
    final int start = Util.RANDOM.nextInt(list.size());
    final int end = start + Util.RANDOM.nextInt(list.size() - start);

    // reverse the sublist between start and end
    Collections.reverse(list.subList(start, end));
  }

}
