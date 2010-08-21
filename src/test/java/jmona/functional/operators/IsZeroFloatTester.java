/**
 * IsZeroFloatTester.java
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
 * Test class for the IsZeroFloat class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class IsZeroFloatTester {

  /**
   * Test method for
   * {@link jmona.functional.operators.IsZeroFloat#execute(java.lang.Float)}
   * .
   */
  @Test
  public void testExecute() {
    IsZeroFloat condition = new IsZeroFloat();
    assertFalse(condition.execute(1.0f));
    assertFalse(condition.execute(-1.0f));
    assertTrue(condition.execute(0.0f));
  }

}
