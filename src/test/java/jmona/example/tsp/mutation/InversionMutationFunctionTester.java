/**
 * InversionMutationFunctionTester.java
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

import java.util.List;

import jmona.MutationException;
import jmona.functional.Range;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the InversionMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class InversionMutationFunctionTester extends
    AbstractTSPMutationFunctionTester {

  /**
   * Instantiate this test class with access to an InversionMutationFunction.
   */
  public InversionMutationFunctionTester() {
    super(new InversionMutationFunction());
  }

  /**
   * Test method for
   * {@link jmona.example.tsp.mutation.InversionMutationFunction#mutate(List)}.
   */
  @Test
  public void testMutate() {
    for (final int j : new Range(NUM_TESTS)) {
      this.setUp();

      assertEquals(LENGTH, this.tour().size());

      try {
        this.function().mutate(this.tour());
      } catch (final MutationException exception) {
        Util.fail(exception);
      }

      assertEquals(LENGTH, this.tour().size());
      for (final int i : new Range(LENGTH)) {
        assertTrue(this.tour().contains(i));
      }

      // find the first city which is different from the pre-mutation tour city
      int start = 0;
      while (start < LENGTH && this.tour().get(start) == start) {
        start += 1;
      }

      if (start >= LENGTH) {

        for (final int i : new Range(LENGTH)) {
          assertEquals(i, this.tour().get(i).intValue());
        }

      } else {

        // determine the ending index of the inverted sublist
        final int end = this.tour().get(start) + 1;

        for (final int i : new Range(start - end)) {
          assertEquals(end - i, this.tour().get(start + i).intValue());
        }
      }
    }
  }

}
