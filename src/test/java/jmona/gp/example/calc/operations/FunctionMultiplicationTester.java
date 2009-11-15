/**
 * FunctionMultiplicationTester.java
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
package jmona.gp.example.calc.operations;

import static org.junit.Assert.assertEquals;
import jmona.gp.example.calc.functions.SingleInputFunction;
import jmona.gp.impl.UniformBinaryOperation;

import org.junit.Test;

/**
 * Test class for the FunctionMultiplication class.
 * 
 * @author jfinkels
 */
public class FunctionMultiplicationTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.gp.example.calc.operations.FunctionMultiplication#operate(jmona.gp.example.calc.functions.SingleInputFunction, jmona.gp.example.calc.functions.SingleInputFunction)}
   * .
   */
  @Test
  public void testOperate() {
    final UniformBinaryOperation<SingleInputFunction<Double, Double>> operation = FunctionMultiplication
        .newInstance();
    SingleInputFunction<Double, Double> result = null;

    // 0 * 0 = 0
    result = operation.operate(Util.ZERO_FUNCTION, Util.ZERO_FUNCTION);
    assertEquals(Util.ZERO_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 0 * 1 = 0
    result = operation.operate(Util.ZERO_FUNCTION, Util.ONE_FUNCTION);
    assertEquals(Util.ZERO_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 1 * 0 = 0
    result = operation.operate(Util.ONE_FUNCTION, Util.ZERO_FUNCTION);
    assertEquals(Util.ZERO_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 0 * 2 = 0
    result = operation.operate(Util.ZERO_FUNCTION, Util.TWO_FUNCTION);
    assertEquals(Util.ZERO_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 2 * 0 = 0
    result = operation.operate(Util.TWO_FUNCTION, Util.ZERO_FUNCTION);
    assertEquals(Util.ZERO_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 1 * 1 = 1
    result = operation.operate(Util.ONE_FUNCTION, Util.ONE_FUNCTION);
    assertEquals(Util.ONE_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 1 * 2 = 2
    result = operation.operate(Util.ONE_FUNCTION, Util.TWO_FUNCTION);
    assertEquals(Util.TWO_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 2 * 1 = 2
    result = operation.operate(Util.TWO_FUNCTION, Util.ONE_FUNCTION);
    assertEquals(Util.TWO_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 1 * 0.5 = 0.5
    result = operation.operate(Util.ONE_FUNCTION, Util.HALF_FUNCTION);
    assertEquals(Util.HALF_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

    // 2 * 0.5 = 1
    result = operation.operate(Util.TWO_FUNCTION, Util.HALF_FUNCTION);
    assertEquals(Util.ONE_FUNCTION.execute(0.0), result.execute(0.0),
        ZERO_DELTA);

  }

}
