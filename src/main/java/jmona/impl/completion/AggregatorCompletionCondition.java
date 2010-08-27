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
import jmona.impl.UnmodifiableCollectionAggregator;

/**
 * A CompletionCondition which checks whether a specified set
 * CompletionConditions are satisfied.
 * 
 * This class iterates over CompletionCondition in the order defined by the
 * iterator of the Collection provided in the constructor of this class.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual in the EvolutionContext to check for
 *          completion.
 * @since 0.4
 */
public class AggregatorCompletionCondition<T> extends
    UnmodifiableCollectionAggregator<CompletionCondition<T>> implements
    CompletionCondition<T> {

  /**
   * Instantiates this class with the specified varargs array of
   * CompletionCondition objects to check every time this Condition is executed.
   * 
   * @param initialConditions
   *          The varargs array of CompletionCondition objects to check every
   *          time this Condition is executed.
   */
  public AggregatorCompletionCondition(
      final CompletionCondition<T>... initialConditions) {
    super(initialConditions);
  }

  /**
   * Instantiates this class with the specified Collection of
   * CompletionConditions to check every time this Condition is executed.
   * 
   * @param initialConditions
   *          The Iterable of CompletionConditions to check every time this
   *          Condition is executed.
   */
  public AggregatorCompletionCondition(
      final Collection<CompletionCondition<T>> initialConditions) {
    super(initialConditions);
  }

  /**
   * Whether the specified EvolutionContext satisfies any of the
   * CompletionConditions specified in the constructor of this class.
   * 
   * This method iterates over CompletionCondition in the order defined by the
   * iterator of the Collection provided in the constructor of this class.
   * 
   * @param input
   *          The EvolutionContext to test for completion.
   * @return Whether the specified EvolutionContext satisfies any of the
   *         CompletionConditions specified in the constructor of this class.
   * @throws CompletionException
   *           If any of the CompletionConditions throws a MappingException.
   * @see jfcommon.functional.Function#execute(java.lang.Object)
   */
  @Override
  public Boolean execute(final EvolutionContext<T> input)
      throws CompletionException {

    for (final CompletionCondition<T> condition : this.collection()) {
      if (condition.execute(input)) {
        return true;
      }
    }

    return false;
  }

}
