/**
 * TourTester.java
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
package jmona.example.tsp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import jmona.example.tsp.Tour;

import org.junit.Test;

/**
 * Test class for the Tour class.
 * 
 * @author jfinkels
 */
public class TourTester {

  /**
   * Test method for {@link jmona.example.tsp.Tour#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final Tour tour = new Tour();
    final Integer x = new Integer(0);
    final Integer y = new Integer(1);
    final Integer z = new Integer(2);

    tour.add(x);
    tour.add(y);
    tour.add(z);

    final Tour clonedTour = tour.deepCopy();

    assertNotSame(tour, clonedTour);
    assertEquals(tour.size(), clonedTour.size());

    for (int i = 0; i < tour.size(); ++i) {
      assertNotSame(tour.get(i), clonedTour.get(i));
      assertEquals(tour.get(i).intValue(), clonedTour.get(i).intValue());
    }
  }

}
