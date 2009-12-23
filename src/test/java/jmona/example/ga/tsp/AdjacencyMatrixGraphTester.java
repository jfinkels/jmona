/**
 * AdjacencyMatrixGraphTester.java
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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the AdjacencyMatrixGraph class.
 * 
 * @author jfinkels
 */
public class AdjacencyMatrixGraphTester {

  /** The number of vertices in the graph. */
  public static final int NUM_VERTICES = 2;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.ga.tsp.AdjacencyMatrixGraph#edgeBetween(java.lang.Integer, java.lang.Integer)}
   * .
   */
  @Test
  public void testEdgeBetween() {
    final double[][] weights = new double[NUM_VERTICES][NUM_VERTICES];
    for (int i = 0; i < weights.length; ++i) {
      for (int j = 0; j < weights.length; ++j) {
        weights[i][j] = i + j;
      }
    }
    final AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(weights);

    for (int i = 0; i < weights.length; ++i) {
      for (int j = 0; j < weights.length; ++j) {
        assertEquals(weights[i][j], graph.edgeBetween(i, j), ZERO_DELTA);
      }
    }
  }

}
