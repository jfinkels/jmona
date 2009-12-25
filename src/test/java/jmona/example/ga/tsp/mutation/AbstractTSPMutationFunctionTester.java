/**
 * AbstractTSPMutationFunctionTester.java
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

import jmona.MutationFunction;
import jmona.example.ga.tsp.Tour;

import org.junit.Before;

/**
 * Test class for the traveling salesman problem MutationFunction classes.
 * 
 * @author jfinkels
 */
public abstract class AbstractTSPMutationFunctionTester {

  /** The length of the Tour under test. */
  public static final int LENGTH = 100;
  /** The number of independent mutations to perform. */
  public static final int NUM_TESTS = 100;
  /** The function under test. */
  private MutationFunction<Tour> function = null;
  /** The tour to mutate. */
  private Tour tour = null;

  /**
   * Instantiate this test class with the specified MutationFunction.
   * 
   * @param initialFunction
   *          The MutationFunction under test.
   */
  public AbstractTSPMutationFunctionTester(
      final MutationFunction<Tour> initialFunction) {
    this.function = initialFunction;
  }
  
  /**
   * Get the MutationFunction under test in this class.
   * 
   * @return The MutationFunction under test in this class.
   */
  public MutationFunction<Tour> function() {
    return this.function;
  }
  
  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.tour = new Tour();
    for (int i = 0; i < LENGTH; ++i) {
      this.tour.add(i);
    }
  }

  /**
   * The test for the {@link jmona.MutationFunction#mutate(jmona.Individual)}
   * method in each traveling salesman problem MutationFunction test class.
   */
  public abstract void testMutate();

  /**
   * Get the tour under test in this class.
   * 
   * @return The tour under test in this class.
   */
  protected Tour tour() {
    return this.tour;
  }
}