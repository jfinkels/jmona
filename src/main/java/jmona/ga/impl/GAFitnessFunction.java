/**
 * GAFitnessFunction.java
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

import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.Individual;
import jmona.Metric;

/**
 * A FitnessFunction which measures the distance between a specified Individual
 * and a target Individual with a metric function.
 * 
 * @param <T>
 *          The type of Individual to measure.
 * @author jfinkels
 */
public class GAFitnessFunction<T extends Individual> implements
    FitnessFunction<T> {

  /** The metric with which to measure the distance between two Individuals. */
  private Metric<T> metric = null;
  /** The target Individual. */
  private T target = null;

  /**
   * Get the fitness of the Individual by computing the multiplicative inverse
   * of the distance between the specified Individual and the target Individual.
   * 
   * Formally, given the individual whose fitness is to be determined,
   * <em>x</em>, a metric, <em>d</em>, and a target individual, <em>t</em>, this
   * method returns <em>1 / d(x,t)</em>.
   * 
   * @param individual
   *          The Individual whose fitness will be determined.
   * @return The fitness of the Individual as a function of its distance from an
   *         Individual as measured by a Metric.
   * @throws FitnessException
   *           {@inheritDoc}
   * @see jmona.Metric
   * @see jmona.Metric#measure(Object, Object)
   * @see jmona.FitnessFunction#fitness(jmona.Individual)
   */
  @Override
  public double fitness(final T individual) throws FitnessException {
    // sanity checks
    try {
      this.sanityCheck();
    } catch (final NullPointerException exception) {
      throw new FitnessException("Failed sanity check.", exception);
    }

    // get the distance of the individual from the target individual
    double distance = this.metric.measure(individual, this.target);

    // TODO this is not an elegant way to do this...make this more general
    double result = 0.0;
    if (distance == 0.0) {
      result = Double.POSITIVE_INFINITY;
    } else {
      result = 1.0 / distance;
    }

    return result;
  }

  /**
   * Perform some sanity checks, that is, check that all necessary properties
   * have been set.
   * 
   * The necessary properties are the Metric and the target Individual.
   * 
   * @throws NullPointerException
   *           If any of the necessary properties have not been set.
   */
  protected void sanityCheck() {
    if (this.metric == null) {
      throw new NullPointerException("No metric has been set.");
    }

    if (this.target == null) {
      throw new NullPointerException("No target Individual has been set.");
    }
  }

  /**
   * Set the metric with which to measure the distance between two Individuals.
   * 
   * @param newMetric
   *          The metric with which to measure the distance between two
   *          Individuals.
   */
  public void setMetric(final Metric<T> newMetric) {
    this.metric = newMetric;
  }

  /**
   * Set the target Individual.
   * 
   * @param newTarget
   *          The target Individual.
   */
  public void setTarget(final T newTarget) {
    this.target = newTarget;
  }

}
