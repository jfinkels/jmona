/**
 * ColoredPolygonFactoryTester.java
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
package jmona.example.monalisa;

import static org.junit.Assert.assertTrue;
import jmona.functional.Range;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ColoredPolygonFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ColoredPolygonFactoryTester extends ColoredPolygonTestSupport {
  /** The factory under test. */
  private ColoredPolygonFactory factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new ColoredPolygonFactory();
    this.factory.setMaxPoints(MAX_POINTS);
    this.factory.setMinPoints(MIN_POINTS);
    this.factory.setMaxX(WIDTH);
    this.factory.setMaxY(HEIGHT);
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ColoredPolygonFactory#createObject()}.
   */
  @Test
  public void testCreateObject() {

    for (int j = 0; j < NUM_POLYGONS; ++j) {
      final ColoredPolygon polygon = this.factory.createObject();

      assertTrue(polygon.npoints <= MAX_POINTS);
      assertTrue(polygon.npoints >= MIN_POINTS);

      for (final int i : new Range(polygon.npoints)) {
        assertTrue(polygon.xpoints[i] <= WIDTH);
        assertTrue(polygon.xpoints[i] >= 0);
        assertTrue(polygon.ypoints[i] <= HEIGHT);
        assertTrue(polygon.ypoints[i] >= 0);
      }
    }
  }
}
