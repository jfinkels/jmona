/**
 * DefaultPheromoneDirectedGraph.java
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

import jmona.aco.PheromoneDirectedGraph;
import jmona.functional.Range;
import jmona.graph.AdjacencyMatrixGraph;

/**
 * A PheromoneDirectedGraph with Integers as vertices and Doubles as edge
 * weights.
 * 
 * This class provides a DirectedGraph with pheromone and visibility on each
 * edge, as well as methods for adding pheromone to and evaporating pheromone
 * from edges.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class DefaultPheromoneDirectedGraph extends AdjacencyMatrixGraph
    implements PheromoneDirectedGraph<Integer, Double> {

  /**
   * The amount of pheromone on each edge between vertices.
   * 
   * The amount of pheromone on the edge from vertex <em>i</em> to vertex
   * <em>j</em> can be accessed by {@code this.pheromone[i][j]}.
   */
  private final double[][] pheromone;
  /**
   * The visibility constant between each pair of vertices.
   * 
   * If the distance from vertex <em>i</em> to vertex <em>j</em> is
   * <em>d<sub>i,j</sub></em>, then the visibility on that edge is equal to
   * <em>1/d<sub>i,j</sub></em>. The visibility from vertex <em>i</em> to vertex
   * <em>j</em> can be accessed by {@code this.visibility[i][j]}.
   */
  private final double[][] visibility;

  /**
   * Instantiates this graph by calling the corresponding constructor of the
   * superclass, then assigning the specified initial pheromone value to each
   * edge and determining and assigning the fixed visibility on each edge.
   * 
   * @param initialAdjacencyMatrix
   *          The adjacency matrix specifying the distances between each pair of
   *          vertices.
   * @param initialPheromone
   *          The initial amount of pheromone to place on each edge.
   */
  public DefaultPheromoneDirectedGraph(final double[][] initialAdjacencyMatrix,
      final double initialPheromone) {
    super(initialAdjacencyMatrix);

    final int numberOfVertices = this.allVertices().size();

    this.pheromone = new double[numberOfVertices][numberOfVertices];
    this.visibility = new double[numberOfVertices][numberOfVertices];

    for (final int i : new Range(numberOfVertices)) {
      for (final int j : new Range(numberOfVertices)) {
        this.pheromone[i][j] = initialPheromone;
        this.visibility[i][j] = 1.0 / this.edgeBetween(i, j);
      }
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @param sourceVertex
   *          {@inheritDoc}
   * @param targetVertex
   *          {@inheritDoc}
   * @param pheromoneToAdd
   *          {@inheritDoc}
   * @see jmona.aco.PheromoneDirectedGraph#addPheromoneBetween(java.lang.Object,
   *      java.lang.Object, double)
   */
  @Override
  public void addPheromoneBetween(final Integer sourceVertex,
      final Integer targetVertex, final double pheromoneToAdd) {
    this.pheromone[sourceVertex][targetVertex] += pheromoneToAdd;
  }

  /**
   * {@inheritDoc}
   * 
   * @param persistence
   *          {@inheritDoc}
   * @see jmona.aco.PheromoneDirectedGraph#evaporate(double)
   */
  @Override
  public void evaporate(final double persistence) {
    for (final int i : new Range(this.pheromone.length)) {
      for (final int j : new Range(this.pheromone[0].length)) {
        this.pheromone[i][j] *= persistence;
      }
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @param sourceVertex
   *          {@inheritDoc}
   * @param targetVertex
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jmona.aco.PheromoneDirectedGraph#pheromoneBetween(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public double pheromoneBetween(final Integer sourceVertex,
      final Integer targetVertex) {
    return this.pheromone[sourceVertex][targetVertex];
  }

  /**
   * {@inheritDoc}
   * 
   * @param sourceVertex
   *          {@inheritDoc}
   * @param targetVertex
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jmona.aco.PheromoneDirectedGraph#visibilityBetween(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public double visibilityBetween(final Integer sourceVertex,
      final Integer targetVertex) {
    return this.visibility[sourceVertex][targetVertex];
  }

}
