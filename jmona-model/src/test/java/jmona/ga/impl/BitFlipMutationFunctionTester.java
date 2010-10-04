/**
 * BitFlipMutationFunctionTester.java
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
package jmona.ga.impl;

import static org.junit.Assert.assertEquals;
import jmona.impl.mutable.MutableByte;

import org.junit.Test;

/**
 * Test class for the BitFlipMutationFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class BitFlipMutationFunctionTester {

  /**
   * Test method for
   * {@link jmona.ga.impl.BitFlipMutationFunction#BitFlipMutationFunction()}.
   */
  @Test
  public void testMutate() {
    final BitFlipMutationFunction function = new BitFlipMutationFunction();
    final MutableByte bit = new MutableByte(0);
    function.mutate(bit);
    assertEquals(1, bit.byteValue());
    function.mutate(bit);
    assertEquals(0, bit.byteValue());
  }

}
