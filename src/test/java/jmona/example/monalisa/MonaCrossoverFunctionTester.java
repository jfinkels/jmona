/**
 * MonaCrossoverFunctionTester.java
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
import static org.junit.Assert.assertSame;

import java.awt.Color;
import java.awt.Polygon;

import jmona.Pair;

import org.junit.Test;

/**
 * Test class for the MonaCrossoverFunction class.
 * 
 * @author jeff
 */
public class MonaCrossoverFunctionTester {

  /**
   * Test method for
   * {@link jmona.example.monalisa.MonaCrossoverFunction#crossover(jmona.Pair)}.
   */
  @Test
  public void testCrossover() {
    final Polygon leftPolygon1 = new Polygon(new int[] { 0, 1, 2 }, new int[] {
        0, 1, 2 }, 3);
    final Polygon rightPolygon1 = new Polygon(new int[] { 5, 2, 2 }, new int[] {
        3, 3, 11 }, 3);

    final Color leftColor1 = new Color(0);
    final Color rightColor1 = new Color(2);

    final MonaIndividual left = new MonaIndividual();
    left.gene().put(leftPolygon1, leftColor1);
    final MonaIndividual right = new MonaIndividual();
    right.gene().put(rightPolygon1, rightColor1);
    final Pair<MonaIndividual, MonaIndividual> parents = new Pair<MonaIndividual, MonaIndividual>(
        left, right);

    final MonaCrossoverFunction function = new MonaCrossoverFunction();
    final Pair<MonaIndividual, MonaIndividual> children = function
        .crossover(parents);

    final Polygon leftParentPolygon = parents.left().gene().keySet().toArray(
        new Polygon[1])[0];
    final Polygon rightParentPolygon = parents.right().gene().keySet().toArray(
        new Polygon[1])[0];
    final Polygon leftChildPolygon = children.left().gene().keySet().toArray(
        new Polygon[1])[0];
    final Polygon rightChildPolygon = children.right().gene().keySet().toArray(
        new Polygon[1])[0];

    final Color leftParentColor = parents.left().gene().values().toArray(
        new Color[1])[0];
    final Color rightParentColor = parents.right().gene().values().toArray(
        new Color[1])[0];
    final Color leftChildColor = children.left().gene().values().toArray(
        new Color[1])[0];
    final Color rightChildColor = children.right().gene().values().toArray(
        new Color[1])[0];

    assertSame(leftParentColor, rightChildColor);
    assertSame(rightParentColor, leftChildColor);
    assertNotSame(leftParentColor, leftChildColor);
    assertNotSame(rightParentColor, rightChildColor);
    
    for (int i = 0; i < leftParentPolygon.npoints; ++i) {
      assertEquals(leftParentPolygon.xpoints[i], rightChildPolygon.xpoints[i]);
      assertEquals(leftParentPolygon.ypoints[i], rightChildPolygon.ypoints[i]);
      assertEquals(rightParentPolygon.xpoints[i], leftChildPolygon.xpoints[i]);
      assertEquals(rightParentPolygon.ypoints[i], leftChildPolygon.ypoints[i]);
    }
  }
}
