/**
 * MappingFitnessFunction.java
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

import jmona.FitnessFunction;
import jmona.Metric;
import jmona.SingleInputFunction;
import jmona.exceptions.FitnessException;
import jmona.exceptions.MappingException;
import jmona.exceptions.MetricException;

/**
 * A FitnessFunction which measures the distance between a specified Individual
 * and a target Individual with a metric function.
 * 
 * @param <T>
 *          The type of Individual to measure.
 * @author jfinkels
 */
// TODO change documentation
public class MappingFitnessFunction<T, V> implements FitnessFunction<T> {

  private SingleInputFunction<T, V> mapping = null;

  public void setMapping(final SingleInputFunction<T, V> newMapping) {
    this.mapping = newMapping;
  }

  /** The metric with which to measure the distance between two Individuals. */
  private Metric<V> metric = null;
  /** The target Individual. */
  private V target = null;

  /**
   * Get the fitness of the Individual by computing the image of the Individual
   * under the mapping in this class, then computing the multiplicative inverse
   * of the distance between the specified image and the target object.
   * 
   * Formally, given the individual whose fitness is to be determined,
   * <em>x</em>, a metric, <em>d</em>, a mapping function, <em>f</em>, and a
   * target individual, <em>t</em>, this method returns <em>1 / d(f(x),t)</em>.
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

    // get the image of the specified individual under the mapping
    V image = null;
    try {
      image = this.mapping.execute(individual);
    } catch (final MappingException exception) {
      throw new FitnessException("Failed to perform mapping on Individual.",
          exception);
    }

    // get the distance between the image and the target
    double distance = Double.POSITIVE_INFINITY;
    try {
      distance = this.metric.measure(image, this.target);
    } catch (final MetricException exception) {
      throw new FitnessException(
          "Failed to measure distance between individual and target.");
    }

    // compute the fitness of the individual
    // TODO this is not an elegant way to do this...make this more general
    double result = Double.POSITIVE_INFINITY;
    if (distance != 0) {
      result = 1.0 / distance;
    }

    return result;
  }

  /**
   * Perform some sanity checks, that is, check that all necessary properties
   * have been set.
   * 
   * The necessary properties are the Metric, the target Individual, and the
   * mapping.
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

    if (this.mapping == null) {
      throw new NullPointerException("No mapping has been set.");
    }
  }

  /**
   * Set the metric with which to measure the distance between two Individuals.
   * 
   * @param newMetric
   *          The metric with which to measure the distance between two
   *          Individuals.
   */
  public void setMetric(final Metric<V> newMetric) {
    this.metric = newMetric;
  }

  /**
   * Set the target Individual.
   * 
   * @param newTarget
   *          The target Individual.
   */
  public void setTarget(final V newTarget) {
    this.target = newTarget;
  }

}
