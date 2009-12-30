/**
 * RangeTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the Range class.
 * 
 * @author jfinkels
 */
public class RangeTester {

  /** The maximum + 1 number in the range. */
  public static final int MAX = 17;
  /** The minimum number in the range. */
  public static final int MIN = 4;
  /** The increment by which the iterator steps over the range. */
  public static final int STEP = 3;
  /**
   * Test method for {@link jmona.impl.Range#range(int)}.
   */
  @Test
  public void testRangeInt() {
    int j = 0;
    for (final int i : Range.range(MAX)) {
      assertEquals(j, i);
      j += 1;
    }

    assertEquals(j, MAX);
  }

  /**
   * Test method for {@link jmona.impl.Range#range(int, int)}.
   */
  @Test
  public void testRangeIntInt() {
    int j = MIN;
    for (final int i : Range.range(MIN, MAX)) {
      assertEquals(j, i);
      j += 1;
    }

    assertEquals(j, MAX);
  }

  /**
   * Test method for {@link jmona.impl.Range#range(int, int, int)}.
   */
  @Test
  public void testRangeIntIntInt() {
    int j = MIN;
    for (final int i : Range.range(MIN, MAX, STEP)) {
      assertEquals(j, i);
      j += STEP;
    }
  }
  
  /**
   * Test method for {@link jmona.impl.Range#hasNext()}.
   */
  @Test
  public void testHasNext() {
    final Range range = new Range(1);

    assertTrue(range.hasNext());

    assertEquals(0, range.next().intValue());

    assertFalse(range.hasNext());

  }

  /**
   * Test method for {@link jmona.impl.Range#iterator()}.
   */
  @Test
  public void testIterator() {
    final Range range = new Range(MIN, MAX, STEP);

    int i = MIN;
    final Iterator<Integer> iterator = range.iterator();
    while (iterator.hasNext()) {
      assertEquals(i, iterator.next().intValue());
      i += STEP;
    }

    assertFalse(iterator.hasNext());
  }

  /**
   * Test method for {@link jmona.impl.Range#next()}.
   */
  @Test
  public void testNext() {
    Range range = new Range(MAX);

    for (int i = 0; i < MAX; ++i) {
      assertEquals(i, range.next().intValue());
    }

    try {
      range.next();
    } catch (final NoSuchElementException exception) {
      assertTrue(exception instanceof NoSuchElementException);
    }

    range = new Range(MIN, MAX);

    for (int i = MIN; i < MAX; ++i) {
      assertEquals(i, range.next().intValue());
    }

    try {
      range.next();
    } catch (final NoSuchElementException exception) {
      assertTrue(exception instanceof NoSuchElementException);
    }

    range = new Range(MIN, MAX, STEP);

    for (int i = MIN; i < MAX; i += STEP) {
      assertEquals(i, range.next().intValue());
    }

    try {
      range.next();
    } catch (final NoSuchElementException exception) {
      assertTrue(exception instanceof NoSuchElementException);
    }
  }

  /**
   * Test method for {@link jmona.impl.Range#Range(int)}.
   */
  @Test
  public void testRangeInt1() {
    int j = 0;
    for (final int i : new Range(MAX)) {
      assertEquals(j, i);
      j += 1;
    }

    assertEquals(j, MAX);
  }

  /**
   * Test method for {@link jmona.impl.Range#Range(int, int)}.
   */
  @Test
  public void testRangeIntInt1() {
    int j = MIN;
    for (final int i : new Range(MIN, MAX)) {
      assertEquals(j, i);
      j += 1;
    }

    assertEquals(j, MAX);
  }

  /**
   * Test method for {@link jmona.impl.Range#Range(int, int, int)}.
   */
  @Test
  public void testRangeIntIntInt1() {
    int j = MIN;
    for (final int i : new Range(MIN, MAX, STEP)) {
      assertEquals(j, i);
      j += STEP;
    }
  }

  /**
   * Test method for {@link jmona.impl.Range#remove()}.
   */
  @Test
  public void testRemove() {
    final Range range = new Range(MAX);
    try {
      range.remove();
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }
  }

}
