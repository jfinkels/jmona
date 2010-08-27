/**
 * BitFactoryTester.java
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
import static org.junit.Assert.assertTrue;
import jfcommon.functional.Range;
import jmona.impl.mutable.MutableByte;

import org.junit.Test;

/**
 * Test class for the BitFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class BitFactoryTester {

  /** The number of bits to create. */
  public static final int NUM_BITS = 100000;

  /**
   * Test method for {@link jmona.ga.impl.BitFactory#createObject()}.
   */
  @Test
  public void testCreateObject() {
    final BitFactory factory = new BitFactory();

    int numberOfOnes = 0;
    int numberOfZeros = 0;
    MutableByte bit = null;
    for (final int i : new Range(NUM_BITS)) {
      bit = factory.createObject();
      assertTrue(bit.byteValue() == 0 || bit.byteValue() == 1);

      if (bit.byteValue() == 0) {
        numberOfZeros += 1;
      }

      if (bit.byteValue() == 1) {
        numberOfOnes += 1;
      }
    }

    assertEquals(NUM_BITS, numberOfOnes + numberOfZeros);

    final int expected = NUM_BITS / 2;
    final double delta = expected * 0.1;
    assertEquals(expected, numberOfOnes, delta);
    assertEquals(expected, numberOfOnes, delta);
  }

}
