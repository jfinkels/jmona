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

import jfcommon.functional.Range;
import jmona.MutationException;
import jmona.random.RandomUtils;

/**
 * A MutationFunction which mutates Lists, with a variable probability of
 * mutating each element of the list.
 * 
 * The {@link #setDistribution(double[])} method must be called before calling
 * the {@link #mutate(List)} method, or an Exception will be thrown.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in the List to mutate.
 * @param <L>
 *          The type of List to mutate.
 * @since 0.5
 */
public class VariableDistributionMutationFunction<E, L extends List<E>> extends
    ElementwiseMutationFunction<E, L> implements
    OrderedListMutationFunction<E, L> {

  /**
   * The probability distribution which determines the probability that an
   * element in a List will be mutated.
   * 
   * Specifically, the element at index <em>i</em> will be mutated with
   * probability {@code distribution[i]}.
   */
  private double[] distribution = null;

  /**
   * Sets the probability distribution which determines the probability that an
   * element in a List will be mutated.
   * 
   * Specifically, the element at index <em>i</em> will be mutated with
   * probability {@code newDistribution[i]}.
   * 
   * @param newDistribution
   *          The probability distribution specifying the probability with which
   *          elements of a List will be mutated.
   */
  public void setDistribution(final double[] newDistribution) {
    this.distribution = newDistribution.clone();
  }

  /**
   * Iterate over the specified list and mutate each element with probability
   * specified by the array of probabilities provided in the
   * {@link #setDistribution(double[])} method.
   * 
   * The {@link #setDistribution(double[])} method must be called
   * <em>before</em> calling this method.
   * 
   * @param list
   *          The list whose elements will be mutated (with variable probability
   *          depending on the probability distribution set in the
   *          {@link #setDistribution(double[])} method).
   * @throws MutationException
   *           If the probability distribution has not been set by calling the
   *           {@link #setDistribution(double[])} method, or if the number of
   *           elements in the probability distribution is not equal to the
   *           number of elements in the specified List to mutate.
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
