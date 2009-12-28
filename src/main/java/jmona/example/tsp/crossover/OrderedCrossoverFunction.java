/**
 * OrderedCrossoverFunction.java
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

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import jmona.CrossoverFunction;
import jmona.impl.Util;

import org.apache.log4j.Logger;

/**
 * The ordered crossover (OX) function for a tour in the traveling salesman
 * problem, by Prins.
 * 
 * @author jfinkels
 */
public class OrderedCrossoverFunction implements CrossoverFunction<List<Integer>> {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(OrderedCrossoverFunction.class);

  /*
   * (non-Javadoc)
   * 
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final List<Integer> tour1, final List<Integer> tour2) {

    // choose a random start and end point for the slice
    final int start = Util.RANDOM.nextInt(tour1.size());
    final int end = start + Util.RANDOM.nextInt(tour1.size() - start);

    LOG.debug("(start, end) = (" + start + ", " + end + ")");

    // instantiate two child tours
    final List<Integer> child1 = new Vector<Integer>();
    final List<Integer> child2 = new Vector<Integer>();

    // add the sublist in between the start and end points to the children
    child1.addAll(tour1.subList(start, end));
    child2.addAll(tour2.subList(start, end));

    LOG.debug("Child 1: " + child1);
    LOG.debug("Child 2: " + child2);

    // iterate over each city in the parent tours
    int currentCityIndex = 0;
    int currentCityInTour1 = 0;
    int currentCityInTour2 = 0;
    for (int i = 0; i < tour1.size(); ++i) {

      // get the index of the current city
      currentCityIndex = end + i % tour1.size();

      // get the city at the current index in each of the two parent tours
      currentCityInTour1 = tour1.get(currentCityIndex);
      currentCityInTour2 = tour2.get(currentCityIndex);

      // if child 1 does not already contain the current city in tour 2, add it
      if (!child1.contains(currentCityInTour2)) {
        child1.add(currentCityInTour2);
      }

      // if child 2 does not already contain the current city in tour 1, add it
      if (!child2.contains(currentCityInTour1)) {
        child2.add(currentCityInTour1);
      }
    }

    LOG.debug("Child 1: " + child1);
    LOG.debug("Child 2: " + child2);

    // rotate the lists so the original slice is in the same place as in the
    // parent tours
    Collections.rotate(child1, start);
    Collections.rotate(child2, start);

    LOG.debug("Child 1: " + child1);
    LOG.debug("Child 2: " + child2);

    // copy the tours from the children back into the parents, because crossover
    // functions are in-place!
    Collections.copy(tour1, child1);
    Collections.copy(tour2, child2);

    LOG.debug("Tour 1: " + tour1);
    LOG.debug("Tour 2: " + tour2);
  }

}
