/**
 * UniformPopulationCompletionCondition.java
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
package jmona.game.impl;

import java.util.List;

import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.EvolutionContext;
import jmona.PopulationEvolutionContext;

/**
 * Determines whether a population in an EvolutionContext contains only
 * individuals of one class.
 * 
 * @param <T>
 *          The type of individual in the population.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class UniformPopulationCompletionCondition<T> implements
    CompletionCondition<T> {

  /**
   * Determines whether the current population in the specified EvolutionContext
   * contains only individuals of one class.
   * 
   * @param context
   *          The EvolutionContext containing the population.
   * @return Whether the current population in the specified EvolutionContext
   *         contains only individuals of one class.
   * @throws CompletionException
   *           If the specified EvolutionContext is not a
   *           PopulationEvolutionContext.
   * @see jmona.CompletionCondition#execute(jmona.EvolutionContext)
   */
  @SuppressWarnings("unchecked")
  @Override
  public Boolean execute(final EvolutionContext<T> context)
      throws CompletionException {

    if (!(context instanceof PopulationEvolutionContext<?>)) {
      throw new CompletionException(
          "The specified EvolutionContext must be an instance of PopulationEvolutionContext. Class of specified EvolutionContext was: "
              + context.getClass());
    }

    // get the current population from the evolution context
    final List<T> population = ((PopulationEvolutionContext<T>) context)
        .currentPopulation();

    // get a class for comparison with classes of the rest of the population
    final Class<T> someClass = (Class<T>) population.get(0).getClass();

    // iterate over every individual in the population
    for (final T individual : population) {

      // if any strategy is of a different class the population is not uniform
      if (!individual.getClass().equals(someClass)) {
        return false;
      }
    }

    // if every strategy had the same class, then there is a uniform population
    return true;
  }

}
