/**
 * BestIndividualPostProcessor.java
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
package jmona.impl.postprocessing;

import jmona.DeepCopyable;
import jmona.EvolutionContext;
import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.GeneticEvolutionContext;
import jmona.LoggingException;
import jmona.MappingException;
import jmona.impl.fitness.FittestIndividualGetter;

import org.apache.log4j.Logger;

/**
 * A LoggingPostProcessor which logs the most fit individual and its fitness (if
 * possible).
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual in the EvolutionContext from which to get the
 *          most fit individual.
 * @since 0.5
 */
public class BestIndividualPostProcessor<T extends DeepCopyable<T>> extends
    LoggingPostProcessor<T> {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(BestIndividualPostProcessor.class);

  /** The function which gets the most fit individual from an EvolutionContext. */
  private final FittestIndividualGetter<T> fittestIndividualGetter = new FittestIndividualGetter<T>();

  /**
   * Gets the String representation of the most fit individual in the specified
   * EvolutionContext, and its fitness if possible.
   * 
   * @param context
   *          The EvolutionContext from which to get the most fit individual.
   * @throws LoggingException
   *           If the specified EvolutionContext is not a
   *           GeneticEvolutionContext, or if there is a problem getting the
   *           most fit individual from the specified EvolutionContext.
   * @see jmona.impl.postprocessing.LoggingPostProcessor#message(jmona.EvolutionContext
   *      )
   */
  @Override
  protected String message(final EvolutionContext<T> context)
      throws LoggingException {
    if (!(context instanceof GeneticEvolutionContext<?>)) {
      throw new LoggingException(
          "Cannot get a fitness function from the EvolutionContext unless it is a GeneticEvolutionContext. Class of EvolutionContext is "
              + context.getClass());
    }

    // get the fitness function from the EvolutionContext
    final FitnessFunction<T> fitnessFunction = ((GeneticEvolutionContext<T>) context)
        .fitnessFunction();

    // make the fittest individual getter aware of the fitness function to use
    this.fittestIndividualGetter.setFitnessFunction(fitnessFunction);

    // get the fittest individual from the EvolutionContext
    T fittestIndividual = null;
    try {
      fittestIndividual = this.fittestIndividualGetter.execute(context);
    } catch (final MappingException exception) {
      throw new LoggingException(
          "Failed to get most fit individual from EvolutionContext.", exception);
    }

    // create a string builder to contain the String to return
    final StringBuilder result = new StringBuilder();

    // append the fittest individual
    result.append(fittestIndividual);

    // append the fitness of that individual if possible
    try {
      result.append(" (fitness = ");
      result.append(fitnessFunction.rawFitness(fittestIndividual));
      result.append(")");
    } catch (final FitnessException exception) {
      LOG.error("Failed to determine fitness of an individual.", exception);
    }

    return result.toString();
  }

}
