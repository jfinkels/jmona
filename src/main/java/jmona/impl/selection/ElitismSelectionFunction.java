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
import java.util.List;
import java.util.Vector;

import jmona.FitnessFunction;
import jmona.MultipleSelectionFunction;
import jmona.impl.fitness.StandardizedFitnessComparator;

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

  /** A comparator for individuals based on their standardized fitnesses. */
  private final StandardizedFitnessComparator<T> standardizedFitnessComparator = new StandardizedFitnessComparator<T>();

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
   * @see jmona.MultipleSelectionFunction#select(java.util.List,
   *      jmona.FitnessFunction, int)
   */
  @Override
  public List<T> select(final List<T> population,
      final FitnessFunction<T> fitnessFunction, final int numberToSelect) {

    // cannot choose more individuals than there are in the population
    if (numberToSelect > population.size()) {
      throw new IllegalArgumentException("Cannot choose " + numberToSelect
          + " individuals from a population of size " + population.size());
    }

    // make the fitness comparator aware of the fitness function
    this.standardizedFitnessComparator.setFitnessFunction(fitnessFunction);

    // create a new list containing references to all the individuals in the
    // population (so as not to upset the order of the elements in the
    // population list)
    final List<T> individuals = new Vector<T>(population);

    // sort the population based on fitness (better individuals first)
    Collections.sort(individuals, this.standardizedFitnessComparator);

    // return the top individuals
    return individuals.subList(0, numberToSelect);
  }

}
