/**
 * ExampleFitnessFunction.java
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
package jmona.impl.example;

import jmona.FitnessException;
import jmona.FitnessFunction;

/**
 * An example FitnessFunction which simply gets the fitness of an Individual
 * from its fitness property.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExampleFitnessFunction implements
    FitnessFunction<ExampleIndividual> {
  /**
   * Get the fitness from the fitness property of the specified Individual.
   * 
   * @param individual
   *          The Individual whose fitness will be determined.
   * @return The fitness of the Individual as determined from its fitness
   *         property.
   * @throws FitnessException
   *           Never throws this exception.
   */
  @Override
  public double fitness(final ExampleIndividual individual)
      throws FitnessException {
    final double fitness = individual.fitness();

    if (fitness < 0) {
      throw new FitnessException("Fitness is negative.");
    }

    return individual.fitness();
  }
}
