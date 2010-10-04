/**
 * StochasticUniversalSampling.java
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
package jmona.impl.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jfcommon.functional.Range;
import jmona.MultipleSelectionFunction;
import jmona.SelectionException;
import jmona.random.RandomUtils;

/**
 * An implementation of stochastic universal sampling, which simultaneously
 * selects an arbitrary number of individuals by simulating a single spin of a
 * roulette wheel with some number of evenly spaced arrows.
 * 
 * The idea is that making a single spin of a roulette wheel with <em>n</em>
 * arrows will be more efficient than making <em>n</em> spins of a roulette
 * wheel with one arrow (as would be done when using
 * {@link jmona.impl.selection.FitnessProportionateSelection fitness
 * proportionate selection} to select <em>n</em> individuals).
 * 
 * For more information, see <a
 * href="http://en.wikipedia.org/wiki/Stochastic_universal_sampling">the
 * Wikipedia article on stochastic universal sampling</a>.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual to select.
 * @since 0.5
 */
public class StochasticUniversalSampling<T> implements
    MultipleSelectionFunction<T> {

  /**
   * Selects the specified number of individuals, with each being selected a
   * number of times proportionate to its fitness as specified in the fitnesses
   * map, by performing one spin of the "roulette wheel" with numberToSelect
   * evenly spaces pointers.
   * 
   * For more information, see <a
   * href="http://en.wikipedia.org/wiki/Stochastic_universal_sampling">the
   * Wikipedia article on stochastic universal sampling</a>.
   * 
   * @param fitnesses
   *          {@inheritDoc}
   * @param numberToSelect
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @throws SelectionException
   *           If the number of individuals selected is not equal to the value
   *           of the numberToSelect parameter.
   * @see jmona.MultipleSelectionFunction#select(java.util.Map, int)
   */
  @Override
  public List<T> select(final Map<T, Double> fitnesses, final int numberToSelect)
      throws SelectionException {
    // if the map is empty, return null
    if (fitnesses.size() == 0) {
      return null;
    }

    // create the list which will contain the chosen individuals
    final List<T> result = new ArrayList<T>();

    // if the number to select is zero, return an empty list
    if (numberToSelect == 0) {
      return result;
    }

    @SuppressWarnings("unchecked")
    final T[] individualsInOrder = (T[]) fitnesses.keySet().toArray();
    final double[] cumulativeFitnesses = new double[individualsInOrder.length];

    // create the map from individual to cumulative fitnesses
    double sum = 0;
    for (int i = 0; i < individualsInOrder.length; ++i) {
      sum += fitnesses.get(individualsInOrder[i]);
      cumulativeFitnesses[i] = sum;
    }

    // if no individual has any fitness, just return random ones
    if (sum == 0.0) {
      return RandomUtils.sampleWithReplacement(fitnesses.keySet(),
          numberToSelect);
    }

    // create a list of numberToSelect pointers spaced evenly on the interval
    // [0, fitnessesSum)
    final double pointerWidth = sum / numberToSelect;
    final double pointer = RandomUtils.nextDouble() * pointerWidth;
    final double[] pointers = new double[numberToSelect];
    for (final int i : new Range(numberToSelect)) {
      pointers[i] = (i * pointerWidth) + pointer;
    }

    // simultaneously iterate over pointers and cumulative fitnesses
    int pointersIndex = 0;
    int individualsIndex = 0;
    double currentPointer = pointers[pointersIndex];
    double currentExtent = cumulativeFitnesses[individualsIndex];
    while (result.size() < numberToSelect) {
      // if the current pointer is less than the current cumulative fitness
      if (currentPointer < currentExtent) {

        // add individual with the current cumulative fitness to the result
        result.add(individualsInOrder[individualsIndex]);

        // increment the pointer which will be examined next time around
        ++pointersIndex;

        // if that was the last pointer, break out of the loop
        if (pointersIndex >= pointers.length) {
          break;
        }

        // otherwise, get the value of the next pointer
        currentPointer = pointers[pointersIndex];

      } else {
        // otherwise, look at the next individual
        ++individualsIndex;

        // if that was the last individual, break out of the loop
        if (individualsIndex >= cumulativeFitnesses.length) {
          break;
        }

        // otherwise get the next cumulative fitness value
        currentExtent = cumulativeFitnesses[individualsIndex];
      }
    }

    if (result.size() != numberToSelect) {
      throw new SelectionException(
          "Failed to select the correct number of individuals. Wanted to select "
              + numberToSelect + " but selected " + result.size());
    }

    return result;
  }

}
