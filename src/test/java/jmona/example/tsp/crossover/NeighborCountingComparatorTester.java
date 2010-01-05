/**
 * NeighborCountingComparatorTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jmona.functional.Range;

import org.junit.Test;

/**
 * Test class for the NeighborCountingComparator class.
 * 
 * @author Jeffrey Finkelstein
 */
public class NeighborCountingComparatorTester {

  /** The number of cities in the tour. */
  public static final int SIZE = 10;

  /**
   * Test method for
   * {@link jmona.example.tsp.crossover.NeighborCountingComparator#compare(java.lang.Integer, java.lang.Integer)}
   * .
   */
  @Test
  public void testCompare() {
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

    // create a comparator based on the created adjacency list
    final NeighborCountingComparator comparator = new NeighborCountingComparator(
        neighborSets);

    // iterate over each city
    for (final int i : new Range(SIZE)) {

      // every city with number less than the current city has fewer neighbors
      for (final int j : new Range(i)) {
        assertTrue(comparator.compare(j, i) < 0);
      }

      // every city always has the same number of neighbors as itself
      assertEquals(0, comparator.compare(i, i));

      // every city with number greater than the current city has more neighbors
      for (final int j : new Range(i + 1, SIZE)) {
        assertTrue(comparator.compare(j, i) > 0);
      }
    }
  }

}
