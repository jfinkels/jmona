/**
 * AbstractListCrossoverFunctionTester.java
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

import jmona.impl.Pair;
import jmona.impl.Range;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractListCrossoverFunction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class AbstractListCrossoverFunctionTester {

  /** The function under test. */
  private AbstractListCrossoverFunction<Byte> function = null;
  /** The length of the binary strings in the tests. */
  public static final int LENGTH = 10;
  /** The binary string of all zeros. */
  private List<Byte> individual1 = null;
  /** The binary string of all ones. */
  private List<Byte> individual2 = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new AbstractListCrossoverFunction<Byte>() {
      @Override
      protected Pair<Integer, Integer> sliceStartEnd(final int length) {
        return new Pair<Integer, Integer>(0, length);
      }

    };

    // this is the binary string of all zeros
    this.individual1 = new Vector<Byte>();
    this.individual2 = new Vector<Byte>();

    for (final int i : new Range(LENGTH)) {
      this.individual1.add((byte) 0);
      this.individual2.add((byte) 1);
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.AbstractListCrossoverFunction#sliceStartEnd(int)} .
   */
  @Test
  public void testSliceStartEnd() {
    assertEquals(0, this.function.sliceStartEnd(LENGTH).left().intValue());
    assertEquals(LENGTH, this.function.sliceStartEnd(LENGTH).right().intValue());
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.AbstractListCrossoverFunction#crossover(Object, Object)}
   * .
   */
  @Test
  public void testCrossover() {
    this.function.crossover(this.individual1, this.individual2);

    for (final int i : new Range(LENGTH)) {
      assertEquals(1, this.individual1.get(i).intValue());
      assertEquals(0, this.individual2.get(i).intValue());
    }
  }

}
