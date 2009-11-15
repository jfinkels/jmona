/**
 * ExampleMutationFunction.java
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

import jmona.MutationException;
import jmona.MutationFunction;

/**
 * An example MutationFunction.
 * 
 * @author jfinkels
 */
public class ExampleMutationFunction implements
    MutationFunction<ExampleIndividual> {

  /**
   * The value by which to increment the fitness of an ExampleIndividual on
   * mutation.
   */
  public static final double INCREMENT = 0.1;

  /**
   * Increment the fitness of the specified Individual by {@value INCREMENT}.
   * 
   * @param individual
   *          {@inheritDoc}
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final ExampleIndividual individual)
      throws MutationException {
    individual.setFitness(individual.fitness() + INCREMENT);
  }

}