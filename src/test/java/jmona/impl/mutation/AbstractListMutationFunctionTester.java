/**
 * AbstractListMutationFunctionTester.java
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

import jmona.DeepCopyableList;
import jmona.MutationFunction;
import jmona.functional.Range;
import jmona.impl.PartialDeepCopyableVector;

import org.junit.Before;

/**
 * Test class for the ListMutationFunctions.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public abstract class AbstractListMutationFunctionTester {

  /** The length of the Tour under test. */
  public static final int LENGTH = 100;
  /** The number of independent mutations to perform. */
  public static final int NUM_TESTS = 100;
  /** The function under test. */
  private MutationFunction<DeepCopyableList<Integer>> function = null;
  /** The list to mutate. */
  private DeepCopyableList<Integer> list = null;

  /**
   * Instantiate this test class with the specified MutationFunction.
   * 
   * @param initialFunction
   *          The MutationFunction under test.
   */
  public AbstractListMutationFunctionTester(
      final MutationFunction<DeepCopyableList<Integer>> initialFunction) {
    this.function = initialFunction;
  }

  /**
   * Get the MutationFunction under test in this class.
   * 
   * @return The MutationFunction under test in this class.
   */
  public MutationFunction<DeepCopyableList<Integer>> function() {
    return this.function;
  }

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.list = new PartialDeepCopyableVector<Integer>();
    for (final int i : new Range(LENGTH)) {
      this.list.add(i);
    }
  }

  /**
   * The test for the {@link jmona.MutationFunction#mutate(Object)} method in
   * each ElementwiseMutationFunction test class.
   */
  public abstract void testMutate();

  /**
   * Get the list under test in this class.
   * 
   * @return The list under test in this class.
   */
  protected DeepCopyableList<Integer> list() {
    return this.list;
  }
}
