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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import jmona.SelectionException;

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
public class LinearRankingSelection<T> extends FitnessProportionateSelection<T> {

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
   * @see jmona.IndependentSelectionFunction#select(java.util.List,
   *      jmona.FitnessFunction)
   */
  @Override
  public T select(final Map<T, Double> fitnesses) {

    // create a comparator based on the fitnesses of the individuals as
    // specified in the map given as input to this method
    final Comparator<T> comparator = new ValueComparator<T, Double>(fitnesses);

    // sort individuals by fitness value, from lowest to highest
    final SortedMap<T, Double> sortedFitnesses = new TreeMap<T, Double>(
        comparator);
    sortedFitnesses.putAll(fitnesses);

    // create a map from individual to its rank based on fitness, with lowest
    // fitness individuals first and highest fitness individuals last
    final Map<T, Double> rank = new HashMap<T, Double>();

    // add the rank of the individual to the ranking map, where rank 1 is the
    // least fit individual
    int i = 1;
    for (final T individual : sortedFitnesses.keySet()) {
      rank.put(individual, (double) i);
      i += 1;
    }

    // use fitness proportionate selection where the adjusted fitness is the
    // rank of the individual
    return super.select(rank);
  }
}
