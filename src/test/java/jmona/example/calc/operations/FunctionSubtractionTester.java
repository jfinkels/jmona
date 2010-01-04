/**
 * FunctionSubtractionTester.java
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
package jmona.example.calc.operations;

import static org.junit.Assert.assertEquals;
import jmona.MappingException;
import jmona.Function;

import org.junit.Test;

/**
 * Test class for the FunctionSubtraction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class FunctionSubtractionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.calc.operations.FunctionSubtraction#operate(jmona.Function, jmona.Function)}
   * .
   */
  @Test
  public void testOperate() {
    final UniformBinaryOperation<Function<Double, Double>> operation = FunctionSubtraction
        .newInstance();
    Function<Double, Double> result = null;
    try {
      // 0 - 0 = 0
      result = operation.operate(Util.ZERO_FUNCTION, Util.ZERO_FUNCTION);
      assertEquals(Util.ZERO_FUNCTION.execute(0.0), result.execute(0.0),
          ZERO_DELTA);

      // 1 - 0 = 1
      result = operation.operate(Util.ONE_FUNCTION, Util.ZERO_FUNCTION);
      assertEquals(Util.ONE_FUNCTION.execute(0.0), result.execute(0.0),
          ZERO_DELTA);

      // 2 - 0 = 2
      result = operation.operate(Util.TWO_FUNCTION, Util.ZERO_FUNCTION);
      assertEquals(Util.TWO_FUNCTION.execute(0.0), result.execute(0.0),
          ZERO_DELTA);

      // 1 - 1 = 0
      result = operation.operate(Util.ONE_FUNCTION, Util.ONE_FUNCTION);
      assertEquals(Util.ZERO_FUNCTION.execute(0.0), result.execute(0.0),
          ZERO_DELTA);

      // 2 - 1 = 1
      result = operation.operate(Util.TWO_FUNCTION, Util.ONE_FUNCTION);
      assertEquals(Util.ONE_FUNCTION.execute(0.0), result.execute(0.0),
          ZERO_DELTA);

      // 1 - 0.5 = 0.5
      result = operation.operate(Util.ONE_FUNCTION, Util.HALF_FUNCTION);
      assertEquals(Util.HALF_FUNCTION.execute(0.0), result.execute(0.0),
          ZERO_DELTA);
    } catch (final MappingException exception) {
      jmona.test.Util.fail(exception);
    }
  }

}
