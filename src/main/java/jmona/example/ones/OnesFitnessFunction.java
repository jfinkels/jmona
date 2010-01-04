/**
 * OnesFitnessFunction.java
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
package jmona.example.ones;

import java.util.List;

import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.functional.Functional;
import jmona.impl.fitness.MaximizingFitnessFunction;

/**
 * A fitness function which gives higher fitness to individuals with a greater
 * number of ones in their genes.
 * 
 * @author Jeffrey Finkelstein
 */
public class OnesFitnessFunction extends MaximizingFitnessFunction<List<Byte>> {

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
   * Get the number of ones in the gene of the specified individual.
   * 
   * @param individual
   *          The individual whose fitness is to be determined.
   * @return The number of ones in the gene of the specified individual.
   * @see jmona.FitnessFunction#rawFitness(Object)
   */
  @Override
  public double rawFitness(final List<Byte> individual) {
    return Functional.sumBytes(individual);
  }

}
