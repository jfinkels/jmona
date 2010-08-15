/**
 * FitnessLoggingPostProcessor.java
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

import java.util.Map;
import java.util.Map.Entry;

import jmona.DeepCopyable;
import jmona.EvolutionContext;
import jmona.GeneticEvolutionContext;
import jmona.LoggingException;

/**
 * Logs the raw fitnesses of the current population.
 * 
 * @param <T>
 *          The type of individual in the EvolutionContext whose raw fitness
 *          will be logged.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class FitnessLoggingPostProcessor<T extends DeepCopyable<T>> extends
    LoggingPostProcessor<T> {

  /**
   * Get the raw fitnesses of the individuals in the current population of the
   * specified EvolutionContext.
   * 
   * @param context
   *          The EvolutionContext from which to get the raw fitnesses of the
   *          current population.
   * @return The raw fitnesses of the individuals in the current population, as
   *         a String.
   * @throws LoggingException
   *           If the specified EvolutionContext is not a
   *           GeneticEvolutionContext, or if there is a problem determining the
   *           raw fitness of an individual.
   * @see jmona.impl.postprocessing.LoggingPostProcessor#message(jmona.EvolutionContext)
   */
  @Override
  protected String message(final EvolutionContext<T> context)
      throws LoggingException {
    if (!(context instanceof GeneticEvolutionContext<?>)) {
      throw new LoggingException(
          "Cannot get a fitness function from the EvolutionContext unless it is a GeneticEvolutionContext. Class of EvolutionContext is "
              + context.getClass());
    }

    final StringBuilder result = new StringBuilder();

    final Map<T, Double> fitnesses = ((GeneticEvolutionContext<T>) context)
        .currentAdjustedFitnesses();

    for (final Entry<T, Double> entry : fitnesses.entrySet()) {
      result.append(NEWLINE);
      result.append(entry.getKey());
      result.append(": ");
      result.append(entry.getValue());
    }

    return result.toString();
  }
}
