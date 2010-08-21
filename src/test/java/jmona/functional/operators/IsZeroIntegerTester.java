/**
 * IsZeroIntegerTester.java
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
package jmona.functional.operators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for the IsZeroInteger class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class IsZeroIntegerTester {

  /**
   * Test method for
   * {@link jmona.functional.operators.IsZeroInteger#execute(java.lang.Integer)}
   * .
   */
  @Test
  public void testExecute() {
    IsZeroInteger condition = new IsZeroInteger();
    assertFalse(condition.execute(1));
    assertFalse(condition.execute(-1));
    assertTrue(condition.execute(0));
  }

}
