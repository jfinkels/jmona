/**
 * ColoredPolygonMutationFunction.java
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

import jmona.MutationFunction;
import jmona.impl.Util;

/**
 * A MutationFunction which mutates ColoredPolygons by randomly changing the
 * color and coordinates of vertices of the polygon.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ColoredPolygonMutationFunction implements
    MutationFunction<ColoredPolygon> {
  /** The maximum value of the red, green, blue, and alpha fields of a color. */
  public static final int MAX_COLOR_VALUE = 255;
  /** The maximum value to shift when mutating colors. */
  public static final int DEFAULT_COLOR_SHIFT = 32;
  /** The maximum height beyond which no polygon may have points. */
  private final int height;
  /** The maximum width beyond which no polygon may have points. */
  private final int width;

  /**
   * Instantiate this MutationFunction with the specified maximum width and
   * height for vertices of a polygon.
   * 
   * @param initialWidth
   *          The maximum width for vertices of a polygon.
   * @param initialHeight
   *          The maximum height for vertices of a polygon.
   */
  public ColoredPolygonMutationFunction(final int initialWidth,
      final int initialHeight) {
    this.width = initialWidth;
    this.height = initialHeight;
  }

  /** The maximum value for color shift during mutation of a polygon's color. */
  private int colorShift = DEFAULT_COLOR_SHIFT;

  /**
   * Set the maximum value for color shift during mutation of a polygon's color.
   * 
   * @param newColorShift
   *          The maximum value for color shift during mutation of a polygon's
   *          color.
   */
  public void setColorShift(final int newColorShift) {
    this.colorShift = newColorShift;
  }

  /**
   * Mutate the specified color to a nearby color without changing the alpha
   * value.
   * 
   * @param color
   *          The input color.
   * @return The mutated copy of the input color.
   */
  protected Color mutate(final Color color) {
    // get the shifted colors
    int newRed = color.getRed()
        + (Util.RANDOM.nextInt(this.colorShift * 2) - this.colorShift);
    int newGreen = color.getGreen()
        + (Util.RANDOM.nextInt(this.colorShift * 2) - this.colorShift);
    int newBlue = color.getBlue()
        + (Util.RANDOM.nextInt(this.colorShift * 2) - this.colorShift);

    // ensure that the values are within the permissible range of values for
    // fields of a color, that is, between 0 and 255, inclusive
    newRed = Math.max(Math.min(newRed, MAX_COLOR_VALUE), 0);
    newGreen = Math.max(Math.min(newGreen, MAX_COLOR_VALUE), 0);
    newBlue = Math.max(Math.min(newBlue, MAX_COLOR_VALUE), 0);

    return new Color(newRed, newGreen, newBlue, color.getAlpha());
  }

  /**
   * Mutate the specified ColoredPolygon by randomly changing the coordinates of
   * one of its vertices.
   * 
   * @param individual
   *          The ColoredPolygon to mutate.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final ColoredPolygon individual) {
    final Color newColor = mutate(individual.color());
    individual.setColor(newColor);

    // choose a point to mutate
    final int mutationPoint = Util.RANDOM.nextInt(individual.npoints);

    // mutate the x and y values of the point
    individual.xpoints[mutationPoint] = (int) (Math.random() * this.width);
    individual.ypoints[mutationPoint] = (int) (Math.random() * this.height);
  }
}
