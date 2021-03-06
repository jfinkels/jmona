/**
 * ExampleGPFitnessFunction.java
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
package jmona.gp.impl.example;

import jmona.FitnessException;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;
import jmona.impl.fitness.MinimizingFitnessFunction;

/**
 * An example FitnessFunction for Trees of ExampleNodes.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class ExampleGPFitnessFunction extends MinimizingFitnessFunction<Tree> {

  /**
   * Evaluate the Tree to an integer.
   * 
   * @param individual
   *          The tree to evaluate.
   * @return The value to which the Tree evaluates.
   * @throws FitnessException
   *           If there is a problem evaluating the Tree.
   * @see jmona.FitnessFunction#rawFitness(java.lang.Object)
   */
  @Override
  public double rawFitness(final Tree individual) throws FitnessException {
    try {
      return Math.abs(((ExampleNode) individual.root()).evaluate());
    } catch (final EvaluationException exception) {
      throw new FitnessException(exception);
    }
  }

}
