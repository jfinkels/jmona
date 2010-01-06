/**
 * MaxGenerationCompletionCriteria.java
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

import jmona.CompletionCondition;
import jmona.DeepCopyable;
import jmona.EvolutionContext;

/**
 * An object which checks if the specified maximum number of generations has
 * been achieved in the specified evolution context.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual in the EvolutionContext to check for
 *          completion.
 * @since 0.4
 */
public class MaxGenerationCompletionCondition<T extends DeepCopyable<T>>
    implements CompletionCondition<T> {

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
   * @see jmona.CompletionCondition#execute(jmona.EvolutionContext)
   */
  @Override
  public Boolean execute(final EvolutionContext<T> context) {
    return context.currentGeneration() >= this.maxGenerations;
  }

  /**
   * Set the maximum number of generations for an evolution to occur before this
   * condition is satisfied.
   * 
   * @param newMaxGenerations
   *          The maximum number of generations for an evolution to occur.
   */
  public void setMaxGenerations(final int newMaxGenerations) {
    this.maxGenerations = newMaxGenerations;
  }
}
