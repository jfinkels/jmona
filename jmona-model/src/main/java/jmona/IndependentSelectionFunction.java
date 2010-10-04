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

import java.util.Map;

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
   * Selects an individual from the specified mapping from individual to
   * fitness.
   * 
   * @param fitnesses
   *          The mapping from individual to fitness.
   * @return An individual chosen from the specified Map.
   * @throws SelectionException
   *           If there is a problem during selection of an individual.
   */
  T select(final Map<T, Double> fitnesses) throws SelectionException;
}
