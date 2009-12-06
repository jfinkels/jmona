/**
 * PopulationFactory.java
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
package jmona;

/**
 * An object which provides a factory method for generating an initial
 * population of the specified type.
 * 
 * Intended for use in creating an initial random population for an
 * EvolutionContext.
 * 
 * Implementing classes should use the
 * {@link IndividualFactory#createIndividual()} method for creating Individuals.
 * Note that this implies an IndividualFactory has been set using the
 * {@link #setIndividualFactory(IndividualFactory)} method.
 * 
 * @param <T>
 *          The type of Individual in the population which is generated.
 * @author jfinkels
 */
public interface PopulationFactory<T extends Individual> {

  /**
   * Create a Population of Individuals of type T.
   * 
   * @return A Population of Individuals of type T.
   * @throws InitializationException
   *           If there is a problem creating the new Population.
   */
  Population<T> createPopulation() throws InitializationException;

  /**
   * Get the factory for creating individuals.
   * 
   * @return The new factory for creating Individuals.
   */
  IndividualFactory<T> individualFactory();

  /**
   * Set the factory for creating Individuals.
   * 
   * @param newIndividualFactory
   *          The new factory for creating Individuals.
   */
  void setIndividualFactory(final IndividualFactory<T> newIndividualFactory);

  /**
   * Set the size of the population to create.
   * 
   * @param newSize
   *          The size of the population to create.
   */
  void setSize(final int newSize);

  /**
   * Get the size of the population to create.
   * 
   * @return The size of the population to create.
   */
  int size();
}
