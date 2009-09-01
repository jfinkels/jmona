/**
 * AbstractEvolutionContext.java
 */
package jmona.impl;

import java.util.HashMap;
import java.util.Map;

import jmona.BreedingFunction;
import jmona.EvolutionContext;
import jmona.FitnessFunction;
import jmona.Individual;
import jmona.MutatorFunction;
import jmona.Population;
import jmona.SelectionFunction;

/**
 * An abstract base class for EvolutionContexts.
 * 
 * @param <T>
 *          The type of individual to evolve.
 * @author jeff
 */
public abstract class AbstractEvolutionContext<T extends Individual> implements
    EvolutionContext<T> {
  /** The breeding function. */
  private BreedingFunction<T> breedingFunction = null;
  /** The current fitnesses of the population. */
  private final Map<T, Double> currentFitnesses = new HashMap<T, Double>();
  /** The desired size of the population at each generation. */
  private int desiredPopulationSize = 0;
  /** The fitness function for determining fitness of individuals. */
  private FitnessFunction<T> fitnessFunction = null;
  /** The current generation of the evolution. */
  private int generation = 0;
  /** The mutator function for this context. */
  private MutatorFunction<T> mutatorFunction = null;
  /** The current population. */
  private Population<T> population = null;
  /** The selection function for this context. */
  private SelectionFunction<T> selectionFunction = null;

  /**
   * Instantiate this evolution context with the specified initial population.
   * The initial population must be greater than or equal to 2 so that mating
   * can occur.
   * 
   * @param initialPopulation
   *          The initial population for the evolution.
   * @throws IllegalArgumentException
   *           If the size of the initial population is less than 2.
   */
  public AbstractEvolutionContext(final Population<T> initialPopulation) {
    if (initialPopulation.size() < 2) {
      throw new IllegalArgumentException(
          "The initial population must be of size greater than or equal to 2.");
    }
    this.population = initialPopulation;
    this.desiredPopulationSize = this.population.size();
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#breedingFunction()
   */
  @Override
  public BreedingFunction<T> breedingFunction() {
    return this.breedingFunction;
  }

  /**
   * Get the current map from individuals to their corresponding fitnesses.
   * 
   * @return The current map from individuals to their corresponding fitnesses.
   */
  protected Map<T, Double> currentFitnesses() {
    return this.currentFitnesses;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#currentGeneration()
   */
  @Override
  public int currentGeneration() {
    return this.generation;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#currentPopulation()
   */
  @Override
  public Population<T> currentPopulation() {
    return this.population;
  }

  /**
   * Get the desired population size after each generation.
   * 
   * @return The desired population size after each generation.
   */
  protected int desiredPopulationSize() {
    return this.desiredPopulationSize;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#fitnessFunction()
   */
  @Override
  public FitnessFunction<T> fitnessFunction() {
    return this.fitnessFunction;
  }

  /**
   * Increment the number of the current generation.
   */
  protected synchronized void incrementGeneration() {
    this.generation++;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#mutatorFunction()
   */
  @Override
  public MutatorFunction<T> mutatorFunction() {
    return this.mutatorFunction;
  }

  /**
   * Get the current population.
   * 
   * @return The current population.
   */
  public Population<T> population() {
    return this.population;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#selectionFunction()
   */
  @Override
  public SelectionFunction<T> selectionFunction() {
    return this.selectionFunction;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.EvolutionContext#setBreedingFunction(jmona.BreedingFunction)
   */
  @Override
  public void setBreedingFunction(final BreedingFunction<T> function) {
    this.breedingFunction = function;
  }

  /**
   * {@inheritDoc}
   * 
   * @param size
   *          {@inheritDoc}
   * @see jmona.EvolutionContext#setDesiredPopulationSize(int)
   */
  @Override
  public void setDesiredPopulationSize(final int size) {
    this.desiredPopulationSize = size;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.EvolutionContext#setFitnessFunction(jmona.FitnessFunction)
   */
  @Override
  public synchronized void setFitnessFunction(final FitnessFunction<T> function) {
    this.fitnessFunction = function;

    // assign the initial fitnesses
    for (final T individual : this.population) {
      this.currentFitnesses.put(individual, this.fitnessFunction
          .fitness(individual));
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.EvolutionContext#setMutatorFunction(jmona.MutatorFunction)
   */
  @Override
  public void setMutatorFunction(final MutatorFunction<T> function) {
    this.mutatorFunction = function;

  }

  /**
   * Set the current population to the specified population.
   * 
   * @param newPopulation
   *          The new current population.
   */
  protected void setPopulation(final Population<T> newPopulation) {
    this.population = newPopulation;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.EvolutionContext#setSelectionFunction(jmona.SelectionFunction)
   */
  @Override
  public void setSelectionFunction(final SelectionFunction<T> function) {
    this.selectionFunction = function;
  }

}
