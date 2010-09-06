/**
 * BestIndividualProcessor.java
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

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import jmona.DeepCopyable;
import jmona.GeneticEvolutionContext;
import jmona.impl.selection.ValueComparator;

/**
 * A LoggingProcessor which logs the most fit individual and its fitness (if
 * possible).
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual in the EvolutionContext from which to get the
 *          most fit individual.
 * @since 0.5
 */
public class BestIndividualProcessor<T extends DeepCopyable<T>, E extends GeneticEvolutionContext<T>>
    extends LoggingProcessor<T, E> {

  /**
   * Gets the String representation of the most fit individual in the specified
   * EvolutionContext, and its fitness if possible.
   * 
   * @param context
   *          The EvolutionContext from which to get the most fit individual.
   * @return A string representation of the most fit individual along with its
   *         fitness.
   * @see jmona.impl.postprocessing.LoggingProcessor#message(jmona.EvolutionContext)
   */
  @Override
  protected String message(final E context) {

    // get the map of fitnesses of individuals in the EvolutionContext
    final Map<T, Double> fitnesses = context.currentAdjustedFitnesses();

    // create a comparator based on the fitnesses of the individuals
    final Comparator<T> comparator = new ValueComparator<T, Double>(fitnesses);

    // get the fittest individual
    final T fittestIndividual = Collections
        .max(fitnesses.keySet(), comparator);

    // create a string builder to contain the String to return
    final StringBuilder result = new StringBuilder();

    // append the most fit individual
    result.append(fittestIndividual);

    // append the fitness of that individual if possible
    result.append(" (fitness = ");
    result.append(fitnesses.get(fittestIndividual));
    result.append(")");

    return result.toString();
  }

}
