/**
 * ConstantFunctionTester.java
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
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ConstantFunction class.
 * 
 * @author jfinkels
 */
public class ConstantFunctionTester {
  /** The constant value of the function under test. */
  public static final double CONSTANT_VALUE = 1.0;
  /** The increment for the input value to the constant function. */
  public static final double INCREMENT = 0.5;
  /** The maximum input value to the constant function. */
  public static final double MAX_INPUT = 100.0;
  /** The minimum input value to the constant function. */
  public static final double MIN_INPUT = -100.0;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The constant function under test in this class. */
  private ConstantFunction<Double, Double> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new ConstantFunction<Double, Double>(CONSTANT_VALUE);
  }

  /**
   * Test method for
   * {@link jmona.example.calc.functions.ConstantFunction#execute(java.lang.Object)}
   * .
   */
  @Test
  public void testExecute() {
    for (double i = MIN_INPUT; i < MAX_INPUT; i += INCREMENT) {
      assertEquals(CONSTANT_VALUE, this.function.execute(i), ZERO_DELTA);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.calc.functions.ConstantFunction#toString()}.
   */
  @Test
  public void testToString() {
    assertTrue(this.function.toString()
        .contains(String.valueOf(CONSTANT_VALUE)));
  }

}
