/**
 * BinaryStringMutationFunction.java
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
package jmona.ga.impl;

import jmona.DeepCopyableList;
import jmona.impl.mutable.MutableByte;
import jmona.impl.mutation.UniformDistributionMutationFunction;

/**
 * A MutationFunction which flips a random bit in a BinaryString object.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BinaryStringMutationFunction extends
    UniformDistributionMutationFunction<MutableByte, DeepCopyableList<MutableByte>> {

  public BinaryStringMutationFunction() {
    this.setElementMutationFunction(new BitFlipMutationFunction());
  }
  
  /**
   * Flips a random bit in the specified BinaryString.
   * 
   * @param binaryString
   *          The BinaryString in which to flip a random bit.
   * @see jmona.MutationFunction#mutate(java.lang.Object)
   */
/*  @Override
  public void mutate(final DeepCopyableList<MutableByte> binaryString) {
    int index = 0;
    if (binaryString.size() > 1) {
      index = RandomUtils.randomData().nextInt(0, binaryString.size() - 1);
    }

    binaryString.flipBit(index);
  }
*/
}
