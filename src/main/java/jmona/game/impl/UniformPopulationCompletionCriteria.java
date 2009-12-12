/**
 * UniformPopulationCompletionCriteria.java
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

import jmona.CompletionCriteria;
import jmona.CompletionException;
import jmona.EvolutionContext;
import jmona.Population;
import jmona.game.Strategy;

/**
 * @author jfinkels
 */
public class UniformPopulationCompletionCriteria<S extends Strategy> implements
    CompletionCriteria<S> {

  /*
   * (non-Javadoc)
   * 
   * @see jmona.CompletionCriteria#isSatisfied(jmona.EvolutionContext)
   */
  @Override
  public boolean isSatisfied(final EvolutionContext<S> context)
      throws CompletionException {

    // get the current population from the evolution context
    final Population<S> population = context.currentPopulation();

    // get a class for comparison with classes of the rest of the population
    final Class<S> someClass = (Class<S>) population.get(0).getClass();

    // iterate over every individual in the population
    for (final S strategy : population) {

      // if any strategy is of a different class the population is not uniform
      if (!strategy.getClass().equals(someClass)) {
        return false;
      }
    }

    // if every strategy had the same class, then there is a uniform population
    return true;
  }

}