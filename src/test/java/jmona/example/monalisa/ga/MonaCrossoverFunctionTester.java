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
package jmona.example.monalisa.ga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.awt.Color;

import jmona.example.monalisa.ColoredPolygon;

import org.junit.Test;

/**
 * Test class for the MonaCrossoverFunction class.
 * 
 * @author jfinkels
 */
public class MonaCrossoverFunctionTester {

  /**
   * Test method for
   * {@link jmona.example.monalisa.ga.MonaCrossoverFunction#crossover(jmona.Individual, jmona.Individual)}
   * .
   */
  @Test
  public void testCrossover() {

    // the number of points in each polygon
    final int npoints = 3;

    // the coordinates for one polygon
    final int[] xpoints1 = new int[] { 0, 1, 2 };
    final int[] ypoints1 = new int[] { 0, 1, 2 };
    
    // the coordinates for another polygon
    final int[] xpoints2 = new int[] { 3, 4, 5 };
    final int[] ypoints2 = new int[] { 3, 4, 5 };

    // the colors for each of the polygons
    final Color color1 = Color.WHITE;
    final Color color2 = Color.BLACK;

    // instantiate one colored polygon
    final ColoredPolygon polygon1 = new ColoredPolygon();
    polygon1.setColor(color1);
    polygon1.xpoints = xpoints1.clone();
    polygon1.ypoints = ypoints1.clone();
    polygon1.npoints = npoints;

    // instantiate another colored polygon
    final ColoredPolygon polygon2 = new ColoredPolygon();
    polygon2.setColor(color2);
    polygon2.xpoints = xpoints2.clone();
    polygon2.ypoints = ypoints2.clone();
    polygon2.npoints = npoints;

    // instantiate the Individuals which hold those colored polygons
    final MonaIndividual individual1 = new MonaIndividual();
    final MonaIndividual individual2 = new MonaIndividual();

    // add the colored polygons to each individual
    individual1.add(polygon1);
    individual2.add(polygon2);
    
    // create the crossover function
    final MonaCrossoverFunction function = new MonaCrossoverFunction();

    // perform the crossover on the two individuals
    function.crossover(individual1, individual2);

    // each individual must have one polygon
    assertEquals(1, individual1.size());
    assertEquals(1, individual2.size());

    // get the new polygons
    final ColoredPolygon newPolygon1 = individual1.get(0);
    final ColoredPolygon newPolygon2 = individual2.get(0);

    assertNotSame(color1, color2);
    assertNotSame(newPolygon1.color(), newPolygon2.color());
    assertSame(color1, newPolygon2.color());
    assertSame(color2, newPolygon1.color());

    for (int i = 0; i < npoints; ++i) {
      assertEquals(polygon1.xpoints[i], newPolygon2.xpoints[i]);
      assertEquals(polygon2.xpoints[i], newPolygon1.xpoints[i]);
      assertEquals(polygon1.ypoints[i], newPolygon2.ypoints[i]);
      assertEquals(polygon2.ypoints[i], newPolygon1.ypoints[i]);
    }
  }
}
