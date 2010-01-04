/**
 * ColoredPolygonTestSupport.java
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

import java.awt.Color;

import jmona.functional.Range;

/**
 * Base class for tests involving ColoredPolygon objects.
 * 
 * @author Jeffrey Finkelstein
 */
public abstract class ColoredPolygonTestSupport {
  /** The maximum x component for a point of a polygon. */
  public static final int WIDTH = 10;
  /** The initial x components of the vertices of a polygon. */
  public static final int[] XPOINTS = new int[] { 0, 1, 2 };
  /** The initial y components of the vertices of a polygon. */
  public static final int[] YPOINTS = new int[] { 3, 4, 5 };
  /** The maximum y component for a point of a polygon. */
  public static final int HEIGHT = 20;
  /** The number of points in a polygon. */
  public static final int NPOINTS = 3;
  /** The color of the polygon. */
  public static final Color COLOR = Color.GRAY;
  /** The number of polygons to create. */
  public static final int NUM_POLYGONS = 100;
  /** The maximum number of vertices in a polygon. */
  public static final int MAX_POINTS = 100;
  /** The minimum number of vertices in a polygon. */
  public static final int MIN_POINTS = 50;

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

    for (final int i : new Range(polygon1.npoints)) {
      if (polygon1.xpoints[i] != polygon2.xpoints[i]
          || polygon1.ypoints[i] != polygon2.ypoints[i]) {
        return false;
      }
    }

    return true;
  }

  /** Instantiation disallowed except by subclasses. */
  protected ColoredPolygonTestSupport() {
    // intentionally unimplemented
  }
}
