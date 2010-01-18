/**
 * ElitismSelectionFunction.java
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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import jmona.MultipleSelectionFunction;

/**
 * A SelectionFunction which chooses an arbitrary number of the most fit
 * individuals from a population.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual which will be selected.
 * @since 0.5
 */
public class ElitismSelectionFunction<T> implements
    MultipleSelectionFunction<T> {

  /**
   * Select the specified number of the most fit individuals from the specified
   * population with respect to the specified fitness function.
   * 
   * @param population
   *          The List of individuals from which to choose the most fit.
   * @param fitnessFunction
   *          The FitnessFunction by which to measure fitness of individuals.
   * @param numberToSelect
   *          The number of most fit individuals to select from the specified
   *          population.
   * @see jmona.MultipleSelectionFunction#select(java.util.Map, int)
   */
  @Override
  public List<T> select(final Map<T, Double> fitnesses, final int numberToSelect) {

    if (numberToSelect > fitnesses.size()) {
      throw new IllegalArgumentException(
          "Number to select must be less than or equal to size of fitnesses map.");
    }

    // create a comparator based on the fitnesses of the individuals as
    // specified in the map given as input to this method
    final Comparator<T> comparator = new ValueComparator<T, Double>(fitnesses);

    // create a map from individual to its rank based on fitness, with highest
    // fitness individuals first and lowest fitness individuals last
    final SortedMap<T, Double> sortedFitnesses = new TreeMap<T, Double>(
        Collections.reverseOrder(comparator));
    sortedFitnesses.putAll(fitnesses);

    final List<T> result = new Vector<T>();

    int i = numberToSelect;
    for (final T individual : sortedFitnesses.keySet()) {
      if (i == 0) {
        break;
      }
      result.add(individual);
      i -= 1;
    }

    return result;
  }

}
