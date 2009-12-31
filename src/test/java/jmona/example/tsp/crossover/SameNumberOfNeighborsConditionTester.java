/**
 * SameNumberOfNeighborsConditionTester.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jmona.impl.Range;

import org.junit.Test;

/**
 * Test class for the SameNumberOfNeighborsCondition class.
 * 
 * @author Jeffrey Finkelstein
 */
public class SameNumberOfNeighborsConditionTester {

  /** The number of cities in the tour. */
  public static final int SIZE = 10;

  /**
   * Test method for
   * {@link jmona.example.tsp.crossover.SameNumberOfNeighborsCondition#execute(java.lang.Integer)}
   * .
   */
  @Test
  public void testExecute() {
    final Map<Integer, Set<Integer>> neighborSets = new HashMap<Integer, Set<Integer>>();

    // iterate over the integers from 0 to SIZE
    Set<Integer> neighbors = null;
    for (final int i : new Range(SIZE)) {
      neighbors = new HashSet<Integer>();

      // add the same number of neighbors as the number of the city
      for (final int j : new Range(i)) {
        neighbors.add(j);
      }

      neighborSets.put(i, neighbors);
    }

    // create the condition based on the created adjacency list
    final SameNumberOfNeighborsCondition condition = new SameNumberOfNeighborsCondition(
        neighborSets);

    // iterate over each city
    for (final int i : new Range(SIZE)) {

      // set the number of neighbors to match
      condition.setNumberOfNeighborsToMatch(neighborSets.get(i).size());

      // iterate over each other city
      for (final int j : new Range(SIZE)) {
        if (j == i) {
          assertTrue(condition.execute(j));
        } else {
          assertFalse(condition.execute(j));
        }
      }
    }
  }
}
