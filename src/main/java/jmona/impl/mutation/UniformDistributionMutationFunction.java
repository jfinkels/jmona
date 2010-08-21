/**
 * UniformDistributionMutationFunction.java
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
package jmona.impl.mutation;

import java.util.List;

import jmona.MutationException;
import jmona.random.RandomUtils;

/**
 * Mutates each element in a list with probability equal to
 * <em>1 / list.size()</em>.
 * 
 * Note that this class does not mutate exactly one element chosen from the
 * list. It may happen that none of the elements of the list get mutated, or it
 * may happen that more than one of the elements of the list get mutated. If you
 * need a class which ensures that exactly one element of the list is mutated on
 * each call to mutate(), use the
 * {@link jmona.impl.mutation.SingleElementwiseMutationFunction} class.
 * 
 * @author Jeffrey Finkelstein
 * @param <L>
 *          The type of List to mutate.
 * @since 0.5
 */
public class UniformDistributionMutationFunction<E, L extends List<E>> extends
    ElementwiseMutationFunction<E, L> {

  /**
   * Mutates each element in the specified list with probability
   * <em>1 / list.size()</em>.
   * 
   * Iterates over each element in the list, checking if that element should be
   * mutated according to the probability.
   * 
   * @see jmona.MutationFunction#mutate(java.lang.Object)
   */
  @Override
  public void mutate(final L list) throws MutationException {
    // the probability that each element in the list will be mutated
    final double probability = 1.0 / list.size();

    for (final E object : list) {
      if (RandomUtils.nextDouble() < probability) {
        this.elementMutationFunction().mutate(object);
      }
    }
  }

}
