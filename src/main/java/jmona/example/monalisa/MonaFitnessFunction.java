/**
 * MonaFitnessFunction.java
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
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.Map.Entry;

import jmona.FitnessException;
import jmona.FitnessFunction;

/**
 * A fitness function which returns the total distance of a specified individual
 * from a given target image. A pixel is four bytes of data packed with
 * 
 * @author jeff
 */
public class MonaFitnessFunction implements FitnessFunction<MonaIndividual> {

  /** The location of the alpha value of a pixel packed in an int. */
  public static final int ALPHA = 8 * 3;

  /** The location of the blue value of a pixel packed in an int. */
  public static final int BLUE = 8 * 0;

  /** A bit mask for a single byte. */
  public static final int BYTE_MASK = 0xFF;

  /** The location of the green value of a pixel packed in an int. */
  public static final int GREEN = 8 * 1;

  /** The location of the red value of a pixel packed in an int. */
  public static final int RED = 8 * 2;

  /**
   * Determine the distance between the two specified pixels, which is the
   * geometric distance between the points represented in the three-dimensional
   * space given by the three colors (red, green, and blue) of the pixels.
   * 
   * @param pixel1
   *          A pixel.
   * @param pixel2
   *          Another pixel.
   * @return The geometric distance between the pixels in the three-dimensional
   *         space defined by the three colors (red, green, and blue) of the
   *         pixels.
   */
  protected static double distance(final int pixel1, final int pixel2) {
    final int red = getColor(pixel1, RED) - getColor(pixel2, RED);
    final int green = getColor(pixel1, GREEN) - getColor(pixel2, GREEN);
    final int blue = getColor(pixel1, BLUE) - getColor(pixel2, BLUE);
    double result = Math.sqrt(Math.pow(red, 2) + Math.pow(green, 2)
        + Math.pow(blue, 2));
    return result;
  }

  /**
   * Get the specified color value from the specified pixel.
   * 
   * @param pixel
   *          The pixel from which to get a color.
   * @param color
   *          The color to get.
   * @return The value of the specified color in the specified pixel.
   */
  protected static int getColor(final int pixel, final int color) {
    return (pixel >> color) & BYTE_MASK;
  }

  /** The height of the target image. */
  private int imageHeight = 0;
  /** The width of the target image. */
  private int imageWidth = 0;

  /** The target image with which to compare individuals. */
  private int[] targetPixels = null;

  /**
   * Set the target image for comparison in the {@link #fitness(MonaIndividual)}
   * method.
   * 
   * @param targetImage
   *          The new target image for comparison in the
   *          {@link #fitness(MonaIndividual)} method.
   * @throws InterruptedException
   *           If there is a problem grabbing the pixel values from the target
   *           image.
   */
  public void setTargetImage(final BufferedImage targetImage)
      throws InterruptedException {
    // get the width and height of the image
    this.imageWidth = targetImage.getWidth();
    this.imageHeight = targetImage.getHeight();

    // TODO must image type be known to calculate pixel distances?
    // if targetImage.getType() == BufferedImage.TYPE_INT_ARGB
    // else if targetImage.getType() == BufferedImage.TYPE_INT_ARGB_PRE
    // TODO getting TYPE_CUSTOM from images i created using GIMP

    // instantiate a new int array of the same size as the target image
    // Note: this can potentially be large, may require increasing heap size
    this.targetPixels = new int[this.imageWidth * this.imageHeight];

    // parameters for the pixelgrabber
    final int startX = 0;
    final int startY = 0;
    final int arrayOffset = 0;
    final int scanlineWidth = this.imageWidth;

    // instantiate a pixelgrabber
    final PixelGrabber pixelGrabber = new PixelGrabber(targetImage, startX,
        startY, this.imageWidth, this.imageHeight, this.targetPixels,
        arrayOffset, scanlineWidth);

    // read the pixels from the image into the int array
    pixelGrabber.grabPixels();
  }

  /**
   * Determine the total distance between the image represented by the gene of
   * the specified individual and the target image.
   * 
   * @param individual
   *          An individual with a gene representing an image.
   * @return The distance of the individual from the target. A large distance
   *         means the individual is very different from the target, and a small
   *         distance means the individual is very similar to the target.
   * @throws FitnessException
   *           If there is a problem determining the fitness of the individual,
   *           or if no target image has been set.
   * @see jmona.FitnessFunction#fitness(jmona.Individual)
   * @see #setTargetImage(BufferedImage)
   */
  @Override
  public double fitness(final MonaIndividual individual)
      throws FitnessException {
    if (this.targetPixels == null) {
      throw new FitnessException("No target image has been set for comparison.");
    }

    /**
     * Step 1: create an image to contain the polygons generated from the gene
     * of the individual
     */
    final BufferedImage generatedImage = new BufferedImage(this.imageWidth,
        this.imageHeight, BufferedImage.TYPE_INT_ARGB_PRE);

    /**
     * Step 2: draw the polygons on the image
     */
    final Graphics2D graphics = generatedImage.createGraphics();
    for (final Entry<Polygon, Color> entry : individual.gene().entrySet()) {
      graphics.setColor(entry.getValue());
      graphics.fill(entry.getKey());
    }

    /**
     * Step 3: grab the pixels from the image
     */
    // parameters for the pixelgrabber
    final int startX = 0;
    final int startY = 0;
    final int arrayOffset = 0;
    final int scanlineWidth = this.imageWidth;
    final int[] generatedPixels = new int[this.targetPixels.length];

    // instantiate a new pixel grabber for grabbing color values from the image
    final PixelGrabber pixelGrabber = new PixelGrabber(generatedImage, startX,
        startY, this.imageWidth, this.imageHeight, generatedPixels,
        arrayOffset, scanlineWidth);

    // grab the pixels from the image
    try {
      pixelGrabber.grabPixels();
    } catch (final InterruptedException exception) {
      throw new FitnessException(
          "Interruption while grabbing pixels from image.", exception);
    }

    /**
     * Step 4: compute the sum of the distance between all pixels from the
     * target image and the generated image.
     */
    double result = 0;
    for (int i = 0; i < this.targetPixels.length; ++i) {
      result += distance(generatedPixels[i], this.targetPixels[i]);
    }

    return result;
  }
}
