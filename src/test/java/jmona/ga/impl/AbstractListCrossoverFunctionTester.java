/**
 * AbstractListCrossoverFunctionTester.java
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
import jfcommon.functional.Range;
import jmona.DeepCopyableList;
import jmona.impl.DeepCopyableVector;
import jmona.impl.Pair;
import jmona.impl.mutable.MutableByte;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractListCrossoverFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class AbstractListCrossoverFunctionTester {

  /** The function under test. */
  private AbstractListCrossoverFunction<MutableByte> function = null;
  /** The length of the binary strings in the tests. */
  public static final int LENGTH = 10;
  /** The binary string of all zeros. */
  private DeepCopyableList<MutableByte> individual1 = null;
  /** The binary string of all ones. */
  private DeepCopyableList<MutableByte> individual2 = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new AbstractListCrossoverFunction<MutableByte>() {
      @Override
      protected Pair<Integer, Integer> sliceStartEnd(final int length) {
        return new Pair<Integer, Integer>(0, length);
      }

    };

    // this is the binary string of all zeros
    this.individual1 = new DeepCopyableVector<MutableByte>();
    this.individual2 = new DeepCopyableVector<MutableByte>();

    for (int i = 0; i < LENGTH; ++i) {
      this.individual1.add(new MutableByte(0));
      this.individual2.add(new MutableByte(1));
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
