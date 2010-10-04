/**
 * MutableFloatTester.java
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
 * Test class for the MutableFloat class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableFloatTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for {@link jmona.impl.mutable.MutableFloat#MutableFloat()}.
   */
  @Test
  public void testMutableFloat() {
    new MutableFloat();
  }

  /**
   * Test method for
   * {@link jmona.impl.mutable.MutableFloat#MutableFloat(float)}.
   */
  @Test
  public void testMutableFloatFloat() {
    final MutableFloat f = new MutableFloat(0f);
    assertEquals(0.0, f.floatValue(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.impl.mutable.MutableFloat#MutableFloat(java.lang.Number)}.
   */
  @Test
  public void testMutableFloatNumber() {
    final MutableFloat f = new MutableFloat(0.25f);
    assertEquals(0.25, f.floatValue(), ZERO_DELTA);
  }

  /**
   * Test method for {@link jmona.impl.mutable.MutableFloat#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final MutableFloat f = new MutableFloat(1.5f);
    final MutableFloat clone = f.deepCopy();
    assertNotSame(f, clone);
    assertEquals(f, clone);
  }

}
