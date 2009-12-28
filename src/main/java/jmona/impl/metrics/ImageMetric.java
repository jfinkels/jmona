/**
 * ImageMetric.java
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
package jmona.impl.metrics;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.List;
import java.util.Vector;

import jmona.Metric;
import jmona.exceptions.MetricException;

/**
 * A metric which measures the distance between images in RGB color space, using
 * the sum of Euclidean distance between each of the pixels in RGB color space.
 * 
 * @author jfinkels
 */
public class ImageMetric implements Metric<BufferedImage> {

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

  /** The height of the images to be measured. */
  private int imageHeight = 0;
  /** The width of the images to be measured. */
  private int imageWidth = 0;

  /**
   * Instantiate this metric with the specified height and width of images to be
   * measured.
   * 
   * @param initialImageWidth
   *          The width of images to be measured.
   * @param initialImageHeight
   *          The height of images to be measured.
   */
  public ImageMetric(final int initialImageWidth, final int initialImageHeight) {
    this.imageWidth = initialImageWidth;
    this.imageHeight = initialImageHeight;
  }

  /**
   * Get the distance between the two specified images determined by the
   * Euclidean distance in RGB color space between each of their corresponding
   * pixels.
   * 
   * @param image1
   *          An image.
   * @param image2
   *          Another image.
   * @return The Euclidean distance between the two specified images in RGB
   *         color space.
   * @throws MetricException
   *           If there is a problem reading the pixels from the images.
   * @see jmona.Metric#measure(java.lang.Object, java.lang.Object)
   */
  @Override
  public double measure(final BufferedImage image1, final BufferedImage image2)
      throws MetricException {
    // parameters for the pixelgrabber
    final int startX = 0;
    final int startY = 0;
    final int arrayOffset = 0;
    final int scanlineWidth = this.imageWidth;
    final int[] pixelsOfImage1 = new int[this.imageWidth * this.imageHeight];
    final int[] pixelsOfImage2 = new int[this.imageWidth * this.imageHeight];

    // instantiate a pixelgrabber
    final PixelGrabber pixelGrabber1 = new PixelGrabber(image1, startX, startY,
        this.imageWidth, this.imageHeight, pixelsOfImage1, arrayOffset,
        scanlineWidth);
    final PixelGrabber pixelGrabber2 = new PixelGrabber(image2, startX, startY,
        this.imageWidth, this.imageHeight, pixelsOfImage2, arrayOffset,
        scanlineWidth);

    // read the pixels from the image into the int array
    try {
      pixelGrabber1.grabPixels();
      pixelGrabber2.grabPixels();
    } catch (final InterruptedException exception) {
      throw new MetricException("Failed to grab pixels from an image.");
    }

    double totalDistance = 0;
    for (int i = 0; i < pixelsOfImage1.length; ++i) {
      totalDistance += distance(pixelsOfImage1[i], pixelsOfImage2[i]);
    }

    return totalDistance;
  }
}
