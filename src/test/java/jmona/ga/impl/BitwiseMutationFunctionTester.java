/**
 * BitwiseMutationFunctionTester.java
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
package jmona.ga.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Vector;

import jmona.ga.BinaryString;

import org.junit.Test;

/**
 * Test class for the BitwiseMutationFunction class.
 * 
 * @author jfinkels
 */
public class BitwiseMutationFunctionTester {

  /** The length of the genes of the individuals to test. */
  public static final int LENGTH = 100;
  /** The number of Individuals on which to test mutation. */
  public static final int NUM_INDIVIDUALS = 100;

  /**
   * Test method for
   * {@link jmona.ga.impl.BitwiseMutationFunction#mutate(jmona.ga.BinaryString)}
   * .
   */
  @Test
  public void testMutate() {
    // create the mutator function
    final BitwiseMutationFunction function = new BitwiseMutationFunction();

    // create a list of all individuals.
    final List<BinaryString> allIndividuals = new Vector<BinaryString>();

    // initialize binary strings, all initially 0
    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {

      // create a binary string with all 0s and add it to the list
      allIndividuals.add(new CharArrayBinaryString(LENGTH));
    }

    // iterate over each individual
    for (final BinaryString individual : allIndividuals) {

      // mutate the current individual
      function.mutate(individual);
    }

    // iterate over all individuals
    int sum = 0;
    for (final BinaryString individual : allIndividuals) {

      // iterate over each bit in the gene of the individual
      for (final byte bit : individual) {

        // increment the number of mutations each time there is a one
        sum += bit;
      }
    }

    // determine the arithmetic mean of mutations over all individuals
    final double mean = (double) sum / allIndividuals.size();

    // determine the expected average mutations
    final double expectedMutations = BitwiseMutationFunction.DEFAULT_MUTATION_PROBABILITY
        * LENGTH;

    // the error tolerance
    final double delta = expectedMutations * .20;

    // TODO use standard deviation or something more official for epsilon
    assertEquals(expectedMutations, mean, delta);
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.BitwiseMutationFunction#setMutationProbability(double)}
   * .
   */
  @Test
  public void testSetMutationProbability() {
    final BitwiseMutationFunction function = new BitwiseMutationFunction();
    
    // no mutations
    int newMutationProbability = 0;
    function.setMutationProbability(newMutationProbability);

    // create a list of all individuals.
    final List<BinaryString> allIndividuals = new Vector<BinaryString>();
    
    // initialize binary strings, all initially 0
    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      allIndividuals.add(new CharArrayBinaryString(LENGTH));
    }

    for (final BinaryString individual : allIndividuals) {
      
      function.mutate(individual);

      for (final byte bit : individual) {
        assertEquals(0, bit);
      }
    }

    // every bit gets mutated
    newMutationProbability = 1;
    function.setMutationProbability(newMutationProbability);
    
    for (final BinaryString individual : allIndividuals) {
      
      function.mutate(individual);
      
      for (final byte bit : individual) {
        assertEquals(1, bit);
      }
    }
    
  }
}
