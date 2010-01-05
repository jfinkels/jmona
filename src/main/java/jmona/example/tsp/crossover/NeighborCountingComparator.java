/**
 * NeighborCountingComparator.java
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
package jmona.example.tsp.crossover;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

/**
 * A comparator which counts the number of neighbors of a city in a traveling
 * salesman problem tour.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
class NeighborCountingComparator implements Comparator<Integer>, Serializable {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -6434172164972282414L;
  /** The adjacency list as a mapping from city to neighbors of that city. */
  private final Map<Integer, Set<Integer>> neighborSets;

  /**
   * Instantiate this Comparator with the specified adjacency list.
   * 
   * @param initialNeighborSets
   *          The mapping from city to neighbors of that city.
   */
  public NeighborCountingComparator(
      final Map<Integer, Set<Integer>> initialNeighborSets) {
    this.neighborSets = initialNeighborSets;
  }

  /**
   * Compare the number of neighbors of the specified cities.
   * 
   * @param city1
   *          A city.
   * @param city2
   *          Another city.
   * @return The difference between the two specified cities.
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final Integer city1, final Integer city2) {
    return this.neighborSets.get(city1).size()
        - this.neighborSets.get(city2).size();
  }

}
