/**
 * ColoredPolygonFactoryTester.java
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ColoredPolygonFactory class.
 * 
 * @author jfinkels
 */
public class ColoredPolygonFactoryTester {

  private ColoredPolygonFactory factory = null;

  @Before
  public final void setUp() {
    this.factory = new ColoredPolygonFactory();
    this.factory.setMaxPoints(MAX_POINTS);
    this.factory.setMinPoints(MIN_POINTS);
    this.factory.setMaxX(WIDTH);
    this.factory.setMaxY(HEIGHT);
  }

  /** The maximum number of vertices in a polygon. */
  public static final int MAX_POINTS = 100;
  /** The minimum number of vertices in a polygon. */
  public static final int MIN_POINTS = 50;
  /** The maximum value of the x component of a vertex of a polygon. */
  public static final int WIDTH = 10;
  /** The maximum value of the y component of a vertex of a polygon. */
  public static final int HEIGHT = 20;

  /**
   * Test method for
   * {@link jmona.example.monalisa.ColoredPolygonFactory#randomColor()}.
   */
  @Test
  public void testRandomColor() {
    final Color color1 = ColoredPolygonFactory.randomColor();
    final Color color2 = ColoredPolygonFactory.randomColor();

    assertFalse(color1.equals(color2));
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ColoredPolygonFactory#createIndividual()}.
   */
  @Test
  public void testCreateIndividual() {

    for (int j = 0; j < NUM_POLYGONS; ++j) {
      final ColoredPolygon polygon = this.factory.createObject();

      assertTrue(polygon.npoints < MAX_POINTS);
      assertTrue(polygon.npoints >= MIN_POINTS);

      for (int i = 0; i < polygon.npoints; ++i) {
        assertTrue(polygon.xpoints[i] < WIDTH);
        assertTrue(polygon.xpoints[i] >= 0);
        assertTrue(polygon.ypoints[i] < HEIGHT);
        assertTrue(polygon.ypoints[i] >= 0);
      }
    }
  }

  /** The number of polygons to create. */
  public static final int NUM_POLYGONS = 100;

}
