/**
 * DivisionFunction.java
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
import jfcommon.functional.MappingException;

/**
 * A function which returns the quotient of two specified functions.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class DivisionFunction extends SecondOrderFunction {

  /**
   * Instantiate this class with the first parameter as the dividend and the
   * second parameter as the divisor.
   * 
   * @param initialFunction1
   *          The function whose image will be the dividend.
   * @param initialFunction2
   *          The function whose image will be the divisor.
   */
  public DivisionFunction(final Function<Double, Double> initialFunction1,
      final Function<Double, Double> initialFunction2) {
    super(initialFunction1, initialFunction2);
  }

  /**
   * Return the quotient of the image of the input under each of the functions
   * specified in the constructor.
   * 
   * @param input
   *          The input to both of the functions.
   * @return The quotient of the two functions with the specified input.
   * @throws MappingException
   *           {@inheritDoc}
   * @see jfcommon.functional.Function#execute(java.lang.Object)
   */
  @Override
  public Double execute(final Double input) throws MappingException {
    return this.function1().execute(input) / this.function2().execute(input);
  }
}
