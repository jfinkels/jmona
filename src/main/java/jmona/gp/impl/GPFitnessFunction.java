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

import java.util.Set;

import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.gp.EquivalenceException;
import jmona.gp.EquivalenceTester;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;

/**
 * A FitnessFunction which measures the distance between the value to which a
 * Tree evaluates and a target value, both of type {@code V}.
 * 
 * @param <V>
 *          The type of value to which a Tree evaluates.
 * @param <I>
 *          The type of input with which to measure the equivalence of two Trees
 *          after evaluation.
 * @author Jeffrey Finkelstein
 */
public class GPFitnessFunction<V, I> implements FitnessFunction<Tree<V>> {

  /** The tester for whether two Trees are equivalent based on a given input. */
  private EquivalenceTester<V, I> equivalenceTester = null;
  /** The inputs used by the EquivalenceTester. */
  private Set<I> evaluationInputs = null;
  /** The target value. */
  private V target = null;

  /**
   * Determine the fitness of the specified Tree by measuring the total number
   * of inputs on which its evaluation is equivalent to a target value.
   * 
   * @param individual
   *          {@inheritDoc}
   * @return The total number of inputs on which its evaluation is equivalent to
   *         a target value.
   * 
   * @see jmona.FitnessFunction#fitness(Object)
   */
  @Override
  public double fitness(final Tree<V> individual) throws FitnessException {
    // sanity checks
    try {
      this.sanityCheck();
    } catch (final NullPointerException exception) {
      throw new FitnessException("Failed sanity check.", exception);
    }

    // initialize the number of successful equivalence checks
    int successes = 0;

    try {
      // iterate over each evaluation input
      for (final I input : this.evaluationInputs) {

        // if the individual and the target are equivalent, increment successes
        if (this.equivalenceTester.areEquivalent(individual.evaluate(),
            this.target, input)) {
          successes++;
        }
      }
    } catch (final EvaluationException exception) {
      throw new FitnessException("Failed to evaluate a Tree.", exception);
    } catch (final EquivalenceException exception) {
      throw new FitnessException(
          "Failed to determine whether two functions are equivalent.",
          exception);
    }

    return successes;
  }

  /**
   * Perform some sanity checks, that is, check that all necessary properties
   * have been set.
   * 
   * The necessary properties are the Set of evaluation inputs, the
   * EquivalenceTester, and the target Tree.
   * 
   * @throws NullPointerException
   *           If any of the necessary properties have not been set.
   */
  protected void sanityCheck() {
    if (this.evaluationInputs == null) {
      throw new NullPointerException("No metric has been set.");
    }

    if (this.equivalenceTester == null) {
      throw new NullPointerException("No EquivalenceTester has been set.");
    }

    if (this.target == null) {
      throw new NullPointerException("No target Individual has been set.");
    }
  }

  /**
   * Set the tester for whether two Trees are equivalent based on a given input.
   * 
   * @param newEquivalenceTester
   *          The tester for whether two Trees are equivalent based on a given
   *          input.
   */
  public void setEquivalenceTester(
      final EquivalenceTester<V, I> newEquivalenceTester) {
    this.equivalenceTester = newEquivalenceTester;
  }

  /**
   * Set the inputs used by the EquivalenceTester.
   * 
   * @param newEvaluationInputs
   *          The inputs used by the EquivalenceTester.
   */
  public void setEvaluationInputs(final Set<I> newEvaluationInputs) {
    this.evaluationInputs = newEvaluationInputs;
  }

  /**
   * Set the target value.
   * 
   * @param newTarget
   *          The target value.
   */
  public void setTarget(final V newTarget) {
    this.target = newTarget;
  }
}
