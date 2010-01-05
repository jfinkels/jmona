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

import java.util.List;

import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.DeepCopyable;
import jmona.EvolutionContext;
import jmona.FitnessFunction;
import jmona.GeneticEvolutionContext;
import jmona.MappingException;
import jmona.functional.Functional;
import jmona.functional.operators.IsZero;
import jmona.impl.fitness.StandardizedFitnessGetter;

/**
 * CompletionCondition used to determine whether an EvolutionContext contains an
 * individual with a standardized fitness of 0 (that is, a perfect match to the
 * exact solution).
 * 
 * @param <T>
 *          The type of individual in the EvolutionContext to test for
 *          completion.
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public class PerfectMatchCompletionCondition<T extends DeepCopyable<T>>
    implements CompletionCondition<T> {

  /** The function which gets standardized fitnesses of individuals. */
  private final StandardizedFitnessGetter<T> standardizedFitnessGetter = new StandardizedFitnessGetter<T>();

  /**
   * Determine whether an individual with standardized fitness 0 exists (that
   * is, an individual which is a perfect match to the solution of the problem).
   * 
   * This method uses the FitnessFunction from
   * {@link EvolutionContext#fitnessFunction()} to measure the fitness of
   * individuals.
   * 
   * @param context
   *          {@inheritDoc}
   * @throws CompletionException
   *           If the fitness function of the EvolutionContext throws a
   *           FitnessException.
   * @see jmona.CompletionCondition#isSatisfied(jmona.EvolutionContext)
   */
  @Override
  public Boolean execute(final EvolutionContext<T> context)
      throws CompletionException {

    if (!(context instanceof GeneticEvolutionContext<?>)) {
      throw new CompletionException(
          "Cannot get a fitness function from the EvolutionContext unless it is a GeneticEvolutionContext. Class of EvolutionContext is "
              + context.getClass());
    }

    // get the fitness function from the EvolutionContext
    final FitnessFunction<T> fitnessFunction = ((GeneticEvolutionContext<T>) context)
        .fitnessFunction();

    if (fitnessFunction == null) {
      throw new CompletionException(
          "No FitnessFunction has been set on the EvolutionContext, so no fitnesses can be determined.");
    }

    // set the fitness function on the standardized fitness getter function
    this.standardizedFitnessGetter.setFitnessFunction(fitnessFunction);
    try {
      // get the list of fitnesses
      final List<Double> fitnesses = Functional.map(
          this.standardizedFitnessGetter, context.currentPopulation());

      // map the list to booleans representing whether any fitness is 0
      final List<Boolean> isZero = Functional.map(new IsZero(), fitnesses);

      // return whether any fitness is greater than the max fitness
      return Functional.any(isZero);
    } catch (final MappingException exception) {
      throw new CompletionException(
          "Failed to determine which individuals had which fitness.", exception);
    }

  }

}
