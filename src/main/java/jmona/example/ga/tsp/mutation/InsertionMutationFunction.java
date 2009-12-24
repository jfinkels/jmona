/**
 * InsertionMutationFunction.java
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
package jmona.example.ga.tsp.mutation;

import jmona.MutationFunction;
import jmona.example.ga.tsp.Tour;
import jmona.impl.Util;

/**
 * Chooses a city in the Tour at random and inserts it at a new random location
 * in the Tour.
 * 
 * @author jfinkels
 */
public class InsertionMutationFunction implements MutationFunction<Tour> {

  /**
   * Choose a city in the specified Tour at random and move it to a new random
   * location in the Tour.
   * 
   * @param tour
   *          The tour to mutate.
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final Tour tour) {
    // choose a random index of a city to remove from the tour
    final int source = Util.RANDOM.nextInt(tour.size());

    // remove that city from the tour
    final int city = tour.remove(source);

    // choose a random index for reinsertion of that city into the tour
    final int target = Util.RANDOM.nextInt(tour.size());

    // reinsert the city into the tour
    tour.add(target, city);
  }

}
