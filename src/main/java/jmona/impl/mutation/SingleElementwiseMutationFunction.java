/**
 * SingleElementwiseMutationFunction.java
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
 * Mutates a single element at random from elements of a list using an
 * elementwise MutationFunction set in the
 * {@link #setElementMutationFunction(jmona.MutationFunction)} method.
 * 
 * If you would like each element of the list to have an equal (that is,
 * uniform) probability of being mutated on each call to the mutate() method,
 * use the {@link jmona.impl.mutation.UniformDistributionMutationFunction}.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class SingleElementwiseMutationFunction<E, L extends List<E>> extends
    ElementwiseMutationFunction<E, L> {

  /**
   * Mutate a random element in the specified List.
   * 
   * @param list
   *          The List in which to mutate a random element.
   * @throws MutationException
   *           If the mutation of the random element throws an Exception, or if
   *           the ListElementMutationException has not been set.
   * @see jmona.MutationFunction#mutate(Object)
   */
  @Override
  public void mutate(final L list) throws MutationException {
    if (this.elementMutationFunction() == null) {
      throw new MutationException(
          "No ListElementMutationFunction has been set.");
    }

    this.elementMutationFunction().mutate(RandomUtils.choice(list));
  }

}
