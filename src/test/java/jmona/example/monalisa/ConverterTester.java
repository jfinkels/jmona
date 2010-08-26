/**
 * ConverterTester.java
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

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.List;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.functional.Range;

import org.junit.Test;

/**
 * Test class for the Converter class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ConverterTester {

  /**
   * Test method for
   * {@link jmona.example.monalisa.Converter#toImage(Iterable, int, int)}.
   */
  @Test
  public void testToImage() {
    new Converter();

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

    // create an individual with the created polygon in its gene
    final List<ColoredPolygon> individual = new Vector<ColoredPolygon>();
    final ColoredPolygon polygon = new ColoredPolygon();
    polygon.xpoints = xpoints;
    polygon.ypoints = ypoints;
    polygon.npoints = npoints;

    polygon.setColor(color);

    individual.add(polygon);

    final BufferedImage image = Converter.toImage(individual, width, height);

    // initialize an array to contain the color values of all the pixels
    final int[] pixels = new int[width * height];

    // create a pixel grabber which will get the color values of all the pixels
    final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width,
        height, pixels, 0, width);

    // get the color values of all the pixels in the image
    try {
      pixelGrabber.grabPixels();
    } catch (final InterruptedException exception) {
      TestUtils.fail(exception);
    }

    int currentColor = 0;
    for (final int i : new Range(width)) {
      for (final int j : new Range(height)) {
        currentColor = Color.BLACK.getRGB();
        if (i >= left && i < right && j >= top && j < bottom) {
          currentColor = color.getRGB();
        }
        assertEquals(currentColor, pixels[j * width + i]);
      }
    }
  }
}
