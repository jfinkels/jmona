/**
 * AbstractBinaryStringCrossoverFunction.java
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

import jmona.CrossoverFunction;
import jmona.Pair;
import jmona.ga.BinaryString;

/**
 * Base class for a CrossoverFunction which operates on binary strings.
 * 
 * @author jfinkels
 */
public abstract class AbstractBinaryStringCrossoverFunction implements
    CrossoverFunction<BinaryString> {

  /**
   * Swap the bit at the specified index between the two specified binary
   * strings.
   * 
   * @param individual1
   *          A binary string.
   * @param individual2
   *          Another binary string.
   * @param index
   *          The index of the bit to swap.
   */
  protected static void swapBit(final BinaryString individual1,
      final BinaryString individual2, final int index) {
    // if the two bits are different, flip them; if they are the same do nothing
    if (individual1.get(index) != individual2.get(index)) {
      individual1.flipBit(index);
      individual2.flipBit(index);
    }
  }

  /**
   * Swap each bit in the specified binary strings between the specified start
   * and end points.
   * 
   * @param individual1
   *          A binary string.
   * @param individual2
   *          Another binary string.
   * @param start
   *          The index of the first bit to swap.
   * @param end
   *          The index of the last bit to swap.
   */
  protected static void swapBits(final BinaryString individual1,
      final BinaryString individual2, final int start, final int end) {
    for (int i = start; i < end; ++i) {
      swapBit(individual1, individual2, i);
    }
  }

  /**
   * Perform a crossover between a range of bits specified by the
   * {@link #sliceStartEnd(int)} method.
   * 
   * @param individual1
   *          A binary string.
   * @param individual2
   *          Another binary string.
   */
  // TODO if end < start, swap (0, end) and (start, length)
  @Override
  public void crossover(final BinaryString individual1,
      final BinaryString individual2) {
    final int length = individual1.size();

    final Pair<Integer, Integer> sliceIndices = this
        .sliceStartEnd(length);

    final int start = sliceIndices.left();
    final int end = sliceIndices.right();

    swapBits(individual1, individual2, start, end);
  }

  /**
   * Get the start index and end index of the range of bits to swap between two
   * binary strings in the {@link #crossover(BinaryString, BinaryString)}
   * method.
   * 
   * @param length
   *          The length of the binary strings.
   * @return The start and end indices of the range of bits to swap.
   */
  protected abstract Pair<Integer, Integer> sliceStartEnd(
      final int length);

}
