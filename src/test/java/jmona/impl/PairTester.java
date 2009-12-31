/**
 * PairTester.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the Pair class.
 * 
 * @author Jeffrey Finkelstein
 */
public class PairTester {

  /**
   * Test method for
   * {@link jmona.impl.Pair#Pair(java.lang.Object, java.lang.Object)} .
   */
  @Test
  public void testPair() {
    final Object left = new Object();
    final Object right = new Object();
    final Pair<Object, Object> pair = new Pair<Object, Object>(left, right);

    assertSame(left, pair.left());
    assertSame(right, pair.right());
    assertTrue(pair.toString().contains(left.toString()));
    assertTrue(pair.toString().contains(right.toString()));
  }

}
