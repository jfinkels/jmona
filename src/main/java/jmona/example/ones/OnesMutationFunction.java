/**
 * OnesMutationFunction.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.example.ones;

import jmona.impl.mutation.ImmutableElementsListMutationFunction;

/**
 * Mutation function which flips a bit randomly in a List of bits.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class OnesMutationFunction extends
    ImmutableElementsListMutationFunction<Byte> {

  /**
   * Return 1 if the specified bit is 0, and return 0 if the specified bit is 1.
   * 
   * @param bit
   *          The bit to mutate.
   * @return The bitwise inverse of the specified bit.
   * @see jmona.impl.mutation.ImmutableElementsListMutationFunction#mutated(java.lang.Object)
   */
  @Override
  protected Byte mutated(final Byte bit) {
    return (byte) (1 - bit);
  }

}
