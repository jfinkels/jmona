/**
 * FunctionalTester.java
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
package jmona.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import jmona.Condition;
import jmona.MappingException;
import jmona.functional.example.BadMapping;
import jmona.functional.example.ToHashCode;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the Functional class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class FunctionalTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for {@link jmona.functional.Functional#any(Iterable).
   */
  @Test
  public void testAny() {
    Iterable<Boolean> iterable = Arrays.asList();
    try {
      assertFalse(Functional.any(iterable));
    } catch (final MappingException exception) {
      Util.fail(exception);
    }

    iterable = Arrays.asList(true, false, false);
    try {
      assertTrue(Functional.any(iterable));
    } catch (final MappingException exception) {
      Util.fail(exception);
    }

    iterable = Arrays.asList(false, false, true);
    try {
      assertTrue(Functional.any(iterable));
    } catch (final MappingException exception) {
      Util.fail(exception);
    }

    iterable = Arrays.asList(false, false, false);
    try {
      assertFalse(Functional.any(iterable));
    } catch (final MappingException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.functional.Functional#all(Iterable).
   */
  @Test
  public void testAll() {
    Iterable<Boolean> iterable = Arrays.asList();
    assertTrue(Functional.all(iterable));

    iterable = Arrays.asList(true, false, false);
    assertFalse(Functional.all(iterable));

    iterable = Arrays.asList(true, false, true);
    assertFalse(Functional.all(iterable));

    iterable = Arrays.asList(true, true, true);
    assertTrue(Functional.all(iterable));
  }

  /**
   * Test method for
   * {@link jmona.functional.Functional#filter(jmona.Condition, java.lang.Iterable)}
   * .
   */
  @Test
  public void testFilter() {

    // create a condition to test for negative numbers
    final Condition<Integer> condition = new Condition<Integer>() {

      /**
       * Returns true if and only if the specified input is less than 0.
       * 
       * @param input
       *          The integer to test for negativity.
       * @return Whether the specified integer is less than 0.
       */
      @Override
      public Boolean execute(final Integer input) {
        return input < 0;
      }
    };

    // add all numbers from -size to size-1 to the list
    final int size = 10;
    final List<Integer> list = new Vector<Integer>();
    for (final int i : new Range(-1 * size, size)) {
      list.add(i);
    }

    assertEquals(size * 2, list.size());

    // filter the list by keeping only negative numbers
    List<Integer> after = null;
    try {
      after = Functional.filter(condition, list);
    } catch (final MappingException exception) {
      jmona.test.Util.fail(exception);
    }

    // assert half of the elements were removed
    assertEquals(size, after.size());

    // assert all elements are less than 0
    for (final int i : after) {
      assertTrue(i < 0);
    }
  }

  /**
   * Test method for {@link jmona.functional.Functional#Functional()}.
   */
  @Test
  public void testFunctional() {
    new Functional();
  }

  /**
   * Test method for
   * {@link jmona.functional.Functional#map(jmona.Function, java.lang.Iterable)}
   * .
   */
  @Test
  public void testMap() {
    final List<Object> list = new Vector<Object>();
    list.add(new Object());
    list.add(new Object());
    list.add(new Object());

    List<Integer> hashCodes = null;
    try {
      hashCodes = Functional.map(new ToHashCode(), list);
    } catch (final MappingException exception) {
      Util.fail(exception);
    }

    for (int i = 0; i < list.size(); ++i) {
      assertEquals(hashCodes.get(i).intValue(), list.get(i).hashCode());
    }

    try {
      Functional.map(new BadMapping(), list);
      Util.shouldHaveThrownException();
    } catch (final MappingException exception) {
      assertTrue(exception instanceof MappingException);
    }
  }

  /**
   * Test method for {@link jmona.functional.Functional#sum(Iterable)}.
   */
  @Test
  public void testSum() {
    final List<Integer> integerList = new Vector<Integer>();
    integerList.add(0);
    integerList.add(1);
    integerList.add(2);

    assertEquals(0 + 1 + 2, Functional.sumInteger(integerList));

    final List<Double> doubleList = new Vector<Double>();
    doubleList.add(0.0);
    doubleList.add(0.1);
    doubleList.add(0.2);

    assertEquals(0.0 + 0.1 + 0.2, Functional.sumDouble(doubleList), ZERO_DELTA);

  }

  /**
   * Test method for {@link jmona.functional.Functional#zip(Iterable, Iterable)}
   * .
   */
//  @Test
//  public void testZip() {
//    List<Integer> left = Arrays.asList(0, 1, 2);
//    List<Double> right = Arrays.asList(0.0, 1.0, 2.0);
//
//    List<Pair<Integer, Double>> zipped = Functional.zip(left, right);
//
//    assertEquals(zipped.size(), Math.min(left.size(), right.size()));
//
//    for (final int i : new Range(3)) {
//      assertEquals(zipped.get(i).left().intValue(), zipped.get(i).right()
//          .intValue());
//      assertEquals(zipped.get(i).left().doubleValue(), zipped.get(i).right()
//          .doubleValue(), ZERO_DELTA);
//    }
//
//    right = Arrays.asList(0.0, 1.0);
//    zipped = Functional.zip(left, right);
//
//    assertEquals(zipped.size(), Math.min(left.size(), right.size()));
//
//    for (final int i : new Range(Math.min(left.size(), right.size()))) {
//      assertEquals(zipped.get(i).left().intValue(), zipped.get(i).right()
//          .intValue());
//      assertEquals(zipped.get(i).left().doubleValue(), zipped.get(i).right()
//          .doubleValue(), ZERO_DELTA);
//    }
//  }
}
