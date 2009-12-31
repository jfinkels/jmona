/**
 * FitnessLoggingPostProcessor.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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
package jmona.impl;

import jmona.DeepCopyable;
import jmona.EvolutionContext;

/**
 * Log the fitnesses of the current population.
 * 
 * @param <T>
 *          The type of individual in the EvolutionContext whose fitnesses will
 *          be logged.
 * @author Jeffrey Finkelstein
 */
public class FitnessLoggingPostProcessor<T extends DeepCopyable<T>> extends
    LoggingPostProcessor<T> {

  /**
   * Get the fitnesses of the individuals in the current population of the
   * specified EvolutionContext.
   * 
   * @param context
   *          The EvolutionContext from which to get the fitnesses of the
   *          current population.
   * @return The fitnesses of the individuals in the current population, as a
   *         String.
   * @see jmona.impl.LoggingPostProcessor#message(jmona.EvolutionContext)
   */
  @Override
  protected String message(final EvolutionContext<T> context) {
    return context.currentFitnesses().toString();
  }
}
