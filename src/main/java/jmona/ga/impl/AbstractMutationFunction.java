/**
 * AbstractMutationFunction.java
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
package jmona.ga.impl;

import jmona.Individual;
import jmona.MutationFunction;

/**
 * Base class for a MutationFunction with a probability of mutation.
 * 
 * @param <T>
 *          The type of Individual to mutate.
 * @author jfinke
 */
public abstract class AbstractMutationFunction<T extends Individual> implements
    MutationFunction<T> {
  /** The default probability of mutation. */
  public static final double DEFAULT_MUTATION_PROBABILITY = 0.05;
  /** The probability of mutation. */
  private double mutationProbability = DEFAULT_MUTATION_PROBABILITY;

  /**
   * Get the probability of mutation.
   * 
   * @return The probability of mutation.
   */
  public double mutationProbability() {
    return this.mutationProbability;
  }

  /**
   * Set the probability of mutation.
   * 
   * @param newMutationProbability
   *          The probability of mutation.
   */
  public void setMutationProbability(final double newMutationProbability) {
    this.mutationProbability = newMutationProbability;
  }
}
