/**
 * ColoredPolygonTester.java
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
package jmona.example.monalisa;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ColoredPolygon class.
 * 
 * @author jfinkels
 */
public class ColoredPolygonTester extends ColoredPolygonTestSupport {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(ColoredPolygonTester.class);
  /** The polygon under test. */
  private ColoredPolygon polygon = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.polygon = new ColoredPolygon();
    this.polygon.npoints = NPOINTS;
    this.polygon.xpoints = XPOINTS;
    this.polygon.ypoints = YPOINTS;
    this.polygon.setColor(COLOR);
  }

  /**
   * Test method for {@link jmona.example.monalisa.ColoredPolygon#color()}.
   */
  @Test
  public void testColor() {
    assertEquals(COLOR, this.polygon.color());
  }

  /**
   * Test method for {@link jmona.example.monalisa.ColoredPolygon#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final ColoredPolygon clonedPolygon = this.polygon.deepCopy();

    assertEquals(NPOINTS, this.polygon.npoints);
    assertEquals(NPOINTS, clonedPolygon.npoints);

    assertArrayEquals(XPOINTS, this.polygon.xpoints);
    assertArrayEquals(XPOINTS, clonedPolygon.xpoints);

    assertArrayEquals(YPOINTS, this.polygon.ypoints);
    assertArrayEquals(YPOINTS, clonedPolygon.ypoints);

    assertTrue(samePolygon(clonedPolygon, this.polygon));

    assertEquals(COLOR, clonedPolygon.color());
    assertEquals(COLOR, this.polygon.color());
  }

  /**
   * Test method for {@link jmona.example.monalisa.ColoredPolygon#toString()}.
   */
  @Test
  public void testToString() {
    LOG.debug(this.polygon.toString());
  }
}
