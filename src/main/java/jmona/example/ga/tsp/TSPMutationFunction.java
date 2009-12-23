/**
 * TSPMutationFunction.java
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
package jmona.example.ga.tsp;

import jmona.ga.impl.AbstractMutationFunction;
import jmona.impl.Util;

/**
 * A MutationFunction which mutates a Tour by swapping cities.
 * 
 * @author jfinkels
 */
public class TSPMutationFunction extends AbstractMutationFunction<Tour> {

  /**
   * Iterate over each city in the Tour, and if a mutation is needed, swap that
   * city with a different randomly chosen city.
   * 
   * @param individual
   *          The Tour to mutate.
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final Tour individual) {

    // iterate over each city in the Tour
    for (int i = 0; i < individual.size(); ++i) {

      // if a mutation is needed
      if (Math.random() < this.mutationProbability()) {

        // determine a random city to swap different from the current one
        int cityToSwap = 0;
        do {
          cityToSwap = Util.RANDOM.nextInt(individual.size());
        } while (cityToSwap == i);

        // swap the cities at index "i" and index "cityToSwap"
        swap(individual, i, cityToSwap);
      }
    }
  }

  /**
   * Swap the cities at the two specified indices in the specified Tour.
   * 
   * @param individual
   *          The Tour within which to swap cities.
   * @param index1
   *          The index of a city to swap.
   * @param index2
   *          The index of another city to swap.
   */
  private static void swap(final Tour individual, final int index1,
      final int index2) {
    final int temp = individual.get(index1);
    individual.set(index1, individual.get(index2));
    individual.set(index2, temp);
  }
}
