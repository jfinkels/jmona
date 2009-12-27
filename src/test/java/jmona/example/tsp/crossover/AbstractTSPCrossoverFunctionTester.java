/**
 * AbstractTSPCrossoverFunctionTester.java
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
package jmona.example.tsp.crossover;

import jmona.CrossoverFunction;
import jmona.example.tsp.Tour;

import org.junit.Before;

/**
 * Test class for the traveling salesman problem CrossoverFunction classes.
 * 
 * @author jfinkels
 */
public abstract class AbstractTSPCrossoverFunctionTester {

  /** The length of the Tours under test. */
  public static final int LENGTH = 100;
  /** The number of independent crossovers to perform. */
  public static final int NUM_TESTS = 100;
  /** The function under test. */
  private CrossoverFunction<Tour> function = null;
  /**
   * A tour on which to perform crossover which contains an increasing sequence
   * of integers.
   */
  private Tour tour1 = null;
  /**
   * A tour on which to perform crossover which contains a decreasing sequence
   * of integers.
   */
  private Tour tour2 = null;

  /**
   * Instantiate this test class with the specified CrossoverFunction.
   * 
   * @param initialFunction
   *          The CrossoverFunction under test.
   */
  public AbstractTSPCrossoverFunctionTester(
      final CrossoverFunction<Tour> initialFunction) {
    this.function = initialFunction;
  }

  /**
   * Get the CrossoverFunction under test in this class.
   * 
   * @return The CrossoverFunction under test in this class.
   */
  public CrossoverFunction<Tour> function() {
    return this.function;
  }

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.tour1 = new Tour();
    this.tour2 = new Tour();

    for (int i = 0; i < LENGTH; ++i) {
      this.tour1.add(i);
      this.tour2.add(LENGTH - 1 - i);
    }
  }

  /**
   * The test for the
   * {@link jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)}
   * method in each traveling salesman problem CrossoverFunction test class.
   */
  public abstract void testCrossover();

  /**
   * Get the tour containing the increasing sequence.
   * 
   * @return The tour containing the increasing sequence.
   */
  protected Tour tour1() {
    return this.tour1;
  }

  /**
   * Get the tour containing the decreasing sequence.
   * 
   * @return The tour containing the decreasing sequence.
   */
  protected Tour tour2() {
    return this.tour2;
  }
}
