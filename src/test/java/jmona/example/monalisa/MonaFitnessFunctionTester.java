/**
 * MonaFitnessFunctionTester.java
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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import jmona.FitnessException;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MonaFitnessFunction class.
 * 
 * @author jeff
 */
public class MonaFitnessFunctionTester {
  /** The coordinates for a rectangle in all columns of a 5x2 image. */
  private static final int[] ALL_COLUMNS = new int[] { 0, 5, 5, 0 };
  /** A file containing a somewhat large image. */
  public static final File BIG_TESTFILE = new File(
      "src/test/resources/jmona/example/monalisa/images/monalisa.jpg");
  /** The coordinates for a rectangle in both rows of a 5x2 image. */
  private static final int[] BOTH_ROWS = new int[] { 0, 0, 2, 2 };
  /** The coordinates for a rectangle in the bottom row of a 5x2 image. */
  private static final int[] BOTTOM_ROW = new int[] { 1, 1, 2, 2 };
  /**
   * A file containing an image containing several colors with varying alpha.
   */
  public static final File COLORS_TESTFILE = new File(
      "src/test/resources/jmona/example/monalisa/images/example-transparency.png");
  /** The coordinates for a rectangle in the far left of a 5x2 image. */
  private static final int[] FAR_LEFT = new int[] { 0, 1, 1, 0 };
  /** The coordinates for a rectangle in the far right of a 5x2 image. */
  private static final int[] FAR_RIGHT = new int[] { 4, 5, 5, 4 };
  /** Half of the maximum value for a color. */
  public static final int HALF_VAL = 0x80;
  /** The maximum value of a color. */
  public static final int MAX_VAL = 0xFF;
  /** The coordinates for a rectangle in the mid-left of a 5x2 image. */
  private static final int[] MID_LEFT = new int[] { 1, 2, 2, 1 };
  /** The coordinates for a rectangle in the mid-right of a 5x2 image. */
  private static final int[] MID_RIGHT = new int[] { 3, 4, 4, 3 };
  /** The coordinates for a rectangle in the middle of a 5x2 image. */
  private static final int[] MIDDLE = new int[] { 2, 3, 3, 2 };
  /**
   * The number of points in each polygon in the image which we will create and
   * test.
   */
  private static final int NUM_POINTS = MIDDLE.length;
  /**
   * A file containing an image containing two colors with alpha overlapping.
   */
  public static final File OVERLAPPING_TESTFILE = new File(
      "src/test/resources/jmona/example/monalisa/images/purple-red.png");
  /** The coordinates for a rectangle in the top row of an image. */
  private static final int[] TOP_ROW = new int[] { 0, 0, 1, 1 };
  /** A polygon which will be filled black. */
  private Polygon blackPolygon;
  /** A polygon which will be filled blue. */
  private Polygon bluePolygon;
  /** A test image with several colors and varying alpha. */
  private BufferedImage colorsImage = null;
  /** The function under test. */
  private final MonaFitnessFunction function = new MonaFitnessFunction();
  /** A polygon which will be filled green. */
  private Polygon greenPolygon;
  /** A polygon which will be filled black with 50% alpha. */
  private Polygon halfBlackPolygon;
  /** A polygon which will be filled blue with 50% alpha. */
  private Polygon halfBluePolygon;
  /** A polygon which will be filled green with 50% alpha. */
  private Polygon halfGreenPolygon;
  /** A polygon which will be filled red with 50% alpha. */
  private Polygon halfRedPolygon;
  /** A polygon which will be filled white with 50% alpha. */
  private Polygon halfWhitePolygon;
  /** A test image with two colors with alpha overlapping. */
  private BufferedImage overlappingImage = null;
  /** A polygon which will be filled red. */
  private Polygon redPolygon;
  /** A polygon which will be filled white. */
  private Polygon whitePolygon;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.redPolygon = new Polygon(FAR_LEFT, TOP_ROW, NUM_POINTS);
    this.greenPolygon = new Polygon(MID_LEFT, TOP_ROW, NUM_POINTS);
    this.bluePolygon = new Polygon(MIDDLE, TOP_ROW, NUM_POINTS);
    this.blackPolygon = new Polygon(MID_RIGHT, TOP_ROW, NUM_POINTS);
    this.whitePolygon = new Polygon(FAR_RIGHT, TOP_ROW, NUM_POINTS);

    this.halfRedPolygon = new Polygon(FAR_LEFT, BOTTOM_ROW, NUM_POINTS);
    this.halfGreenPolygon = new Polygon(MID_LEFT, BOTTOM_ROW, NUM_POINTS);
    this.halfBluePolygon = new Polygon(MIDDLE, BOTTOM_ROW, NUM_POINTS);
    this.halfBlackPolygon = new Polygon(MID_RIGHT, BOTTOM_ROW, NUM_POINTS);
    this.halfWhitePolygon = new Polygon(FAR_RIGHT, BOTTOM_ROW, NUM_POINTS);

    try {
      this.colorsImage = ImageIO.read(COLORS_TESTFILE);
      this.overlappingImage = ImageIO.read(OVERLAPPING_TESTFILE);
      ImageIO.read(BIG_TESTFILE);
    } catch (final FileNotFoundException exception) {
      Util.fail(exception);
    } catch (final IOException exception) {
      Util.fail(exception);
    }

  }

  /**
   * Test for opening a large image, possibly resulting in an OutOfMemoryError.
   */
  @Test
  public void testBigImage() {
    // try {
    // this.function.setTargetImage(this.bigImage);
    // } catch (final InterruptedException exception) {
    // fail(exception);
    // } catch (final OutOfMemoryError error) {
    // fail(error);
    // }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaFitnessFunction#distance(int, int)}.
   */
  @Test
  public void testDistance() {
    final int testPixel1 = 0xAABBCC;
    final int testPixel2 = 0xAABBCD;
    final int testPixel3 = 0x00BBCC;
    final int expectedDistance = 0xAA;
    final double epsilon = 0.0;
    assertEquals(0.0, MonaFitnessFunction.distance(testPixel1, testPixel1),
        epsilon);
    assertEquals(1.0, MonaFitnessFunction.distance(testPixel1, testPixel2),
        epsilon);
    assertEquals(expectedDistance, MonaFitnessFunction.distance(testPixel1,
        testPixel3), epsilon);

  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaFitnessFunction#fitness(jmona.example.monalisa.MonaIndividual)}
   * .
   */
  @Test
  public void testFitness() {
    try {
      this.function.fitness(new MonaIndividual());
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final FitnessException exception) {
      assertTrue(exception instanceof FitnessException);
    }

    try {
      this.function.setTargetImage(this.colorsImage);
    } catch (final InterruptedException exception) {
      Util.fail(exception);
    }

    final MonaIndividual individual = new MonaIndividual();

    individual.gene().put(this.redPolygon, Color.RED);
    individual.gene().put(this.greenPolygon, Color.GREEN);
    individual.gene().put(this.bluePolygon, Color.BLUE);
    individual.gene().put(this.blackPolygon, Color.BLACK);
    individual.gene().put(this.whitePolygon, Color.WHITE);

    individual.gene().put(this.halfRedPolygon,
        new Color(MAX_VAL, 0x00, 0x00, HALF_VAL));
    individual.gene().put(this.halfGreenPolygon,
        new Color(0x00, MAX_VAL, 0x00, HALF_VAL));
    individual.gene().put(this.halfBluePolygon,
        new Color(0x00, 0x00, MAX_VAL, HALF_VAL));
    individual.gene().put(this.halfBlackPolygon,
        new Color(0x00, 0x00, 0x00, HALF_VAL));
    individual.gene().put(this.halfWhitePolygon,
        new Color(MAX_VAL, MAX_VAL, MAX_VAL, HALF_VAL));

    double fitness = -1;
    try {
      fitness = this.function.fitness(individual);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }

    final double epsilon = 0.0;
    assertEquals(Double.POSITIVE_INFINITY, fitness, epsilon);

    final MonaIndividual individual2 = new MonaIndividual();
    try {
      fitness = this.function.fitness(individual2);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }

    assertNotSame(0, fitness);

  }

  /**
   * Test method for the fitness of an individual with two overlapping polygons
   * with 50% alpha affected colors.
   */
  @Test
  public final void testFitnessOverlapping() {
    try {
      this.function.setTargetImage(this.overlappingImage);
    } catch (final InterruptedException exception) {
      Util.fail(exception);
    }

    final Polygon redBox = new Polygon(ALL_COLUMNS, BOTH_ROWS, NUM_POINTS);
    final Color halfRed = new Color(MAX_VAL, 0x00, 0x00, HALF_VAL);

    final Polygon blueBox = new Polygon(ALL_COLUMNS, TOP_ROW, NUM_POINTS);
    final Color halfBlue = new Color(0x00, 0x00, MAX_VAL, HALF_VAL);

    final MonaIndividual individual = new MonaIndividual();

    individual.gene().put(blueBox, halfBlue);
    individual.gene().put(redBox, halfRed);

    double fitness = -1;
    try {
      fitness = this.function.fitness(individual);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }

    // TODO there is some non-deterministic behavior here... sometimes the
    // resuting image has pixels c0aa0055, sometimes x05500aa
    final double kludge = 1 / 601;
    assertTrue(fitness == Double.POSITIVE_INFINITY || (int) fitness == kludge);
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaFitnessFunction#getColor(int, int)}.
   */
  @Test
  public void testGetColor() {
    // test values
    final int alpha = 0xAA;
    final int red = 0xBB;
    final int green = 0xCC;
    final int blue = 0xDD;

    // the pixel is in ARGB order
    final int testPixel = alpha << (MonaFitnessFunction.ALPHA)
        | red << (MonaFitnessFunction.RED)
        | green << (MonaFitnessFunction.GREEN)
        | blue << (MonaFitnessFunction.BLUE);

    // ensure the test pixel comes out as expected
    final int expected = 0xAABBCCDD;
    assertEquals(expected, testPixel);

    assertEquals(alpha, MonaFitnessFunction.getColor(testPixel,
        MonaFitnessFunction.ALPHA));
    assertEquals(red, MonaFitnessFunction.getColor(testPixel,
        MonaFitnessFunction.RED));
    assertEquals(green, MonaFitnessFunction.getColor(testPixel,
        MonaFitnessFunction.GREEN));
    assertEquals(blue, MonaFitnessFunction.getColor(testPixel,
        MonaFitnessFunction.BLUE));
  }

}
