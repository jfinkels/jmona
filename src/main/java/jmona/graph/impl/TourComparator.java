/**
 * TourComparator.java
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

import java.util.Comparator;
import java.util.List;

import jmona.graph.DirectedGraph;

/**
 * A comparator for total distances of tours.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class TourComparator implements Comparator<List<Integer>> {

  /** The graph on which to measure distances. */
  private final DirectedGraph<Integer, Double> graph;

  /**
   * Instantiates this Comparator with the specified graph with which to
   * determine distances of tours.
   * 
   * @param initialGraph
   *          The graph with which to determine distances of tours.
   */
  public TourComparator(final DirectedGraph<Integer, Double> initialGraph) {
    this.graph = initialGraph;
  }

  /**
   * Compares the distances of the tours specified by the two Lists.
   * 
   * @param list1
   *          A tour.
   * @param list2
   *          Another tour.
   * @return {@inheritDoc}
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final List<Integer> list1, final List<Integer> list2) {
    return Double.compare(GraphUtils.totalDistance(list1, this.graph),
        GraphUtils.totalDistance(list2, this.graph));
  }

}
