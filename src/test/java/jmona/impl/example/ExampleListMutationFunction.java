/**
 * ExampleListMutationFunction.java
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

import jmona.impl.ImmutableElementsListMutationFunction;

/**
 * A MutationFunction for a List of ExampleIndividual objects.
 * 
 * @author jfinkels
 */
public class ExampleListMutationFunction extends
    ImmutableElementsListMutationFunction<ExampleIndividual> {

  /**
   * Get the ExampleIndividual with fitness equal to the additive inverse of the
   * fitness of the specified individual.
   * 
   * @param individual
   *          The individual to mutate.
   *@return A copy of an ExampleIndividual with the additive inverse of the
   *         specified input ExampleIndividual.
   * @see jmona.impl.ImmutableElementsListMutationFunction#mutated(java.lang.Object)
   */
  @Override
  protected ExampleIndividual mutated(final ExampleIndividual individual) {
    final ExampleIndividual result = new ExampleIndividual();
    result.setFitness(-1 * individual.fitness());
    return result;
  }

}
