/**
 * SameNumberOfNeighborsCondition.java
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

import java.util.Map;
import java.util.Set;

import jfcommon.functional.Predicate;

/**
 * A condition which determines whether a city has a specified number of
 * neighbors.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
class SameNumberOfNeighborsCondition implements Predicate<Integer> {

  /** The adjacency list as a mapping from city to neighbors of that city. */
  private final Map<Integer, Set<Integer>> neighborSets;

  /** The number of neighbors which a specified city is to match. */
  private int numberOfNeighborsToMatch = 0;

  /**
   * Instantiate this Condition with the specified adjacency list.
   * 
   * @param initialNeighborSets
   *          The mapping from city to neighbors of that city.
   */
  public SameNumberOfNeighborsCondition(
      final Map<Integer, Set<Integer>> initialNeighborSets) {
    this.neighborSets = initialNeighborSets;
  }

  /**
   * Determines whether the number of neighbors of the specified city is equals
   * to the value of the {@link #numberOfNeighborsToMatch} property.
   * 
   * @param city
   *          The city whose neighbors will be counted.
   * @return Whether the specified city has the same number of neighbors as the
   *         number to match.
   * @see jfcommon.functional.Function#execute(java.lang.Object)
   */
  @Override
  public Boolean execute(final Integer city) {
    return this.numberOfNeighborsToMatch == this.neighborSets.get(city).size();
  }

  /**
   * Set the number of neighbors which a city specified in the
   * {@link #execute(Integer)} method is to match.
   * 
   * @param newNumberOfNeighborsToMatch
   *          The number of neighbors which a specified city is to match.
   */
  public void setNumberOfNeighborsToMatch(final int newNumberOfNeighborsToMatch) {
    this.numberOfNeighborsToMatch = newNumberOfNeighborsToMatch;
  }

}
