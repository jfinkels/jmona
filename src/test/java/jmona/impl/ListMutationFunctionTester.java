/**
 * ListMutationFunctionTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.exceptions.MutationException;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMutationFunction;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the ListMutationFunction class.
 * 
 * @author jfinkels
 */
public class ListMutationFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.ListMutationFunction#mutate(java.util.List)}.
   */
  @Test
  public void testMutate() {
    final ListMutationFunction<ExampleIndividual> function = new ListMutationFunction<ExampleIndividual>();

    try {
      function.mutate(null);
      Util.shouldHaveThrownException();
    } catch (final MutationException exception) {
      // elementMutationFunction has not been set
      assertTrue(exception instanceof MutationException);
    }

    final double initialFitness = 1;
    final ExampleIndividual individual = new ExampleIndividual(initialFitness);

    final List<ExampleIndividual> list = new Vector<ExampleIndividual>();
    list.add(individual);

    function.setElementMutationFunction(new ExampleMutationFunction());

    try {
      function.mutate(list);
    } catch (final MutationException exception) {
      Util.fail(exception);
    }

    assertSame(individual, list.get(0));
    assertEquals(ExampleMutationFunction.SCALE * initialFitness, list.get(0)
        .fitness(), ZERO_DELTA);
  }

}
