/**
 * InversionMutationFunctionTester.java
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
import jmona.functional.Range;
import jmona.impl.mutable.MutableInteger;
import jmona.impl.mutable.functional.MutableRange;

import org.junit.Test;

/**
 * Test class for the InversionMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public class InversionMutationFunctionTester extends
    AbstractListMutationFunctionTester {

  /**
   * Instantiate this test class with access to an InversionMutationFunction.
   */
  public InversionMutationFunctionTester() {
    super(new InversionMutationFunction<MutableInteger, List<MutableInteger>>());
  }

  /**
   * Test method for
   * {@link jmona.impl.mutation.InversionMutationFunction#mutate(List)}.
   */
  @Override
  @Test
  public void testMutate() {
    for (final int j : new Range(NUM_TESTS)) {
      this.setUp();

      assertEquals(LENGTH, this.list().size());

      try {
        this.function().mutate(this.list());
      } catch (final MutationException exception) {
        TestUtils.fail(exception);
      }

      assertEquals(LENGTH, this.list().size());
      for (final MutableInteger i : new MutableRange(LENGTH)) {
        assertTrue(this.list().contains(i));
      }

      // find the first element which is different from the pre-mutation list
      // element
      int start = 0;
      while (start < LENGTH && this.list().get(start).intValue() == start) {
        start += 1;
      }

      if (start >= LENGTH) {

        for (final int i : new Range(LENGTH)) {
          assertEquals(i, this.list().get(i).intValue());
        }

      } else {

        // determine the ending index of the inverted sublist
        final int end = this.list().get(start).intValue() + 1;

        for (final int i : new Range(start - end)) {
          assertEquals(end - i, this.list().get(start + i).intValue());
        }
      }
    }
  }

}
