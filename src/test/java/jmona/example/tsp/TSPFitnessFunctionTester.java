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

import java.util.List;
import java.util.Vector;

import org.junit.Test;

/**
 * Test class for the TSPFitnessFunction class.
 * 
 * @author jfinkels
 */
public class TSPFitnessFunctionTester {
  /** The number of vertices in the graph. */
  public static final int NUM_VERTICES = 2;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.tsp.TSPFitnessFunction#fitness(jmona.example.tsp.Tour)}
   * .
   */
  @Test
  public void testFitness() {

    final double[][] weights = new double[NUM_VERTICES][NUM_VERTICES];

    for (int i = 0; i < weights.length; ++i) {
      for (int j = 0; j < weights.length; ++j) {
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

    assertEquals(1.0 / (weights[0][1] + weights[1][0]), function.fitness(tour),
        ZERO_DELTA);

    // this tour is 1 -> 0 -> 1
    tour.clear();
    tour.add(1);
    tour.add(0);

    assertEquals(1.0 / (weights[1][0] + weights[0][1]), function.fitness(tour),
        ZERO_DELTA);

    tour.clear();
    tour.add(0);
    
    assertEquals(Double.POSITIVE_INFINITY, function.fitness(tour), ZERO_DELTA);
  }
}