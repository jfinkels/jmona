/**
 * PathLoggingPostProcessorTester.java
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

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.LoggingException;
import jmona.functional.Range;
import joptsimple.internal.Strings;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PathLoggingPostProcessor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class PathLoggingPostProcessorTester {

  /** The initial pheromone to place on each edge in the graph. */
  public static final double INITIAL_PHEROMONE = 10;
  /** The number of vertices to in the graph. */
  public static final int NUMBER_OF_VERTICES = 10;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The adjacency matrix for the graph. */
  private double[][] adjacencyMatrix = null;
  /** The graph on which the Ants live. */
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
   * {@link jmona.aco.impl.PathLoggingPostProcessor#message(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testMessageEvolutionContextOfA() {
    final PathLoggingPostProcessor<WorkerAnt> processor = new PathLoggingPostProcessor<WorkerAnt>();

    final WorkerAnt ant1 = new WorkerAnt(0);
    ant1.moveTo(1);
    ant1.moveTo(2);

    final WorkerAnt ant2 = new WorkerAnt(1);
    ant2.moveTo(2);
    ant2.moveTo(0);

    final List<WorkerAnt> population = new Vector<WorkerAnt>();
    population.add(ant1);
    population.add(ant2);

    final AntColonyEvolutionContext<WorkerAnt> context = new AntColonyEvolutionContext<WorkerAnt>(
        population, this.graph, new AntCycleStrategy());

    String result = Strings.EMPTY;
    try {
      result = processor.message(context);
    } catch (final LoggingException exception) {
      TestUtils.fail(exception);
    }

    assertTrue(result.contains("[0, 1, 2]"));
    assertTrue(result.contains("[1, 2, 0]"));

    processor.setGraph(this.graph);

    try {
      result = processor.message(context);
    } catch (final LoggingException exception) {
      TestUtils.fail(exception);
    }

    assertTrue(result.contains("[0, 1, 2], total distance: 6.0"));
    assertTrue(result.contains("[1, 2, 0], total distance: 6.0"));
  }

}
