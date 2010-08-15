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

import java.util.Arrays;

import jmona.DeepCopyable;
import jmona.DeepCopyableList;
import jmona.MutationException;

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
 * @since 0.5
 */
public class UniformDistributionMutationFunction<E extends DeepCopyable<E>>
    extends VariableDistributionMutationFunction<E> {

  /*
   * (non-Javadoc)
   * 
   * @see jmona.MutationFunction#mutate(java.lang.Object)
   */
  @Override
  public void mutate(final DeepCopyableList<E> list) throws MutationException {
    // the probability distribution is uniform for each element in the list
    final double[] probabilities = new double[list.size()];
    Arrays.fill(probabilities, 1.0 / list.size());

    // set the probability distribution on this object
    this.setDistribution(probabilities);

    // call the mutate() method of the superclass
    super.mutate(list);
  }

}
