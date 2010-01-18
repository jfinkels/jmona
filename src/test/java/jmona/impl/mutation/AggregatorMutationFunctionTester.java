/**
 * AggregatorMutationFunctionTester.java
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
package jmona.impl.mutation;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Vector;

import jmona.MutationException;
import jmona.MutationFunction;
import jmona.functional.Range;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AggregatorMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class AggregatorMutationFunctionTester {

  class ExampleMutationFunction1 implements
      MutationFunction<ExampleIndividual> {

    /**
     * Increments the counter for mutations1.
     * 
     * @see jmona.MutationFunction#mutate(Object)
     */
    @Override
    public void mutate(ExampleIndividual individual1) {
      AggregatorMutationFunctionTester.this.mutations1 += 1;
    }

  }

  class ExampleMutationFunction2 implements
      MutationFunction<ExampleIndividual> {

    /**
     * Increments the counter for mutations2.
     * 
     * @see jmona.MutationFunction#mutate(Object)
     */
    @Override
    public void mutate(ExampleIndividual individual1){
      AggregatorMutationFunctionTester.this.mutations2 += 1;
    }

  }

  /** The number of mutations to perform. */
  public static final int NUM_MUTATIONS = 10000;
  /** The number of times the first mutation function is called. */
  int mutations1 = 0;
  /** The number of times the second mutation function is called. */
  int mutations2 = 0;

  /**
   * Resets the counters for the number of times each MutationFunction is
   * executed.
   */
  @Before
  public void setUp() {
    this.mutations1 = 0;
    this.mutations2 = 0;
  }

  /**
   * Test method for
   * {@link jmona.impl.mutation.AggregatorMutationFunction#AggregatorMutationFunction(java.util.Collection)}
   * .
   */
  @Test
  public void testAggregatorMutationFunction() {
    final List<MutationFunction<ExampleIndividual>> list = new Vector<MutationFunction<ExampleIndividual>>();
    list.add(new ExampleMutationFunction1());
    list.add(new ExampleMutationFunction2());

    final AggregatorMutationFunction<ExampleIndividual> function = new AggregatorMutationFunction<ExampleIndividual>(
        list);

    try {
      for (final int i : new Range(NUM_MUTATIONS)) {
        function.mutate(null);
      }
    } catch (final MutationException exception) {
      Util.fail(exception);
    }

    final double delta = (NUM_MUTATIONS / 2) * 0.1;
    assertEquals(this.mutations1, this.mutations2, delta);
  }

}
