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

import java.util.List;

import jmona.CrossoverFunction;
import jmona.impl.Pair;
import jmona.impl.Util;

/**
 * Base class for a CrossoverFunction which operates on Lists.
 * 
 * @param <E>
 *          The type of element contained in the List on which to perform
 *          crossover (that List must also extends Individual).
 * @param <T>
 *          The type of Individual on which to perform crossover (must also
 *          extend List).
 * @author jfinkels
 */
public abstract class AbstractListCrossoverFunction<E> implements
    CrossoverFunction<List<E>> {

  /**
   * Perform a crossover between a range of elements specified by the
   * {@link #sliceStartEnd(int)} method.
   * 
   * @param list1
   *          A List.
   * @param list2
   *          Another List.
   */
  // TODO if end < start, swap (0, end) and (start, length)
  @Override
  public void crossover(final List<E> list1, final List<E> list2) {
    final int length = list1.size();

    final Pair<Integer, Integer> sliceIndices = this.sliceStartEnd(length);

    final int start = sliceIndices.left();
    final int end = sliceIndices.right();

    Util.swap(list1, list2, start, end);
  }

  /**
   * Get the start index and end index of the range of elements to swap between
   * two Lists in the {@link #crossover(Individual, Individual)} method.
   * 
   * @param length
   *          The length of the Lists.
   * @return The start and end indices of the range of elements to swap.
   */
  protected abstract Pair<Integer, Integer> sliceStartEnd(final int length);

}
