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
public class BinaryStringMutationFunction
    extends
    UniformDistributionMutationFunction<MutableByte, DeepCopyableList<MutableByte>> {

  /**
   * Instantiates this MutationFunction by setting the mutation function which
   * mutates individual elements of the list to be the
   * {@link jmona.ga.impl.BitFlipMutationFunction}.
   */
  public BinaryStringMutationFunction() {
    this.setElementMutationFunction(new BitFlipMutationFunction());
  }

}
