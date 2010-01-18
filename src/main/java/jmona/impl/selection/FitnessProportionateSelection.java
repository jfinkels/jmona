/**
 * FitnessProportionateSelection.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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

import java.util.Map;
import java.util.Map.Entry;

import jmona.IndependentSelectionFunction;
import jmona.SelectionException;
import jmona.functional.Functional;
import jmona.random.RandomUtils;

import org.apache.log4j.Logger;

/**
 * Fitness-proportionate selection, also known as "roulette wheel selection".
 * 
 * @param <T>
 *          The type of Individual to select.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
// TODO allow for stochastic universal sampling
// TODO what happens on Double.POSITIVE_INFINITY fitnesses?
public class FitnessProportionateSelection<T> implements
    IndependentSelectionFunction<T> {
  private static final transient Logger LOG = Logger
      .getLogger(FitnessProportionateSelection.class);

  /**
   * Fitness-proportionate selection, also known as "roulette wheel selection",
   * which chooses an individual from the specified mapping with a probability
   * weighted by the corresponding fitnesses.
   * 
   * @param fitnesses
   *          {@inheritDoc}
   * @return An individual chosen with probability weighted by corresponding
   *         fitnesses.
   * @throws SelectionException
   *           {@inheritDoc}
   * @see jmona.IndependentSelectionFunction#select(Map)
   */
  @Override
  public T select(final Map<T, Double> fitnesses) {

    // if the map is empty, return null
    if (fitnesses.size() == 0) {
      return null;
    }

    // get the sum of all fitnesses
    final double fitnessesSum = Functional.sum(fitnesses.values());

    // if no individual has any fitness, just return a random one
    if (fitnessesSum == 0.0) {
      return RandomUtils.choice(fitnesses.keySet());
    }

    // choose a number between 0 and the sum of all fitnesses
    final double selection = RandomUtils.randomData().nextUniform(0,
        fitnessesSum);

    // iterate over all entries in the list of fitnesses
    double pointer = 0.0;
    for (final Entry<T, Double> entry : fitnesses.entrySet()) {

      // get the fitness of the current individual
      final double fitness = entry.getValue();

      // if the selection is between the pointer and the pointer plus the
      // fitness of the current individual, return the current individual
      if (pointer < selection && pointer + fitness >= selection) {
        return entry.getKey();
      }

      // increment the pointer by the fitness of the current individual
      pointer += fitness;
    }

    return null;
  }
}
