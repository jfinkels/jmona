/**
 * AdjacencyMatrixGraph.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.graph;

import java.util.Set;

import jmona.functional.HashSetFromIterable;
import jmona.functional.Range;

/**
 * A DirectedGraph backed by an adjacency matrix (a two-dimensional array)
 * storing edge weights as {@code double}s.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class AdjacencyMatrixGraph implements DirectedGraph<Integer, Double> {

  /**
   * The adjacency matrix which represents this graph.
   * 
   * The row indexes the source vertex and column indexes the target vertex. For
   * example, to get the weight of the edge from vertex 1 to vertex 2, use
   * {@code adjacencyMatrix[1][2]}. To get the weight of the edge from vertex 2
   * to vertex 0, use {@code adjacencyMatrx[2][0]}.
   */
  private double[][] adjacencyMatrix = null;

  /** The Set of all vertices in this graph. */
  private final Set<Integer> vertices;

  /**
   * Instantiates this graph with the specified adjacency matrix representation.
   * 
   * If the number of rows in the two-dimensional array is <em>m</em>, then the
   * set of all vertices in this Graph is defined to be
   * <em>{0, 1, 2, &hellip;, m-1}</em>.
   * 
   * @param initialAdjacencyMatrix
   *          The adjacency matrix which represents this graph.
   */
  public AdjacencyMatrixGraph(final double[][] initialAdjacencyMatrix) {

    // copy the array
    this.adjacencyMatrix = initialAdjacencyMatrix.clone();

    // add all the integers up to the length of the array - 1 to the set of all
    // vertices
    this.vertices = new HashSetFromIterable<Integer>(new Range(
        this.adjacencyMatrix.length));
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.graph.Graph#allVertices()
   */
  @Override
  public Set<Integer> allVertices() {
    return this.vertices;
  }

  /**
   * {@inheritDoc}
   * 
   * @param sourceVertex
   *          {@inheritDoc}
   * @param targetVertex
   *          {@inheritDoc}
   * @return The weight of the edge incident to both specified vertices,
   *         directed from the source vertex to the target vertex.
   * @see jmona.graph.DirectedGraph#edgeBetween(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public Double edgeBetween(final Integer sourceVertex,
      final Integer targetVertex) {
    return this.adjacencyMatrix[sourceVertex][targetVertex];
  }

  /**
   * Gets the number of vertices in this graph.
   * 
   * This method returns the size of the Set returned by the
   * {@link #allVertices()} method.
   * 
   * @return The number of vertices in this graph.
   */
  public int numberOfVertices() {
    return this.vertices.size();
  }
}
