/**
 * FitnessProportionateSelection.java
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
package jmona.impl.selection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jmona.Individual;
import jmona.SelectionFunction;
import jmona.impl.Util;

/**
 * Fitness-proportionate selection, also known as "roulette wheel selection".
 * 
 * @param <T>
 *          The type of Individual to select.
 * @author jfinkels
 */
// TODO allow for stochastic universal sampling
public class FitnessProportionateSelection<T extends Individual> implements
    SelectionFunction<T> {

  /**
   * Get the sum of the specified Collection of values.
   * 
   * @param values
   *          The Collection of values to sum.
   * @return The sum of the specified Collection of values.
   */
  private static double sum(final Collection<Double> values) {
    double result = 0.0;
    for (final Double value : values) {
      result += value;
    }
    return result;
  }

  /**
   * Fitness-proportionate selection, also known as "roulette wheel selection",
   * which chooses an individual from the specified mapping with a probability
   * weighted by the corresponding fitnesses.
   * 
   * @param fitnesses
   *          The mapping from Individual to fitnesses.
   * @return An Individual chosen with probability weighted by corresponding
   *         fitnesses.
   * @see jmona.SelectionFunction#select(java.util.Map)
   */
  // TODO more documentation on fitness-proportionate selection, i.e. formulae
  @Override
  public T select(final Map<T, Double> fitnesses) {
    // choose a number between 0 and the sum of all fitnesses
    final double selectionPointer = Util.RANDOM.nextDouble()
        * sum(fitnesses.values());

    // initialize some local variables
    double currentPointer = 0.0;
    double currentFitness = 0.0;

    // TODO iterators are ugly
    // iterate over all entries in the fitnesses map
    final Iterator<Entry<T, Double>> iterator = fitnesses.entrySet().iterator();
    Entry<T, Double> entry = null;
    while (iterator.hasNext()) {
      entry = iterator.next();
      currentFitness = entry.getValue();
      if (currentPointer < selectionPointer
          && currentPointer + currentFitness >= selectionPointer) {
        return entry.getKey();
      }
      currentPointer += currentFitness;
    }

    return null;
  }

}
