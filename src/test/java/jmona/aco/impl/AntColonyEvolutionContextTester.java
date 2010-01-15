/**
 * AntColonyEvolutionContextTester.java
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

import java.util.List;
import java.util.Vector;

import jmona.EvolutionException;
import jmona.ProcessingException;
import jmona.aco.PheromoneUpdateStrategy;
import jmona.functional.Range;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AntColonyEvolutionContext class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class AntColonyEvolutionContextTester {

  /** The initial pheromone to place on each edge in the graph under test. */
  public static final double INITIAL_PHEROMONE = 10;
  /** The number of vertices to in the graph under test. */
  public static final int NUMBER_OF_VERTICES = 10;
  /** The pheromone update strategy to use in the evolution. */
  public static final PheromoneUpdateStrategy STRATEGY = new AntCycleStrategy();
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The adjacency matrix for the graph under test. */
  private double[][] adjacencyMatrix = null;
  /** The AntColonyEvolutionContext under test. */
  private AntColonyEvolutionContext<WorkerAnt> context = null;
  /** The graph under test. */
  private DefaultPheromoneDirectedGraph graph = null;
  /** The population of Ants in the evolution. */
  private List<WorkerAnt> population = null;

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

    this.population = new Vector<WorkerAnt>();

    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      this.population.add(new WorkerAnt(i));
    }

    this.context = new AntColonyEvolutionContext<WorkerAnt>(this.population,
        this.graph, STRATEGY);
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.AntColonyEvolutionContext#executeGenerationStep()}.
   */
  @Test
  public void testExecuteGenerationStep() {
    try {
      this.context.executeGenerationStep();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

    for (final WorkerAnt ant : this.context.currentPopulation()) {
      for (final int j : new Range(NUMBER_OF_VERTICES)) {
        assertEquals(ant.memory().indexOf(j), ant.memory().lastIndexOf(j));
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.AntColonyEvolutionContext#setPheromoneImportance(double)}
   * .
   */
  @Test
  public void testSetPheromoneImportance() {
    final double newPheromoneImportance = 0.999;
    this.context.setPheromoneImportance(newPheromoneImportance);
    // TODO assertions
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.AntColonyEvolutionContext#setPheromonePersistence(double)}
   * .
   */
  @Test
  public void testSetPheromonePersistence() {
    this.context = new AntColonyEvolutionContext<WorkerAnt>(
        new Vector<WorkerAnt>(), this.graph, new AntCycleStrategy());

    final double newPheromonePersistence = 0.9;
    this.context.setPheromonePersistence(newPheromonePersistence);
    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    }

    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      for (final int j : new Range(NUMBER_OF_VERTICES)) {
        assertEquals(INITIAL_PHEROMONE * newPheromonePersistence, this.graph
            .pheromoneBetween(i, j), ZERO_DELTA);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.AntColonyEvolutionContext#setVisibilityImportance(double)}
   * .
   */
  @Test
  public void testSetVisibilityImportance() {
    final double newVisibilityImportance = 0.999;
    this.context.setVisibilityImportance(newVisibilityImportance);
    // TODO assertions
  }

}
