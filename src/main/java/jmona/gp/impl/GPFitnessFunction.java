/**
 * GPFitnessFunction.java
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

import jmona.FitnessException;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;

/**
 * A FitnessFunction which measures the distance between the value to which a
 * Tree evaluates and a target value, both of type {@code V}.
 * 
 * @param <V>
 *          The type of value to which a Tree evaluates; can be measured with a
 *          Metric.
 * @author jfinkels
 */
public class GPFitnessFunction<V> extends AbstractGPFitnessFunction<V> {

  /** The target value. */
  private V target = null;

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

    if (this.target == null) {
      throw new FitnessException("No target value has been set.");
    }

    double result = 0.0;
    try {
      result = this.metric().measure(
          individual.evaluate(this.evaluationInput()), this.target);
    } catch (final EvaluationException exception) {
      throw new FitnessException("Failed to evaluate the Tree.", exception);
    }

    return result;
  }

  /**
   * Set the target value.
   * 
   * @param newTarget
   *          Set the target value.
   */
  public void setTarget(final V newTarget) {
    this.target = newTarget;
  }

}
