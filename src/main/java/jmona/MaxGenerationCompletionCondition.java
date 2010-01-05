/**
 * MaxGenerationCompletionCondition.java
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
 * A CompletionCondition which tests for completion based on the number of
 * generations which have passed in an EvolutionContext.
 * 
 * That is, once a certain number of generations have passed, the criteria has
 * been satisfied.
 * 
 * @param <T>
 *          The type of Individual in the EvolutionContext which will be tested
 *          for completion.
 * @author Jeffrey Finkelstein
 */
public interface MaxGenerationCompletionCondition<T extends DeepCopyable<T>>
    extends CompletionCondition<T> {
  /**
   * Set the maximum number of generations for this evolution.
   * 
   * If the number of generations in the evolution meets or exceeds this value,
   * then the evolution stops.
   * 
   * @param newMaxGenerations
   *          The maximum number of generations for this evolution.
   */
  void setMaxGenerations(final int newMaxGenerations);
}
