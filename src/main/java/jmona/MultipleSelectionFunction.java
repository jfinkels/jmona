/**
 * MultipleSelectionFunction.java
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
 * A SelectionFunction in which multiple individuals can be selected
 * simultaneously.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual which will be selected.
 * @since 0.5
 */
public interface MultipleSelectionFunction<T> extends SelectionFunction<T> {

  /**
   * Selects the specified number of individuals from the specified population
   * using the specified fitness function.
   * 
   * @param population
   *          The population from which to select individuals.
   * @param fitnessFunction
   *          The fitness function to use to determine the fitnesses of
   *          individuals in the specified population.
   * @param numberToSelect
   *          The number of individuals to select from the specified population.
   * @return A List of individuals of size {@code numberToSelect}, chosen from
   *         the specified population.
   * @throws SelectionException
   *           If there is a problem during selection of individuals.
   */
  List<T> select(final List<T> population,
      final FitnessFunction<T> fitnessFunction, final int numberToSelect)
      throws SelectionException;
}
