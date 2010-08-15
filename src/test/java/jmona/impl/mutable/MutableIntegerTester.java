/**
 * MutableIntegerTester.java
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

import org.junit.Test;

/**
 * Test class for the MutableInteger class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableIntegerTester {

  /**
   * Test method for {@link jmona.impl.mutable.MutableInteger#MutableInteger()}.
   */
  @Test
  public void testMutableInteger() {
    final MutableInteger i = new MutableInteger();
  }

  /**
   * Test method for {@link jmona.impl.mutable.MutableInteger#MutableInteger(int)}.
   */
  @Test
  public void testMutableIntegerInteger() {
    final MutableInteger i = new MutableInteger(0);
    assertEquals(0, i.intValue());
  }

  /**
   * Test method for
   * {@link jmona.impl.mutable.MutableInteger#MutableInteger(java.lang.Number)}.
   */
  @Test
  public void testMutableIntegerNumber() {
    final MutableInteger i = new MutableInteger(1.1);
    assertEquals(1, i.intValue());
  }

  /**
   * Test method for {@link jmona.impl.mutable.MutableInteger#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final MutableInteger i = new MutableInteger(1);
    final MutableInteger clone = i.deepCopy();
    assertEquals(i, clone);
  }

}
