/**
 * AggregatorCompletionCondition.java
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

import java.util.Collection;

import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.EvolutionContext;

/**
 * A CompletionCondition which checks whether a specified set
 * CompletionConditions are satisfied.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual in the EvolutionContext to check for
 *          completion.
 * @since 0.4
 */
public class AggregatorCompletionCondition<T> implements CompletionCondition<T> {

  /**
   * The Set of CompletionConditions to check every time this Condition is
   * executed.
   */
  private final Collection<CompletionCondition<T>> conditions;

  /**
   * Instantiates this class with the specified Set of CompletionConditions to
   * check every time this Condition is executed.
   * 
   * @param initialConditions
   *          The Set of CompletionConditions to check every time this Condition
   *          is executed.
   */
  public AggregatorCompletionCondition(
      final Collection<CompletionCondition<T>> initialConditions) {
    this.conditions = initialConditions;
  }

  /**
   * Whether the specified EvolutionContext satisfies any of the
   * CompletionConditions specified in the constructor of this class.
   * 
   * @param input
   *          The EvolutionContext to test for completion.
   * @return Whether the specified EvolutionContext satisfies any of the
   *         CompletionConditions specified in the constructor of this class.
   * @throws CompletionException
   *           If any of the CompletionConditions throws a MappingException.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Boolean execute(final EvolutionContext<T> input)
      throws CompletionException {

    for (final CompletionCondition<T> condition : this.conditions) {
      if (condition.execute(input)) {
        return true;
      }
    }

    return false;
  }

}
