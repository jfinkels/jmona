/**
 * SingleInputFunction.java
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
 * A function with domain of type T and codomain of type R.
 * 
 * @param <T>
 *          The type of the domain of this function.
 * @param <R>
 *          The type of the codomain of this function.
 * @author jfinkels
 */
public interface SingleInputFunction<T, R> {

  /**
   * Get the image of the specified input under this function.
   * 
   * @param input
   *          The input to this function.
   * @return The image of the specified input under this function.
   */
  R execute(final T input);
}
