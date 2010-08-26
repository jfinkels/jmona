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
import java.util.Set;

import jfcommon.test.TestUtils;
import jmona.CopyingException;
import jmona.example.monalisa.ColoredPolygon;
import jmona.example.monalisa.ColoredPolygonFactory;
import jmona.example.monalisa.ColoredPolygonTestSupport;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ColoredPolygonNode class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ColoredPolygonNodeTester extends ColoredPolygonTestSupport {

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
      TestUtils.fail(exception);
    }

    assertNotNull(clonedNode);

    ColoredPolygon clonedPolygon = (ColoredPolygon) clonedNode.evaluate()
        .toArray()[0];

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
    Set<ColoredPolygon> polygons = this.node.evaluate();

    assertEquals(1, polygons.size());
    assertTrue(polygons.contains(this.polygon));
    assertTrue(samePolygon((ColoredPolygon) polygons.toArray()[0], this.polygon));

    final ColoredPolygonNodeFactory factory = new ColoredPolygonNodeFactory();
    factory.setColoredPolygonFactory(new ColoredPolygonFactory());
    this.node.children().add(factory.createObject());

    polygons = this.node.evaluate();

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

    ColoredPolygon newNodePolygon = (ColoredPolygon) this.node.evaluate()
        .toArray()[0];

    assertTrue(samePolygon(newPolygon, newNodePolygon));
    assertNotSame(newNodePolygon, this.polygon);
  }

}
