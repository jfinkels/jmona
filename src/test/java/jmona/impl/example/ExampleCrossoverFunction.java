/**
 * ExampleCrossoverFunction.java
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
package jmona.impl.example;

import jmona.CrossoverFunction;

/**
 * An example CrossoverFunction.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ExampleCrossoverFunction implements
    CrossoverFunction<ExampleIndividual> {

  /**
   * Swap the fitnesses of the specified individuals.
   * 
   * @param individual1
   *          {@inheritDoc}
   * @param individual2
   *          {@inheritDoc}
   * @see jmona.CrossoverFunction#crossover(Object, Object)
   */
  @Override
  public void crossover(final ExampleIndividual individual1,
      final ExampleIndividual individual2) {
    final double temp = individual1.fitness();
    individual1.setFitness(individual2.fitness());
    individual2.setFitness(temp);
  }

}
