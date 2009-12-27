/**
 * ColoredPolygonMutationFunctionTester.java
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ColoredPolygonMutationFunction class.
 * 
 * @author jfinkels
 */
public class ColoredPolygonMutationFunctionTester {

  /** The location of the blue value of a pixel packed in an int. */
  public static final int BLUE = 8 * 0;
  /** A bit mask for a single byte. */
  public static final int BYTE_MASK = 0xFF;
  /** The value to use for each component color to get gray. */
  public static final int GRAY_VALUE = 0x88;
  /** The location of the green value of a pixel packed in an int. */
  public static final int GREEN = 8 * 1;
  /** The maximum y component for a point of a polygon. */
  public static final int HEIGHT = 20;
  /** The number of points in a polygon. */
  public static final int NPOINTS = 3;
  /** The number of individuals to mutate. */
  public static final int NUM_INDIVIDUALS = 100;
  /** The location of the red value of a pixel packed in an int. */
  public static final int RED = 8 * 2;
  /** The maximum shift when mutating a color. */
  public static final int SHIFT = 10;
  /** The maximum x component for a point of a polygon. */
  public static final int WIDTH = 10;
  /** The initial x components of the vertices of a polygon. */
  public static final int[] XPOINTS = new int[] { 0, 1, 2 };
  /** The initial y components of the vertices of a polygon. */
  public static final int[] YPOINTS = new int[] { 3, 4, 5 };

  /**
   * Determines whether the specified polygons have the same color and the same
   * coordinates.
   * 
   * @param polygon1
   *          A polygon.
   * @param polygon2
   *          Another polygon.
   * @return Whether the two polygons have the same color and the same
   *         coordinates.
   */
  protected static boolean samePolygon(final ColoredPolygon polygon1,
      final ColoredPolygon polygon2) {
    if (polygon1.npoints != polygon2.npoints) {
      return false;
    }

    if (!polygon1.color().equals(polygon2.color())) {
      return false;
    }

    for (int i = 0; i < polygon1.npoints; ++i) {
      if (polygon1.xpoints[i] != polygon2.xpoints[i]
          || polygon1.ypoints[i] != polygon2.ypoints[i]) {
        return false;
      }
    }

    return true;
  }

  private Color color = null;
  private ColoredPolygonMutationFunction function = null;

  private ColoredPolygon polygon = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new ColoredPolygonMutationFunction(WIDTH, HEIGHT);
    this.function.setColorShift(SHIFT);

    this.color = new Color(GRAY_VALUE << RED | GRAY_VALUE << GREEN
        | GRAY_VALUE << BLUE);

    this.polygon = new ColoredPolygon();
    this.polygon.npoints = NPOINTS;
    this.polygon.xpoints = XPOINTS.clone();
    this.polygon.ypoints = YPOINTS.clone();
    this.polygon.setColor(this.color);
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ColoredPolygonMutationFunction#mutate(java.awt.Color)}
   * .
   */
  @Test
  public void testMutateColor() {
    assertNotSame(Color.BLACK, this.function.mutate(Color.BLACK));

    final Color newColor = this.function.mutate(this.color);

    assertTrue(newColor.getRed() < GRAY_VALUE + SHIFT);
    assertTrue(newColor.getRed() >= GRAY_VALUE - SHIFT);
    assertTrue(newColor.getGreen() < GRAY_VALUE + SHIFT);
    assertTrue(newColor.getGreen() >= GRAY_VALUE - SHIFT);
    assertTrue(newColor.getBlue() < GRAY_VALUE + SHIFT);
    assertTrue(newColor.getBlue() >= GRAY_VALUE - SHIFT);
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ColoredPolygonMutationFunction#mutate(jmona.example.monalisa.ColoredPolygon)}
   * .
   */
  @Test
  public void testMutateColoredPolygon() {
    final ColoredPolygon original = this.polygon.deepCopy();

    this.function.mutate(this.polygon);

    assertEquals(NPOINTS, this.polygon.npoints);

    assertFalse(samePolygon(original, this.polygon));

    for (int i = 0; i < this.polygon.npoints; ++i) {
      assertTrue(this.polygon.ypoints[i] < HEIGHT);
      assertTrue(this.polygon.ypoints[i] >= 0);

      assertTrue(this.polygon.xpoints[i] < WIDTH);
      assertTrue(this.polygon.xpoints[i] >= 0);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ColoredPolygonMutationFunction#setColorShift(int)}
   * .
   */
  @Test
  public void testSetColorShift() {
    final int newShift = SHIFT / 2;
    this.function.setColorShift(newShift);

    assertEquals(GRAY_VALUE, this.color.getRed());
    assertEquals(GRAY_VALUE, this.color.getGreen());
    assertEquals(GRAY_VALUE, this.color.getBlue());

    final Color newColor = this.function.mutate(this.color);

    assertTrue(newColor.getRed() < GRAY_VALUE + newShift);
    assertTrue(newColor.getRed() >= GRAY_VALUE - newShift);
    assertTrue(newColor.getGreen() < GRAY_VALUE + newShift);
    assertTrue(newColor.getGreen() >= GRAY_VALUE - newShift);
    assertTrue(newColor.getBlue() < GRAY_VALUE + newShift);
    assertTrue(newColor.getBlue() >= GRAY_VALUE - newShift);

  }

}
