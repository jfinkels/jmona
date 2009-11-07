/**
 * EquivalenceMetric.java
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
package jmona.gp.example.calc.metrics;

import java.util.Set;

import jmona.gp.example.calc.functions.SingleInputFunction;
import jmona.gp.impl.Metric;

/**
 * A metric which determines the closeness of two functions by comparing them
 * for equivalence based on a fixed Set of inputs.
 * 
 * @param <T>
 *          The type of the domain of the functions to measure.
 * @param <R>
 *          The type of the codomain of the functions to measure.
 * @author jfinkels
 */
public class EquivalenceMetric<T, R> implements
    Metric<SingleInputFunction<T, R>> {

  /** The metric to use for measuring distance in the codomain. */
  private Metric<R> codomainMetric = null;
  /** The Set of inputs to use for evaluating closeness of functions. */
  private Set<T> inputs = null;

  /**
   * Get the distance between the two specified functions by testing the number
   * of equivalences using a fixed set of inputs.
   * 
   * Formally, let <em>f = {@code element1}</em> and <em>g = {@code element2}</em>, then this method returns the number of
   * inputs, <em>x &isin; T</em>, such that <em>f(x)=g(x)</em>.
   * 
   * @param element1
   *          A function.
   * @param element2
   *          Another function.
   * @return The number of inputs for which the two specified functions are
   *         equivalent.
   * @see jmona.gp.impl.Metric#measure(java.lang.Object, java.lang.Object)
   */
  @Override
  public double measure(final SingleInputFunction<T, R> element1,
      final SingleInputFunction<T, R> element2) {

    int successes = 0;

    for (final T input : this.inputs) {
      if (this.codomainMetric.measure(element1.execute(input), element2
          .execute(input)) == 0) {
        successes += 1;
      }
    }

    return successes;
  }

  /**
   * Set the metric to use for measuring distance in the codomain.
   * 
   * @param newCodomainMetric
   *          The metric to use for measuring distance in the codomain.
   */
  public void setCodomainMetric(final Metric<R> newCodomainMetric) {
    this.codomainMetric = newCodomainMetric;
  }

  /**
   * Set the Set of inputs to use for evaluating closeness of functions.
   * 
   * @param newInputs
   *          The Set of inputs to use for evaluating closeness of functions.
   */
  public void setInputs(final Set<T> newInputs) {
    this.inputs = newInputs;
  }

}
