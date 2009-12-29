/**
 * UtilTester.java
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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import jmona.exceptions.CopyingException;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the Util class.
 * 
 * @author jfinkels
 */
public class UtilTester {

  /** The number of individuals in a list. */
  public static final int NUM_INDIVIDUALS = 100;
  /** The number of times to repeat the test. */
  public static final int NUM_TESTS = 10000;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for {@link jmona.impl.Util#deepCopy(java.util.Collection)}.
   */
  @Test
  public void testDeepCopy() {
    final List<ExampleIndividual> list = new Vector<ExampleIndividual>();

    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      list.add(new ExampleIndividual(i));
    }

    List<ExampleIndividual> clonedList = null;
    try {
      clonedList = Util.deepCopy(list);
    } catch (final CopyingException exception) {
      jmona.test.Util.fail(exception);
    }

    assertEquals(list.size(), clonedList.size());

    for (int i = 0; i < list.size(); ++i) {
      assertNotSame(list.get(i), clonedList.get(i));
      assertEquals(list.get(i).fitness(), clonedList.get(i).fitness(),
          ZERO_DELTA);
    }
  }

  /**
   * Test method for {@link jmona.impl.Util#firstValue(java.util.Map)}.
   */
  @Test
  public void testFirstValue() {
    final Map<Object, Object> map = new HashMap<Object, Object>();

    final Object key = new Object();
    final Object value = new Object();

    map.put(key, value);

    assertEquals(value, Util.firstValue(map));
  }

  /**
   * Test method for
   * {@link jmona.impl.Util#randomFromCollection(java.util.Collection)}.
   */
  @Test
  public void testRandomFromCollection() {
    final Set<Object> set = new HashSet<Object>();
    final Object object1 = new Object();
    final Object object2 = new Object();
    final Object object3 = new Object();

    set.add(object1);
    set.add(object2);
    set.add(object3);

    Map<Object, Integer> selectionsMap = new HashMap<Object, Integer>();

    Object choice = null;
    for (int i = 0; i < NUM_TESTS; ++i) {
      choice = Util.randomFromCollection(set);

      if (selectionsMap.containsKey(choice)) {
        selectionsMap.put(choice, selectionsMap.get(choice) + 1);
      } else {
        selectionsMap.put(choice, 1);
      }
    }

    int sum = 0;
    for (final Integer selections : selectionsMap.values()) {
      sum += selections;
    }

    final double meanSelections = (double) sum / selectionsMap.size();

    final double delta = meanSelections * 0.1;

    for (final Integer selection : selectionsMap.values()) {
      assertEquals(meanSelections, selection, delta);
    }
  }

  /**
   * Test for randomly selecting an Object uniformly from a set with only one
   * element.
   */
  @Test
  public void testRandomFromSingletonSet() {
    final Set<Object> set = new HashSet<Object>();
    final Object object = new Object();
    set.add(object);

    Object choice = null;
    for (int i = 0; i < NUM_TESTS; ++i) {
      choice = Util.randomFromCollection(set);
      assertSame(object, choice);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.Util#swap(java.util.List, java.util.List, int)}.
   */
  @Test
  public void testSwapListOfEListOfEInt() {
    final List<Object> list1 = new Vector<Object>();
    final List<Object> list2 = new Vector<Object>();
    final int index = 1;

    final Object list1Object0 = new Object();
    final Object list1Object1 = new Object();
    final Object list1Object2 = new Object();

    final Object list2Object0 = new Object();
    final Object list2Object1 = new Object();
    final Object list2Object2 = new Object();

    list1.add(list1Object0);
    list1.add(list1Object1);
    list1.add(list1Object2);

    list2.add(list2Object0);
    list2.add(list2Object1);
    list2.add(list2Object2);

    Util.swap(list1, list2, index);

    assertSame(list1Object0, list1.get(0));
    assertSame(list2Object0, list2.get(0));

    assertSame(list2Object1, list1.get(1));
    assertSame(list1Object1, list2.get(1));

    assertSame(list1Object2, list1.get(2));
    assertSame(list2Object2, list2.get(2));
  }

  /**
   * Test method for
   * {@link jmona.impl.Util#swap(java.util.List, java.util.List, int, int)}.
   */
  @Test
  public void testSwapListOfEListOfEIntInt() {
    final List<Object> list1 = new Vector<Object>();
    final List<Object> list2 = new Vector<Object>();

    final Object list1Object0 = new Object();
    final Object list1Object1 = new Object();
    final Object list1Object2 = new Object();

    final Object list2Object0 = new Object();
    final Object list2Object1 = new Object();
    final Object list2Object2 = new Object();

    list1.add(list1Object0);
    list1.add(list1Object1);
    list1.add(list1Object2);

    list2.add(list2Object0);
    list2.add(list2Object1);
    list2.add(list2Object2);

    final int start = 1;
    final int end = 3;
    Util.swap(list1, list2, start, end);

    assertSame(list1Object0, list1.get(0));
    assertSame(list2Object0, list2.get(0));

    assertSame(list2Object1, list1.get(1));
    assertSame(list1Object1, list2.get(1));

    assertSame(list2Object2, list1.get(2));
    assertSame(list1Object2, list2.get(2));
  }

  /**
   * Test method for {@link jmona.impl.Util#Util()}.
   */
  @Test
  public void testUtil() {
    new Util();
  }
}
