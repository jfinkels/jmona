/**
 * GraphSolutionLandscapeTester.java
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
package jmona.bco.impl;

import static org.junit.Assert.fail;
import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.bco.Solution;
import jmona.graph.DirectedGraph;
import jmona.graph.impl.AdjacencyMatrixGraph;
import jmona.impl.fitness.MinimizingFitnessFunction;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class GraphSolutionLandscapeTester {

  private GraphSolutionLandscape<Integer, Double> landscape = null;
  
  @Before
  public final void setup() {
    final double[][] adjacency = { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } };
    final DirectedGraph<Integer, Double> graph = new AdjacencyMatrixGraph(
        adjacency);
    final FitnessFunction<Double> fitnessFunction = new MinimizingFitnessFunction<Double>() {
      @Override
      public double rawFitness(final Double individual) throws FitnessException {
        return individual;
      }
    };
    this.landscape = new GraphSolutionLandscape<Integer, Double>(
        graph, fitnessFunction);
  }

  /**
   * Test method for
   * {@link jmona.bco.impl.GraphSolutionLandscape#GraphSolutionLandscape(jmona.graph.DirectedGraph, jmona.FitnessFunction)}
   * .
   */
  @Test
  public void testGraphSolutionLandscape() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.bco.impl.GraphSolutionLandscape#getNextComponent(jmona.bco.Solution)}
   * .
   */
  @Test
  public void testGetNextComponent() {
    final Solution<Integer> solution = new ExampleSolution();
    solution.append(0);
    //this.landscape.getNextComponent();
  }

}
