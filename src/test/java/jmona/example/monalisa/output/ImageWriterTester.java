/**
 * ImageWriterTester.java
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
package jmona.example.monalisa.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import jmona.example.monalisa.MonaIndividual;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;

/**
 * Test class for the ImageWriter class.
 * 
 * @author jeff
 */
public class ImageWriterTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(ImageWriterTester.class);

  /** The filename at which to write a test image file. */
  public static final String OUTPUT_FILENAME = "target/testoutput.png";

  /** Perform cleanup after each test. */
  @After
  public final void tearDown() {
    final File outputFile = new File(OUTPUT_FILENAME);
    if (outputFile.exists() && !outputFile.delete()) {
        LOG.debug("Failed to delete output file at " + OUTPUT_FILENAME);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.output.ImageWriter#toImage(jmona.example.monalisa.MonaIndividual, int, int)}
   * .
   */
  @Test
  public void testToImage() {
    // the width and height of the image to generate
    final int width = 5;
    final int height = 5;

    // the color of the image to generate
    final int color = 0xFFFF0000; // same as Color.RED

    // the corners for a rectangle
    final int minCol = 0;
    final int maxCol = 3;
    final int minRow = 0;
    final int maxRow = 2;

    // the coordinates for the polygon
    final int[] xpoints = new int[] { minCol, maxCol, maxCol, minCol };
    final int[] ypoints = new int[] { minRow, minRow, maxRow, maxRow };
    final int npoints = xpoints.length;

    // create an individual with the created polygon in its gene
    final MonaIndividual individual = new MonaIndividual();
    individual.gene().put(new Polygon(xpoints, ypoints, npoints),
        new Color(color));

    // convert the gene of that individual to an image
    final BufferedImage image = ImageWriter.toImage(individual, width, height);

    // initialize an array to contain the color values of all the pixels
    final int[] pixels = new int[width * height];

    // create a pixel grabber which will get the color values of all the pixels
    final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width,
        height, pixels, 0, width);

    // get the color values of all the pixels in the image
    try {
      pixelGrabber.grabPixels();
    } catch (final InterruptedException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }

    // assert that the rectangle was drawn appropriately
    for (int i = minCol; i < maxCol; ++i) {
      for (int j = minRow; j < maxRow; ++j) {
        assertEquals(color, pixels[j * width + i]);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.output.ImageWriter#writeImage(java.awt.image.BufferedImage, java.lang.String)}
   * .
   */
  @Test
  public void testWriteImage() {
    final BufferedImage image = new BufferedImage(100, 100,
        BufferedImage.TYPE_INT_ARGB_PRE);
    try {
      ImageWriter.writeImage(image, OUTPUT_FILENAME);
    } catch (final IOException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }
  }
}
