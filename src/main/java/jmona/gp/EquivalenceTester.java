/**
 * EquivalenceTester.java
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
package jmona.gp;

/**
 * A tester for equivalence of two objects based on specified test input.
 * 
 * @param <V>
 *          The type of object which will be compared for equivalence based on
 *          the specified input of type I.
 * @param <I>
 *          The type of the input which will be applied to the objects of type V
 *          to compare for equivalence.
 * @author jfinkels
 */
public interface EquivalenceTester<V, I> {
  /**
   * Determine whether the specified objects are equivalent with respect to the
   * specified input.
   * 
   * @param object1
   *          An object.
   * @param object2
   *          Another object.
   * @param input
   *          Input to the specified objects, for use in determining their
   *          equivalence.
   * @return Whether the two specified objects are equivalent with respect to
   *         the specified input.
   * @throws EquivalenceException
   *           If there is a problem determining whether the two specified
   *           objects are equivalent with respect to the specified input.
   */
  boolean areEquivalent(final V object1, final V object2, final I input)
      throws EquivalenceException;
}
