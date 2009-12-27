/**
 * Converter.java
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
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * A utility class for converting a List of ColoredPolygon objects to a
 * BufferedImage.
 * 
 * @author jfinkels
 */
public final class Converter {

  /**
   * Convert the specified List of ColoredPolygon objects to an image with the
   * specified dimensions.
   * 
   * @param polygons
   *          The List of ColoredPolygons to draw on the image.
   * @param width
   *          The width of the image to create.
   * @param height
   *          The height of the image to create.
   * @return A BufferedImage containing the specified List of polygons filled
   *         with their respective colors.
   */
  public static BufferedImage toImage(final List<ColoredPolygon> polygons,
      final int width, final int height) {
    // create an image on which to draw the polygons from the individual
    final BufferedImage result = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_ARGB_PRE);

    // get the graphics object for this image
    final Graphics2D graphics = result.createGraphics();

    // draw the black background
    graphics.setColor(Color.BLACK);
    graphics.fill(new Rectangle2D.Double(0, 0, width, height));

    // draw and fill the polygons
    for (final ColoredPolygon polygon : polygons) {
      graphics.setColor(polygon.color());
      graphics.fill(polygon);
    }

    return result;
  }

  /** Instantiation disallowed. */
  protected Converter() {
    // intentionally unimplemented
  }
}
