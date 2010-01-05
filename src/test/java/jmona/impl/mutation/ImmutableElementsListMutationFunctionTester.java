/**
 * ImmutableElementsListMutationFunctionTester.java
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

import java.util.List;
import java.util.Vector;

import jmona.MutationException;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleListMutationFunction;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the ImmutableElementsListMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class ImmutableElementsListMutationFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.mutation.ImmutableElementsListMutationFunction#mutate(java.util.List)}
   * .
   */
  @Test
  public void testMutate() {
    final ImmutableElementsListMutationFunction<ExampleIndividual> function = new ExampleListMutationFunction();
    final List<ExampleIndividual> list = new Vector<ExampleIndividual>();
    final ExampleIndividual individual = new ExampleIndividual();
    list.add(individual);
    try {
      function.mutate(list);
    } catch (final MutationException exception) {
      Util.fail(exception);
    }

    assertEquals(-1 * individual.fitness(), list.get(0).fitness(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.impl.mutation.ImmutableElementsListMutationFunction#mutated(java.lang.Object)}
   * .
   */
  @Test
  public void testMutated() {
    final ImmutableElementsListMutationFunction<ExampleIndividual> function = new ExampleListMutationFunction();
    final ExampleIndividual individual = new ExampleIndividual();
    ExampleIndividual mutatedIndividual = null;
    try {
      mutatedIndividual = function.mutated(individual);
    } catch (final MutationException exception) {
      Util.fail(exception);
    }

    assertEquals(-1 * individual.fitness(), mutatedIndividual.fitness(),
        ZERO_DELTA);
  }

}
