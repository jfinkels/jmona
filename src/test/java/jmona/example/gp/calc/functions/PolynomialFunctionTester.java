/**
 * PolynomialFunctionTester.java
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
package jmona.example.gp.calc.functions;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PolynomialFunction class.
 * 
 * @author jfinkels
 */
public class PolynomialFunctionTester {

  /** The coefficients in the polynomial. */
  private double[] coefficients = null;
  /** The function under test. */
  private PolynomialFunction function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.coefficients = new double[] { 1, 2, 1 }; // x^2 + 2x + 1 = (x + 1)^2
    this.function = new PolynomialFunction(this.coefficients);
  }

  /**
   * Test method for
   * {@link jmona.example.gp.calc.functions.PolynomialFunction#PolynomialFunction(double[])}
   * .
   */
  @Test
  public void testPolynomialFunction() {
    assertArrayEquals(this.coefficients, this.function.coefficients(),
        ZERO_DELTA);
  }

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.gp.calc.functions.PolynomialFunction#coefficients()}.
   */
  @Test
  public void testCoefficients() {
    this.function.coefficients()[0] = Double.POSITIVE_INFINITY;
    assertFalse(Double.POSITIVE_INFINITY == this.function.coefficients()[0]);
  }

  /**
   * Test method for
   * {@link jmona.example.gp.calc.functions.PolynomialFunction#execute(java.lang.Double)}
   * .
   */
  @Test
  public void testExecute() {
    final double[] inputs = new double[] { 0, -1, 1, -2, 2 };
    final double[] expectedOutputs = new double[] { 1, 0, 4, 1, 9 };

    for (int i = 0; i < inputs.length; ++i) {
      assertEquals(expectedOutputs[i], this.function.execute(inputs[i])
          .doubleValue(), ZERO_DELTA);
    }
  }
}