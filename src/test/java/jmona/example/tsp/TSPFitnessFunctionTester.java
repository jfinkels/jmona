/**
 * TSPFitnessFunctionTester.java
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
package jmona.example.tsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.functional.Range;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the TSPFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class TSPFitnessFunctionTester {
  /** The number of vertices in the graph. */
  public static final int NUM_VERTICES = 2;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for {@link jmona.example.tsp.TSPFitnessFunction#fitness(List)}.
   */
  @Test
  public void testFitness() {

    final double[][] weights = new double[NUM_VERTICES][NUM_VERTICES];

    for (final int i : new Range(weights.length)) {
      for (final int j : new Range(weights.length)) {
        weights[i][j] = i + j;
      }
    }

    final DirectedGraph<Integer, Double> graph = new AdjacencyMatrixGraph(
        weights);
    final TSPFitnessFunction function = new TSPFitnessFunction(graph);

    // this tour is 0 -> 1 -> 0
    final List<Integer> tour = new Vector<Integer>();
    tour.add(0);
    tour.add(1);

    assertEquals(1.0 / (weights[0][1] + weights[1][0]), function
        .rawFitness(tour), ZERO_DELTA);

    // this tour is 1 -> 0 -> 1
    tour.clear();
    tour.add(1);
    tour.add(0);

    assertEquals(1.0 / (weights[1][0] + weights[0][1]), function
        .rawFitness(tour), ZERO_DELTA);

    tour.clear();
    tour.add(0);

    assertEquals(Double.POSITIVE_INFINITY, function.rawFitness(tour),
        ZERO_DELTA);

    tour.clear();

    try {
      function.rawFitness(tour);
      Util.shouldHaveThrownException();
    } catch (final IllegalArgumentException exception) {
      // tour has size 0
      assertEquals(0, tour.size());
      assertTrue(exception instanceof IllegalArgumentException);
    }
  }
}
