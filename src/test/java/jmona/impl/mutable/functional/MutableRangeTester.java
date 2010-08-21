/**
 * MutableRangeTester.java
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
package jmona.impl.mutable.functional;

import static org.junit.Assert.assertEquals;
import jmona.impl.mutable.MutableInteger;

import org.junit.Test;

/**
 * Test class for the MutableRange class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableRangeTester {

  /**
   * Test method for {@link jmona.functional.MutableRange#MutableRange(int)}.
   */
  @Test
  public void testMutableRangeInt() {
    int j = 0;
    for (final MutableInteger i : new MutableRange(10)) {
      assertEquals(j, i.intValue());
      j += 1;
    }

    assertEquals(10, j);
  }

  /**
   * Test method for
   * {@link jmona.functional.MutableRange#MutableRange(int, int)}.
   */
  @Test
  public void testMutableRangeIntInt() {
    int j = 5;
    for (final MutableInteger i : new MutableRange(5, 10)) {
      assertEquals(j, i.intValue());
      j += 1;
    }

    assertEquals(10, j);
  }

  /**
   * Test method for
   * {@link jmona.functional.MutableRange#MutableRange(int, int, int)}.
   */
  @Test
  public void testMutableRangeIntIntInt() {
    int j = 0;
    for (final MutableInteger i : new MutableRange(0, 10, 2)) {
      assertEquals(j, i.intValue());
      j += 2;
    }

    assertEquals(10, j);
  }

  /**
   * Test method for {@link jmona.functional.MutableRange#getValue()}.
   */
  @Test
  public void testGetValue() {
    final MutableRange range = new MutableRange(10);
    for (int i = 0; i < 10; ++i) {
      range.next();
      assertEquals(i, range.getValue().intValue());
    }
  }

}
