/**
 * UniformPopulationCompletionCondition.java
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
package jmona.game.impl;

import jmona.EvolutionContext;
import jmona.Individual;
import jmona.Population;
import jmona.impl.DefaultMaxGenerationCompletionCondition;

/**
 * Determines whether a Population in an EvolutionContext contains only
 * Individuals of one class.
 * 
 * @param <T>
 *          The type of Individual in the Population.
 * @author jfinkels
 */
public class UniformPopulationCompletionCondition<T extends Individual> extends
    DefaultMaxGenerationCompletionCondition<T> {

  /**
   * Determines whether the current Population in the specified EvolutionContext
   * contains only Individuals of one class.
   * 
   * @param context
   *          The EvolutionContext containing the Population.
   * @return Whether the current Population in the specified EvolutionContext
   *         contains only Individuals of one class.
   * @see jmona.CompletionCondition#isSatisfied(jmona.EvolutionContext)
   */
  @Override
  public boolean isSatisfied(final EvolutionContext<T> context) {

    // if the maximum number of generations have passed, return true
    if (super.isSatisfied(context)) {
      return true;
    }

    // get the current population from the evolution context
    final Population<T> population = context.currentPopulation();

    // get a class for comparison with classes of the rest of the population
    final Class<T> someClass = (Class<T>) population.get(0).getClass();

    // iterate over every individual in the population
    for (final T strategy : population) {

      // if any strategy is of a different class the population is not uniform
      if (!strategy.getClass().equals(someClass)) {
        return false;
      }
    }

    // if every strategy had the same class, then there is a uniform population
    return true;
  }

}
