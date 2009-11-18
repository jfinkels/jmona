/**
 * EqualityTester.java
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

import jmona.gp.EquivalenceTester;

/**
 * A tester for equality of two objects regardless of input.
 * 
 * @param <V>
 *          The type of object to compare for equality.
 * @author jfinkels
 */
public class EqualityTester<V> implements EquivalenceTester<V, Object> {

  /**
   * Determine whether the two specified objects are equal, as determined by the
   * {@link Object#equals(Object)} method, ignoring the specified input.
   * 
   * @param object1
   *          An object.
   * @param object2
   *          Another object.
   * @param input
   *          This parameter is ignored.
   * @return Whether the two specified objects are equal, as determined by the
   *         {@link Object#equals(Object)} method.
   * @see jmona.gp.EquivalenceTester#areEquivalent(java.lang.Object,
   *      java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean areEquivalent(final V object1, final V object2,
      final Object input) {
    return object1.equals(object2);
  }

}
