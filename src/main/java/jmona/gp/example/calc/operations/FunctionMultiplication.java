/**
 * FunctionMultiplication.java
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

import jmona.gp.example.calc.functions.MultiplicationFunction;
import jmona.gp.example.calc.functions.SingleInputFunction;
import jmona.gp.impl.BinaryOperation;

/**
 * An operation which provides simple multiplication of two real-valued
 * SingleInputFunctions.
 * 
 * @author jfinkels
 */
public class FunctionMultiplication
    implements
    BinaryOperation<SingleInputFunction<Object, Double>, SingleInputFunction<Object, Double>> {

  /** A singleton instance of this class. */
  private static final FunctionMultiplication INSTANCE = new FunctionMultiplication();

  /**
   * Get a singleton instance of this class.
   * 
   * @return A singleton instance of this class.
   */
  public static FunctionMultiplication newInstance() {
    return FunctionMultiplication.INSTANCE;
  }

  /**
   * Returns a function which is the product of the two specified input
   * functions.
   * 
   * @param object1
   *          A function.
   * @param object2
   *          Another function
   * @return A function which is the product of the two specified input
   *         functions.
   * @see jmona.gp.impl.BinaryOperation#operate(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public SingleInputFunction<Object, Double> operate(
      final SingleInputFunction<Object, Double> object1,
      final SingleInputFunction<Object, Double> object2) {
    return new MultiplicationFunction(object1, object2);
  }
}
