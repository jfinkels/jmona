/**
 * InversionMutationFunction.java
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

import java.util.Collections;

import jmona.MutationFunction;
import jmona.example.ga.tsp.Tour;
import jmona.impl.Util;

/**
 * A MutationFunction which mutates a Tour by inverting the order of a sublist
 * of cities in the tour.
 * 
 * @author jfinkels
 */
public class InversionMutationFunction implements MutationFunction<Tour> {

  /**
   * Invert a random sublist of cities in the Tour.
   * 
   * @param tour
   *          The Tour to mutate.
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final Tour tour) {
    // get a random start and end of the sublist
    final int start = Util.RANDOM.nextInt(tour.size());
    final int end = start + Util.RANDOM.nextInt(tour.size() - start);

    // reverse the sublist between start and end
    Collections.reverse(tour.subList(start, end));
  }

}
