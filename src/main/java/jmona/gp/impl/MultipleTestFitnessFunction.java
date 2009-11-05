/**
 * MultipleTestFitnessFunction.java
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
package jmona.gp.impl;

import java.util.Set;

import jmona.FitnessException;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;

/**
 * A FitnessFunction which determines the (discrete) number of tests which a
 * given individual passes.
 * 
 * @param <V>
 *          The type of value to which a Tree evaluates.
 * @author jfinkels
 */
public class MultipleTestFitnessFunction<V> extends
    AbstractGPFitnessFunction<V> {
  /** The target value. */
  private Set<V> targets = null;

  /**
   * Determine the fitness of the specified individual by measuring its distance
   * from a target.
   * 
   * @param individual
   *          {@inheritDoc}
   * @return The distance between the specified Individual and the target, as
   *         specified by the {@link GPFitnessFunction#target} property.
   * 
   * @see jmona.FitnessFunction#fitness(jmona.Individual)
   */
  @Override
  public double fitness(final Tree<V> individual) throws FitnessException {

    if (this.metric() == null) {
      throw new FitnessException("No metric has been set.");
    }

    if (this.targets == null) {
      throw new FitnessException("No Set of target values has been set.");
    }

    int successes = 0;
    try {
      for (final V target : this.targets) {
        if (this.metric().measure(individual.evaluate(this.evaluationInput()),
            target) == 0) {
          successes += 1;
        }
      }
    } catch (final EvaluationException exception) {
      throw new FitnessException("Failed to evaluate a Tree.", exception);
    }

    return successes;
  }

  /**
   * Set the Set of target values.
   * 
   * @param newTargets
   *          Set the Set of target values.
   */
  public void setTargets(final Set<V> newTargets) {
    this.targets = newTargets;
  }

}
