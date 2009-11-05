/**
 * AbstractGPFitnessFunction.java
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

import jmona.FitnessFunction;
import jmona.gp.Tree;

/**
 * A base class for FitnessFunctions which have some evaluation input, and use a
 * Metric for determining distance from a target value.
 * 
 * @param <V>
 *          The type of value to which a Tree evaluates.
 * @author jfinkels
 */
public abstract class AbstractGPFitnessFunction<V> implements
    FitnessFunction<Tree<V>> {
  /**
   * The array of input objects for use in the evaluation of Trees in the
   * evolution.
   */
  private Object[] evaluationInput = null;

  /** The metric for comparison of Individuals. */
  private Metric<V> metric = null;

  /**
   * Get the array of input objects for use in the evaluation of Trees in the
   * evolution.
   * 
   * @return The array of input objects for use in the evaluation of Trees in
   *         the evolution.
   */
  public Object[] evaluationInput() {
    return this.evaluationInput;
  }

  /**
   * Get the metric for comparison of Individuals.
   * 
   * @return The metric for comparison of Individuals.
   */
  public Metric<V> metric() {
    return this.metric;
  }

  /**
   * Set the array of input objects for use in the evaluation of Trees in the
   * evolution.
   * 
   * @param newEvaluationInput
   *          The array of input objects for use in the evaluation of Trees in
   *          the evolution.
   */
  public void setEvaluationInput(final Object[] newEvaluationInput) {
    this.evaluationInput = newEvaluationInput;
  }

  /**
   * Set the metric for comparison of Individuals.
   * 
   * @param newMetric
   *          The metric for comparison of Individuals.
   */
  public void setMetric(final Metric<V> newMetric) {
    this.metric = newMetric;
  }

}
