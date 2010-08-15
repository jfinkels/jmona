/**
 * BitFlipMutationFunction.java
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

import jmona.MutationFunction;
import jmona.impl.mutable.MutableByte;

/**
 * Mutation function which flips a single bit (represented as a byte with value
 * either 0 or 1).
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class BitFlipMutationFunction implements MutationFunction<MutableByte> {

  /**
   * Flips the specified bit from 1 to 0 or from 0 to 1, depending on the value
   * of the input bit.
   * 
   * Assumes as a precondition that the value of the {@code bit} parameter is
   * either 1 or 0.
   * 
   * @param bit
   *          The bit to mutate, implemented as a MutableByte object.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final MutableByte bit) {
    bit.setValue(1 - bit.intValue());
  }

}
