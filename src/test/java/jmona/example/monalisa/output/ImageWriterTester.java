/**
 * ImageWriterTester.java
 */
package jmona.example.monalisa.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.IOException;

import jmona.example.monalisa.MonaIndividual;

import org.junit.Test;

/**
 * Test class for the ImageWriter class.
 * 
 * @author jeff
 */
public class ImageWriterTester {

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
      ImageWriter.writeImage(image, "target/testoutput.png");
    } catch (final IOException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }
  }
}
