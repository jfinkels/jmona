/**
 * CalcFitnessFunction.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.example.calc;

import java.util.Set;

import jfcommon.functional.Function;
import jfcommon.functional.MappingException;
import jmona.FitnessException;
import jmona.gp.Tree;
import jmona.impl.fitness.MaximizingFitnessFunction;

/**
 * Determines how close a Tree is to representing a target function.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class CalcFitnessFunction extends MaximizingFitnessFunction<Tree> {

  /** The Tree evaluator. */
  public static final CalcTreeEvaluator EVALUATOR = new CalcTreeEvaluator();
  /** The inputs used by the EquivalenceTester. */
  private Set<Double> evaluationInputs = null;
  /** The target function. */
  private Function<Double, Double> target = null;

  /**
   * Instantiates this FitnessFunction with the specified maximum possible raw
   * fitness value.
   * 
   * @param maximumRawFitness
   *          The maximum possible raw fitness value.
   */
  public CalcFitnessFunction(final double maximumRawFitness) {
    super(maximumRawFitness);
  }

  /**
   * Determines how close the function to which the specified Tree evaluates is
   * to a target function.
   * 
   * This method executes the function to which the specified Tree evaluates and
   * the target function with the same inputs, and returns the number of inputs
   * for which the evaluated function and the target function yield the same
   * output.
   * 
   * @param tree
   *          The tree to evaluate.
   * @return The number of inputs for which the function to which the specified
   *         Tree evaluates and the target function yield the same output.
   * @throws FitnessException
   *           If there is a problem evaluating the Tree, or if there is a
   *           problem getting the output of either of the functions.
   * @see jmona.FitnessFunction#rawFitness(Object)
   */
  @Override
  public double rawFitness(final Tree tree) throws FitnessException {
    Function<Double, Double> function = null;
    try {
      function = EVALUATOR.execute(tree);
    } catch (final MappingException exception) {
      throw new FitnessException("Failed to evaluate the Tree.", exception);
    }

    int successes = 0;

    try {
      for (final double input : this.evaluationInputs) {
        if (function.execute(input).equals(this.target.execute(input))) {
          successes += 1;
        }
      }
    } catch (final MappingException exception) {
      throw new FitnessException("Failed to execute a function on an input.",
          exception);
    }

    return successes;
  }

  /**
   * Set the inputs used by the EquivalenceTester.
   * 
   * @param newEvaluationInputs
   *          The inputs used by the EquivalenceTester.
   */
  public void setEvaluationInputs(final Set<Double> newEvaluationInputs) {
    this.evaluationInputs = newEvaluationInputs;
  }

  /**
   * Set the target function.
   * 
   * @param newTarget
   *          The target function.
   */
  public void setTarget(final Function<Double, Double> newTarget) {
    this.target = newTarget;
  }
}
