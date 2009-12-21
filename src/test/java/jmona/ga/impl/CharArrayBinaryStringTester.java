/**
 * CharArrayBinaryStringTester.java
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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import jmona.ga.BinaryString;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the CharArrayBinaryString class.
 * 
 * @author jfinkels
 */
public class CharArrayBinaryStringTester {

  /** The length of the binary string for these tests. */
  public static final int LENGTH = 15;
  /** The binary string under test. */
  private CharArrayBinaryString bitstring = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.bitstring = new CharArrayBinaryString(LENGTH);
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#bitCount()}.
   */
  @Test
  public void testBitCount() {

    assertEquals(0, this.bitstring.bitCount());

    this.bitstring.flipBit(0);
    assertEquals(1, this.bitstring.bitCount());

    this.bitstring.flipBit(1);
    assertEquals(2, this.bitstring.bitCount());

    this.bitstring.flipBit(0);
    assertEquals(1, this.bitstring.bitCount());
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#BitString(int)}.
   */
  @Test
  public void testBitStringInt() {
    assertEquals(LENGTH, this.bitstring.length());
    for (final byte bit : this.bitstring) {
      assertEquals(0, bit);
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#BitString(int, boolean)}.
   */
  @Test
  public void testBitStringIntBoolean() {
    this.bitstring = new CharArrayBinaryString(LENGTH, false);

    assertEquals(LENGTH, this.bitstring.length());

    for (final byte bit : this.bitstring) {
      assertEquals(0, bit);
    }

    this.bitstring = new CharArrayBinaryString(LENGTH, true);

    int numberOfOnes = 0;
    for (final byte bit : this.bitstring) {
      numberOfOnes += bit;
    }

    assertTrue(numberOfOnes > 0);
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#BitString(int, char[])}.
   */
  @Test
  public void testBitStringIntCharArray() {
    final char[] chars = new char[] { 0x0808, 0x7f7f, 0, 1 };
    this.bitstring = new CharArrayBinaryString(chars.length * Character.SIZE,
        chars);

    assertEquals(chars.length * Character.SIZE, this.bitstring.length());
    assertEquals(2 + 14 + 0 + 1, this.bitstring.bitCount());

    byte bit = 0;
    for (int i = 0; i < chars.length * Character.SIZE; ++i) {
      bit = this.bitstring.getBit(i);

      if (i == 4 || i == 12 || (i >= 17 && i <= 23) || (i >= 25 && i <= 31)
          || i == 63) {
        assertEquals(1, bit);
      } else {
        assertEquals(0, bit);
      }
    }
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {

    // create a random binary string
    this.bitstring = new CharArrayBinaryString(LENGTH, true);

    // clone that random binary string
    final BinaryString clone = this.bitstring.deepCopy();

    assertNotSame(clone, this.bitstring);

    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(this.bitstring.getBit(i), clone.getBit(i));
    }
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#flipBit(int)}.
   */
  @Test
  public void testFlipBit() {

    int index = 10;
    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.bitstring.getBit(i));
      } else {
        assertEquals(0, this.bitstring.getBit(i));
      }
    }

    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(0, this.bitstring.getBit(i));
    }

    index = 0;
    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.bitstring.getBit(i));
      } else {
        assertEquals(0, this.bitstring.getBit(i));
      }
    }
    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(0, this.bitstring.getBit(i));
    }

    index = 8;
    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.bitstring.getBit(i));
      } else {
        assertEquals(0, this.bitstring.getBit(i));
      }
    }

    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(0, this.bitstring.getBit(i));
    }
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#getBit(int)}.
   */
  @Test
  public void testGetBit() {
    for (final byte bit : this.bitstring) {
      assertEquals(0, bit);
    }

    final int index = 10;
    this.bitstring.flipBit(index);

    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.bitstring.getBit(i));
      } else {
        assertEquals(0, this.bitstring.getBit(i));
      }
    }

  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#iterator()}.
   */
  @Test
  public void testIterator() {
    this.bitstring = new CharArrayBinaryString(LENGTH, true);

    int i = 0;
    for (final byte bit : this.bitstring) {
      assertEquals(bit, this.bitstring.getBit(i));
      i += 1;
    }
    assertEquals(i, this.bitstring.length());
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#length()}.
   */
  @Test
  public void testLength() {
    assertEquals(LENGTH, this.bitstring.length());
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#toString()}.
   */
  @Test
  public void testToString() {
    this.bitstring = new CharArrayBinaryString(LENGTH, true);
    String result = this.bitstring.toString();
    assertEquals(this.bitstring.length(), result.length());

    for (int i = 0; i < LENGTH; ++i) {
      if (this.bitstring.getBit(i) == 1) {
        assertEquals('1', result.charAt(i));
      } else {
        assertEquals('0', result.charAt(i));
      }
    }
    
    int numberOfOnes = 0;
    for (int i = 0; i < LENGTH; ++i) {
      if (result.charAt(i) == '1') {
        numberOfOnes += 1;
      }
    }
    assertEquals(numberOfOnes, this.bitstring.bitCount());
    
  }

}
