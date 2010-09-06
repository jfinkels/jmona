/**
 * DisplacementMutationFunctionTester.java
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
package jmona.impl.mutation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import jfcommon.test.TestUtils;
import jmona.MutationException;
import jmona.impl.mutable.MutableInteger;
import jmona.impl.mutable.functional.MutableRange;

import org.junit.Test;

/**
 * Test class for the DisplacementMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public class DisplacementMutationFunctionTester extends
    AbstractListMutationFunctionTester {

  /**
   * Instantiate this test class with access to an DisplacementMutationFunction.
   */
  public DisplacementMutationFunctionTester() {
    super(
        new DisplacementMutationFunction<MutableInteger, List<MutableInteger>>());
  }

  /**
   * Test method for
   * {@link jmona.impl.mutation.DisplacementMutationFunction#mutate(List)} .
   */
  @Override
  @Test
  public void testMutate() {
    for (int j = 0; j < NUM_TESTS; ++j) {
      this.setUp();

      try {
        this.function().mutate(this.list());
      } catch (final MutationException exception) {
        TestUtils.fail(exception);
      }

      // for example:
      // before: 0 1 2 3 4 5 6 7 8 9
      // after : 0|4 5 6 7|1 2 3|8 9

      assertEquals(LENGTH, this.list().size());

      for (final MutableInteger i : new MutableRange(LENGTH)) {
        assertTrue(this.list().contains(i));
      }

      // get the index of the start of the first slice
      int start = 0;
      for (int i = 0; i < this.list().size(); ++i) {
        if (this.list().get(i).intValue() != i) {
          start = i;
          break;
        }
      }

      // get the index between the first and second slices
      int middle = 0;
      for (int i = start + 1; i < this.list().size(); ++i) {
        if (this.list().get(i).intValue() != this.list().get(i - 1).intValue() + 1) {
          middle = i;
          break;
        }
      }

      // get the index of the end of the second slice. if the last change isn't
      // found, it is at the end
      int end = this.list().size();
      for (int i = middle + 1; i < this.list().size(); ++i) {
        if (this.list().get(i).intValue() != this.list().get(i - 1).intValue() + 1) {
          end = i;
          break;
        }
      }

      // get the length of the swapped slices
      final int firstLength = middle - start;
      final int secondLength = end - middle;

      // from the start to the first change
      for (int i = 0; i < start; ++i) {
        assertEquals(i, this.list().get(i).intValue());
      }

      // from the first change to the second change
      for (int i = 0; i < firstLength; ++i) {
        assertEquals(this.list().get(start).intValue() + i,
            this.list().get(start + i).intValue());
      }

      // from the second change to the end of the slices
      for (int i = 0; i < secondLength; ++i) {
        assertEquals(this.list().get(middle).intValue() + i,
            this.list().get(middle + i).intValue());
      }

      // from the end of the slices to the end of the list
      for (int i = end; i < this.list().size(); ++i) {
        assertEquals(i, this.list().get(i).intValue());
      }

    }
  }
}
