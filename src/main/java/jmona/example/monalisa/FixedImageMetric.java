/**
 * FixedImageMetric.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
import java.awt.image.PixelGrabber;
import java.util.List;
import java.util.Vector;

import jfcommon.functional.Range;
import jmona.MetricException;
import jmona.impl.metrics.EuclideanVectorMetric;

/**
 * A class which measures the distance between a target image and any other
 * image, based on the color value of each corresponding pixel.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class FixedImageMetric {
  /** The location of the blue value of a pixel packed in an int. */
  public static final int BLUE = 8 * 0;
  /** A bit mask for a single byte. */
  public static final int BYTE_MASK = 0xFF;
  /** The Euclidean metric. */
  private static final EuclideanVectorMetric<Integer> EUCLIDEAN_METRIC = new EuclideanVectorMetric<Integer>();
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
   * @throws MetricException
   *           If there is a problem determining the Euclidean distance between
   *           the two specified pixels.
   */
  protected static double distance(final int pixel1, final int pixel2)
      throws MetricException {
    return EUCLIDEAN_METRIC.measure(toList(pixel1), toList(pixel2));
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

  /**
   * Convert the specified pixel to a List of its red, green, and blue component
   * color values.
   * 
   * @param pixel
   *          The pixel to convert.
   * @return A List of the red, green, and blue component color values of the
   *         specified pixel.
   */
  protected static List<Integer> toList(final int pixel) {
    final List<Integer> result = new Vector<Integer>();
    result.add(getColor(pixel, RED));
    result.add(getColor(pixel, GREEN));
    result.add(getColor(pixel, BLUE));
    return result;
  }

  /** The pixels of the target image. */
  private final int[] targetPixels;

  /**
   * Instantiates this object with the specified target image for comparison.
   * 
   * @param targetImage
   *          The image with which to compare other images when determining
   *          distance.
   * @throws InterruptedException
   *           If the pixels fail to be read from the image.
   */
  public FixedImageMetric(final BufferedImage targetImage)
      throws InterruptedException {
    // parameters for the pixelgrabber
    final int startX = 0;
    final int startY = 0;
    final int arrayOffset = 0;
    final int width = targetImage.getWidth();
    final int height = targetImage.getHeight();
    this.targetPixels = new int[width * height];

    // instantiate a pixelgrabber
    final PixelGrabber pixelGrabber = new PixelGrabber(targetImage, startX,
        startY, width, height, this.targetPixels, arrayOffset, width);

    // read the pixels from the image into the int array
    pixelGrabber.grabPixels();

  }

  /**
   * Gets the distance between the specified image and the target image defined
   * in the constructor of this class, defined by the sum of the Euclidean
   * distances between each of the pixels of the specified image and the target
   * image in RGB color space.
   * 
   * @param image
   *          The image whose distance from the target image is to be measured.
   * @return The sum of the Euclidean distances between each pixel of this image
   *         and the target image in RGB color space.
   * @throws MetricException
   *           If there is a problem reading the pixels of this image, or if
   *           there is problem measuring the distance between some two pixels.
   */
  public double distanceFromTarget(final BufferedImage image)
      throws MetricException {
    // parameters for the pixelgrabber
    final int startX = 0;
    final int startY = 0;
    final int arrayOffset = 0;
    final int width = image.getWidth();
    final int height = image.getHeight();
    final int[] pixels = new int[width * height];

    // instantiate a pixelgrabber
    final PixelGrabber pixelGrabber = new PixelGrabber(image, startX, startY,
        width, height, pixels, arrayOffset, width);

    // read the pixels from the image into the int array
    try {
      pixelGrabber.grabPixels();
    } catch (final InterruptedException exception) {
      throw new MetricException("Failed to read pixels from the image.",
          exception);
    }

    double totalDistance = 0;
    for (final int i : new Range(pixels.length)) {
      totalDistance += distance(pixels[i], this.targetPixels[i]);
    }

    return totalDistance;
  }
}
