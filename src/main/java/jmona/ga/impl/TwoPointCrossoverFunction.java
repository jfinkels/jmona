/**
 * TwoPointCrossoverFunction.java
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

import jmona.ImmutablePair;
import jmona.impl.Util;

/**
 * Performs a two-point crossover between binary strings.
 * 
 * @author jfinke
 */
public class TwoPointCrossoverFunction extends
    AbstractBinaryStringCrossoverFunction {

  /**
   * A pair in which the left and right numbers are random integers less than
   * the specified length, and the left number is less than or equal to the
   * right number.
   * 
   * @param length
   *          The maximum length of the slice.
   * @return Random start and end indices for the slice.
   * @see jmona.ga.impl.AbstractBinaryStringCrossoverFunction#sliceStartEnd(int)
   */
  @Override
  protected ImmutablePair<Integer, Integer> sliceStartEnd(final int length) {

    final int start = Util.RANDOM.nextInt(length);
    final int end = start + Util.RANDOM.nextInt(length - start);

    return new ImmutablePair<Integer, Integer>(start, end);
  }

}