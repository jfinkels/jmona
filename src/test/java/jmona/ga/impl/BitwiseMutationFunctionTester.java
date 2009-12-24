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
    
    BinaryString individual = null;
    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      
      // create a binary string with all 0s
      individual = new CharArrayBinaryString(LENGTH);
      
      // flip one bit at random
      function.mutate(individual);
      
      assertEquals(1, individual.bitCount());
    }

  }
}
