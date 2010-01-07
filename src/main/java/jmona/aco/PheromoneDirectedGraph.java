/**
 * PheromoneDirectedGraph.java
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
package jmona.aco;

import jmona.graph.DirectedGraph;

/**
 * A DirectedGraph which has pheromone on its edges.
 * 
 * @author Jeffrey Finkelstein
 * @param <V>
 *          The type of vertex in this graph.
 * @param <E>
 *          The type of edge in this graph.
 * @since 0.5
 */
public interface PheromoneDirectedGraph<V, E> extends DirectedGraph<V, E> {
  /**
   * Adds the specified amount of pheromone to the edge between the two
   * specified vertices.
   * 
   * @param sourceVertex
   *          The source vertex.
   * @param targetVertex
   *          The target vertex.
   * @param pheromoneToAdd
   *          The amount of pheromone to add to the edge between the two
   *          specified vertices.
   */
  void addPheromoneBetween(final V sourceVertex, final V targetVertex,
      final double pheromoneToAdd);

  /**
   * Evaporates pheromone from each edge in the graph by the specified
   * persistence scaling factor (that is, the percentage of pheromone which will
   * remain after evaporation).
   * 
   * For example, a persistence factor of 0.25 will leave 25% of the original
   * amount of pheromone on each edge, and a persistence factor of 0.90 will
   * leave 90% of the original amount of pheromone on each edge.
   * 
   * @param persistence
   *          A value between 0 and 1, inclusive, by which to scale the amount
   *          of pheromone on each edge.
   */
  void evaporate(final double persistence);

  /**
   * Gets the amount of pheromone on the edge between the two specified
   * vertices.
   * 
   * @param sourceVertex
   *          The source vertex.
   * @param targetVertex
   *          The target vertex.
   * @return The amount of pheromone between the two specified vertices.
   */
  double pheromoneBetween(final V sourceVertex, final V targetVertex);

  /**
   * Gets the visibility of the target vertex as seen by an individual on the
   * source vertex.
   * 
   * @param sourceVertex
   *          The target vertex.
   * @param targetVertex
   *          The source vertex.
   * @return The visibility of the target vertex as seen by an individual on the
   *         source vertex.
   */
  double visibilityBetween(final V sourceVertex, final V targetVertex);
}
