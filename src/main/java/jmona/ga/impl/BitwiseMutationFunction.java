/**
 * BitwiseMutationFunction.java
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
package jmona.ga.impl;

import jmona.MutationFunction;
import jmona.ga.BinaryString;
import jmona.impl.Util;

/**
 * A class which mutates genes by flipping a random bit.
 * 
 * @author jfinke
 */
public class BitwiseMutationFunction implements MutationFunction<BinaryString> {
  /**
   * Flip a random bit in the specified binary string.
   * 
   * @param individual
   *          The binary string in which to flip a random bit.
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final BinaryString individual) {
    final int bitToFlip = Util.RANDOM.nextInt(individual.size());
    individual.flipBit(bitToFlip);
  }
}
