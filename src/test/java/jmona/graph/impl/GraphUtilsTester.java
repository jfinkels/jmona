/**
 * GraphUtilsTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.graph.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Vector;

import jfcommon.functional.Range;
import jmona.graph.DirectedGraph;

import org.junit.Test;

/**
 * Test class for the GraphUtils class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class GraphUtilsTester {

  /** The number of vertices in the graph. */
  public static final int NUM_VERTICES = 2;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.graph.impl.GraphUtils#totalDistance(java.util.List, jmona.graph.DirectedGraph)}
   * .
   */
  @Test
  public void testTotalDistance() {

    final double[][] weights = new double[NUM_VERTICES][NUM_VERTICES];

    for (final int i : new Range(weights.length)) {
      for (final int j : new Range(weights.length)) {
        weights[i][j] = i + j;
      }
    }

    final DirectedGraph<Integer, Double> graph = new AdjacencyMatrixGraph(
        weights);

    // this tour is 0 -> 1 -> 0
    final List<Integer> tour = new Vector<Integer>();
    tour.add(0);
    tour.add(1);

    assertEquals(weights[0][1] + weights[1][0], GraphUtils.totalDistance(tour,
        graph), ZERO_DELTA);

    // this tour is 1 -> 0 -> 1
    tour.clear();
    tour.add(1);
    tour.add(0);

    assertEquals(weights[1][0] + weights[0][1], GraphUtils.totalDistance(tour,
        graph), ZERO_DELTA);

    tour.clear();
    tour.add(0);

    assertEquals(0, GraphUtils.totalDistance(tour, graph), ZERO_DELTA);
    
    tour.clear();
    assertEquals(0, GraphUtils.totalDistance(tour, graph), ZERO_DELTA);
  }

  /**
   * Test method for {@link jmona.graph.impl.GraphUtils#GraphUtils()}.
   */
  @Test
  public void testGraphUtils() {
    new GraphUtils();
  }

}
