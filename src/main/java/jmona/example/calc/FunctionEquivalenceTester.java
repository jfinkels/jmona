/**
 * FunctionEquivalenceTester.java
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
package jmona.example.calc;

import jmona.SingleInputFunction;
import jmona.exceptions.MappingException;
import jmona.gp.EquivalenceException;
import jmona.gp.EquivalenceTester;

/**
 * A class which tests the equivalence of two functions with respect to a single
 * input.
 * 
 * @param <S>
 *          The type of the domain of the functions to test for equivalence.
 * @param <T>
 *          The type of the codomain of the functions to test for equivalence.
 * @author jfinkels
 */
public class FunctionEquivalenceTester<S, T> implements
    EquivalenceTester<SingleInputFunction<S, T>, S> {

  /**
   * Whether the two specified functions have the same output on the specified
   * input.
   * 
   * Formally, let <em>f</em> be {@code object1}, <em>g</em> be {@code object2},
   * and <em>x<sub>0</sub></em> be {@code input}. Then this method returns
   * {@code true} if and only if <em>f(x<sub>0</sub>) = g(x<sub>0</sub>)</em>.
   * 
   * @param object1
   *          A function.
   * @param object2
   *          Another function.
   * @param input
   *          The input to the two functions.
   * @return Whether the two specified functions have the same output on the
   *         specified input.
   * @see jmona.gp.EquivalenceTester#areEquivalent(java.lang.Object,
   *      java.lang.Object, java.lang.Object)
   * @throws EquivalenceException
   *           If an Exception is thrown when executing one of the two specified
   *           functions.
   */
  @Override
  public boolean areEquivalent(final SingleInputFunction<S, T> object1,
      final SingleInputFunction<S, T> object2, final S input)
      throws EquivalenceException {
    try {
      return object1.execute(input).equals(object2.execute(input));
    } catch (final MappingException exception) {
      throw new EquivalenceException("Failed to execute one of the functions.",
          exception);
    }
  }

}
