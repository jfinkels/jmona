/**
 * DefaultMaxFitnessCompletionCondition.java
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
package jmona.impl.completion;

import java.util.List;

import jmona.CompletionException;
import jmona.DeepCopyable;
import jmona.EvolutionContext;
import jmona.FitnessFunction;
import jmona.GeneticEvolutionContext;
import jmona.MappingException;
import jmona.MaxFitnessCompletionCondition;
import jmona.functional.Functional;
import jmona.functional.operators.GreaterThanOrEqualTo;
import jmona.impl.fitness.RawFitnessGetter;

/**
 * CompletionCondition used to determine whether an EvolutionContext contains an
 * Individual with a maximum fitness value.
 * 
 * @param <T>
 *          The type of Individual in the EvolutionContext to test for
 *          completion.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class DefaultMaxFitnessCompletionCondition<T extends DeepCopyable<T>>
    implements MaxFitnessCompletionCondition<T> {

  /** The default maximum fitness of an Individual. */
  public static final double DEFAULT_MAX_FITNESS = Double.MAX_VALUE;
  /** The maximum fitness of an Individual. */
  private double maxFitness = DEFAULT_MAX_FITNESS;
  /** The function which gets raw fitnesses of individuals. */
  private final RawFitnessGetter<T> rawFitnessGetter = new RawFitnessGetter<T>();

  /**
   * Determine whether an Individual with the maximum fitness exists in the
   * current population of the specified EvolutionContext.
   * 
   * This method uses the FitnessFunction from
   * {@link EvolutionContext#fitnessFunction()} to measure the fitness of
   * Individuals.
   * 
   * @param context
   *          {@inheritDoc}
   * @throws CompletionException
   *           If the fitness function of the EvolutionContext throws a
   *           FitnessException.
   * @see jmona.CompletionCondition#isSatisfied(jmona.EvolutionContext)
   */
  @Override
  public boolean isSatisfied(final EvolutionContext<T> context)
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

    // set the fitness function on the raw fitness getter function
    this.rawFitnessGetter.setFitnessFunction(fitnessFunction);

    try {
      // get the list of fitnesses
      final List<Double> fitnesses = Functional.map(this.rawFitnessGetter,
          context.currentPopulation());

      // map the list to booleans representing whether any is greater than or
      // equal to the max fitness
      final List<Boolean> greaterThan = Functional.map(
          new GreaterThanOrEqualTo(this.maxFitness), fitnesses);

      // return whether any fitness is greater than the max fitness
      return Functional.any(greaterThan);

    } catch (final MappingException exception) {
      throw new CompletionException("Failed to get fitnesses.", exception);
    }

  }

  /**
   * {@inheritDoc}
   * 
   * @param newMaxFitness
   *          {@inheritDoc}
   * @see jmona.MaxFitnessCompletionCondition#setMaxFitness(double)
   */
  @Override
  public void setMaxFitness(final double newMaxFitness) {
    this.maxFitness = newMaxFitness;
  }
}
