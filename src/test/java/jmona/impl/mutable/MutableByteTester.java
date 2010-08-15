/**
 * MutableByteTester.java
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
 * Test class for the MutableByte class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableByteTester {

  /**
   * Test method for {@link jmona.impl.mutable.MutableByte#MutableByte()}.
   */
  @Test
  public void testMutableByte() {
    final MutableByte b = new MutableByte();
  }

  /**
   * Test method for {@link jmona.impl.mutable.MutableByte#MutableByte(byte)}.
   */
  @Test
  public void testMutableByteByte() {
    final MutableByte b = new MutableByte(0);
    assertEquals(0, b.byteValue());
  }

  /**
   * Test method for
   * {@link jmona.impl.mutable.MutableByte#MutableByte(java.lang.Number)}.
   */
  @Test
  public void testMutableByteNumber() {
    final MutableByte b = new MutableByte(1.1);
    assertEquals(1, b.byteValue());
  }

  /**
   * Test method for {@link jmona.impl.mutable.MutableByte#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final MutableByte b = new MutableByte(1);
    final MutableByte clone = b.deepCopy();
    assertEquals(b, clone);
  }

}
