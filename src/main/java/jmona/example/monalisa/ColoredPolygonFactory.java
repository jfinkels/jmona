/**
 * ColoredPolygonFactory.java
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

import java.awt.Color;

import jmona.Factory;
import jmona.functional.Range;
import jmona.impl.Util;

/**
 * A factory which creates ColoredPolygon objects.
 * 
 * @author Jeffrey Finkelstein
 */
public class ColoredPolygonFactory implements Factory<ColoredPolygon> {
  /** The default maximum number of points in a polygon. */
  public static final int DEFAULT_MAX_POINTS = 10;
  /** The default maximum x value of a polygon. */
  public static final int DEFAULT_MAX_X = 256;
  /** The default maximum y value of a polygon. */
  public static final int DEFAULT_MAX_Y = 256;
  /** The default minimum number of points in a polygon. */
  public static final int DEFAULT_MIN_POINTS = 3;
  /** The maximum value for a red, green, blue, or alpha color value. */
  public static final int MAX_VALUE = 0xFF;

  /**
   * Generate a random Color (including random alpha value).
   * 
   * @return A random Color.
   */
  protected static Color randomColor() {
    final int alpha = Util.RANDOM.nextInt(MAX_VALUE);
    final int red = Util.RANDOM.nextInt(MAX_VALUE);
    final int green = Util.RANDOM.nextInt(MAX_VALUE);
    final int blue = Util.RANDOM.nextInt(MAX_VALUE);

    final Color result = new Color(alpha, red, green, blue);

    return result;
  }

  /** The maximum number of points in a polygon. */
  private int maxPoints = DEFAULT_MAX_POINTS;
  /** The maximum x value of a polygon. */
  private int maxX = DEFAULT_MAX_X;
  /** The maximum y value of a polygon. */
  private int maxY = DEFAULT_MAX_Y;
  /** The minimum number of points in a polygon. */
  private int minPoints = DEFAULT_MIN_POINTS;

  /**
   * Generate a random ColoredPolygon.
   * 
   * @return A random ColoredPolygon.
   * @see jmona.Factory#createObject()
   */
  @Override
  public ColoredPolygon createObject() {
    // randomly select the number of points in the polygon between min and max
    int numPoints = this.minPoints
        + Util.RANDOM.nextInt(this.maxPoints - this.minPoints);

    // create new arrays to contain the x and y coordinates
    int[] xpoints = new int[numPoints];
    int[] ypoints = new int[numPoints];

    // iterate over the number of points in the array
    for (final int i : new Range(numPoints)) {
      // randomly select a new x and y less than the max width and height
      xpoints[i] = Util.RANDOM.nextInt(this.maxX);
      ypoints[i] = Util.RANDOM.nextInt(this.maxY);
    }

    // create a new ColoredPolygon with the specified points
    final ColoredPolygon result = new ColoredPolygon();
    result.npoints = numPoints;
    result.xpoints = xpoints;
    result.ypoints = ypoints;

    // set the color of the ColoredPolygon to be a random color
    result.setColor(randomColor());

    return result;
  }

  /**
   * Set the maximum number of points in a polygon.
   * 
   * @param newMaxPoints
   *          The maximum number of points in a polygon.
   */
  public void setMaxPoints(final int newMaxPoints) {
    this.maxPoints = newMaxPoints;
  }

  /**
   * Set the maximum x value of a polygon.
   * 
   * @param newMaxX
   *          The maximum x value of a polygon.
   */
  public void setMaxX(final int newMaxX) {
    this.maxX = newMaxX;
  }

  /**
   * Set the maximum y value of a polygon.
   * 
   * @param newMaxY
   *          The maximum y value of a polygon.
   */
  public void setMaxY(final int newMaxY) {
    this.maxY = newMaxY;
  }

  /**
   * Set the minimum number of points in a polygon.
   * 
   * @param newMinPoints
   *          The minimum number of points in a polygon.
   */
  public void setMinPoints(final int newMinPoints) {
    this.minPoints = newMinPoints;
  }

}
