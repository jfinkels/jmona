/**
 * AbstractBinaryStringCrossoverFunctionTester.java
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
import jmona.Pair;
import jmona.ga.BinaryString;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractBinaryStringCrossoverFunction class.
 * 
 * @author jfinkels
 */
public class AbstractBinaryStringCrossoverFunctionTester {

  /** The function under test. */
  private AbstractBinaryStringCrossoverFunction function = null;
  /** The length of the binary strings in the tests. */
  public static final int LENGTH = 10;
  /** The binary string of all zeros. */
  private BinaryString individual1 = null;
  /** The binary string of all ones. */
  private BinaryString individual2 = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new AbstractBinaryStringCrossoverFunction() {
      @Override
      protected Pair<Integer, Integer> sliceStartEnd(final int length) {
        return new Pair<Integer, Integer>(0, length);
      }

    };

    // this is the binary string of all zeros
    this.individual1 = new CharArrayBinaryString(LENGTH);

    // this is the binary string of all ones
    this.individual2 = new CharArrayBinaryString(LENGTH);
    for (int i = 0; i < LENGTH; ++i) {
      this.individual2.flipBit(i);
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.AbstractBinaryStringCrossoverFunction#swapBit(jmona.ga.BinaryString, jmona.ga.BinaryString, int)}
   * .
   */
  @Test
  public void testSwapBit() {

    final int index = 5;

    AbstractBinaryStringCrossoverFunction.swapBit(this.individual1,
        this.individual2, index);

    for (int i = 0; i < LENGTH; ++i) {
      if (i == index) {
        assertEquals(1, this.individual1.get(i).intValue());
        assertEquals(0, this.individual2.get(i).intValue());
      } else {
        assertEquals(0, this.individual1.get(i).intValue());
        assertEquals(1, this.individual2.get(i).intValue());
      }
    }

    AbstractBinaryStringCrossoverFunction.swapBit(this.individual1,
        this.individual2, index);

    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(0, this.individual1.get(i).intValue());
      assertEquals(1, this.individual2.get(i).intValue());
    }

  }

  /**
   * Test method for
   * {@link jmona.ga.impl.AbstractBinaryStringCrossoverFunction#swapBits(jmona.ga.BinaryString, jmona.ga.BinaryString, int, int)}
   * .
   */
  @Test
  public void testSwapBits() {
    final int start = 3;
    final int end = 9;
    AbstractBinaryStringCrossoverFunction.swapBits(this.individual1,
        this.individual2, start, end);

    for (int i = 0; i < LENGTH; ++i) {
      if (i >= start && i < end) {
        assertEquals(1, this.individual1.get(i).intValue());
        assertEquals(0, this.individual2.get(i).intValue());
      } else {
        assertEquals(0, this.individual1.get(i).intValue());
        assertEquals(1, this.individual2.get(i).intValue());
      }
    }

    AbstractBinaryStringCrossoverFunction.swapBits(this.individual1,
        this.individual2, start, end);

    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(0, this.individual1.get(i).intValue());
      assertEquals(1, this.individual2.get(i).intValue());
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.AbstractBinaryStringCrossoverFunction#sliceStartEnd(int)}
   * .
   */
  @Test
  public void testSliceStartEnd() {
    assertEquals(0, this.function.sliceStartEnd(LENGTH).left().intValue());
    assertEquals(LENGTH, this.function.sliceStartEnd(LENGTH).right().intValue());
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.AbstractBinaryStringCrossoverFunction#crossover(jmona.ga.BinaryString, jmona.ga.BinaryString)}
   * .
   */
  @Test
  public void testCrossover() {
    this.function.crossover(this.individual1, this.individual2);

    for (int i = 0; i < LENGTH; ++i) {
      assertEquals(1, this.individual1.get(i).intValue());
      assertEquals(0, this.individual2.get(i).intValue());
    }
  }

}
