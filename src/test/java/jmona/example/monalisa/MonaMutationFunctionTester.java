/**
 * MonaMutationFunctionTester.java
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.exceptions.MutationException;
import jmona.impl.ListMutationFunction;
import jmona.impl.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MonaMutationFunction class.
 * 
 * @author jfinkels
 */
public class MonaMutationFunctionTester extends ColoredPolygonTestSupport {

  /** The function under test. */
  private ListMutationFunction<ColoredPolygon> function = null;
  /** The individual to mutate. */
  private List<ColoredPolygon> individual = null;
  /** The polygons in the individual. */
  private List<ColoredPolygon> polygons = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new ListMutationFunction<ColoredPolygon>();
    this.function
        .setElementMutationFunction(new ColoredPolygonMutationFunction(WIDTH,
            HEIGHT));

    this.polygons = new Vector<ColoredPolygon>();

    ColoredPolygon polygon = null;
    for (int i = 0; i < NUM_POLYGONS; ++i) {
      polygon = new ColoredPolygon();
      polygon.setColor(COLOR);
      polygon.npoints = NPOINTS;
      for (int j = 0; j < NPOINTS; ++j) {
        polygon.xpoints[j] = Util.RANDOM.nextInt();
        polygon.ypoints[j] = Util.RANDOM.nextInt();
      }
      this.polygons.add(polygon);
    }

    this.individual = new Vector<ColoredPolygon>();

    for (final ColoredPolygon coloredPolygon : this.polygons) {
      this.individual.add(coloredPolygon.deepCopy());
    }

  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ga.MonaMutationFunction#mutate(jmona.example.monalisa.ga.MonaIndividual)}
   * .
   */
  @Test
  public void testMutate() {
    try {
      this.function.mutate(this.individual);
    } catch (final MutationException exception) {
      jmona.test.Util.fail(exception);
    }

    assertEquals(this.polygons.size(), this.individual.size());

    ColoredPolygon changedPolygon = null;
    for (int i = 0; i < NUM_POLYGONS; ++i) {
      if (!samePolygon(this.polygons.get(i), this.individual.get(i))) {
        changedPolygon = this.individual.get(i);
        break;
      }
    }

    assertNotNull(changedPolygon);
    for (final ColoredPolygon polygon : this.individual) {
      if (polygon.equals(changedPolygon)) {
        assertTrue(samePolygon(polygon, changedPolygon));
      } else {
        assertFalse(samePolygon(polygon, changedPolygon));
      }
    }
  }

}
