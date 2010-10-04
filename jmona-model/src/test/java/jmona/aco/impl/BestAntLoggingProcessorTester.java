/**
 * BestAntLoggingProcessorTester.java
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

import java.util.ArrayList;
import java.util.List;

import jmona.PopulationEvolutionContext;
import jmona.graph.DirectedGraph;
import jmona.graph.impl.AdjacencyMatrixGraph;
import jmona.impl.context.AbstractPopulationEvolutionContext;

import org.junit.Test;

/**
 * Test class for the BestAntLoggingProcessor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BestAntLoggingProcessorTester {

  /**
   * Test method for
   * {@link jmona.aco.impl.BestAntLoggingProcessor#message(jmona.PopulationEvolutionContext)}
   * .
   */
  @Test
  public void testMessage() {
    double[][] adjacencyMatrix = new double[][] { { 0.0, 1.0, 2.0 },
        { 1.0, 0.0, 4.0 }, { 1.0, 1.0, 0.0 } };
    DirectedGraph<Integer, Double> graph = new AdjacencyMatrixGraph(
        adjacencyMatrix);
    final BestAntLoggingProcessor<WorkerAnt, PopulationEvolutionContext<WorkerAnt>> p = new BestAntLoggingProcessor<WorkerAnt, PopulationEvolutionContext<WorkerAnt>>(
        graph);

    final List<WorkerAnt> population = new ArrayList<WorkerAnt>();
    WorkerAnt ant = new WorkerAnt(0);
    ant.moveTo(1);
    population.add(ant);

    ant = new WorkerAnt(1);
    ant.moveTo(2);
    population.add(ant);

    ant = new WorkerAnt(2);
    ant.moveTo(0);
    population.add(ant);

    final PopulationEvolutionContext<WorkerAnt> context = new AbstractPopulationEvolutionContext<WorkerAnt>(
        population) {
      /** This method does nothing. */
      @Override
      protected void executeGenerationStep() {
        // intentionally unimplemented
      }
    };

    // the best ant is the one with the shortest distance
    assertEquals(
        "distance " + (graph.edgeBetween(0, 1) + graph.edgeBetween(1, 0))
            + ": " + population.get(0).toString(), p.message(context));
  }

}
