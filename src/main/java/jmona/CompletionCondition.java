/**
 * CompletionCondition.java
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
package jmona;


/**
 * An object which contains a method which tells whether the criteria for the
 * completion of the evolution have been met.
 * 
 * For example, a certain number of generations have been evolved, or an
 * individual with a maximum fitness has been born, etc.
 * 
 * @param <T>
 *          The type of the Individual in the evolution whose completion
 *          criteria this object encapsulates.
 * @author jfinkels
 */
public interface CompletionCondition<T extends DeepCopyable<T>> {
  /**
   * Whether the criteria for completion of the evolution have been met.
   * 
   * @param context
   *          The evolution context which contains this completion criteria.
   * @return Whether the criteria for completion of the evolution have been met.
   * @throws CompletionException
   *           If there is a problem determining whether this completion
   *           criteria has been satisfied.
   */
  boolean isSatisfied(final EvolutionContext<T> context)
      throws CompletionException;
}
