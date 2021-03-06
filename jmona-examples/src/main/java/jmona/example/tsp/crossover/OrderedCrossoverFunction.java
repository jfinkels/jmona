/**
 * OrderedCrossoverFunction.java
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
package jmona.example.tsp.crossover;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import jfcommon.functional.Range;
import jmona.CrossoverFunction;
import jmona.impl.mutable.MutableInteger;
import jmona.random.RandomUtils;

/**
 * Ordered crossover (also known as OX) for a tour in the traveling salesman
 * problem.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
// TODO references for the original authors of TSP crossover functions
public class OrderedCrossoverFunction implements
    CrossoverFunction<List<MutableInteger>> {

  /**
   * Perform ordered crossover (also known as OX) on the specified tours.
   * 
   * Ordered crossover works in two stages. First, a random slice is swapped
   * between the two tours, as in a two-point crossover. Second, repeated cities
   * not in the swapped area are removed, and the remaining integers are added
   * from the other tour, in the order that they appear starting from the end
   * index of the swapped section.
   * 
   * @param tour1
   *          A tour.
   * @param tour2
   *          Another tour.
   * @see jmona.CrossoverFunction#crossover(Object, Object)
   */
  @Override
  public void crossover(final List<MutableInteger> tour1,
      final List<MutableInteger> tour2) {

    // get the size of the tours
    final int size = tour1.size();

    // choose two random numbers for the start and end indices of the slice
    // (one can be at index "size")
    final int number1 = RandomUtils.randomData().nextInt(0, size - 1);
    final int number2 = RandomUtils.randomData().nextInt(0, size);

    // make the smaller the start and the larger the end
    final int start = Math.min(number1, number2);
    final int end = Math.max(number1, number2);

    // instantiate two child tours
    final List<MutableInteger> child1 = new Vector<MutableInteger>();
    final List<MutableInteger> child2 = new Vector<MutableInteger>();

    // add the sublist in between the start and end points to the children
    child1.addAll(tour1.subList(start, end));
    child2.addAll(tour2.subList(start, end));

    // iterate over each city in the parent tours
    int currentCityIndex = 0;
    MutableInteger currentCityInTour1 = null;
    MutableInteger currentCityInTour2 = null;
    for (final int i : new Range(size)) {

      // get the index of the current city
      currentCityIndex = (end + i) % size;

      // get the city at the current index in each of the two parent tours
      currentCityInTour1 = tour1.get(currentCityIndex);
      currentCityInTour2 = tour2.get(currentCityIndex);

      // if child 1 does not already contain the current city in tour 2, add it
      // Note: MutableIntegers override equals() so .contains() works
      if (!child1.contains(currentCityInTour2)) {
        child1.add(currentCityInTour2);
      }

      // if child 2 does not already contain the current city in tour 1, add it
      // Note: MutableIntegers override equals() so .contains() works
      if (!child2.contains(currentCityInTour1)) {
        child2.add(currentCityInTour1);
      }
    }

    // rotate the lists so the original slice is in the same place as in the
    // parent tours
    Collections.rotate(child1, start);
    Collections.rotate(child2, start);

    // copy the tours from the children back into the parents, because crossover
    // functions are in-place!
    Collections.copy(tour1, child2);
    Collections.copy(tour2, child1);
  }

}
