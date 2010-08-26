/**
 * UniformDistributionMutationFunctionTester.java
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
import jmona.functional.Range;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMutationFunction;

import org.junit.Test;

/**
 * Test class for the UniformDistributionMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class UniformDistributionMutationFunctionTester {
  /** The number of individuals in the list to mutate. */
  public static final int NUM_INDIVIDUALS = 100;
  /** The number of mutations to perform. */
  public static final int NUM_MUTATIONS = 1000;

  /**
   * Test method for
   * {@link jmona.impl.mutation.UniformDistributionMutationFunction#mutate(jmona.DeepCopyableList)}
   * .
   */
  @Test
  public void testMutate() {
    final UniformDistributionMutationFunction<ExampleIndividual, List<ExampleIndividual>> function = new UniformDistributionMutationFunction<ExampleIndividual, List<ExampleIndividual>>();
    function.setElementMutationFunction(new ExampleMutationFunction());

    for (final int i : new Range(NUM_MUTATIONS)) {
      // populate a list with individuals each storing the value -1
      final List<ExampleIndividual> list = new ArrayList<ExampleIndividual>();
      for (final int j : new Range(NUM_INDIVIDUALS)) {
        list.add(new ExampleIndividual(-1));
      }

      // mutate each element of the list with uniform probability
      try {
        function.mutate(list);
      } catch (final MutationException exception) {
        TestUtils.fail(exception);
      }

      // count the number of mutated individuals in the list
      int numChanged = 0;
      for (final ExampleIndividual individual : list) {
        if (individual.fitness() == 1) {
          numChanged += 1;
        }
      }

      // the number of mutated individuals in the list should be about 1
      // TODO determine a test for this class
      final double delta = 10;
      assertEquals(1, numChanged, delta);
    }
  }

}
