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

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * Test class for the ColoredPolygon class.
 * 
 * @author jfinkels
 */
public class ColoredPolygonTester {

  /**
   * Determines whether the specified polygons have the same color and the same
   * coordinates.
   * 
   * @param polygon1
   *          A polygon.
   * @param polygon2
   *          Another polygon.
   * @return Whether the two polygons have the same color and the same
   *         coordinates.
   */
  protected static boolean samePolygon(final ColoredPolygon polygon1,
      final ColoredPolygon polygon2) {
    if (polygon1.npoints != polygon2.npoints) {
      return false;
    }

    if (!polygon1.color().equals(polygon2.color())) {
      return false;
    }

    for (int i = 0; i < polygon1.npoints; ++i) {
      if (polygon1.xpoints[i] != polygon2.xpoints[i]
          || polygon1.ypoints[i] != polygon2.ypoints[i]) {
        return false;
      }
    }

    return true;
  }

  /**
   * Test method for {@link jmona.example.monalisa.ColoredPolygon#color()}.
   */
  @Test
  public void testColor() {
    final ColoredPolygon polygon = new ColoredPolygon();
    polygon.setColor(Color.GRAY);
    assertEquals(Color.GRAY, polygon.color());
  }

  /**
   * Test method for {@link jmona.example.monalisa.ColoredPolygon#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final int npoints = 3;
    final int[] xpoints = new int[] { 0, 1, 2 };
    final int[] ypoints = new int[] { 3, 4, 5 };
    final Color color = Color.GRAY;

    final ColoredPolygon polygon = new ColoredPolygon();
    polygon.npoints = npoints;
    polygon.xpoints = xpoints;
    polygon.ypoints = ypoints;
    polygon.setColor(color);

    final ColoredPolygon clonedPolygon = polygon.deepCopy();
    assertEquals(npoints, polygon.npoints);
    assertEquals(npoints, clonedPolygon.npoints);
    assertArrayEquals(xpoints, polygon.xpoints);
    assertArrayEquals(xpoints, clonedPolygon.xpoints);
    assertArrayEquals(ypoints, polygon.ypoints);
    assertArrayEquals(ypoints, clonedPolygon.ypoints);

    assertTrue(samePolygon(clonedPolygon, polygon));

    assertEquals(color, clonedPolygon.color());
    assertEquals(color, polygon.color());
  }

}
