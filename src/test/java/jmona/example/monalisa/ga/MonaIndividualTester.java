/**
 * MonaIndividualTester.java
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import jmona.CopyingException;
import jmona.example.monalisa.ColoredPolygon;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the MonaIndividual class.
 * 
 * @author jfinkels
 */
public class MonaIndividualTester {

  /**
   * Test method for {@link jmona.example.monalisa.ga.MonaIndividual#deepCopy()}
   * .
   */
  @Test
  public void testDeepCopy() {
    // create a new individual
    final MonaIndividual individual = new MonaIndividual();

    // create a colored polygon
    final ColoredPolygon polygon = new ColoredPolygon();
    polygon.xpoints = new int[] { 0 };
    polygon.ypoints = new int[] { 1 };
    polygon.npoints = 1;
    polygon.setColor(Color.BLACK);

    // add the colored polygon to the list of polygons in the individual
    individual.add(polygon);

    // clone the individual
    MonaIndividual clone = null;
    try {
      clone = individual.deepCopy();
    } catch (final CopyingException exception) {
      Util.fail(exception);
    }

    assertNotSame(individual, clone);
    assertNotSame(individual, clone);
    assertNotSame(individual.get(0), clone.get(0));
    assertEquals(individual.get(0).npoints,
        clone.get(0).npoints);

    assertEquals(individual.size(), clone.size());

    assertTrue(individual.contains(polygon));
    assertFalse(clone.contains(polygon));

    for (int i = 0; i < polygon.npoints; ++i) {
      assertEquals(polygon.xpoints[i], clone.get(0).xpoints[i]);
      assertEquals(polygon.ypoints[i], clone.get(0).ypoints[i]);
    }

  }

}
