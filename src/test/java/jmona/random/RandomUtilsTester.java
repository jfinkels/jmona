/**
 * RandomUtilsTester.java
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
package jmona.random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import jmona.functional.Range;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the RandomUtils class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public class RandomUtilsTester {
  /** The number of times to repeat the test. */
  public static final int NUM_TESTS = 10000;

  /**
   * Test method for
   * {@link jmona.random.RandomUtils#choice(java.util.Collection)}.
   */
  @Test
  public void testChoice() {
    final Set<Object> set = new HashSet<Object>();
    final Object object1 = new Object();
    final Object object2 = new Object();
    final Object object3 = new Object();

    set.add(object1);
    set.add(object2);
    set.add(object3);

    Map<Object, Integer> selectionsMap = new HashMap<Object, Integer>();

    Object choice = null;
    for (final int i : new Range(NUM_TESTS)) {
      choice = RandomUtils.choice(set);

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
    for (final int i : new Range(NUM_TESTS)) {
      choice = RandomUtils.choice(set);
      assertSame(object, choice);
    }
  }

  /**
   * Test method for
   * {@link jmona.random.RandomUtils#sample(java.util.Collection, int)}.
   */
  @Test
  public void testSample() {
    final List<Object> list = new Vector<Object>();
    final Object o1 = new Object();
    final Object o2 = new Object();

    list.add(o1);
    list.add(o2);

    List<Object> sample = RandomUtils.sample(list, list.size());

    assertEquals(list.size(), sample.size());

    for (final Object o : sample) {
      assertTrue(list.contains(o));
    }

    int count1 = 0;
    int count2 = 0;
    for (final int i : new Range(NUM_TESTS)) {
      sample = RandomUtils.sample(list, 1);
      assertEquals(1, sample.size());

      if (sample.get(0).equals(o1)) {
        count1 += 1;
      } else {
        count2 += 1;
      }
    }

    final int expected = NUM_TESTS / 2;
    final double delta = expected * 0.1;

    assertEquals(expected, count1, delta);
    assertEquals(expected, count2, delta);

    count1 = 0;
    count2 = 0;
    for (final int i : new Range(NUM_TESTS)) {
      sample = RandomUtils.sample(list, 2);

      assertEquals(2, sample.size());

      if (sample.get(0).equals(o1)) {
        count1 += 1;
      } else {
        count2 += 1;
      }

      if (sample.get(1).equals(o2)) {
        count2 += 1;
      } else {
        count1 += 1;
      }
    }

    assertEquals(count1, count2);
  }

  /**
   * Test method for
   * {@link jmona.random.RandomUtils#sampleWithReplacement(java.util.Collection, int)}
   * .
   */
  @Test
  public void testSampleWithReplacement() {
    final List<Object> list = new Vector<Object>();
    final Object o1 = new Object();
    final Object o2 = new Object();

    list.add(o1);
    list.add(o2);

    List<Object> sample = RandomUtils.sampleWithReplacement(list, list.size());

    assertEquals(list.size(), sample.size());

    for (final Object o : sample) {
      assertTrue(list.contains(o));
    }

    int count1 = 0;
    int count2 = 0;
    for (final int i : new Range(NUM_TESTS)) {
      sample = RandomUtils.sampleWithReplacement(list, 1);
      assertEquals(1, sample.size());

      if (sample.get(0).equals(o1)) {
        count1 += 1;
      } else {
        count2 += 1;
      }
    }

    final int expected = NUM_TESTS / 2;
    final double delta = expected * 0.1;

    assertEquals(expected, count1, delta);
    assertEquals(expected, count2, delta);
    
    final Map<Object, Integer> counts = new HashMap<Object, Integer>();
    counts.put(o1, 0);
    counts.put(o2, 0);
    for (final int i : new Range(NUM_TESTS)) {
      sample = RandomUtils.sampleWithReplacement(list, 2);
      
      assertEquals(2, sample.size());
      
      final Map<Object, Integer> newCounts = Util.count(sample);
      for (final Object object : newCounts.keySet()) {
        counts.put(object, counts.get(object) + newCounts.get(object));
      }
    }

    final double delta2 = NUM_TESTS * 0.1;
    assertEquals(counts.get(o1), counts.get(o2), delta2);
  }

  /**
   * Test method for {@link jmona.random.RandomUtils#RandomUtils()}.
   */
  @Test
  public void testRandomUtils() {
    new RandomUtils();
  }

}
