/**
 * MultiplicationFunction.java
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

import jmona.SingleInputFunction;
import jmona.exceptions.MappingException;

/**
 * A function which returns the product of two specified functions.
 * 
 * @author jfinkels
 */
public class MultiplicationFunction extends SecondOrderFunction {

  /**
   * Instantiate this class with the two specified functions to multiply.
   * 
   * @param initialFunction1
   *          A function to multiply.
   * @param initialFunction2
   *          Another function to multiply.
   */
  public MultiplicationFunction(
      final SingleInputFunction<Double, Double> initialFunction1,
      final SingleInputFunction<Double, Double> initialFunction2) {
    super(initialFunction1, initialFunction2);
  }

  /**
   * Return the product of the image of the input under each of the functions
   * specified in the constructor.
   * 
   * @param input
   *          The input to both of the functions.
   * @return The product of the two functions with the specified input.
   * @throws MappingException
   *           {@inheritDoc}
   * @see jmona.SingleInputFunction#execute(java.lang.Object)
   */
  @Override
  public Double execute(final Double input) throws MappingException {
    return this.function1().execute(input) * this.function2().execute(input);
  }
}
