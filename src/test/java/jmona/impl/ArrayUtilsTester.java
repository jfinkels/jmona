/**
 * ArrayUtilsTester.java
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

import static org.junit.Assert.assertSame;
import jmona.functional.Range;

import org.junit.Test;

/**
 * Test class for the ArrayUtils class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ArrayUtilsTester {

  /**
   * Test method for
   * {@link jmona.impl.ArrayUtils#slice(java.lang.String[], int, int)}.
   */
  @Test
  public void testSlice() {
    final String[] array = { new String(), new String(), new String() };
    int start = 1;
    int end = 2;
    String[] result = ArrayUtils.slice(array, start, end);
    for (final int i : new Range(0, end - start)) {
      assertSame(array[start + i], result[i]);
    }

    start = 0;
    end = 3;
    result = ArrayUtils.slice(array, start, end);
    for (final int i : new Range(0, end - start)) {
      assertSame(array[start + i], result[i]);
    }

    start = 1;
    end = 3;
    result = ArrayUtils.slice(array, start, end);
    for (final int i : new Range(0, end - start)) {
      assertSame(array[start + i], result[i]);
    }
  }

  /**
   * Test method for {@link jmona.impl.ArrayUtils#ArrayUtils()}.
   */
  @Test
  public void testArrayUtils() {
    new ArrayUtils();
  }

}
