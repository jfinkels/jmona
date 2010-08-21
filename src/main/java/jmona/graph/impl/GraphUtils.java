/**
 * GraphUtils.java
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
package jmona.graph.impl;

import java.util.List;

import jmona.functional.Range;
import jmona.graph.DirectedGraph;

/**
 * A utility class which contains a static method for determining the total
 * distance of a tour in a graph.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public final class GraphUtils {

  /**
   * Determines the total distance in the specified tour with respect to the
   * specified DirectedGraph.
   * 
   * A tour of length 0 or 1 has a total distance of 0.
   * 
   * @param <V>
   *          The type of vertex in the graph on which distances are measured.
   * @param tour
   *          The tour whose total distance is to be determined.
   * @param graph
   *          The graph in which the specified tour has its vertices.
   * @return The total distance (the sum of the edge weights) in the specified
   *         tour with respect to the weights defined in the specified Graph.
   */
  public static <V> double totalDistance(final List<V> tour,
      final DirectedGraph<V, Double> graph) {

    // the number of cities in the tour
    final int size = tour.size();

    // get the sum of the distances between each of the vertices (including from
    // city at index size - 1 to city at index 0)
    double sum = 0;
    for (final int i : new Range(1, size + 1)) {
      sum += graph.edgeBetween(tour.get(i - 1), tour.get(i % size));
    }

    return sum;
  }

  /** Instantiation disallowed except by subclasses. */
  protected GraphUtils() {
    // intentionally unimplemented
  }
}
