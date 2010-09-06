/**
 * AggregatorCrossoverFunctionTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.impl.crossover;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.CrossoverException;
import jmona.CrossoverFunction;
import jmona.impl.example.ExampleIndividual;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AggregatorCrossoverFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class AggregatorCrossoverFunctionTester {

  /**
   * An example CrossoverFunction which simply increments
   * {@link AggregatorCrossoverFunctionTester#crossovers1}.
   * 
   * @author Jeffrey Finkelstein
   * @since 0.5
   */
  private class ExampleCrossoverFunction1 implements
      CrossoverFunction<ExampleIndividual> {

    /**
     * Instantiates this class.
     */
    public ExampleCrossoverFunction1() {
      // intentionally unimplemented; provided to avoid synthetic accessor
      // emulation
    }

    /**
     * Increments the counter for crossover1.
     * 
     * @param individual1
     *          {@inheritDoc}
     * @param individual2
     *          {@inheritDoc}
     * @see jmona.CrossoverFunction#crossover(java.lang.Object,java.lang.Object)
     */
    @Override
    public void crossover(final ExampleIndividual individual1,
        final ExampleIndividual individual2) {
      AggregatorCrossoverFunctionTester.this.crossovers1 += 1;
    }

  }

  /**
   * An example CrossoverFunction which simply increments
   * {@link AggregatorCrossoverFunctionTester#crossovers2}.
   * 
   * @author Jeffrey Finkelstein
   * @since 0.5
   */
  private class ExampleCrossoverFunction2 implements
      CrossoverFunction<ExampleIndividual> {

    /**
     * Instantiates this class.
     */
    public ExampleCrossoverFunction2() {
      // intentionally unimplemented; provided to avoid synthetic accessor
      // emulation
    }

    /**
     * Increments the counter for crossovers2.
     * 
     * @param individual1
     *          {@inheritDoc}
     * @param individual2
     *          {@inheritDoc}
     * @see jmona.CrossoverFunction#crossover(java.lang.Object,java.lang.Object)
     */
    @Override
    public void crossover(final ExampleIndividual individual1,
        final ExampleIndividual individual2) {
      AggregatorCrossoverFunctionTester.this.crossovers2 += 1;
    }

  }

  /** The number of crossovers to perform. */
  public static final int NUM_CROSSOVERS = 10000;
  /** The number of times the first crossover function is called. */
  int crossovers1 = 0;
  /** The number of times the second crossover function is called. */
  int crossovers2 = 0;

  /**
   * Resets the counters for the number of times each CrossoverFunction is
   * executed.
   */
  @Before
  public void setUp() {
    this.crossovers1 = 0;
    this.crossovers2 = 0;
  }

  /**
   * Test method for
   * {@link jmona.impl.crossover.AggregatorCrossoverFunction#AggregatorCrossoverFunction(java.util.Collection)}
   * .
   */
  @Test
  public void testAggregatorCrossoverFunction() {
    final List<CrossoverFunction<ExampleIndividual>> list = new Vector<CrossoverFunction<ExampleIndividual>>();
    list.add(new ExampleCrossoverFunction1());
    list.add(new ExampleCrossoverFunction2());

    final AggregatorCrossoverFunction<ExampleIndividual> function = new AggregatorCrossoverFunction<ExampleIndividual>(
        list);

    try {
      for (int i = 0; i < NUM_CROSSOVERS; ++i) {
        function.crossover(null, null);
      }
    } catch (final CrossoverException exception) {
      TestUtils.fail(exception);
    }

    final double delta = (NUM_CROSSOVERS / 2) * 0.1;
    assertEquals(this.crossovers1, this.crossovers2, delta);
  }

}
