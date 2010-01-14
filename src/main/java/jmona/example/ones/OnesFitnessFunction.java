/**
 * OnesFitnessFunction.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.example.ones;

import jmona.DeepCopyableList;
import jmona.functional.Functional;
import jmona.impl.fitness.MaximizingFitnessFunction;

/**
 * A fitness function which gives higher fitness to individuals with a greater
 * number of ones.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class OnesFitnessFunction extends
    MaximizingFitnessFunction<DeepCopyableList<Byte>> {

  /**
   * Instantiates this FitnessFunction with the specified length of binary
   * strings to measure.
   * 
   * The maximum raw fitness value is set to the specified length, because a
   * binary string of length <em>l</em> consisting of all ones has a bit count
   * of <em>l</em>.
   * 
   * @param length
   *          The length of the binary string which this FitnessFunction
   *          measures.
   */
  public OnesFitnessFunction(final int length) {
    super(length);
  }

  /**
   * Gets the number of ones in the specified individual.
   * 
   * @param individual
   *          The individual whose fitness is to be determined.
   * @return The number of ones in the specified individual.
   * @see jmona.FitnessFunction#rawFitness(Object)
   */
  @Override
  public double rawFitness(final DeepCopyableList<Byte> individual) {
    return Functional.sumBytes(individual);
  }

}
