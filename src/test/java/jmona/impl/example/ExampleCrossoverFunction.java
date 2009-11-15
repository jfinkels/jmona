/**
 * ExampleCrossoverFunction.java
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

import jmona.CrossoverFunction;

/**
 * An example CrossoverFunction.
 * 
 * @author jfinkels
 */
public class ExampleCrossoverFunction implements
    CrossoverFunction<ExampleIndividual> {

  /**
   * Swap the fitnesses of the specified individuals.
   * 
   * @param parent1
   *          {@inheritDoc}
   * @param parent2
   *          {@inheritDoc}
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final ExampleIndividual parent1,
      final ExampleIndividual parent2) {
    final double temp = parent1.fitness();
    parent1.setFitness(parent2.fitness());
    parent2.setFitness(temp);
  }

}
