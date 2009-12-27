/**
 * BinaryOperation.java
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
package jmona.gp.impl;

/**
 * A binary operation, or more formally, a function, <em>f: T x V &rarr; R</em>.
 * 
 * @param <T>
 *          The type of the left component of the domain of this binary
 *          operation (the input type of the first parameter).
 * @param <V>
 *          The type of the right component of the domain of this binary
 *          operation (the input type of the second parameter).
 * @param <R>
 *          The type of the codomain of this binary operation (the return type).
 * @author jfinkels
 */
public interface BinaryOperation<T, V, R> extends Operation<R> {
  /**
   * Perform this operation on the specified inputs.
   * 
   * @param leftObject
   *          An object of type T.
   * @param rightObject
   *          An object of type V.
   * @return The result of this operation on the two specified objects.
   */
  R operate(final T leftObject, final V rightObject);
}
