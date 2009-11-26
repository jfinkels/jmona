/**
 * SquareFunction.java
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
 * A function which returns the square of its input.
 * 
 * @author jfinkels
 */
public class SquareFunction implements SingleInputFunction<Double, Double> {

  /**
   * Return the square of the specified input.
   * 
   * @param input
   *          A number.
   * @return The square of the specified input.
   * @see jmona.example.gp.calc.functions.SingleInputFunction#execute(java.lang.Object)
   */
  @Override
  public Double execute(final Double input) {
    return Math.pow(input, 2);
  }
}
