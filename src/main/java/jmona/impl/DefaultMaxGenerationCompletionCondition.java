/**
 * DefaultCompletionCriteria.java
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
package jmona.impl;

import jmona.DeepCopyable;
import jmona.EvolutionContext;
import jmona.MaxGenerationCompletionCondition;

/**
 * An object which checks if the specified maximum number of generations has
 * been achieved in the specified evolution context.
 * 
 * @param <T>
 *          The type of Individual being evolved.
 * @author Jeffrey Finkelstein
 */
public class DefaultMaxGenerationCompletionCondition<T extends DeepCopyable<T>>
    implements MaxGenerationCompletionCondition<T> {

  /** The default maximum number of generations in the evolution. */
  public static final int DEFAULT_MAX_GENERATIONS = Integer.MAX_VALUE;
  /**
   * The maximum number of generations for this evolution. If the number of
   * generations in the evolution meets or exceeds this value, then the
   * evolution stops.
   */
  private int maxGenerations = DEFAULT_MAX_GENERATIONS;

  /**
   * Whether the maximum number of generations has already occurred.
   * 
   * @param context
   *          {@inheritDoc}
   * @return Whether the maximum number of generations has already occurred.
   * @see jmona.CompletionCondition#isSatisfied(jmona.EvolutionContext)
   */
  @Override
  public boolean isSatisfied(final EvolutionContext<T> context) {
    return context.currentGeneration() >= this.maxGenerations;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newMaxGenerations
   *          {@inheritDoc}
   */
  public void setMaxGenerations(final int newMaxGenerations) {
    this.maxGenerations = newMaxGenerations;
  }
}
