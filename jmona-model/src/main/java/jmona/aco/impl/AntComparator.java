/**
 * AntComparator.java
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

import java.io.Serializable;
import java.util.Comparator;

import jmona.aco.Ant;
import jmona.graph.DirectedGraph;
import jmona.graph.impl.TourComparator;

/**
 * Compares Ants with respect to the length of the tours stored in the memory of
 * each Ant.
 * 
 * @author Jeffrey Finkelstein
 * @param <A>
 *          The type of Ant to compare.
 * @since 0.5
 */
public class AntComparator<A extends Ant> implements Comparator<A>,
    Serializable {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -5424779888400377470L;
  
  /** The comparator which compares distances of tours. */
  private final TourComparator tourComparator;

  /**
   * Instantiates this Comparator with the specified graph with which to
   * determine distances of tours.
   * 
   * @param initialGraph
   *          The graph with which to determine distances of tours.
   */
  public AntComparator(final DirectedGraph<Integer, Double> initialGraph) {
    this.tourComparator = new TourComparator(initialGraph);
  }

  /**
   * Compares the two specified Ants with respect to the length of the tours in
   * their current memories.
   * 
   * @param ant1
   *          An ant.
   * @param ant2
   *          Another ant.
   * @return The result of comparing the tours specified in the memory of the
   *         Ants.
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final A ant1, final A ant2) {
    return this.tourComparator.compare(ant1.memory(), ant2.memory());
  }

}
