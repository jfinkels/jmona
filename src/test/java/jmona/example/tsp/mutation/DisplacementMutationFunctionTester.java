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
package jmona.example.tsp.mutation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmona.MutationException;
import jmona.impl.Range;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the DisplacementMutationFunction class.
 * 
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
   * {@link jmona.example.tsp.mutation.DisplacementMutationFunction#mutate(List)}
   * .
   */
  @Test
  public void testMutate() {
    for (final int j : new Range(NUM_TESTS)) {
      this.setUp();

      try {
        this.function().mutate(this.tour());
      } catch (final MutationException exception) {
        Util.fail(exception);
      }

      // for example:
      // before: 0 1 2 3 4 5 6 7 8 9
      // after : 0|4 5 6 7|1 2 3|8 9

      assertEquals(LENGTH, this.tour().size());

      for (final int i : new Range(LENGTH)) {
        assertTrue(this.tour().contains(i));
      }

      // get the index of the start of the first slice
      int start = 0;
      for (final int i : new Range(this.tour().size())) {
        if (this.tour().get(i) != i) {
          start = i;
          break;
        }
      }

      // get the index between the first and second slices
      int middle = 0;
      for (final int i : new Range(start + 1, this.tour().size())) {
        if (this.tour().get(i) != this.tour().get(i - 1) + 1) {
          middle = i;
          break;
        }
      }

      // get the index of the end of the second slice
      int end = 0;
      for (final int i : new Range(middle + 1, this.tour().size())) {
        if (this.tour().get(i) != this.tour().get(i - 1) + 1) {
          end = i;
          break;
        }
      }

      // get the length of the swapped slices
      final int firstLength = middle - start;
      final int secondLength = end - middle;

      // from the start to the first change
      for (final int i : new Range(start)) {
        assertEquals(i, this.tour().get(i).intValue());
      }

      // from the first change to the second change
      for (final int i : new Range(firstLength)) {
        assertEquals(this.tour().get(start) + i, this.tour().get(start + i)
            .intValue());
      }

      // from the second change to the end of the slices
      for (final int i : new Range(secondLength)) {
        assertEquals(this.tour().get(middle) + i, this.tour().get(middle + i)
            .intValue());
      }

      // from the end of the slices to the end of the tour
      for (final int i : new Range(end, this.tour().size())) {
        assertEquals(i, this.tour().get(i).intValue());
      }

    }
  }
}
