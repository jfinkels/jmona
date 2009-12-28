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

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Test class for the Util class.
 * 
 * @author jfinkels
 */
public class UtilTester {

  /** The number of times to repeat the test. */
  public static final int NUM_TESTS = 10000;

  /**
   * Test method for {@link jmona.impl.Util#deepCopy(java.util.Collection)}.
   */
  @Test
  public void testDeepCopy() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link jmona.impl.Util#firstValue(java.util.Map)}.
   */
  @Test
  public void testFirstValue() {
    fail("Not yet implemented");
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
   * Test method for
   * {@link jmona.impl.Util#swap(java.util.List, java.util.List, int)}.
   */
  @Test
  public void testSwapListOfEListOfEInt() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.Util#swap(java.util.List, java.util.List, int, int)}.
   */
  @Test
  public void testSwapListOfEListOfEIntInt() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link jmona.impl.Util#Util()}.
   */
  @Test
  public void testUtil() {
    fail("Not yet implemented");
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
}
