/**
 * OnesPostProcessor.java
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
package jmona.impl.postprocessing;

import jmona.EvolutionContext;
import jmona.PopulationEvolutionContext;

/**
 * A PostProcessor which logs the current population of a
 * PopulationEvolutionContext.
 * 
 * @param <T>
 *          The type of individual in the PopulationEvolutionContext whose
 *          population will be logged.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class PopulationLoggingPostProcessor<T, E extends PopulationEvolutionContext<T>>
    extends LoggingPostProcessor<T, E> {

  /**
   * Get the current population of the specified EvolutionContext, as a String.
   * 
   * @param context
   *          The EvolutionContext containing the population.
   * @return The current population of the specified EvolutionContext, as a
   *         String.
   * @see jmona.impl.postprocessing.LoggingPostProcessor#message(EvolutionContext)
   */
  @Override
  protected String message(final E context) {
    return context.currentPopulation().toString();
  }

}
