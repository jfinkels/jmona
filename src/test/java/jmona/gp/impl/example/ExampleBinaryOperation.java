/**
 * ExampleBinaryOperation.java
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
package jmona.gp.impl.example;

import jmona.gp.impl.BinaryOperation;

/**
 * An example BinaryOperation which returns the difference between two integers.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExampleBinaryOperation implements
    BinaryOperation<Integer, Integer, Integer> {

  /** The symbol of this operation. */
  public static final String SYMBOL = "-";

  /**
   * Get the difference between the two specified integers.
   * 
   * @param leftObject
   *          The subtrahend.
   * @param rightObject
   *          The minuend.
   * @return The difference between the two specified integers.
   * @see jmona.gp.impl.BinaryOperation#operate(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public Integer operate(final Integer leftObject, final Integer rightObject) {
    return leftObject - rightObject;
  }

  /**
   * Return the String representation of this operation.
   * 
   * @return The String representation of this operation.
   */
  @Override
  public String toString() {
    return SYMBOL;
  }
}
