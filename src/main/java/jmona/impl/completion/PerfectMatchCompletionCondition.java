/**
 * PerfectMatchCompletionCondition.java
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
package jmona.impl.completion;

import java.util.Map;

import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.DeepCopyable;
import jmona.EvolutionContext;
import jmona.GeneticEvolutionContext;

/**
 * CompletionCondition used to determine whether an EvolutionContext contains an
 * individual with a adjusted fitness of 1 (that is, a perfect match to the
 * exact solution).
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual in the EvolutionContext to test for
 *          completion.
 * @since 0.4
 */
public class PerfectMatchCompletionCondition<T extends DeepCopyable<T>>
    implements CompletionCondition<T> {

  /** The optimal adjusted fitness for an individual. */
  public static final double PERFECT_FITNESS = 1.0;

  /**
   * Determine whether an individual with adjusted fitness 1 exists (that is, an
   * individual which is a perfect match to the solution of the problem).
   * 
   * @param context
   *          {@inheritDoc}
   * @throws CompletionException
   *           If the specified context is not a GeneticEvolutionContext
   * @see jmona.CompletionCondition#execute(jmona.EvolutionContext)
   */
  @Override
  public Boolean execute(final EvolutionContext<T> context)
      throws CompletionException {

    if (!(context instanceof GeneticEvolutionContext<?>)) {
      throw new CompletionException(
          "Cannot get a fitness function from the EvolutionContext unless it is a GeneticEvolutionContext. Class of EvolutionContext is "
              + context.getClass());
    }

    final Map<T, Double> fitnesses = ((GeneticEvolutionContext<T>) context)
        .currentAdjustedFitnesses();

    return fitnesses.containsValue(PERFECT_FITNESS);
  }
}
