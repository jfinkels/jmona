/**
 * TwoPointCrossoverFunction.java
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
package jmona.ga.impl;

import jmona.impl.Pair;
import jmona.random.RandomUtils;

/**
 * Performs a two-point crossover between two Lists.
 * 
 * @param <E>
 *          The type of element contained in the List on which to perform
 *          crossover.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class TwoPointCrossoverFunction<E> extends
    AbstractListCrossoverFunction<E> {

  /**
   * A pair in which the left and right numbers are random integers less than
   * the specified length, and the left number is less than or equal to the
   * right number.
   * 
   * @param length
   *          The maximum length of the slice.
   * @return Random start and end indices for the slice.
   * @see jmona.ga.impl.AbstractListCrossoverFunction#sliceStartEnd(int)
   */
  @Override
  protected Pair<Integer, Integer> sliceStartEnd(final int length) {

    // get a random start and end for the slice (one can be equal to length)
    final int number1 = RandomUtils.randomData().nextInt(0, length - 1);
    final int number2 = RandomUtils.randomData().nextInt(0, length);

    return new Pair<Integer, Integer>(Math.min(number1, number2), Math.max(
        number1, number2));
  }

}
