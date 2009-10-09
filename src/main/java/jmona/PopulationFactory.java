/**
 * PopulationFactory.java
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
 * @author jeff
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
