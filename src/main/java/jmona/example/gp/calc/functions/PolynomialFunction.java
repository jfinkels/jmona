/**
 * PolynomialFunction.java
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

/**
 * An immutable polynomial in a single variable with an array of coefficient
 * values specified at instantiation.
 * 
 * For example, the polynomial <em>f(x)=1.5x<sup>2</sup>+3.2x+0.7</em> is
 * represented by the array {@code 0.7, 3.2, 1.5} . Note that the values in the
 * array are in reverse order from the canonical form of the polynomial (that
 * is, with variables in order of decreasing degree).
 * 
 * @author jfinkels
 */
public class PolynomialFunction implements SingleInputFunction<Double, Double> {

  /**
   * The coefficients of this polynomial, where the index in the array
   * corresponds to the degree of the variable.
   */
  private final double[] coefficients;

  /**
   * Instantiate a polynomial with the specified coefficients, where the index
   * in the array corresponds to the degree of the variable.
   * 
   * @param initialCoefficients
   *          The initial array of coefficients, where the index in the array
   *          corresponds to the degree of the variable.
   */
  public PolynomialFunction(final double[] initialCoefficients) {
    this.coefficients = initialCoefficients.clone();
  }

  /**
   * Get a clone of the array containing the coefficients of this polynomial,
   * where the index in the array corresponds to the degree of the variable.
   * 
   * @return A clone of the coefficients of this polynomial, where the index in
   *         the array corresponds to the degree of the variable.
   */
  public double[] coefficients() {
    return this.coefficients.clone();
  }

  /**
   * Return the value of the polynomial with the value of the variable specified
   * by the input value.
   * 
   * @param input
   *          The input value to this polynomial.
   * @return The value of this polynomial at the specified input value.
   * @see jmona.example.gp.calc.functions.SingleInputFunction#execute(java.lang.Object
   *      )
   */
  @Override
  public Double execute(final Double input) {
    double result = 0.0;

    for (int i = 0; i < this.coefficients.length; ++i) {
      result += this.coefficients[i] * (Math.pow(input, i));
    }

    return result;
  }
}
