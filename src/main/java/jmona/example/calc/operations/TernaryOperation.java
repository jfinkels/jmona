/**
 * TernaryOperation.java
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
package jmona.example.calc.operations;

/**
 * A ternary operation, or more formally, a function,
 * <em>f: T x U X V &rarr; R</em>.
 * 
 * @param <T>
 *          The type of the left component of the domain of this ternary
 *          operation (the input type of the first parameter).
 * @param <U>
 *          The type of the middle component of the domain of this ternary
 *          operation (the input type of the second parameter).
 * @param <V>
 *          The type of the right component of the domain of this ternary
 *          operation (the input type of the third parameter).
 * @param <R>
 *          The type of the codomain of this ternary operation (the return
 *          type).
 * @author Jeffrey Finkelstein
 */
public interface TernaryOperation<T, U, V, R> extends Operation<R> {
  /**
   * Perform this operation on the specified inputs.
   * 
   * @param leftObject
   *          An object of type T.
   * @param middleObject
   *          An object of type U.
   * @param rightObject
   *          An object of type V.
   * @return The result of this operation on the three specified objects.
   */
  R operate(final T leftObject, final U middleObject, final V rightObject);
}
