/**
 * PartiallyMatchedCrossoverFunction.java
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
package jmona.example.tsp.crossover;

import java.util.List;

import jmona.CrossoverFunction;
import jmona.impl.Util;

/**
 * Partially matched crossover (also known as PMX), which swaps a slice of the
 * tours, then uses the permutation defined by the integers in the swapped
 * slices to replace repeated cities in the non-swapped sections of the tours.
 * 
 * @author Jeffrey Finkelstein
 */
// TODO inherit from two-point crossover?
public class PartiallyMatchedCrossoverFunction implements
    CrossoverFunction<List<Integer>> {

  /**
   * Perform partially matched crossover (also known as PMX) on the specified
   * tours.
   * 
   * Partially matched crossover works in two stages. First, a random slice is
   * swapped between the two tours, as in a two-point crossover. Second, any
   * repeated cities in the non-swapped parts of the tours are replaced so the
   * tours become valid again. This is done by using the mapping defined by
   * <em>f({@code tour1.get(i)}) = {@code tour2.get(i)}</em> for all <em>i</em>
   * in the interval defined by the start and end indices of the swapped slice,
   * and the inverse of this mapping. (If <em>f(x)</em> is still in the swapped
   * section of the tour, then apply the mapping repeatedly to find the
   * replacement city).
   * 
   * @param tour1
   *          A tour.
   * @param tour2
   *          Another tour.
   * @see jmona.CrossoverFunction#crossover(Object, Object)
   */
  @Override
  public void crossover(final List<Integer> tour1, final List<Integer> tour2) {

    // get the size of the tours
    final int size = tour1.size();

    // choose two random numbers for the start and end indices of the slice
    final int number1 = Util.RANDOM.nextInt(size);
    final int number2 = Util.RANDOM.nextInt(size);

    // make the smaller the start and the larger the end
    final int start = Math.min(number1, number2);
    final int end = Math.max(number1, number2);

    // crossover the section in between the start and end indices
    Util.swap(tour1, tour2, start, end);

    // get a view of the crossover over sections in each tour
    final List<Integer> swappedSectionInTour1 = tour1.subList(start, end);
    final List<Integer> swappedSectionInTour2 = tour2.subList(start, end);

    int currentCity = 0;
    int replacementCityIndex = 0;
    int replacementCity = 0;

    // iterate over each city in not in the crossed over section
    for (int i = end; i >= end || i < start; i = (i + 1) % size) {

      // get the current city being examined in tour 1
      currentCity = tour1.get(i);

      // if that city is repeated in the crossed over section
      if (swappedSectionInTour1.contains(currentCity)) {

        // get the index of the city to replace the repeated city (within the
        // swapped section)
        replacementCityIndex = swappedSectionInTour1.indexOf(currentCity);

        // get the city that is intended to replace the repeated city
        replacementCity = swappedSectionInTour2.get(replacementCityIndex);

        // if the repeated city is also contained in the crossed over section
        while (swappedSectionInTour1.contains(replacementCity)) {

          // get the index of the city to replace the repeated city
          replacementCityIndex = swappedSectionInTour1.indexOf(replacementCity);

          // get the city that is intended to replace the repeated city
          replacementCity = swappedSectionInTour2.get(replacementCityIndex);

        }

        // replace the current city with the replacement city
        tour1.set(i, replacementCity);
      }

      // get the current city being examined in tour 2
      currentCity = tour2.get(i);

      // if that city is repeated in the crossed over section
      if (swappedSectionInTour2.contains(currentCity)) {

        // get the index of the city to replace the repeated city
        replacementCityIndex = swappedSectionInTour2.indexOf(currentCity);

        // get the city that is intended to replace the repeated city
        replacementCity = swappedSectionInTour1.get(replacementCityIndex);

        // if the repeated city is also contained in the crossed over section
        while (swappedSectionInTour2.contains(replacementCity)) {

          // get the index of the city to replace the repeated city
          replacementCityIndex = swappedSectionInTour2.indexOf(replacementCity);

          // get the city that is intended to replace the repeated city
          replacementCity = swappedSectionInTour1.get(replacementCityIndex);
        }

        // replace the current city with the replacement city
        tour2.set(i, replacementCity);
      }
    }

  }
}
