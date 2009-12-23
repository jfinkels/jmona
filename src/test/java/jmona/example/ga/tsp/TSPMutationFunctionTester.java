/**
 * TSPMutationFunctionTester.java
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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the TSPMutationFunction class.
 * 
 * @author jfinkels
 */
public class TSPMutationFunctionTester {

  /** The number of tests to run. */
  public static final int NUM_TESTS = 10000;

  /**
   * Test method for
   * {@link jmona.example.ga.tsp.TSPMutationFunction#mutate(jmona.example.ga.tsp.Tour)}
   * .
   */
  @Test
  public void testMutate() {
    final TSPMutationFunction function = new TSPMutationFunction();

    Tour tour = null;
    for (int i = 0; i < NUM_TESTS; ++i) {
      tour = new Tour();
      tour.add(1);
      tour.add(0);
      function.mutate(tour);
      
      assertTrue((tour.get(0) == 1 && tour.get(1) == 0)
          || (tour.get(0) == 0 && tour.get(1) == 1));
    }

  }

}
