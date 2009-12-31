/**
 * DisplacementMutationFunction.java
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
package jmona.example.tsp.mutation;

import java.util.List;
import java.util.Vector;

import jmona.MutationFunction;
import jmona.impl.Util;

/**
 * Displaces a random sublist within a Tour to a new random location in the
 * Tour.
 * 
 * @author Jeffrey Finkelstein
 */
// TODO generalize all TSP MutationFunctions to all Lists
public class DisplacementMutationFunction implements
    MutationFunction<List<Integer>> {

  /**
   * Select a random sublist from the specified Tour and displace it to a random
   * new location in the Tour.
   * 
   * @param tour
   *          The tour to mutate.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final List<Integer> tour) {
    // get a random start and end of the sublist
    final int start = Util.RANDOM.nextInt(tour.size());
    final int end = start + Util.RANDOM.nextInt(tour.size() - start);

    // make a copy of the sublist
    final List<Integer> sublist = new Vector<Integer>();
    for (final int i : tour.subList(start, end)) {
      sublist.add(i);
    }

    // remove all elements from the tour which are in the sublist
    tour.removeAll(sublist);

    // choose a random index for reinsertion of the sublist into the tour
    final int insertionPoint = Util.RANDOM.nextInt(tour.size());

    // add the sublist back into the tour
    tour.addAll(insertionPoint, sublist);
  }
}
