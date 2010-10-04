/**
 * ListUtilsTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Test;

/**
 * Test class for the ListUtils class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ListUtilsTester {

  /** Test method for {@link ListUtils#count(Iterable)}. */
  @SuppressWarnings("unchecked")
  @Test
  public void testCount() {
    assertEquals(0, ListUtils.count(Collections.EMPTY_LIST).size());

    final Iterable<String> iterable = Arrays.asList("a", "a", "b", "c", "c",
        "c");
    final Map<String, Integer> result = ListUtils.count(iterable);
    assertEquals(3, result.size());
    assertEquals(2, result.get("a").intValue());
    assertEquals(1, result.get("b").intValue());
    assertEquals(3, result.get("c").intValue());
  }

  /**
   * Test method for {@link jmona.impl.ListUtils#ListUtils()}.
   */
  @Test
  public void testListUtils() {
    new ListUtils();
  }

  /**
   * Test method for
   * {@link jmona.impl.ListUtils#swap(java.util.List, java.util.List, int)}.
   */
  @Test
  public void testSwapLLInt() {
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

    ListUtils.swap(list1, list2, index);

    assertSame(list1Object0, list1.get(0));
    assertSame(list2Object0, list2.get(0));

    assertSame(list2Object1, list1.get(1));
    assertSame(list1Object1, list2.get(1));

    assertSame(list1Object2, list1.get(2));
    assertSame(list2Object2, list2.get(2));
  }

  /**
   * Test method for
   * {@link jmona.impl.ListUtils#swap(java.util.List, java.util.List, int, int)}
   * .
   */
  @Test
  public void testSwapLLIntInt() {
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
    ListUtils.swap(list1, list2, start, end);

    assertSame(list1Object0, list1.get(0));
    assertSame(list2Object0, list2.get(0));

    assertSame(list2Object1, list1.get(1));
    assertSame(list1Object1, list2.get(1));

    assertSame(list2Object2, list1.get(2));
    assertSame(list1Object2, list2.get(2));
  }

}
