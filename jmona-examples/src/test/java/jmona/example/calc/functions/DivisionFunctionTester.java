/**
 * DivisionFunctionTester.java
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
import jfcommon.functional.MappingException;
import jfcommon.test.TestUtils;
import jmona.example.calc.operations.Util;

import org.junit.Test;

/**
 * Test class for the DivisionFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class DivisionFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.calc.functions.DivisionFunction#execute(java.lang.Double)}
   * .
   */
  @Test
  public void testExecute() {
    DivisionFunction function = null;
    try {
      function = new DivisionFunction(Util.ONE_FUNCTION, Util.ONE_FUNCTION);
      assertEquals(Util.ONE_FUNCTION.execute(0.0), function.execute(0.0),
          ZERO_DELTA);

      function = new DivisionFunction(Util.ZERO_FUNCTION, Util.ONE_FUNCTION);
      assertEquals(Util.ZERO_FUNCTION.execute(0.0), function.execute(0.0),
          ZERO_DELTA);

      function = new DivisionFunction(Util.ONE_FUNCTION, Util.TWO_FUNCTION);
      assertEquals(Util.HALF_FUNCTION.execute(0.0), function.execute(0.0),
          ZERO_DELTA);
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    }
  }
}
