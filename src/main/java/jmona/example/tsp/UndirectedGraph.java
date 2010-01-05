/**
 * UndirectedGraph.java
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
package jmona.example.tsp;

/**
 * An undirected graph.
 * 
 * @param <V>
 *          The type of the vertices in this graph.
 * @param <E>
 *          The type of the edges in this graph.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public interface UndirectedGraph<V, E> extends DirectedGraph<V, E> {
  /**
   * Get the edge between the two specified vertices.
   * 
   * This method is symmetric on vertices. In other words, if {@code v1} and
   * {@code v2} are two vertices, then {@code edgeBetween(v1, v2)} returns the
   * same value as {@code edgeBetween(v2, v1)}, because this graph is
   * undirected.
   * 
   * @param vertex1
   *          A vertex in this graph.
   * @param vertex2
   *          Another vertex in this graph.
   * @return The edge between the two specified vertices.
   */
  @Override
  E edgeBetween(final V vertex1, final V vertex2);

}
