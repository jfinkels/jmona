/**
 * CompletionCondition.java
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
package jmona;

/**
 * A condition on EvolutionContexts which determines when the evolution should
 * stop.
 * 
 * @param <T>
 *          The type of the individual in the evolution whose completion
 *          criteria this object encapsulates.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public interface CompletionCondition<T extends DeepCopyable<T>> extends
    Condition<EvolutionContext<T>> {

  /**
   * Whether the evolution of the specified EvolutionContext should complete.
   * 
   * @param context
   *          The EvolutionContext to test for completion.
   * @return Whether the specified EvolutionContext satisfies the condition
   *         encapsulated by this class.
   * @throws CompletionException
   *           If there is a problem determining whether this condition is
   *           satisfied.
   */
  @Override
  Boolean execute(final EvolutionContext<T> context) throws CompletionException;
}
