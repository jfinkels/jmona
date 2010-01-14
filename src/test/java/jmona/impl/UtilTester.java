/**
 * UtilTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jmona.CopyingException;
import jmona.functional.Range;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the TreeUtils class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class UtilTester {

  /** The number of individuals in a list. */
  public static final int NUM_INDIVIDUALS = 100;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for {@link jmona.impl.Util#deepCopy(java.util.Collection)}.
   */
  @Test
  public void testDeepCopy() {
    final List<ExampleIndividual> list = new Vector<ExampleIndividual>();

    for (final int i : new Range(NUM_INDIVIDUALS)) {
      list.add(new ExampleIndividual(i));
    }

    List<ExampleIndividual> clonedList = null;
    try {
      clonedList = Util.deepCopy(list);
    } catch (final CopyingException exception) {
      jmona.test.Util.fail(exception);
    }

    assertEquals(list.size(), clonedList.size());

    for (final int i : new Range(list.size())) {
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
   * Test method for {@link jmona.impl.Util#Util()}.
   */
  @Test
  public void testUtil() {
    new Util();
  }
}
