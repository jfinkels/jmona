/**
 * EvolutionContext.java
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
package jmona;

import java.util.List;

/**
 * A context in which evolution occurs.
 * 
 * @param <T>
 *          The type of individual on which this evolution occurs.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public interface EvolutionContext<T extends DeepCopyable<T>> {
  /**
   * Get the current generation number; the initial generation should be 0, and
   * each subsequent generation should be incremented by 1.
   * 
   * @return The current generation number of the evolution.
   */
  int currentGeneration();

  /**
   * Get the current population.
   * 
   * @return The current population.
   */
  List<T> currentPopulation();

  /**
   * Perform some action on the current population and assign that new
   * Population to be the current population.
   * 
   * @throws EvolutionException
   *           If there is a problem during creation of the next generation.
   */
  void stepGeneration() throws EvolutionException;
}
