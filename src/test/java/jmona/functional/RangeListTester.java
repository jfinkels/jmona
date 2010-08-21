/**
 * RangeListTester.java
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

import java.util.List;

import org.junit.Test;

/**
 * Test class for the RangeList class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class RangeListTester {

  /**
   * Test method for {@link jmona.functional.RangeList#RangeList(int)}.
   */
  @Test
  public void testRangeListInt() {
    final List<Integer> list = new RangeList(10);
    assertEquals(10, list.size());
    for (int i = 0; i < 10; ++i) {
      assertEquals(i, list.get(i).intValue());
    }
  }

  /**
   * Test method for {@link jmona.functional.RangeList#RangeList(int, int)}.
   */
  @Test
  public void testRangeListIntInt() {
    final List<Integer> list = new RangeList(5, 10);
    assertEquals(5, list.size());
    for (int i = 0; i < 5; ++i) {
      assertEquals(5 + i, list.get(i).intValue());
    }
  }

  /**
   * Test method for {@link jmona.functional.RangeList#RangeList(int, int, int)}
   * .
   */
  @Test
  public void testRangeListIntIntInt() {
    final List<Integer> list = new RangeList(0, 20, 2);
    assertEquals(10, list.size());
    for (int i = 0; i < 10; ++i) {
      assertEquals(2 * i, list.get(i).intValue());
    }
  }

}
