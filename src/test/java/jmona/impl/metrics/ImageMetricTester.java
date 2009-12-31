/**
 * ImageMetricTester.java
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

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import jmona.Metric;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ImageMetric class.
 * 
 * @author Jeffrey Finkelstein
 */
public class ImageMetricTester extends
    AbstractMetricTester<BufferedImage, Metric<BufferedImage>> {

  /** The color green. */
  public static final int GREEN = 0x00FF00;
  /** The color half-red. */
  public static final int HALF_RED = 0x880000;
  /** The height of the images to measure. */
  public static final int HEIGHT = 2;
  /** The maximum value for a color component. */
  public static final int MAX_VALUE = 0xFF;
  /** The color red. */
  public static final int RED = 0xFF0000;
  /** The width of the images to measure. */
  public static final int WIDTH = 5;

  /** Establish a fixture for tests in this class. */
  @Before
  public void setUp() {
    this.setMetric(new ImageMetric(WIDTH, HEIGHT));
    final BufferedImage x = new BufferedImage(WIDTH, HEIGHT,
        BufferedImage.TYPE_INT_ARGB);
    final BufferedImage y = new BufferedImage(WIDTH, HEIGHT,
        BufferedImage.TYPE_INT_ARGB);
    final BufferedImage z = new BufferedImage(WIDTH, HEIGHT,
        BufferedImage.TYPE_INT_ARGB);

    final Graphics graphicsX = x.getGraphics();
    final Graphics graphicsY = y.getGraphics();
    final Graphics graphicsZ = z.getGraphics();

    graphicsX.setColor(new Color(RED));
    graphicsX.fillRect(0, 0, WIDTH, HEIGHT / 2);

    graphicsY.setColor(new Color(HALF_RED));
    graphicsY.fillRect(0, 0, WIDTH, HEIGHT);

    graphicsZ.setColor(new Color(GREEN));
    graphicsZ.fillRect(0, 0, WIDTH / 2, HEIGHT);

    this.setX(x);
    this.setY(y);
    this.setZ(z);

  }

  /**
   * Test method for {@link jmona.impl.metrics.ImageMetric#getColor(int, int)}.
   */
  @Test
  public void testGetColor() {
    assertEquals(MAX_VALUE, ImageMetric.getColor(Color.RED.getRGB(),
        ImageMetric.RED));
    assertEquals(0, ImageMetric.getColor(Color.RED.getRGB(), ImageMetric.GREEN));
    assertEquals(0, ImageMetric.getColor(Color.RED.getRGB(), ImageMetric.BLUE));

    assertEquals(0, ImageMetric.getColor(Color.GREEN.getRGB(), ImageMetric.RED));
    assertEquals(MAX_VALUE, ImageMetric.getColor(Color.GREEN.getRGB(),
        ImageMetric.GREEN));
    assertEquals(0, ImageMetric
        .getColor(Color.GREEN.getRGB(), ImageMetric.BLUE));

    assertEquals(0, ImageMetric.getColor(Color.BLUE.getRGB(), ImageMetric.RED));
    assertEquals(0, ImageMetric
        .getColor(Color.BLUE.getRGB(), ImageMetric.GREEN));
    assertEquals(MAX_VALUE, ImageMetric.getColor(Color.BLUE.getRGB(),
        ImageMetric.BLUE));
  }

}
