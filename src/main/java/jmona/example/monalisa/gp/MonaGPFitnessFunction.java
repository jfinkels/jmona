/**
 * MonaGPFitnessFunction.java
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
package jmona.example.monalisa.gp;

import java.awt.image.BufferedImage;

import jmona.FitnessException;
import jmona.example.monalisa.ga.MonaGAFitnessFunction;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;
import jmona.impl.fitness.MinimizingFitnessFunction;

/**
 * Determines the distance between the evaluation of a Tree and a target image.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class MonaGPFitnessFunction extends MinimizingFitnessFunction<Tree> {

  /**
   * The FitnessFunction used to compare the result of evaluating individual
   * Trees to a target image.
   */
  private final MonaGAFitnessFunction fitnessFunction;

  /**
   * Instantiates this FitnessFunction with the specified target image against
   * which to compare individuals.
   * 
   * @param targetImage
   *          The target image against which to compare individuals.
   * @throws InterruptedException
   *           If there is a problem reading the pixels from the specified
   *           target image.
   */
  public MonaGPFitnessFunction(final BufferedImage targetImage)
      throws InterruptedException {
    this.fitnessFunction = new MonaGAFitnessFunction(targetImage);
  }

  /**
   * Evaluates the specified Tree to a List of ColoredPolygon objects, then
   * measures the distance of that List to the target image.
   * 
   * @param tree
   *          The tree to evaluate to a List of ColoredPolygon objects.
   * @return The distance between the evaluation of the specified Tree and the
   *         target image.
   * @throws FitnessException
   *           If there is a problem evaluating a Tree, or if there is a problem
   *           determining the fitness of the result of an evaluation.
   * @see jmona.FitnessFunction#rawFitness(java.lang.Object)
   */
  @Override
  public double rawFitness(final Tree tree) throws FitnessException {
    return this.fitnessFunction.rawFitness(((ColoredPolygonNode) tree.root())
        .evaluate());
  }

}
