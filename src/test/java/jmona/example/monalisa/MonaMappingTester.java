/**
 * MonaMappingTester.java
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

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.List;
import java.util.Vector;

import jmona.example.monalisa.ColoredPolygon;
import jmona.example.monalisa.MonaMapping;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the MonaMapping class.
 * 
 * @author jfinkels
 */
public class MonaMappingTester {

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaMapping#execute(java.util.List)}.
   */
  @Test
  public void testExecute() {
    // the width and height of the image to generate
    final int width = 5;
    final int height = 5;

    // the color of the image to generate
    final int colorAsInteger = 0xFF0000; // same as Color.RED
    final Color color = new Color(colorAsInteger);

    // the corners for a rectangle
    final int left = 0;
    final int right = 3;
    final int top = 0;
    final int bottom = 2;

    // the coordinates for the polygon
    final int[] ypoints = new int[] { top, top, bottom, bottom };
    final int[] xpoints = new int[] { left, right, right, left };
    final int npoints = xpoints.length;

    // create a polygon
    final ColoredPolygon polygon = new ColoredPolygon();
    polygon.xpoints = xpoints;
    polygon.ypoints = ypoints;
    polygon.npoints = npoints;

    polygon.setColor(color);

    // create an individual
    final List<ColoredPolygon> individual = new Vector<ColoredPolygon>();
    individual.add(polygon);

    // create a mapping function from trees to images
    final MonaMapping function = new MonaMapping(width, height);

    final BufferedImage image = function.execute(individual);

    // initialize an array to contain the color values of all the pixels
    final int[] pixels = new int[width * height];

    // create a pixel grabber which will get the color values of all the pixels
    final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width,
        height, pixels, 0, width);

    // get the color values of all the pixels in the image
    try {
      pixelGrabber.grabPixels();
    } catch (final InterruptedException exception) {
      Util.fail(exception);
    }

    int currentColor = 0;
    for (int i = 0; i < width; ++i) {
      for (int j = 0; j < height; ++j) {
        currentColor = Color.BLACK.getRGB();
        if (i >= left && i < right && j >= top && j < bottom) {
          currentColor = color.getRGB();
        }
        assertEquals(currentColor, pixels[j * width + i]);
      }
    }
  }

}
