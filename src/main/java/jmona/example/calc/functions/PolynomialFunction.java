/**
 * PolynomialFunction.java
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

import jfcommon.functional.Function;
import jfcommon.functional.Range;


/**
 * An immutable polynomial in a single variable with an array of coefficient
 * values specified at instantiation.
 * 
 * For example, the polynomial <em>f(x)=1.5x<sup>2</sup>+3.2x+0.7</em> is
 * represented by the array {@code 0.7, 3.2, 1.5} . Note that the values in the
 * array are in reverse order from the canonical form of the polynomial (that
 * is, with variables in order of decreasing degree).
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class PolynomialFunction implements Function<Double, Double> {

  /**
   * The String representing the format in which to output one variable, with
   * its coefficient, and its degree in the polynomial, when returned by the
   * {@link PolynomialFunction#toString()} method.
   */
  public static final String FORMAT_STRING = "+%.4g*x^%d";

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
   * @throws IllegalArgumentException
   *           If the input array is null or of length zero.
   */
  public PolynomialFunction(final double[] initialCoefficients) {
    if (initialCoefficients == null || initialCoefficients.length == 0) {
      throw new IllegalArgumentException(
          "Array must be non-null and non-empty.");
    }

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
   * @see jfcommon.functional.Function#execute(java.lang.Object )
   */
  @Override
  public Double execute(final Double input) {
    double result = 0.0;

    for (final int i : new Range(this.coefficients.length)) {
      result += this.coefficients[i] * (Math.pow(input, i));
    }

    return result;
  }

  /**
   * Return the String representation of this polynomial, with real coefficients
   * rounded to two decimal places.
   * 
   * @return The String representation of this polynomial.
   */
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder();

    result.append(this.coefficients[0]);

    for (final int i : new Range(this.coefficients.length)) {
      result.append(String.format(FORMAT_STRING, this.coefficients[i], i));
    }

    return result.toString();
  }
}
