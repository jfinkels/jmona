/**
 * AbstractEvolutionContext.java
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

import java.util.HashMap;
import java.util.Map;

import jmona.BreedingFunction;
import jmona.EvolutionContext;
import jmona.FitnessException;
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
   * Set the current population.
   * 
   * @param newCurrentPopulation
   *          The new current population.
   */
  protected void setCurrentPopulation(final Population<T> newCurrentPopulation) {
    this.population = newCurrentPopulation;
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
   * @throws FitnessException
   *           {@inheritDoc}
   * @see jmona.EvolutionContext#setFitnessFunction(jmona.FitnessFunction)
   */
  @Override
  public synchronized void setFitnessFunction(final FitnessFunction<T> function)
      throws FitnessException {
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
