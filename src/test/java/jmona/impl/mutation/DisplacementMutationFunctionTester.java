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

import jmona.MutationException;
import jmona.functional.MutableRange;
import jmona.functional.Range;
import jmona.impl.mutable.MutableInteger;
import jmona.test.Util;

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
    for (final int j : new Range(NUM_TESTS)) {
      this.setUp();

      try {
        this.function().mutate(this.list());
      } catch (final MutationException exception) {
        Util.fail(exception);
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
      for (final int i : new Range(this.list().size())) {
        if (this.list().get(i).intValue() != i) {
          start = i;
          break;
        }
      }

      // get the index between the first and second slices
      int middle = 0;
      for (final int i : new Range(start + 1, this.list().size())) {
        if (this.list().get(i).intValue() != this.list().get(i - 1).intValue() + 1) {
          middle = i;
          break;
        }
      }

      // get the index of the end of the second slice. if the last change isn't
      // found, it is at the end
      int end = this.list().size();
      for (final int i : new Range(middle + 1, this.list().size())) {
        if (this.list().get(i).intValue() != this.list().get(i - 1).intValue() + 1) {
          end = i;
          break;
        }
      }

      // get the length of the swapped slices
      final int firstLength = middle - start;
      final int secondLength = end - middle;

      // from the start to the first change
      for (final int i : new Range(start)) {
        assertEquals(i, this.list().get(i).intValue());
      }

      // from the first change to the second change
      for (final int i : new Range(firstLength)) {
        assertEquals(this.list().get(start).intValue() + i,
            this.list().get(start + i).intValue());
      }

      // from the second change to the end of the slices
      for (final int i : new Range(secondLength)) {
        assertEquals(this.list().get(middle).intValue() + i,
            this.list().get(middle + i).intValue());
      }

      // from the end of the slices to the end of the list
      for (final int i : new Range(end, this.list().size())) {
        assertEquals(i, this.list().get(i).intValue());
      }

    }
  }
}
