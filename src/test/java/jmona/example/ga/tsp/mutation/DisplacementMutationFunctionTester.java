/**
 * DisplacementMutationFunctionTester.java
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
package jmona.example.ga.tsp.mutation;

import static org.junit.Assert.assertEquals;
import jmona.MutationException;
import jmona.test.Util;

import org.junit.Test;

/**
 * @author jfinkels
 */
public class DisplacementMutationFunctionTester extends
    AbstractTSPMutationFunctionTester {

  /**
   * Instantiate this test class with access to an DisplacementMutationFunction.
   */
  public DisplacementMutationFunctionTester() {
    super(new DisplacementMutationFunction());
  }

  /**
   * Test method for
   * {@link jmona.example.ga.tsp.mutation.DisplacementMutationFunction#mutate(jmona.example.ga.tsp.Tour)}
   * .
   */
  @Test
  public void testMutate() {
    for (int j = 0; j < NUM_TESTS; ++j) {
      this.setUp();

      try {
        this.function().mutate(this.tour());
      } catch (final MutationException exception) {
        Util.fail(exception);
      }

      // before: 0 1 2 3 4 5 6 7 8 9
      // after : 0 4 5 6 7 1 2 3 8 9

      int start = 0;
      for (int i = 0; i < this.tour().size(); ++i) {
        if (this.tour().get(i) != i) {
          start = i;
          break;
        }
      }

      /*
       * int end = 0; for (int i = start + 1; i < this.tour().size(); ++i) { if
       * (this.tour().get(i) != (this.tour().get(i - 1) + 1)) { end = i; break;
       * } }
       */

      // undo the displacement
      int originalLocation = 0;
      while (this.tour().get(start) != start) {
        originalLocation = this.tour().remove(start);
        this.tour().add(originalLocation, originalLocation);
      }

      for (int i = 0; i < this.tour().size(); ++i) {
        assertEquals(i, this.tour().get(i).intValue());
      }
    }
  }
}
