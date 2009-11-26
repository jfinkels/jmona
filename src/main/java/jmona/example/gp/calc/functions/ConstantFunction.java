/**
 * ConstantFunction.java
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
 * A function which always returns a constant value.
 * 
 * @param <T>
 *          The type of the domain of this function.
 * @param <R>
 *          The type of the codomain of this function.
 * @author jfinkels
 */
public class ConstantFunction<T, R> implements SingleInputFunction<T, R> {

  /** The constant value of this function. */
  private final R value;

  /**
   * Instantiate this function with the specified constant value.
   * 
   * @param initialValue
   *          The constant value of this function.
   */
  public ConstantFunction(final R initialValue) {
    this.value = initialValue;
  }

  /**
   * Always returns the value specified in the constructor; ignores all input.
   * 
   * @param input
   *          This parameter is ignored.
   * @return The constant value specified in the constructor.
   * @see jmona.example.gp.calc.functions.SingleInputFunction#execute(java.lang.Object)
   */
  @Override
  public R execute(final T input) {
    return this.value;
  }

  /**
   * Returns a String representation of the constant value of this function.
   * 
   * @return A String representation of the constant value of this function.
   */
  @Override
  public String toString() {
    return this.value.toString();
  }

}
