/**
 * SwapMutationFunction.java
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

import java.util.Collections;
import java.util.List;

import jmona.MutationFunction;
import jmona.impl.Util;

/**
 * Swap two cities chosen at random from a Tour.
 * 
 * @author Jeffrey Finkelstein
 */
public class SwapMutationFunction implements MutationFunction<List<Integer>> {

  /**
   * Swap two cities chosen at random from the specified Tour.
   * 
   * @param tour
   *          The Tour to mutate.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final List<Integer> tour) {

    // get two random indices into the tour
    final int source = Util.RANDOM.nextInt(tour.size());
    final int target = Util.RANDOM.nextInt(tour.size());

    // swap the cities at the source index and target index in the tour
    Collections.swap(tour, source, target);
  }

}
