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

import java.util.List;

import jmona.FitnessFunction;
import jmona.IndependentSelectionFunction;
import jmona.MappingException;
import jmona.SelectionException;
import jmona.functional.Functional;
import jmona.impl.fitness.AdjustedFitnessGetter;
import jmona.random.RandomUtils;

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

  /**
   * The function which gets adjusted fitnesses from individuals in the
   * population using a FitnessFunction.
   */
  private final AdjustedFitnessGetter<T> adjustedFitnessGetter = new AdjustedFitnessGetter<T>();

  /**
   * Fitness-proportionate selection, also known as "roulette wheel selection",
   * which chooses an individual from the specified mapping with a probability
   * weighted by the corresponding fitnesses.
   * 
   * @param population
   *          The list of individuals from which to select.
   * @param fitnessFunction
   *          The FitnessFunction which determines the adjusted fitness of
   *          individuals in the specified population.
   * @return An Individual chosen with probability weighted by corresponding
   *         fitnesses.
   * @throws SelectionException
   *           If there is a problem determining the adjusted fitnesses of the
   *           population.
   * @see jmona.IndependentSelectionFunction#select(List, FitnessFunction)
   */
  // TODO more documentation on fitness-proportionate selection, i.e. formulae
  @Override
  public T select(final List<T> population,
      final FitnessFunction<T> fitnessFunction) throws SelectionException {
    // if the map is empty, return null
    if (population.size() == 0) {
      return null;
    }

    // set the fitness function with which to get the adjusted fitnesses
    this.adjustedFitnessGetter.setFitnessFunction(fitnessFunction);

    // get the list of fitnesses in the same order of the population
    List<Double> fitnesses = null;
    try {
      fitnesses = Functional.map(this.adjustedFitnessGetter, population);
    } catch (final MappingException exception) {
      throw new SelectionException(
          "Failed to get adjusted fitnesses of the population.", exception);
    }

    // get the sum of all fitnesses
    final double fitnessesSum = Functional.sum(fitnesses);

    // if no individual has any fitness, just return a random one
    if (fitnessesSum == 0.0) {
      return RandomUtils.choice(population);
    }

    // choose a number between 0 and the sum of all fitnesses
    final double selection = Math.random() * fitnessesSum;

    // iterate over all entries in the list of fitnesses
    T result = null;
    double pointer = 0.0;
    int i = 0;
    for (final double fitness : fitnesses) {
      if (pointer < selection && pointer + fitness >= selection) {
        result = population.get(i);
        break;
      }
      pointer += fitness;
      i += 1;
    }

    return result;
  }
}
