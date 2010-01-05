/**
 * EdgeRecombinationCrossoverFunctionTester.java
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

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jmona.functional.Range;

import org.junit.Test;

/**
 * Test class for the EdgeRecombinationCrossoverFunction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class EdgeRecombinationCrossoverFunctionTester extends
    AbstractTSPCrossoverFunctionTester {

  /** The size of a tour to create. */
  public static final int SIZE = 10;

  /** Instantiate this test class with a EdgeRecombinationCrossoverFunction. */
  public EdgeRecombinationCrossoverFunctionTester() {
    super(new EdgeRecombinationCrossoverFunction());
  }

  /**
   * Test method for
   * {@link jmona.example.tsp.crossover.EdgeRecombinationCrossoverFunction#removeFromAllNeighborSets(int, java.util.Map)}
   * .
   */
  @Test
  public void testRemoveAllFromNeighborSets() {
    final Map<Integer, Set<Integer>> neighborSets = new HashMap<Integer, Set<Integer>>();

    Set<Integer> neighbors = null;
    for (final int i : new Range(SIZE)) {

      neighbors = new HashSet<Integer>();

      for (final int j : new Range(SIZE)) {
        neighbors.add((i + j) % SIZE);
      }

      neighborSets.put(i, neighbors);
    }
    
    final int cityToRemove = 1;
    EdgeRecombinationCrossoverFunction.removeFromAllNeighborSets(cityToRemove,
        neighborSets);
    
    for (final Set<Integer> neighborCities : neighborSets.values()) {
      assertFalse(neighborCities.contains(cityToRemove));
    }
  }
}
