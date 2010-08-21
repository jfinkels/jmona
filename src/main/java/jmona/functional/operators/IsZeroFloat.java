/**
 * IsZeroFloat.java
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
package jmona.functional.operators;

/**
 * Condition for testing whether an float is 0.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class IsZeroFloat implements IsZero<Float> {

  /**
   * Whether the specified input number is 0.
   * 
   * @param input
   *          The number to test for zero.
   * @return Whether the specified input number is 0.
   */
  @Override
  public Boolean execute(final Float input) {
    return input == 0.0f;
  }

}
