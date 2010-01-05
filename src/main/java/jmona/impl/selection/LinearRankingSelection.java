/**
 * LinearRankingSelection.java
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jmona.FitnessFunction;
import jmona.IndependentSelectionFunction;
import jmona.SelectionException;
import jmona.functional.Range;
import jmona.impl.fitness.PresetFitnessFunction;
import jmona.impl.fitness.StandardizedFitnessComparator;

/**
 * SelectionFunction which assigns a linear rank to individuals (1, 2, 3,
 * &hellip;), then uses those numbers as the adjusted fitness in a
 * fitness-proportionate selection to select an individual.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual to choose.
 * @since 0.3
 */
public class LinearRankingSelection<T> implements
    IndependentSelectionFunction<T> {

  /** The comparator with which to rank the individuals in the population. */
  private final StandardizedFitnessComparator<T> standardizedFitnessComparator = new StandardizedFitnessComparator<T>();

  /**
   * Use a linear ranking as the probability distribution with which to choose
   * an individual from the specified population.
   * 
   * Linear ranking is performed by assigning a rank (1, 2, 3, &hellip;, {@code
   * population.size()}) to each individual in the specified population. This
   * number is then used as the fitness of the individual in a
   * fitness-proportionate selection.
   * 
   * @param population
   *          The population from which to choose an individual.
   * @param fitnessFunction
   *          The fitness function which is used to determine ranking of
   *          individuals of the specified population.
   * @return An individual chosen from the specified population.
   * @throws SelectionException
   *           If the fitness-proportionate selection phase of this method
   *           throws a SelectionException.
   * @see jmona.SelectionFunction#select(java.util.List, jmona.FitnessFunction)
   */
  @Override
  public T select(final List<T> population,
      final FitnessFunction<T> fitnessFunction) throws SelectionException {

    // make the fitness comparator aware of the fitness function
    this.standardizedFitnessComparator.setFitnessFunction(fitnessFunction);

    // create a new list containing references to all the individuals in the
    // population (so as not to upset the order of the elements in the
    // population list)
    final List<T> individuals = new Vector<T>(population);

    // sort the population based on fitness (better individuals first)
    Collections.sort(individuals, this.standardizedFitnessComparator);

    // reverse the order so that better individuals have a higher index in the
    // list
    Collections.reverse(individuals);

    // define a map from individual to its rank (that is, its index in the list)
    final Map<T, Double> rank = new HashMap<T, Double>();
    for (final int i : new Range(individuals.size())) {
      rank.put(individuals.get(i), (double) (i + 1));
    }

    // create a fitness function which uses that Map to determine adjusted
    // fitnesses
    final FitnessFunction<T> presetFitnessFunction = new PresetFitnessFunction<T>(
        rank);

    // use fitness proportionate selection where the adjusted fitness is the
    // rank of the individual
    final FitnessProportionateSelection<T> fitnessProportionateSelection = new FitnessProportionateSelection<T>();

    return fitnessProportionateSelection.select(individuals,
        presetFitnessFunction);
  }
}
