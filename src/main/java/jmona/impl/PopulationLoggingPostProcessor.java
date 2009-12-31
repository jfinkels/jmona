/**
 * OnesPostProcessor.java
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
 * A PostProcessor which logs the current population.
 * 
 * @param <T>
 *          The type of individual in the EvolutionContext whose population will
 *          be logged.
 * @author Jeffrey Finkelstein
 */
public class PopulationLoggingPostProcessor<T extends DeepCopyable<T>> extends
    LoggingPostProcessor<T> {

  /**
   * Get the current population of the specified EvolutionContext, as a String.
   * 
   * @param context
   *          The EvolutionContext containing the population.
   * @return The current population of the specified EvolutionContext, as a
   *         String.
   * @see jmona.impl.LoggingPostProcessor#message(EvolutionContext)
   */
  @Override
  protected String message(final EvolutionContext<T> context) {
    return context.currentPopulation().toString();
  }

}
