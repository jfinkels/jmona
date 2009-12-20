/**
 * MaxFitnessCompletionCriteria.java
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
 * A CompletionCondition which tests for completion based on whether the current
 * population in an EvolutionContext contains an Individual with a maximum
 * fitness.
 * 
 * @param <T>
 *          The type of Individual in the EvolutionContext which will be tested
 *          for completion.
 * @author jfinkels
 */
public interface MaxFitnessCompletionCondition<T extends Individual> extends
    CompletionCondition<T> {

  /**
   * Set the maximum fitness of an Individual in the EvolutionContext to be
   * tested for completion.
   * 
   * If an Individual in the EvolutionContext tested has a fitness greater than
   * or equal to the maximum fitness specified by this method, then the
   * CompletionCondition has been met.
   * 
   * @param newMaxFitness
   *          The maximum fitness of an Individual in the EvolutionContext to be
   *          tested for completion.
   */
  void setMaxFitness(final double newMaxFitness);
}
