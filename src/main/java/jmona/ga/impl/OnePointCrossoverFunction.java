/**
 * OnePointCrossoverFunction.java
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

import jmona.impl.Pair;
import jmona.impl.Util;

/**
 * Performs a one-point crossover between two Lists.
 * 
 * @param <E>
 *          The type of element contained in the List on which to perform
 *          crossover (that List must also extends Individual).
 * @param <T>
 *          The type of Individual on which to perform crossover (must also
 *          extend List).
 * @author jfinkels
 */
public class OnePointCrossoverFunction<E> extends
    AbstractListCrossoverFunction<E> {

  /**
   * Return a pair in which the left number is a random integer less than the
   * specified length, and the right number is the specified length.
   * 
   * @param length
   *          The maximum length of the slice.
   * @return A pair with a random left number and the specified length as the
   *         right number.
   * @see jmona.ga.impl.crossover.AbstractListCrossoverFunction#sliceStartEnd(int)
   */
  @Override
  protected Pair<Integer, Integer> sliceStartEnd(final int length) {
    final int start = Util.RANDOM.nextInt(length);

    return new Pair<Integer, Integer>(start, length);
  }

}
