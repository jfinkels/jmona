/**
 * DefaultPopulationFactory.java
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
