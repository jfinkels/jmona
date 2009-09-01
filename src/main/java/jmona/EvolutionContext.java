/**
 * EvolutionContext.java
 */
package jmona;

import jmona.impl.EvolutionException;

/**
 * A context in which evolution occurs.
 * 
 * @param <T>
 *          The type of Individual on which this evolution occurs.
 * @author jfinke
 */
public interface EvolutionContext<T extends Individual> {

  /**
   * Get the breeding function used by this context.
   * 
   * @return The breeding function used by this context.
   */
  BreedingFunction<T> breedingFunction();

  /**
   * Get the current generation number; the initial generation should be 0, and
   * each subsequent generation should be incremented by 1.
   * 
   * @return The current generation number of the evolution.
   */
  int currentGeneration();

  /**
   * Get the current Population.
   * 
   * @return The current Population.
   */
  Population<T> currentPopulation();

  /**
   * Get the fitness function used by this context.
   * 
   * @return The fitness function used by this context.
   */
  FitnessFunction<T> fitnessFunction();

  /**
   * Get the mutator function used by this context.
   * 
   * @return The mutator function used by this context.
   */
  MutatorFunction<T> mutatorFunction();

  /**
   * Get the current population.
   * 
   * @return The current population.
   */
  Population<T> population();

  /**
   * Get the selection function used by this context.
   * 
   * @return The selection function used by this context.
   */
  SelectionFunction<T> selectionFunction();

  /**
   * Set the breeding function used by this context.
   * 
   * @param function
   *          The breeding function used by this context.
   */
  void setBreedingFunction(final BreedingFunction<T> function);

  /**
   * Set the desired size of the population at the end of each generation.
   * 
   * @param size
   *          The desired size of the population at the end of each generation.
   */
  void setDesiredPopulationSize(final int size);

  /**
   * Set the fitness function used by this context.
   * 
   * @param function
   *          The fitness function used by this context.
   */
  void setFitnessFunction(final FitnessFunction<T> function);

  /**
   * Set the mutator function used by this context.
   * 
   * @param function
   *          The mutator function used by this context.
   */
  void setMutatorFunction(final MutatorFunction<T> function);

  /**
   * Set the selection function used by this context.
   * 
   * @param function
   *          The selection function used by this context
   */
  void setSelectionFunction(final SelectionFunction<T> function);

  /**
   * Perform mutation and selection on the current population and assign that
   * new Population to be the current population.
   * 
   * @throws EvolutionException
   *           If there is a problem during creation of the next generation.
   */
  void stepGeneration() throws EvolutionException;
}
