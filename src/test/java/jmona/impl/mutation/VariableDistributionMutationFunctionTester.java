/**
 * VariableDistributionMutationFunctionTester.java
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

import java.util.ArrayList;
import java.util.List;

import jfcommon.test.TestUtils;
import jmona.MutationException;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMutationFunction;

import org.junit.Test;

/**
 * Test class for the VariableDistributionMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class VariableDistributionMutationFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.impl.mutation.VariableDistributionMutationFunction#mutate(jmona.DeepCopyableList)}
   * .
   */
  @Test
  public void testMutate() {
    final VariableDistributionMutationFunction<ExampleIndividual, List<ExampleIndividual>> function = new VariableDistributionMutationFunction<ExampleIndividual, List<ExampleIndividual>>();
    function.setElementMutationFunction(new ExampleMutationFunction());
    function.setDistribution(new double[] { 0.0, 1.0 });

    final List<ExampleIndividual> list = new ArrayList<ExampleIndividual>();
    list.add(new ExampleIndividual(-1));
    list.add(new ExampleIndividual(-1));

    try {
      function.mutate(list);
    } catch (final MutationException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(-1, list.get(0).fitness(), ZERO_DELTA);
    assertEquals(1, list.get(1).fitness(), ZERO_DELTA);

    try {
      function.mutate(list);
    } catch (final MutationException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(-1, list.get(0).fitness(), ZERO_DELTA);
    assertEquals(-1, list.get(1).fitness(), ZERO_DELTA);

    function.setDistribution(new double[] { 1.0, 0.0 });

    try {
      function.mutate(list);
    } catch (final MutationException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(1, list.get(0).fitness(), ZERO_DELTA);
    assertEquals(-1, list.get(1).fitness(), ZERO_DELTA);

    try {
      function.mutate(list);
    } catch (final MutationException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(-1, list.get(0).fitness(), ZERO_DELTA);
    assertEquals(-1, list.get(1).fitness(), ZERO_DELTA);

  }

}
