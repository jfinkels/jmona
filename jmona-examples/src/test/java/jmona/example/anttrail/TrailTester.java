/**
 * TrailTester.java
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
package jmona.example.anttrail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Trail class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class TrailTester {

  /** The height of the trail. */
  public static final int HEIGHT = 2;
  /** The width of the trail. */
  public static final int WIDTH = 2;
  /** The two-dimensional array representing where food exists on the trail. */
  private boolean[][] foodAt = null;
  /** The Trail under test. */
  private Trail trail = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.foodAt = new boolean[HEIGHT][WIDTH];
    this.foodAt[0][0] = true;
    this.foodAt[1][1] = true;

    this.trail = new Trail(this.foodAt);
  }

  /**
   * Test method for {@link jmona.example.anttrail.Trail#bounds()}.
   */
  @Test
  public void testBounds() {
    assertEquals(new CoordinatePair(WIDTH, HEIGHT), this.trail.bounds());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.Trail#foodAt(jmona.example.anttrail.CoordinatePair)}
   * .
   */
  @Test
  public void testFoodAt() {
    assertTrue(this.trail.foodAt(new CoordinatePair(0, 0)));
    assertTrue(this.trail.foodAt(new CoordinatePair(1, 1)));
    assertFalse(this.trail.foodAt(new CoordinatePair(0, 1)));
    assertFalse(this.trail.foodAt(new CoordinatePair(1, 0)));
  }

}
