/**
 * DefaultPopulationFactory.java
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
package jmona.impl;

import jmona.Individual;
import jmona.InitializationException;
import jmona.Population;

/**
 * A factory which creates a random initial population of Individuals of type T
 * using the IndividualFactory#createIndividual() method.
 * 
 * @param <T>
 *          The type of individual in the population to create.
 * 
 * @author jeff
 */
public class DefaultPopulationFactory<T extends Individual> extends
    AbstractPopulationFactory<T> {

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws InitializationException
   *           {@inheritDoc}
   * @see jmona.PopulationFactory#createPopulation()
   */
  @Override
  public Population<T> createPopulation() throws InitializationException {
    if (this.individualFactory() == null) {
      throw new InitializationException("No IndividualFactory has been set.");
    }

    final Population<T> result = new DefaultPopulation<T>();

    for (int i = 0; i < this.size(); ++i) {
      result.add(this.individualFactory().createIndividual());
    }

    return result;
  }
}
