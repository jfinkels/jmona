/**
 * FixedImageMetricTester.java
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

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import jfcommon.test.TestUtils;
import jmona.MetricException;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test class for the FixedImageMetric class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class FixedImageMetricTester {
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The example image which is the target for the FixedImageMetric under test. */
  public static final File TESTFILE = new File(
      "src/test/resources/jmona/example/monalisa/images/purple-red.png");

  /**
   * Test method for
   * {@link jmona.example.monalisa.FixedImageMetric#distance(int, int)}.
   */
  @Test
  public void testDistance() {
    try {
      assertEquals(0xFF, FixedImageMetric.distance(0, 0xFF), ZERO_DELTA);
      assertEquals(0xFF, FixedImageMetric.distance(0, 0xFF00), ZERO_DELTA);
      assertEquals(0xFF, FixedImageMetric.distance(0, 0xFF0000), ZERO_DELTA);

      assertEquals(0xFF, FixedImageMetric.distance(0xFF, 0xFF00FF), ZERO_DELTA);

      assertEquals(Math.sqrt(2 * Math.pow(0xFF, 2)),
          FixedImageMetric.distance(0, 0xFF00FF), ZERO_DELTA);

      assertEquals(0, FixedImageMetric.distance(0xFF, 0xFF), ZERO_DELTA);
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.FixedImageMetric#getColor(int, int)}.
   */
  @Test
  public void testGetColor() {
    final int red = 0x12;
    final int green = 0x34;
    final int blue = 0x56;
    final int pixel = red << 16 | green << 8 | blue << 0;

    assertEquals(red, FixedImageMetric.getColor(pixel, FixedImageMetric.RED));
    assertEquals(green,
        FixedImageMetric.getColor(pixel, FixedImageMetric.GREEN));
    assertEquals(blue, FixedImageMetric.getColor(pixel, FixedImageMetric.BLUE));
  }

  /**
   * Test method for {@link jmona.example.monalisa.FixedImageMetric#toList(int)}
   * .
   */
  @Test
  public void testToList() {
    final int red = 0x12;
    final int green = 0x34;
    final int blue = 0x56;
    final int pixel = red << 16 | green << 8 | blue << 0;

    final List<Integer> list = FixedImageMetric.toList(pixel);
    assertEquals(red, list.get(0).intValue());
    assertEquals(green, list.get(1).intValue());
    assertEquals(blue, list.get(2).intValue());
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.FixedImageMetric#FixedImageMetric(java.awt.image.BufferedImage)}
   * .
   */
  @Test
  public void testFixedImageMetric() {
    try {
      new FixedImageMetric(ImageIO.read(TESTFILE));
    } catch (final InterruptedException exception) {
      TestUtils.fail(exception);
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.FixedImageMetric#distanceFromTarget(java.awt.image.BufferedImage)}
   * .
   */
  // TODO On some platforms, a rectangle of height n fills in n row of pixels.
  // On other platforms, a rectangle of height n fills in n - 1 rows of
  // pixels. I can't seem to figure out why this happens, so I'm leaving this
  // test out for now.
  @Ignore
  @Test
  public void testDistanceFromTarget() {
    FixedImageMetric metric = null;
    try {
      metric = new FixedImageMetric(ImageIO.read(TESTFILE));
    } catch (final InterruptedException exception) {
      TestUtils.fail(exception);
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }

    try {
      assertEquals(0.0, metric.distanceFromTarget(ImageIO.read(TESTFILE)),
          ZERO_DELTA);
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }

    // create an example image of known distance from the target image
    final int width = 5;
    final int height = 2;
    final Polygon polygon = new Polygon();
    polygon.xpoints = new int[] { 0, width, width, 0 };
    // TODO See TODO on this method.
    polygon.ypoints = new int[] { 0, 0, height / 2, height / 2 };
    // polygon.ypoints = new int[] { 0, 0, 0, 0};
    polygon.npoints = 4;

    // create an image on which to draw the polygon
    final BufferedImage testImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_ARGB_PRE);

    // get the graphics object for this image
    final Graphics2D graphics = testImage.createGraphics();

    // set the color to purple and draw the purple rectangle with 0xC0 alpha
    graphics.setColor(new Color(0x55, 0x00, 0xAA, 0xC0));
    graphics.draw(polygon);

    double difference = 0.0;
    try {
      difference = width * (FixedImageMetric.distance(0x000000, 0xFF0000));
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    }

    try {
      assertEquals(difference, metric.distanceFromTarget(testImage), ZERO_DELTA);
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    }
  }

}
