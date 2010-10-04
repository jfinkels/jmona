/**
 * DistanceGetterTester.java
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
import jmona.graph.DirectedGraph;
import jmona.graph.impl.AdjacencyMatrixGraph;

import org.junit.Test;

/**
 * Test class for the DistanceGetter class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class DistanceGetterTester {

  /**
   * Test method for
   * {@link jmona.aco.impl.DistanceGetter#execute(jmona.aco.Ant)}.
   */
  @Test
  public void testExecute() {
    double[][] adjacencyMatrix = new double[][] { { 0.0, 1.0 }, { 2.0, 0.0 } };
    DirectedGraph<Integer, Double> graph = new AdjacencyMatrixGraph(
        adjacencyMatrix);
    DistanceGetter<WorkerAnt> f = new DistanceGetter<WorkerAnt>(graph);

    WorkerAnt ant = new WorkerAnt(0);
    ant.moveTo(1);
    assertEquals(3.0, f.execute(ant), 0.0);

    ant = new WorkerAnt(1);
    ant.moveTo(0);
    assertEquals(3.0, f.execute(ant), 0.0);

    adjacencyMatrix = new double[][] { { 0.0, 100.0 }, { -50.0, 0.0 } };
    graph = new AdjacencyMatrixGraph(adjacencyMatrix);
    f = new DistanceGetter<WorkerAnt>(graph);

    ant = new WorkerAnt(0);
    ant.moveTo(1);
    assertEquals(50.0, f.execute(ant), 0.0);

    ant = new WorkerAnt(1);
    ant.moveTo(0);
    assertEquals(50.0, f.execute(ant), 0.0);

  }

}
