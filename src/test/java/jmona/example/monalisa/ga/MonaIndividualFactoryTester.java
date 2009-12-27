/**
 * MonaIndividualFactoryTester.java
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
package jmona.example.monalisa.ga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Polygon;

import jmona.example.monalisa.ColoredPolygon;
import jmona.example.monalisa.ColoredPolygonFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MonaIndividualFactory class.
 * 
 * @author jfinkels
 */
// TODO strict inequality or non-strict inequality?
public class MonaIndividualFactoryTester {

  /** The factory under test. */
  private MonaIndividualFactory factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new MonaIndividualFactory();
  }

  /**
   * Test method for
   * {@link jmona.example.ga.monalisa.MonaIndividualFactory#createIndividual()}.
   */
  @Test
  public void testCreateIndividual() {
    MonaIndividual individual = this.factory.createIndividual();

    assertEquals(MonaIndividualFactory.DEFAULT_NUMBER_OF_POLYGONS, individual
        .size());
    for (final ColoredPolygon polygon : individual) {

      // number of points in the polygon must be between default min and max
      assertTrue(polygon.npoints < ColoredPolygonFactory.DEFAULT_MAX_POINTS);
      assertTrue(polygon.npoints >= ColoredPolygonFactory.DEFAULT_MIN_POINTS);

      // iterate over each coordinate pair in the polygon
      for (int i = 0; i < polygon.npoints; ++i) {
        // each coordinate must be between 0 and default max
        assertTrue(polygon.xpoints[i] >= 0
            && polygon.xpoints[i] < ColoredPolygonFactory.DEFAULT_MAX_X);
        assertTrue(polygon.ypoints[i] >= 0
            && polygon.ypoints[i] < ColoredPolygonFactory.DEFAULT_MAX_Y);
      }

      final Color color = polygon.color();

      // each component of the color must be between 0 and max
      assertTrue(color.getRed() >= 0
          && color.getRed() < ColoredPolygonFactory.MAX_VALUE);
      assertTrue(color.getGreen() >= 0
          && color.getGreen() < ColoredPolygonFactory.MAX_VALUE);
      assertTrue(color.getBlue() >= 0
          && color.getBlue() < ColoredPolygonFactory.MAX_VALUE);
      assertTrue(color.getAlpha() >= 0
          && color.getAlpha() < ColoredPolygonFactory.MAX_VALUE);
    }

  }

  /**
   * Test method for
   * {@link jmona.example.ga.monalisa.MonaIndividualFactory#setNumberOfPolygons(int)}
   * .
   */
  @Test
  public void testSetNumberOfPolygons() {
    final int newNumberOfPolygons = 10;
    this.factory.setNumberOfPolygons(newNumberOfPolygons);
    final MonaIndividual individual = this.factory.createIndividual();
    assertEquals(newNumberOfPolygons, individual.size());
  }

  /**
   * Test method for
   * {@link jmona.example.ga.monalisa.MonaIndividualFactory#setMaxPoints(int)}.
   */
  @Test
  public void testSetMaxPoints() {
    final int newMaxPoints = 5;
    this.factory.setMaxPoints(newMaxPoints);
    final MonaIndividual individual = this.factory.createIndividual();
    for (final Polygon polygon : individual) {
      assertTrue(polygon.npoints < newMaxPoints);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.ga.monalisa.MonaIndividualFactory#setMaxX(int)}.
   */
  @Test
  public void testSetMaxX() {
    final int newMaxX = 10;
    this.factory.setMaxX(newMaxX);
    final MonaIndividual individual = this.factory.createIndividual();
    for (final Polygon polygon : individual) {
      for (int i = 0; i < polygon.npoints; ++i) {
        assertTrue(polygon.xpoints[i] < newMaxX);
        assertTrue(polygon.xpoints[i] >= 0);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.example.ga.monalisa.MonaIndividualFactory#setMaxY(int)}.
   */
  @Test
  public void testSetMaxY() {
    final int newMaxY = 10;
    this.factory.setMaxY(newMaxY);
    final MonaIndividual individual = this.factory.createIndividual();
    for (final Polygon polygon : individual) {
      for (int i = 0; i < polygon.npoints; ++i) {
        assertTrue(polygon.ypoints[i] < newMaxY);
        assertTrue(polygon.ypoints[i] >= 0);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.example.ga.monalisa.MonaIndividualFactory#setMinPoints(int)}.
   */
  @Test
  public void testSetMinPoints() {
    final int newMinPoints = 5;
    this.factory.setMinPoints(newMinPoints);
    final MonaIndividual individual = this.factory.createIndividual();
    for (final Polygon polygon : individual) {
      assertTrue(polygon.npoints >= newMinPoints);
    }
  }

}
