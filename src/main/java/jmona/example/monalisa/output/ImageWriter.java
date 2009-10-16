/**
 * ImageWriter.java
 */
package jmona.example.monalisa.output;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import jmona.example.monalisa.MonaIndividual;

/**
 * An utility class for outputting images.
 * 
 * @author jeff
 */
public class ImageWriter {

  /** The identifier for the PNG image format. */
  public static final String IMAGE_FORMAT_PNG = "png";

  /**
   * Create an image from the polygons stored in the gene of the specified
   * Individual.
   * 
   * @param individual
   *          The Individual which contains the polygons to write to a file.
   * @param width
   *          The width of the output image.
   * @param height
   *          The height of the output image.
   * @return The image with the polygons written to it.
   */
  public static BufferedImage toImage(final MonaIndividual individual,
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
    for (final Entry<Polygon, Color> entry : individual.gene().entrySet()) {
      graphics.setColor(entry.getValue());
      graphics.fill(entry.getKey());
    }

    return result;
  }

  /**
   * Write the specified image to the specified file.
   * 
   * @param image
   *          The image to write.
   * @param filename
   *          The filename in which to write the image.
   * @throws IOException
   *           If there is a problem writing the image.
   */
  public static void writeImage(final BufferedImage image, final String filename)
      throws IOException {
    ImageIO.write(image, IMAGE_FORMAT_PNG, new File(filename));
  }

  /** Instantiation disallowed. */
  protected ImageWriter() {
    // intentionally unimplemented
  }
}
