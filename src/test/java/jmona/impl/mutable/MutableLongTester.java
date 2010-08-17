/**
 * MutableLongTester.java
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
package jmona.impl.mutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

/**
 * Test class for the MutableLong class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableLongTester {

  /**
   * Test method for {@link jmona.impl.mutable.MutableLong#MutableLong()}.
   */
  @Test
  public void testMutableLong() {
    final MutableLong l = new MutableLong();
  }

  /**
   * Test method for {@link jmona.impl.mutable.MutableLong#MutableLong(long)}.
   */
  @Test
  public void testMutableLongLong() {
    final MutableLong l = new MutableLong(0l);
    assertEquals(0l, l.longValue());
  }

  /**
   * Test method for
   * {@link jmona.impl.mutable.MutableLong#MutableLong(java.lang.Number)}.
   */
  @Test
  public void testMutableLongNumber() {
    final MutableLong l = new MutableLong(1l);
    assertEquals(1, l.longValue());
  }

  /**
   * Test method for {@link jmona.impl.mutable.MutableLong#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final MutableLong l = new MutableLong(2l);
    final MutableLong clone = l.deepCopy();
    assertNotSame(l, clone);
    assertEquals(l, clone);
  }

}
