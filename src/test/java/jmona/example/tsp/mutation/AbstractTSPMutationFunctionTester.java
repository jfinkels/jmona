/**
 * AbstractTSPMutationFunctionTester.java
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
package jmona.example.tsp.mutation;

import java.util.List;
import java.util.Vector;

import jmona.MutationFunction;
import jmona.functional.Range;

import org.junit.Before;

/**
 * Test class for the traveling salesman problem MutationFunction classes.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public abstract class AbstractTSPMutationFunctionTester {

  /** The length of the Tour under test. */
  public static final int LENGTH = 100;
  /** The number of independent mutations to perform. */
  public static final int NUM_TESTS = 100;
  /** The function under test. */
  private MutationFunction<List<Integer>> function = null;
  /** The tour to mutate. */
  private List<Integer> tour = null;

  /**
   * Instantiate this test class with the specified MutationFunction.
   * 
   * @param initialFunction
   *          The MutationFunction under test.
   */
  public AbstractTSPMutationFunctionTester(
      final MutationFunction<List<Integer>> initialFunction) {
    this.function = initialFunction;
  }

  /**
   * Get the MutationFunction under test in this class.
   * 
   * @return The MutationFunction under test in this class.
   */
  public MutationFunction<List<Integer>> function() {
    return this.function;
  }

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.tour = new Vector<Integer>();
    for (final int i : new Range(LENGTH)) {
      this.tour.add(i);
    }
  }

  /**
   * The test for the {@link jmona.MutationFunction#mutate(Object)} method in
   * each traveling salesman problem MutationFunction test class.
   */
  public abstract void testMutate();

  /**
   * Get the tour under test in this class.
   * 
   * @return The tour under test in this class.
   */
  protected List<Integer> tour() {
    return this.tour;
  }
}
