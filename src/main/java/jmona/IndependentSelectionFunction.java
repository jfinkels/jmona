/**
 * IndependentSelectionFunction.java
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
package jmona;

import java.util.List;

/**
 * A selection function in which the fitness of each individual can be measured
 * independently of all others in the population.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual in the population from which to select.
 * @since 0.3
 */
public interface IndependentSelectionFunction<T> extends SelectionFunction<T> {
  /**
   * Select an individual from the specified population using the specified
   * fitness function.
   * 
   * @param population
   *          The population from which to select an individual.
   * @param fitnessFunction
   *          The fitness function to use to determine the fitnesses of
   *          individuals in the specified population.
   * @return An individual chosen from the specified population.
   * @throws SelectionException
   *           If there is a problem during selection of an individual.
   */
  T select(final List<T> population, final FitnessFunction<T> fitnessFunction)
      throws SelectionException;
}
