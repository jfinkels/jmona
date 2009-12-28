/**
 * OnePointCrossoverFunctionTester.java
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
package jmona.ga.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmona.impl.Pair;

import org.junit.Test;

/**
 * Test class for the OnePointCrossoverFunction class.
 * 
 * @author jfinkels
 */
public class OnePointCrossoverFunctionTester {

  /**
   * Test method for
   * {@link jmona.ga.impl.OnePointCrossoverFunction#sliceStartEnd(int)}.
   */
  @Test
  public void testSliceStartEnd() {
    final OnePointCrossoverFunction function = new OnePointCrossoverFunction();

    final int length = 14;
    final Pair<Integer, Integer> pair = function.sliceStartEnd(length);

    assertTrue(pair.left() >= 0 && pair.left() <= length);
    assertEquals(length, pair.right().intValue());
  }

}
