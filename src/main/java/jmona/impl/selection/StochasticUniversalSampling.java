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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jmona.MultipleSelectionFunction;
import jmona.SelectionException;
import jmona.functional.Functional;
import jmona.functional.Range;
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
   * @see jmona.impl.selection.FitnessProportionateSelection(java.util.Map)
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
    List<T> result = new ArrayList<T>();

    // get the sum of all fitnesses
    final double fitnessesSum = Functional.sumDouble(fitnesses.values());

    // if no individual has any fitness, just return random ones
    if (fitnessesSum == 0.0) {
      for (final int i : new Range(numberToSelect)) {
        result.add(RandomUtils.choice(fitnesses.keySet()));
      }
      return result;
    }

    // create the map from individual to cumulative fitnesses
    Map<T, Double> cumulativeFitnesses = new HashMap<T, Double>();
    double sum = 0;
    for (final Entry<T, Double> entry : fitnesses.entrySet()) {
      sum += entry.getValue();
      cumulativeFitnesses.put(entry.getKey(), sum);
    }

    // create a list of numberToSelect pointers spaced evenly on the interval
    // [0, fitnessesSum)
    double pointerWidth = fitnessesSum / numberToSelect;
    double pointer = RandomUtils.nextDouble() * pointerWidth;
    List<Double> pointers = new ArrayList<Double>();
    for (final int i : new Range(numberToSelect)) {
      pointers.add((i * pointerWidth) + pointer);
    }

    // get an iterator over the pointers and the cumulative fitnesses map
    final Iterator<Double> pointerIterator = pointers.iterator();
    final Iterator<Entry<T, Double>> cumulativeFitnessesIterator = cumulativeFitnesses
        .entrySet().iterator();

    // simultaneously iterate over using these two iterators, finding each of
    // the selected individuals and adding them to the result list
    double currentPointer = pointerIterator.next();
    Entry<T, Double> currentEntry = cumulativeFitnessesIterator.next();
    while (result.size() < numberToSelect && pointerIterator.hasNext()
        && cumulativeFitnessesIterator.hasNext()) {

      // if the current pointer is less than the value of the current entry,
      // then add it to the result list and move the pointer to the next value
      if (currentPointer < currentEntry.getValue()) {
        result.add(currentEntry.getKey());
        currentPointer = pointerIterator.next();
      } else {
        // otherwise, look at the next individual/cumulative fitness entry
        currentEntry = cumulativeFitnessesIterator.next();
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
