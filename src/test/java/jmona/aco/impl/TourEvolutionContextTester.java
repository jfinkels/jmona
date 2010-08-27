/**
 * TourEvolutionContextTester.java
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
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jfcommon.functional.Functional;
import jfcommon.functional.Range;
import jfcommon.test.TestUtils;
import jmona.EvolutionException;
import jmona.SelectionException;
import jmona.aco.PheromoneUpdateStrategy;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the TourEvolutionContext class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class TourEvolutionContextTester {

  /** The initial pheromone to place on each edge in the graph under test. */
  public static final double INITIAL_PHEROMONE = 10;
  /** The number of selections to make for the next vertex to which to move. */
  public static final int NUM_SELECTIONS = 100000;
  /** The number of vertices to in the graph under test. */
  public static final int NUMBER_OF_VERTICES = 3;
  /** The pheromone update strategy to use in the evolution. */
  public static final PheromoneUpdateStrategy STRATEGY = new AntCycleStrategy();
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The adjacency matrix for the graph under test. */
  private double[][] adjacencyMatrix = null;
  /** The TourEvolutionContext under test. */
  private TourEvolutionContext<WorkerAnt> context = null;
  /** The graph under test. */
  private DefaultPheromoneDirectedGraph graph = null;

  /** The population of Ants in the evolution. */
  private List<WorkerAnt> population = null;

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

    this.population = new Vector<WorkerAnt>();

    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      this.population.add(new WorkerAnt(i));
    }

    this.context = new TourEvolutionContext<WorkerAnt>(this.population,
        this.graph, STRATEGY);
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.TourEvolutionContext#chooseNextVertex(jmona.aco.Ant)}
   * .
   */
  @Test
  public void testChooseNextVertex() {
    final WorkerAnt ant = this.population.get(0);

    final Map<Integer, Integer> selections = new HashMap<Integer, Integer>();
    for (final int i : new Range(NUMBER_OF_VERTICES)) {
      selections.put(i, 0);
    }

    try {
      int selection = 0;
      for (final int i : new Range(NUM_SELECTIONS)) {
        selection = this.context.chooseNextVertex(ant);
        selections.put(selection, selections.get(selection) + 1);
      }
    } catch (final SelectionException exception) {
      TestUtils.fail(exception);
    }

    final int totalSelections = Functional.sumInteger(selections.values());
    assertEquals(totalSelections, NUM_SELECTIONS);

    assertEquals(0, selections.get(0).intValue());
    for (final int i : new Range(2, NUMBER_OF_VERTICES)) {
      assertTrue(selections.get(i - 1) > selections.get(i));
    }
    
    final double delta = selections.get(2) * 0.1;
    if (NUMBER_OF_VERTICES == 3) {
      assertEquals(selections.get(1), selections.get(2) * 2, delta);
    }
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.TourEvolutionContext#executeGenerationStep()}.
   */
  @Test
  public void testExecuteGenerationStep() {
    for (final int i : new Range(NUMBER_OF_VERTICES - 1)) {
      try {
        this.context.executeGenerationStep();
      } catch (final EvolutionException exception) {
        TestUtils.fail(exception);
      }

      for (final WorkerAnt ant : this.context.currentPopulation()) {
        for (final int j : new Range(NUMBER_OF_VERTICES)) {
          assertEquals(ant.memory().indexOf(j), ant.memory().lastIndexOf(j));
        }
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.TourEvolutionContext#setPheromoneImportance(double)}.
   */
  @Test
  public void testSetPheromoneImportance() {
    final double newPheromoneImportance = 0.999;
    this.context.setPheromoneImportance(newPheromoneImportance);
    // TODO assertions
  }

  /**
   * Test method for
   * {@link jmona.aco.impl.TourEvolutionContext#setVisibilityImportance(double)}
   * .
   */
  @Test
  public void testSetVisibilityImportance() {
    final double newVisibilityImportance = 0.999;
    this.context.setVisibilityImportance(newVisibilityImportance);
    // TODO assertions
  }

}
