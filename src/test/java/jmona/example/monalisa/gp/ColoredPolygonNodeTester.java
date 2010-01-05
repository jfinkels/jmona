/**
 * ColoredPolygonNodeTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.List;

import jmona.CopyingException;
import jmona.example.monalisa.ColoredPolygon;
import jmona.example.monalisa.ColoredPolygonFactory;
import jmona.functional.Range;
import jmona.gp.EvaluationException;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ColoredPolygonNode class.
 * 
 * @author Jeffrey Finkelstein
 */
public class ColoredPolygonNodeTester {

  /** The color of the polygon. */
  public static final Color COLOR = Color.GRAY;
  /** The number of points in the polygon. */
  public static final int NPOINTS = 3;
  /** The x component of the coordinates of the vertices of the polygon. */
  public static final int[] XPOINTS = { 0, 1, 2 };
  /** The y component of the coordinates of the vertices of the polygon. */
  public static final int[] YPOINTS = { 3, 4, 5 };

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

    for (final int i : new Range(polygon1.npoints)) {
      if (polygon1.xpoints[i] != polygon2.xpoints[i]
          || polygon1.ypoints[i] != polygon2.ypoints[i]) {
        return false;
      }
    }

    return true;
  }

  /** The Node under test. */
  private ColoredPolygonNode node = null;
  /** The ColoredPolygon in the Node under test. */
  private ColoredPolygon polygon = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.polygon = new ColoredPolygon();
    this.polygon.npoints = NPOINTS;
    this.polygon.xpoints = XPOINTS.clone();
    this.polygon.ypoints = YPOINTS.clone();
    this.polygon.setColor(new Color(COLOR.getRGB()));

    this.node = new ColoredPolygonNode();
    this.node.setColoredPolygon(this.polygon);
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.gp.ColoredPolygonNode#arity()}.
   */
  @Test
  public void testArity() {
    assertEquals(1, ColoredPolygonNode.ARITY);
    assertEquals(1, this.node.arity());
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.gp.ColoredPolygonNode#children()}.
   */
  @Test
  public void testChildren() {
    assertEquals(0, this.node.children().size());
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.gp.ColoredPolygonNode#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    ColoredPolygonNode clonedNode = null;
    try {
      clonedNode = this.node.deepCopy();
    } catch (final CopyingException exception) {
      Util.fail(exception);
    }

    assertNotNull(clonedNode);

    ColoredPolygon clonedPolygon = null;

    try {
      clonedPolygon = clonedNode.evaluate().get(0);
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }

    assertNotNull(clonedPolygon);
    assertNotSame(clonedPolygon, this.polygon);
    assertTrue(samePolygon(clonedPolygon, this.polygon));
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.gp.ColoredPolygonNode#evaluate()}.
   */
  @Test
  public void testEvaluate() {
    List<ColoredPolygon> polygons = null;
    try {
      polygons = this.node.evaluate();
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }

    assertEquals(1, polygons.size());
    assertTrue(polygons.contains(this.polygon));
    assertTrue(samePolygon(polygons.get(0), this.polygon));

    final ColoredPolygonNodeFactory factory = new ColoredPolygonNodeFactory();
    factory.setColoredPolygonFactory(new ColoredPolygonFactory());
    this.node.children().add(factory.createObject());

    try {
      polygons = this.node.evaluate();
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }

    assertEquals(2, polygons.size());
    assertTrue(polygons.contains(this.polygon));
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.gp.ColoredPolygonNode#setColoredPolygon(jmona.example.monalisa.ColoredPolygon)}
   * .
   */
  @Test
  public void testSetColoredPolygon() {
    final ColoredPolygon newPolygon = this.polygon.deepCopy();

    this.node.setColoredPolygon(newPolygon);

    ColoredPolygon newNodePolygon = null;
    try {
      newNodePolygon = this.node.evaluate().get(0);
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }

    assertTrue(samePolygon(newPolygon, newNodePolygon));
    assertNotSame(newNodePolygon, this.polygon);
  }

}
