/**
 * VariableDistributionMutationFunction.java
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
import jmona.functional.Range;
import jmona.random.RandomUtils;

/**
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class VariableDistributionMutationFunction<E, L extends List<E>> extends
    ElementwiseMutationFunction<E, L> implements
    OrderedListMutationFunction<E, L> {

  private double[] distribution = null;

  public void setDistribution(final double[] newDistribution) {
    this.distribution = newDistribution.clone();
  }

  /*
   * (non-Javadoc)
   * 
   * @see jmona.MutationFunction#mutate(java.lang.Object)
   */
  @Override
  public void mutate(final L list) throws MutationException {
    if (this.distribution == null) {
      throw new MutationException(
          "Probability distribution has not been set; must call setDistribution() before mutate().");
    }

    if (this.distribution.length != list.size()) {
      throw new MutationException("Length of probability distribution ("
          + this.distribution.length
          + ") must equal number of elements in list to mutate (" + list.size()
          + ").");
    }

    if (this.elementMutationFunction() == null) {
      throw new MutationException(
          "MutationFunction for elements has not been set.");
    }

    // iterate over each probability/element pair
    for (final int i : new Range(list.size())) {
      // if a random double is less than the current probability
      if (RandomUtils.nextDouble() < this.distribution[i]) {
        // mutate the element
        this.elementMutationFunction().mutate(list.get(i));
      }
    }
  }

}
