/**
 * AntTrailFitnessFunction.java
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
package jmona.example.anttrail;

import jmona.gp.Tree;
import jmona.impl.fitness.MaximizingFitnessFunction;

/**
 * A FitnessFunction which calculates the number of units of food that an Ant
 * collects on its Trail given a Tree representing the program that controls it.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntTrailFitnessFunction extends MaximizingFitnessFunction<Tree> {

  /**
   * Instantiates this FitnessFunction with the specified maximum possible raw
   * fitness.
   * 
   * @param maximumRawFitness
   *          The maximum possible raw fitness.
   */
  public AntTrailFitnessFunction(final double maximumRawFitness) {
    super(maximumRawFitness);
  }

  /**
   * The function that executes a Tree containing AntNodes and returns the
   * number of units of food that the Ant controlled by that Tree collected on
   * its Trail.
   */
  public static final AntTrailExecutor EXECUTOR = new AntTrailExecutor();

  /**
   * Returns the number of units of food that the Ant controlled by the
   * specified Tree eats on its Trail.
   * 
   * @param tree
   *          The tree representing the program that controls an Ant on a Trail.
   * @return The number of units of food that the Ant ate.
   * @see jmona.FitnessFunction#rawFitness(java.lang.Object)
   */
  @Override
  public double rawFitness(final Tree tree) {
    return EXECUTOR.execute(tree);
  }

}
