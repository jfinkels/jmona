/**
 * MutableDoubleTester.java
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
 * Test class for the MutableDouble class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableDoubleTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for {@link jmona.impl.mutable.MutableDouble#MutableDouble()}.
   */
  @Test
  public void testMutableDouble() {
    new MutableDouble();
  }

  /**
   * Test method for
   * {@link jmona.impl.mutable.MutableDouble#MutableDouble(double)}.
   */
  @Test
  public void testMutableDoubleDouble() {
    final MutableDouble d = new MutableDouble(0);
    assertEquals(0.0, d.doubleValue(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.impl.mutable.MutableDouble#MutableDouble(java.lang.Number)}.
   */
  @Test
  public void testMutableDoubleNumber() {
    final MutableDouble b = new MutableDouble(1.1);
    assertEquals(1.1, b.doubleValue(), ZERO_DELTA);
  }

  /**
   * Test method for {@link jmona.impl.mutable.MutableDouble#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final MutableDouble d = new MutableDouble(1.5);
    final MutableDouble clone = d.deepCopy();
    assertNotSame(d, clone);
    assertEquals(d, clone);
  }

}
