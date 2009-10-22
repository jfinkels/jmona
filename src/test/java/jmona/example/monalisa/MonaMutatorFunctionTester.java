/**
 * MonaMutatorFunctionTester.java
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

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Polygon;

import jmona.MutationException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MonaMutatorFunction class.
 * 
 * @author jeff
 */
public class MonaMutatorFunctionTester {

  /** The height of the bounding rectangle. */
  public static final int HEIGHT = 200;
  /** The width of the bounding rectangle. */
  public static final int WIDTH = 100;

  /**
   * Print the stack trace of the specified exception and fail the test.
   * 
   * @param cause
   *          The exception which caused the test failure.
   */
  protected static final void fail(final Throwable cause) {
    cause.printStackTrace(System.err);
    org.junit.Assert.fail(cause.getMessage());
  }

  /** A color used for testing. */
  private Color color = null;

  /** The function under test. */
  private MonaMutatorFunction function = null;

  /** A polygon used for testing. */
  private Polygon polygon = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    final int[] xpoints = new int[] { 1, 2, 3, 4 };
    final int[] ypoints = new int[] { 11, 12, 13, 14 };
    final int npoints = xpoints.length;
    this.function = new MonaMutatorFunction();
    this.function.setWidth(WIDTH);
    this.function.setHeight(HEIGHT);
    this.polygon = new Polygon(xpoints, ypoints, npoints);
    this.color = new Color(0);
  }

  /**
   * Test for negative width and height.
   */
  @Test
  public void testBadWidthAndHeight() {
    this.function.setWidth(-1);
    try {
      this.function.mutate(new MonaIndividual());
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
    }

    this.function.setHeight(-1);
    try {
      this.function.mutate(new MonaIndividual());
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
    }

    this.function.setWidth(WIDTH);
    try {
      this.function.mutate(new MonaIndividual());
      org.junit.Assert
          .fail("Exception should have been thrown on the previous line.");
    } catch (final MutationException exception) {
      assertTrue(exception instanceof MutationException);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaMutatorFunction#mutate(java.awt.Color)}.
   */
  @Test
  public void testMutateColor() {
    final Color mutant = this.function.mutate(color);

    assertNotSame(color, mutant);
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaMutatorFunction#mutate(jmona.example.monalisa.MonaIndividual)}
   * .
   */
  @Test
  public void testMutateMonaIndividual() {
    final MonaIndividual individual = new MonaIndividual();
    individual.gene().put(this.polygon, this.color);

    this.function.setMutationProbability(1.0);
    try {
      this.function.mutate(individual);
    } catch (final MutationException exception) {
      fail(exception);
    }

    final Polygon mutantPolygon = (Polygon) individual.gene().keySet()
        .toArray()[0];
    final Color mutantColor = (Color) individual.gene().values().toArray()[0];

    assertNotSame(this.polygon, mutantPolygon);
    assertNotSame(this.color, mutantColor);
    boolean different = false;
    for (int i = 0; i < polygon.npoints; ++i) {
      if (polygon.xpoints[i] != mutantPolygon.xpoints[i]
          || polygon.ypoints[i] != mutantPolygon.ypoints[i]) {
        different = true;
        break;
      }
    }
    assertTrue(different);
  }
  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaMutatorFunction#mutate(java.awt.Polygon)}
   * .
   */
  @Test
  public void testMutatePolygon() {
    final Polygon mutant = this.function.mutate(polygon);

    assertNotSame(this.polygon, mutant);

    boolean different = false;

    for (int i = 0; i < polygon.npoints; ++i) {
      if (polygon.xpoints[i] != mutant.xpoints[i]
          || polygon.ypoints[i] != mutant.ypoints[i]) {
        different = true;
        break;
      }
    }
    assertTrue(different);

    for (int i = 0; i < this.polygon.npoints; ++i) {
      assertTrue(this.polygon.xpoints[i] < WIDTH
          && this.polygon.xpoints[i] >= 0);
      assertTrue(this.polygon.ypoints[i] < HEIGHT
          && this.polygon.ypoints[i] >= 0);
    }

  }

  /** Test method for definitely no mutation. */
  @Test
  public void testNoMutation() {
    final MonaIndividual individual = new MonaIndividual();
    individual.gene().put(this.polygon, this.color);

    this.function.setMutationProbability(0.0);
    try {
      this.function.mutate(individual);
    } catch (final MutationException exception) {
      fail(exception);
    }

    final Polygon mutantPolygon = individual.gene().keySet().toArray(
        new Polygon[1])[0];
    final Color mutantColor = individual.gene().values().toArray(new Color[1])[0];

    assertSame(this.polygon, mutantPolygon);
    assertSame(this.color, mutantColor);
  }
}
