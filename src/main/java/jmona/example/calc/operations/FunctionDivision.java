/**
 * FunctionAddition.java
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
package jmona.example.calc.operations;

import jmona.Function;
import jmona.example.calc.functions.DivisionFunction;

/**
 * An operation which provides simple division of two real-valued
 * SingleInputFunctions.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class FunctionDivision implements
    UniformBinaryOperation<Function<Double, Double>> {

  /** A singleton instance of this class. */
  private static final FunctionDivision INSTANCE = new FunctionDivision();

  /** The String symbol which represents this operation. */
  public static final String SYMBOL = "/";

  /**
   * Get a singleton instance of this class.
   * 
   * @return A singleton instance of this class.
   */
  public static FunctionDivision newInstance() {
    return FunctionDivision.INSTANCE;
  }

  /**
   * Returns a function which is the quotient of the two specified input
   * functions.
   * 
   * @param object1
   *          A function.
   * @param object2
   *          Another function
   * @return A function which is the quotient of the two specified input
   *         functions.
   * @see jmona.example.calc.operations.BinaryOperation#operate(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public Function<Double, Double> operate(
      final Function<Double, Double> object1,
      final Function<Double, Double> object2) {
    return new DivisionFunction(object1, object2);
  }

  /**
   * Get the String which represents this operation.
   * 
   * @return The String which represents this operation.
   */
  @Override
  public String toString() {
    return SYMBOL;
  }
}
