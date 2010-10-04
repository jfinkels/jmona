/**
 * AbstractPopulationEvolutionContext.java
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
package jmona.impl.context;

import java.util.List;

import jmona.PopulationEvolutionContext;

/**
 * A base class for evolutions which involve individuals in a population.
 * 
 * @param <T>
 *          The type of individual to evolve.
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public abstract class AbstractPopulationEvolutionContext<T> extends
    AbstractEvolutionContext<T> implements PopulationEvolutionContext<T> {

  /**
   * Instantiates this EvolutionContext with the specified initial population of
   * individuals.
   * 
   * @param initialPopulation
   *          The initial population of individuals in this evolution.
   */
  public AbstractPopulationEvolutionContext(final List<T> initialPopulation) {
    this.population = initialPopulation;
  }

  /** The current population. */
  private List<T> population = null;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.PopulationEvolutionContext#currentPopulation()
   */
  @Override
  public List<T> currentPopulation() {
    return this.population;
  }

  /**
   * Sets the current population.
   * 
   * @param newCurrentPopulation
   *          The new current population.
   */
  protected void setCurrentPopulation(final List<T> newCurrentPopulation) {
    this.population = newCurrentPopulation;
  }
}
