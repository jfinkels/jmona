/**
 * SecondOrderFunction.java
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
package jmona.gp.example.calc.functions;

/**
 * A function which operates on functions.
 * 
 * @author jfinkels
 */
public abstract class SecondOrderFunction implements
    SingleInputFunction<Object, Double> {

  /** One of the functions on which to operate. */
  private final SingleInputFunction<Object, Double> function1;
  /** One of the functions on which to operate. */
  private final SingleInputFunction<Object, Double> function2;

  /**
   * Instantiate this class with the two specified functions lower order
   * functions.
   * 
   * @param initialFunction1
   *          A function on which to operate.
   * @param initialFunction2
   *          Another function on which to operate.
   */
  public SecondOrderFunction(
      final SingleInputFunction<Object, Double> initialFunction1,
      final SingleInputFunction<Object, Double> initialFunction2) {
    this.function1 = initialFunction1;
    this.function2 = initialFunction2;
  }

  /**
   * Get the first function on which to operate.
   * 
   * @return The first function on which to operate.
   */
  protected SingleInputFunction<Object, Double> function1() {
    return this.function1;
  }

  /**
   * Get the second function on which to operate.
   * 
   * @return The second function on which to operate.
   */
  protected SingleInputFunction<Object, Double> function2() {
    return this.function2;
  }

}