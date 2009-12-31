/**
 * FunctionSubtraction.java
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

import jmona.SingleInputFunction;
import jmona.example.calc.functions.SubtractionFunction;
import jmona.gp.impl.UniformBinaryOperation;

/**
 * An operation which provides simple addition of two real-valued
 * SingleInputFunctions.
 * 
 * @author Jeffrey Finkelstein
 */
public class FunctionSubtraction implements
    UniformBinaryOperation<SingleInputFunction<Double, Double>> {

  /** A singleton instance of this class. */
  private static final FunctionSubtraction INSTANCE = new FunctionSubtraction();

  /** The String symbol which represents this operation. */
  public static final String SYMBOL = "-";

  /**
   * Get a singleton instance of this class.
   * 
   * @return A singleton instance of this class.
   */
  public static FunctionSubtraction newInstance() {
    return FunctionSubtraction.INSTANCE;
  }

  /**
   * Returns a function which is the difference between the two specified input
   * functions.
   * 
   * @param object1
   *          A function.
   * @param object2
   *          Another function
   * @return A function which is the difference between the two specified input
   *         functions.
   * @see jmona.gp.impl.BinaryOperation#operate(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public SingleInputFunction<Double, Double> operate(
      final SingleInputFunction<Double, Double> object1,
      final SingleInputFunction<Double, Double> object2) {
    return new SubtractionFunction(object1, object2);
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
