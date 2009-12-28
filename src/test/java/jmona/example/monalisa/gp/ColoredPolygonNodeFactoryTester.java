/**
 * ColoredPolygonNodeFactoryTester.java
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
package jmona.example.monalisa.gp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import jmona.example.monalisa.ColoredPolygon;
import jmona.gp.EvaluationException;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ColoredPolygonNodeFactory class.
 * 
 * @author jfinkels
 */
public class ColoredPolygonNodeFactoryTester {

  /** The maximum y component for vertices in a polygon. */
  public static final int HEIGHT = 20;
  /** The maximum number of vertices in a polygon. */
  public static final int MAX_POINTS = 10;
  /** The minimum number of vertices in a polygon. */
  public static final int MIN_POINTS = 3;
  /** The maximum x component for vertices in a polygon. */
  public static final int WIDTH = 10;
  /** The factory under test. */
  private ColoredPolygonNodeFactory factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new ColoredPolygonNodeFactory();
    /*this.factory.setMaxPoints(MAX_POINTS);
    this.factory.setMinPoints(MIN_POINTS);
    this.factory.setMaxX(WIDTH);
    this.factory.setMaxY(HEIGHT);
 */ }

  /**
   * Test method for
   * {@link jmona.example.monalisa.gp.ColoredPolygonNodeFactory#createNode()}.
   */
  @Test
  public void testCreateNode() {
    final ColoredPolygonNode node = this.factory.createObject();
    List<ColoredPolygon> polygons = null;
    try {
      polygons = node.evaluate();
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }

    assertEquals(1, polygons.size());

    final ColoredPolygon polygon = polygons.get(0);
    assertNotNull(polygon.color());

    assertTrue(polygon.npoints < MAX_POINTS);
    assertTrue(polygon.npoints >= MIN_POINTS);

    for (int i = 0; i < polygon.npoints; ++i) {
      assertTrue(polygon.xpoints[i] < WIDTH);
      assertTrue(polygon.xpoints[i] >= 0);

      assertTrue(polygon.ypoints[i] < HEIGHT);
      assertTrue(polygon.ypoints[i] >= 0);
    }
  }
}
