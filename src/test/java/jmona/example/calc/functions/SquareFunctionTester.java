/**
 * SquareFunctionTester.java
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
package jmona.example.calc.functions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the SquareFunction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class SquareFunctionTester {

  /** The value by which to increment the input to the function. */
  public static final double INCREMENT = 0.1;
  /** The maximum value of the input to the function. */
  public static final double MAX_VALUE = 100.0;
  /** The minimum value of the input to the function. */
  public static final double MIN_VALUE = 0.0;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.calc.functions.SquareFunction#execute(java.lang.Double)}
   * .
   */
  @Test
  public void testExecute() {
    final SquareFunction function = new SquareFunction();

    for (double x = MIN_VALUE; x < MAX_VALUE; x += INCREMENT) {
      assertEquals(Math.pow(x, 2), function.execute(x), ZERO_DELTA);
    }
  }

}
