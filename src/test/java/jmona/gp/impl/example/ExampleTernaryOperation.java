/**
 * ExampleTernaryOperation.java
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

import jmona.gp.impl.TernaryOperation;

/**
 * An example TernaryOperation which returns the sum of three integers.
 * 
 * @author jfinkels
 */
public class ExampleTernaryOperation implements
    TernaryOperation<Integer, Integer, Integer, Integer> {

  /**
   * Get the sum of the three specified integers.
   * 
   * @param leftObject
   *          An integer.
   * @param middleObject
   *          Another integer.
   * @param rightObject
   *          Another integer.
   * @return The sum of the three specified integers.
   * @see jmona.gp.impl.BinaryOperation#operate(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  public Integer operate(final Integer leftObject, final Integer middleObject,
      final Integer rightObject) {
    return leftObject + middleObject + rightObject;
  }

  @Override
  public String toString() {
    return "-";
  }
}
