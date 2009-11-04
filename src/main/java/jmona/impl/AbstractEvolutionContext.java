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

import jmona.CrossoverFunction;
import jmona.EvolutionContext;
import jmona.FitnessException;
import jmona.FitnessFunction;
import jmona.Individual;
import jmona.MutationFunction;
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
  /**
   * The default probability that crossover will be performed on Individuals
   * selected for breeding.
   */
  public static final double DEFAULT_CROSSOVER_PROBABILITY = 0.6;
  /** The crossover function. */
  private CrossoverFunction<T> crossoverFunction = null;
  /**
   * The probability that crossover will be performed on Individuals selected
   * for breeding.
   */
  private double crossoverProbability = DEFAULT_CROSSOVER_PROBABILITY;
  /** The current fitnesses of the population. */
  private Map<T, Double> currentFitnesses = new HashMap<T, Double>();
  /** The fitness function for determining fitness of individuals. */
  private FitnessFunction<T> fitnessFunction = null;
  /** The current generation of the evolution. */
  private int generation = 0;
  /** The mutator function for this context. */
  private MutationFunction<T> mutationFunction = null;
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
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#crossoverFunction()
   */
  @Override
  public CrossoverFunction<T> crossoverFunction() {
    return this.crossoverFunction;
  }

  /**
   * Get the probability that crossover will be performed on Individuals
   * selected for breeding.
   * 
   * @return The probability that crossover will be performed.
   */
  @Override
  public double crossoverProbability() {
    return this.crossoverProbability;
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
   * @see jmona.EvolutionContext#mutationFunction()
   */
  @Override
  public MutationFunction<T> mutationFunction() {
    return this.mutationFunction;
  }

  /**
   * Reset the mapping from individual in the current population to its fitness.
   * 
   * @throws FitnessException
   *           If there is a problem determining the fitness of an Individual.
   */
  protected synchronized void recalculateFitnesses() throws FitnessException {
    this.currentFitnesses = new HashMap<T, Double>();

    for (final T individual : this.population) {
      this.currentFitnesses.put(individual, this.fitnessFunction
          .fitness(individual));
    }
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
   * @see jmona.EvolutionContext#setCrossoverFunction(jmona.CrossoverFunction)
   */
  @Override
  public void setCrossoverFunction(final CrossoverFunction<T> function) {
    this.crossoverFunction = function;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newCrossoverProbability
   *          {@inheritDoc}
   * @see jmona.EvolutionContext#setCrossoverProbability(double)
   */
  @Override
  public void setCrossoverProbability(final double newCrossoverProbability) {
    this.crossoverProbability = newCrossoverProbability;
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

    this.recalculateFitnesses();
  }

  public static final double DEFAULT_MUTATION_PROBABILITY = 0.1;
  private double mutationProbability = DEFAULT_MUTATION_PROBABILITY;

  /**
   * {@inheritDoc}
   * 
   * @param newMutationProbability
   *          {@inheritDoc}
   * @see jmona.EvolutionContext#setMutationProbability(double)
   */
  @Override
  public void setMutationProbability(final double newMutationProbability) {
    this.mutationProbability = newMutationProbability;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.EvolutionContext#mutationProbability()
   */
  @Override
  public double mutationProbability() {
    return this.mutationProbability;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.EvolutionContext#setMutatorFunction(jmona.MutationFunction)
   */
  @Override
  public void setMutatorFunction(final MutationFunction<T> function) {
    this.mutationFunction = function;
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
