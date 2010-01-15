/**
 * BinaryStringMutationFunctionTester.java
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
package jmona.ga.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmona.functional.Range;
import jmona.ga.BinaryString;

import org.junit.Test;

/**
 * Test class for the BinaryStringMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BinaryStringMutationFunctionTester {

  /** The number of tests to run. */
  public static final int NUM_TESTS = 100;

  /**
   * Test method for
   * {@link jmona.ga.impl.BinaryStringMutationFunction#mutate(jmona.ga.BinaryString)}
   * .
   */
  @Test
  public void testMutate() {
    final BinaryStringMutationFunction function = new BinaryStringMutationFunction();

    BinaryString bitstring = new CharArrayBinaryString(1);

    function.mutate(bitstring);

    assertEquals(1, bitstring.get(0).intValue());

    for (final int i : new Range(NUM_TESTS)) {
      bitstring = new CharArrayBinaryString(2);

      function.mutate(bitstring);
      assertTrue((bitstring.get(0) == 0 && bitstring.get(1) == 1)
          || (bitstring.get(0) == 1 && bitstring.get(1) == 0));
    }
  }

}
