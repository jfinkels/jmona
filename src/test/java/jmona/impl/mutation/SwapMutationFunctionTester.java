/**
 * SwapMutationFunctionTester.java
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
 * Test class for the SwapMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public class SwapMutationFunctionTester extends
    AbstractListMutationFunctionTester {

  /** Instantiate this test class with access to a SwapMutationFunction. */
  public SwapMutationFunctionTester() {
    super(new SwapMutationFunction<MutableInteger, List<MutableInteger>>());
  }

  /**
   * Test method for
   * {@link jmona.impl.mutation.SwapMutationFunction#mutate(List)}.
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

      assertEquals(LENGTH, this.list().size());
      for (final MutableInteger i : new MutableRange(LENGTH)) {
        assertTrue(this.list().contains(i));
      }

      int changed = 0;
      for (final int i : new Range(this.list().size())) {
        if (this.list().get(i).intValue() != i) {
          changed = i;
          break;
        }
      }

      final int swappedElement = this.list().get(changed).intValue();

      assertEquals(changed, this.list().get(swappedElement).intValue());
    }

  }
}
