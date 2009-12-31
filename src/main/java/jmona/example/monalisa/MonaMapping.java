/**
 * MonaMapping.java
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

import java.awt.image.BufferedImage;
import java.util.List;

import jmona.SingleInputFunction;

/**
 * A function which maps a List of ColoredPolygon objects to the BufferedImage
 * which it represents.
 * 
 * @author Jeffrey Finkelstein
 */
public class MonaMapping implements
    SingleInputFunction<List<ColoredPolygon>, BufferedImage> {

  /** The width of the image to output. */
  private final int width;
  /** The height of the image to output. */
  private final int height;

  /**
   * Instantiate this mapping with the specified width and height of the image
   * to which to map a List of ColoredPolygon objects.
   * 
   * @param initialWidth
   *          The width of the output image.
   * @param initialHeight
   *          The height of the output image.
   */
  public MonaMapping(final int initialWidth, final int initialHeight) {
    this.width = initialWidth;
    this.height = initialHeight;
  }

  /**
   * Convert the specified List of ColoredPolygon objects to the BufferedImage
   * which it represents.
   * 
   * @param input
   *          A List of ColoredPolygon objects which represent an image.
   * @return The BufferedImage which the specified List represents.
   * @see jmona.SingleInputFunction#execute(java.lang.Object)
   */
  @Override
  public BufferedImage execute(final List<ColoredPolygon> input) {
    return Converter.toImage(input, this.width, this.height);
  }

}
