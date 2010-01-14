/**
 * FittestIndividualGetter.java
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
package jmona.impl.fitness;

import java.util.Collections;

import jmona.EvolutionContext;
import jmona.FitnessFunction;
import jmona.Function;
import jmona.MappingException;

/**
 * A function which gets the most fit individual from an EvolutionContext.
 * 
 * A FitnessFunction must be set on this object before the most fit individual
 * can be determined.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual in the EvolutionContext in which to determine
 *          the fittest individual.
 * @since 0.5
 */
public class FittestIndividualGetter<T> extends FitnessFunctionCollaborator<T>
    implements Function<EvolutionContext<T>, T> {

  /** A comparator for standardized fitnesses of individuals. */
  private final StandardizedFitnessComparator<T> standardizedFitnessComparator = new StandardizedFitnessComparator<T>();

  /**
   * Gets the individual in the current population of the specified
   * EvolutionContext with the least standardized fitness value (that is, the
   * most fit individual).
   * 
   * @param context
   *          The EvolutionContext from which to get the most fit individual.
   * @throws MappingException
   *           If no FitnessFunction has been set on this object.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public T execute(final EvolutionContext<T> context) throws MappingException {
    if (this.fitnessFunction() == null) {
      throw new MappingException("No FitnessFunction has been set.");
    }

    return Collections.min(context.currentPopulation(),
        this.standardizedFitnessComparator);
  }

  /**
   * Sets the FitnessFunction on this object so that it knows how to determine
   * standardized fitness.
   * 
   * @param newFitnessFunction
   *          The FitnessFunction which determines standardized fitness of
   *          individuals in an EvolutionContext.
   */
  @Override
  public void setFitnessFunction(final FitnessFunction<T> newFitnessFunction) {
    super.setFitnessFunction(newFitnessFunction);
    this.standardizedFitnessComparator.setFitnessFunction(newFitnessFunction);
  }
}
