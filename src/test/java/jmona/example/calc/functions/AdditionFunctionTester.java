/**
 * AdditionFunctionTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
import jmona.MappingException;
import jmona.example.calc.operations.Util;

import org.junit.Test;

/**
 * Test class for the AdditionFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class AdditionFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.calc.functions.AdditionFunction#execute(java.lang.Double)}
   * .
   */
  @Test
  public void testExecute() {
    AdditionFunction function = null;

    try {
      function = new AdditionFunction(Util.ONE_FUNCTION, Util.ONE_FUNCTION);
      assertEquals(Util.TWO_FUNCTION.execute(0.0), function.execute(0.0),
          ZERO_DELTA);

      function = new AdditionFunction(Util.ONE_FUNCTION, Util.ZERO_FUNCTION);
      assertEquals(Util.ONE_FUNCTION.execute(0.0), function.execute(0.0),
          ZERO_DELTA);

      function = new AdditionFunction(Util.ZERO_FUNCTION, Util.TWO_FUNCTION);
      assertEquals(Util.TWO_FUNCTION.execute(0.0), function.execute(0.0),
          ZERO_DELTA);
    } catch (final MappingException exception) {
      jmona.test.Util.fail(exception);
    }
  }
}
