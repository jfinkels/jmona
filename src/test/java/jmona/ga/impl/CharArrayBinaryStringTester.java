/**
 * CharArrayBinaryStringTester.java
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import jmona.ga.BinaryString;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the CharArrayBinaryString class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class CharArrayBinaryStringTester {

  /** The length of the binary string for these tests. */
  public static final int LENGTH = 15;
  /** An index into the binary string. */
  public static final int INDEX = LENGTH / 2;
  /** A one byte. */
  public static final Byte ONE = 1;
  /** An index into the binary string. */
  public static final int TEST_INDEX = 10;
  /** A zero byte. */
  public static final Byte ZERO = 0;
  /** The binary string under test. */
  private CharArrayBinaryString bitstring = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.bitstring = new CharArrayBinaryString(LENGTH);
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#CharArrayBinaryString(int)}.
   */
  @Test
  public void testBitStringInt() {
    assertEquals(LENGTH, this.bitstring.size());
    for (final byte bit : this.bitstring) {
      assertEquals(0, bit);
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#CharArrayBinaryString(int, boolean)}
   * .
   */
  @Test
  public void testBitStringIntBoolean() {
    this.bitstring = new CharArrayBinaryString(LENGTH, false);

    assertEquals(LENGTH, this.bitstring.size());

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
   * {@link jmona.ga.impl.CharArrayBinaryString#CharArrayBinaryString(int, char[])}
   * .
   */
  @Test
  public void testBitStringIntCharArray() {
    final char[] chars = new char[] { 0x0808, 0x7f7f, 0, 1 };
    this.bitstring = new CharArrayBinaryString(chars.length * Character.SIZE,
        chars);

    assertEquals(chars.length * Character.SIZE, this.bitstring.size());
//    assertEquals(2 + 14 + 0 + 1, this.bitstring.bitCount());

    byte bit = 0;
    for (int i = 0; i < chars.length * Character.SIZE; ++i) {
      bit = this.bitstring.get(i).byteValue();

      if (i == 4 || i == 12 || (i >= 17 && i <= 23) || (i >= 25 && i <= 31)
          || i == 63) {
        assertEquals(1, bit);
      } else {
        assertEquals(0, bit);
      }
    }
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#checkIndex(int)}
   * .
   */
  @Test
  public void testCheckIndex() {
    try {
      this.bitstring.flipBit(LENGTH);
      Util.shouldHaveThrownException();
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }

    try {
      this.bitstring.flipBit(LENGTH + 1);
      Util.shouldHaveThrownException();
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#checkValue(byte)} .
   */
  @Test
  public void testCheckValue() {
    final int index = 1;
    Byte value = 1;

    try {
      this.bitstring.set(index, value);
    } catch (final IllegalArgumentException exception) {
      Util.fail(exception);
    }

    value = 2;
    try {
      this.bitstring.set(index, value);
      Util.shouldHaveThrownException();
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#contains(Object)}.
   */
  @Test
  public void testContains() {
    assertFalse(this.bitstring.contains(ONE));
    this.bitstring.flipBit(0);
    assertTrue(this.bitstring.contains(ONE));
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#containsAll(Collection)}.
   */
  @Test
  public void testContainsAll() {
    final List<Byte> values = new Vector<Byte>();
    values.add(ZERO);
    values.add(ONE);

    assertFalse(this.bitstring.containsAll(values));

    for (int i = 0; i < this.bitstring.size(); ++i) {
      this.bitstring.flipBit(i);
    }

    assertFalse(this.bitstring.containsAll(values));

    this.bitstring.flipBit(0);

    assertTrue(this.bitstring.containsAll(values));
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
      assertEquals(this.bitstring.get(i).intValue(), clone.get(i).intValue());
    }
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#flipBit(int)}.
   */
  @Test
  public void testFlipBit() {
    int index = TEST_INDEX;

    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.bitstring.get(i).intValue());
      } else {
        assertEquals(0, this.bitstring.get(i).intValue());
      }
    }

    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(0, this.bitstring.get(i).intValue());
    }

    index = 0;
    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.bitstring.get(i).intValue());
      } else {
        assertEquals(0, this.bitstring.get(i).intValue());
      }
    }
    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(0, this.bitstring.get(i).intValue());
    }

    index = TEST_INDEX / 2;
    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.bitstring.get(i).intValue());
      } else {
        assertEquals(0, this.bitstring.get(i).intValue());
      }
    }

    this.bitstring.flipBit(index);
    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(0, this.bitstring.get(i).intValue());
    }
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#get(int)}.
   */
  @Test
  public void testget() {
    for (final byte bit : this.bitstring) {
      assertEquals(0, bit);
    }

    final int index = 10;
    this.bitstring.flipBit(index);

    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.bitstring.get(i).intValue());
      } else {
        assertEquals(0, this.bitstring.get(i).intValue());
      }
    }

  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#lastIndexOf(Object)} and
   * {@link jmona.ga.impl.CharArrayBinaryString#indexOf(Object)}.
   */
  @Test
  public void testIndexOf() {
    final int smallIndex = 1;
    final int largeIndex = 5;

    this.bitstring.flipBit(smallIndex);
    this.bitstring.flipBit(largeIndex);

    final Byte value = 1;

    assertEquals(smallIndex, this.bitstring.indexOf(value));
    assertEquals(largeIndex, this.bitstring.lastIndexOf(value));

    this.bitstring.flipBit(smallIndex);
    this.bitstring.flipBit(largeIndex);

    assertEquals(-1, this.bitstring.indexOf(value));
    assertEquals(-1, this.bitstring.lastIndexOf(value));
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#isEmpty()}.
   */
  @Test
  public void testIsEmpty() {
    assertFalse(this.bitstring.isEmpty());

    this.bitstring = new CharArrayBinaryString(0);
    assertTrue(this.bitstring.isEmpty());
  }
  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#iterator()}.
   */
  @Test
  public void testIterator() {
    final Iterator<Byte> iterator = this.bitstring.iterator();

    int i = 0;
    while (iterator.hasNext()) {
      assertEquals(iterator.next().intValue(), this.bitstring.get(i).intValue());
      i += 1;
    }
    assertEquals(i, this.bitstring.size());
  }
  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#listIterator()}
   * and {@link jmona.ga.impl.CharArrayBinaryString#listIterator(int)}.
   */
  @Test
  public void testListIterator() {
    ListIterator<Byte> iterator = this.bitstring.listIterator();

    int i = 0;
    while (iterator.hasNext()) {
      assertEquals(iterator.next().intValue(), this.bitstring.get(i).intValue());
      i += 1;
    }

    assertEquals(this.bitstring.size(), i);
    i -= 1;

    this.bitstring.flipBit(0);

    while (iterator.hasPrevious()) {
      i -= 1;
      assertEquals(iterator.previous().intValue(), this.bitstring.get(i)
          .intValue());
    }

    assertEquals(0, i);

    iterator = this.bitstring.listIterator(LENGTH);

    i = LENGTH;
    while (iterator.hasPrevious()) {
      i -= 1;
      assertEquals(iterator.previous().intValue(), this.bitstring.get(i)
          .intValue());
    }

    assertEquals(0, i);
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#set(int, Byte)}.
   */
  @Test
  public void testSet() {
    final int index = 1;

    assertEquals(this.bitstring.get(index).intValue(), this.bitstring.set(
        index, ZERO).intValue());

    this.bitstring.set(index, ONE);

    assertEquals(ONE.intValue(), this.bitstring.get(index).intValue());

    this.bitstring.set(index, ONE);

    assertEquals(ONE.intValue(), this.bitstring.get(index).intValue());

    this.bitstring.set(index, ZERO);

    assertEquals(ZERO.intValue(), this.bitstring.get(index).intValue());

    this.bitstring.set(index, ZERO);

    assertEquals(ZERO.intValue(), this.bitstring.get(index).intValue());
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#size()}.
   */
  @Test
  public void testSize() {
    assertEquals(LENGTH, this.bitstring.size());
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#toArray()}.
   */
  @Test
  public void testToArray() {
    final Byte[] array = this.bitstring.toArray();
    assertEquals(array.length, this.bitstring.size());

    for (int i = 0; i < array.length; ++i) {
      assertEquals(this.bitstring.get(i), array[i]);
    }

  }

  /**
   * Test method for
   * {@link jmona.ga.impl.CharArrayBinaryString#toArray(Object[])}.
   */
  @Test
  public void testToArrayObject() {
    // this array is not big enough to hold the entire binary string
    final Byte[] array = new Byte[LENGTH - 1];

    final Byte[] newArray = this.bitstring.toArray(array);

    assertNotSame(array, newArray);

    assertEquals(newArray.length, this.bitstring.size());

    for (int i = 0; i < newArray.length; ++i) {
      assertEquals(this.bitstring.get(i), newArray[i]);
    }

    final Byte[] newNewArray = this.bitstring.toArray(newArray);

    assertSame(newArray, newNewArray);

    for (int i = 0; i < newNewArray.length; ++i) {
      assertEquals(this.bitstring.get(i), newNewArray[i]);
    }

    final Byte[] largeArray = new Byte[2 * LENGTH];

    final Byte[] newLargeArray = this.bitstring.toArray(largeArray);

    assertSame(largeArray, newLargeArray);

    for (int i = 0; i < this.bitstring.size(); ++i) {
      assertEquals(this.bitstring.get(i), newLargeArray[i]);
    }

    assertNull(newLargeArray[this.bitstring.size()]);
  }

  /**
   * Test method for {@link jmona.ga.impl.CharArrayBinaryString#toString()}.
   */
  @Test
  public void testToString() {
    this.bitstring = new CharArrayBinaryString(LENGTH, true);
    String result = this.bitstring.toString();
    assertEquals(this.bitstring.size(), result.length());

    for (int i = 0; i < LENGTH; ++i) {
      if (this.bitstring.get(i).intValue() == 1) {
        assertEquals('1', result.charAt(i));
      } else {
        assertEquals('0', result.charAt(i));
      }
    }
  }

  /** Test for unsupported operations. */
  @Test
  public void testUnsupportedOperations() {
    final CharArrayBinaryString binaryString = new CharArrayBinaryString(LENGTH);
    final int index = 0;
    final Byte testBit = 0;
    final Collection<Byte> collection = new Vector<Byte>();
    collection.add(testBit);

    try {
      binaryString.add(testBit);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.add(index, testBit);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.addAll(collection);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.addAll(index, collection);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.clear();
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.remove(index);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.remove(testBit);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.removeAll(collection);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.retainAll(collection);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }

    try {
      binaryString.subList(index, index);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }
  }
}
