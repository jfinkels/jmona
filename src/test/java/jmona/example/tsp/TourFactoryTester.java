/**
 * TourFactoryTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import jmona.functional.Range;

import org.junit.Test;

/**
 * Test class for the TourFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class TourFactoryTester {
  /** The number of cities in a Tour generated by the TourFactory. */
  public static final int NUM_CITIES = 10;

  /**
   * Test method for {@link jmona.example.tsp.TourFactory#createObject()} .
   */
  @Test
  public void testCreateObject() {
    final TourFactory factory = new TourFactory();
    factory.setSize(NUM_CITIES);
    final List<Integer> tour = factory.createObject();

    assertEquals(NUM_CITIES, tour.size());
    for (final int i : new Range(NUM_CITIES)) {
      assertTrue(tour.contains(i));
    }
  }

}
