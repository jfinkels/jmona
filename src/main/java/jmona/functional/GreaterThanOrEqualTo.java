/**
 * GreaterThanOrEqualTo.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.functional;

import jmona.Condition;

/**
 * A condition representing the greater than or equal to operation.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class GreaterThanOrEqualTo implements Condition<Double> {

  /** The target with which to compare. */
  private final double target;

  /**
   * Instantiates this function with the specified target value against which to
   * compare values.
   * 
   * @param initialTarget
   *          The target value against which to compare.
   */
  public GreaterThanOrEqualTo(final double initialTarget) {
    this.target = initialTarget;
  }

  /**
   * Determines whether the specified input value is greater than or equal to
   * the target value specified in the constructor of this class.
   * 
   * @param input
   *          The value to compare against the target value.
   * @return Whether the specified input value is greater than or equal to the
   *         target value specified in the constructor of this class.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Boolean execute(final Double input) {
    return input.doubleValue() >= this.target;
  }

}
