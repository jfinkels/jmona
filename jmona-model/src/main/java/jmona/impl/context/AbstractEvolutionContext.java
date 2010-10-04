/**
 * AbstractEvolutionContext.java
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
package jmona.impl.context;

import jmona.EvolutionContext;
import jmona.EvolutionException;

/**
 * An abstract base class for EvolutionContexts.
 * 
 * @param <T>
 *          The type of individual to evolve.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public abstract class AbstractEvolutionContext<T> implements
    EvolutionContext<T> {

  /** The current generation of the evolution. */
  private int generation = 0;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#currentGeneration()
   */
  @Override
  public int currentGeneration() {
    return this.generation;
  }

  /**
   * Perform the steps necessary for selection, variation, and fitness
   * calculation for this EvolutionContext.
   * 
   * @throws EvolutionException
   *           If there is a problem during the evolution.
   */
  protected abstract void executeGenerationStep() throws EvolutionException;

  /**
   * {@inheritDoc}
   * 
   * @throws EvolutionException
   *           {@inheritDoc}
   */
  @Override
  public void stepGeneration() throws EvolutionException {
    this.executeGenerationStep();

    this.generation += 1;
  }
}
