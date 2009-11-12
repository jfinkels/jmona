/**
 * UtilTester.java
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
package jmona;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the testing utility class.
 * 
 * @author jfinkels
 */
public class UtilTester {

  /**
   * Test method for {@link jmona.Util#fail(java.lang.Throwable)}.
   */
  @Test
  public void testFail() {
    try {
      Util.fail(new Exception());
    } catch (final AssertionError error) {
      assertTrue(error instanceof AssertionError);
    }
  }

}
