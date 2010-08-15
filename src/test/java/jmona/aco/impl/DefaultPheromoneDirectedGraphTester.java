/**
 * DefaultPheromoneDirectedGraphTester.java
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
package jmona.aco.impl;

import static org.junit.Assert.assertEquals;
import jmona.functional.Range;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultPheromoneDirectedGraph class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class DefaultPheromoneDirectedGraphTester {

  /** The initial pheromone to place on each edge in the graph under test. */
  public static final double INITIAL_PHEROMONE = 10;
  /** The number of vertices to in the graph under test. */
  public static final int NUMBER_OF_VERTICES = 10;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The adjacency matrix for the graph under test. */
  private double[][] adjacencyMatrix = null;
  /** The graph under test. */
  private DefaultPheromoneDirectedGraph graph = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public void setUp() {
    this.adjacencyMatrix = new double[NUMBER_OF_VERTICES][NUMBER_OF_VERTICES];

    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      for (final int j : new Range(NUMBER_OF_VERTICES)) {
        this.adjacencyMatrix[i][j] = i + j;
      }
    }

    this.graph = new DefaultPheromoneDirectedGraph(this.adjacencyMatrix,
        INITIAL_PHEROMONE);
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.DefaultPheromoneDirectedGraph#addPheromoneBetween(java.lang.Integer, java.lang.Integer, double)}
   * .
   */
  @Test
  public void testAddPheromoneBetween() {
    final double pheromoneToAdd = 1;
    final int sourceVertex = 2;
    final int targetVertex = 0;

    this.graph.addPheromoneBetween(sourceVertex, targetVertex, pheromoneToAdd);

    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      for (final int j : new Range(NUMBER_OF_VERTICES)) {

        if (i == sourceVertex && j == targetVertex) {
          assertEquals(INITIAL_PHEROMONE + pheromoneToAdd, this.graph
              .pheromoneBetween(i, j), ZERO_DELTA);
        } else {
          assertEquals(INITIAL_PHEROMONE, this.graph.pheromoneBetween(i, j),
              ZERO_DELTA);
        }

      }
    }
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.DefaultPheromoneDirectedGraph#evaporate(double)}.
   */
  @Test
  public void testEvaporate() {
    final double persistence = 0.1;
    this.graph.evaporate(persistence);
    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      for (final int j : new Range(NUMBER_OF_VERTICES)) {
        assertEquals(INITIAL_PHEROMONE * persistence, this.graph
            .pheromoneBetween(i, j), ZERO_DELTA);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.DefaultPheromoneDirectedGraph#pheromoneBetween(java.lang.Integer, java.lang.Integer)}
   * .
   */
  @Test
  public void testPheromoneBetween() {
    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      for (final int j : new Range(NUMBER_OF_VERTICES)) {
        assertEquals(INITIAL_PHEROMONE, this.graph.pheromoneBetween(i, j),
            ZERO_DELTA);
      }
    }

    this.graph.addPheromoneBetween(0, 1, 1);

    assertEquals(INITIAL_PHEROMONE + 1, this.graph.pheromoneBetween(0, 1),
        ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.DefaultPheromoneDirectedGraph#visibilityBetween(java.lang.Integer, java.lang.Integer)}
   * .
   */
  @Test
  public void testVisibilityBetween() {
    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      for (final int j : new Range(NUMBER_OF_VERTICES)) {
        assertEquals(INITIAL_PHEROMONE, this.graph.pheromoneBetween(i, j),
            ZERO_DELTA);
      }
    }
  }

}
