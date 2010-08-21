/**
 * ToClass.java
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

import jmona.Function;

/**
 * Maps an object to its class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ToClass<T> implements Function<T, Class<?>> {

  /**
   * Gets the class of the specified input object.
   * 
   * @param input
   *          The object whose class to get.
   * @return The class of the input object.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Class<?> execute(final T input) {
    return input.getClass();
  }

}
