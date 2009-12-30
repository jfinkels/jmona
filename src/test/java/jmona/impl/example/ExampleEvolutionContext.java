/**
 * ExampleEvolutionContext.java
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

import java.util.List;

import jmona.FitnessException;
import jmona.impl.AbstractEvolutionContext;

/**
 * An example EvolutionContext.
 * 
 * @author jfinkels
 */
public class ExampleEvolutionContext extends
    AbstractEvolutionContext<ExampleIndividual> {

  /**
   * Instantiate this EvolutionContext by calling the corresponding constructor
   * in the superclass.
   * 
   * @param initialPopulation
   *          The initial population for this EvolutionContext.
   * @throws RuntimeException
   *           If there is a problem determining the initial fitnesses of the
   *           individuals in the specified population.
   */
  public ExampleEvolutionContext(final List<ExampleIndividual> initialPopulation) {
    super(initialPopulation);
    try {
      this.setFitnessFunction(new ExampleFitnessFunction());
    } catch (final FitnessException exception) {
      throw new RuntimeException("Failed to determine fitnesses.", exception);
    }
  }

  /**
   * Does nothing.
   */
  @Override
  public void executeGenerationStep() {
    // intentionally unimplemented
  }

}
