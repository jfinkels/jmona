/**
 * AbstractGeneticEvolutionContext.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.impl.context;

import java.util.List;

import jmona.CrossoverFunction;
import jmona.DeepCopyable;
import jmona.FitnessFunction;
import jmona.GeneticEvolutionContext;
import jmona.IndependentSelectionFunction;
import jmona.MultipleSelectionFunction;
import jmona.MutationFunction;
import jmona.impl.selection.ElitismSelectionFunction;

/**
 * An abstract base class for GeneticEvolutionContexts.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual to evolve.
 * @since 0.3
 */
public abstract class AbstractGeneticEvolutionContext<T extends DeepCopyable<T>>
    extends AbstractEvolutionContext<T> implements GeneticEvolutionContext<T> {

  /**
   * The default probability that crossover will be performed on Individuals
   * selected for breeding.
   */
  public static final double DEFAULT_CROSSOVER_PROBABILITY = 0.6;

  /**
   * The default number of top individuals which are copied directly from the
   * current generation to the next generation without variation on each
   * generation step.
   */
  public static final int DEFAULT_ELITISM = 0;
  /** The default probability of mutating an Individual. */
  public static final double DEFAULT_MUTATION_PROBABILITY = 0.1;
  /** The crossover function. */
  private CrossoverFunction<T> crossoverFunction = null;
  /**
   * The probability that crossover will be performed on Individuals selected
   * for breeding.
   */
  private double crossoverProbability = DEFAULT_CROSSOVER_PROBABILITY;
  /**
   * The number of top individuals which are copied directly from the current
   * generation to the next generation without variation on each generation
   * step.
   */
  private int elitism = DEFAULT_ELITISM;
  /** The SelectionFunction which is used when elitism is greater than 0. */
  private final MultipleSelectionFunction<T> elitismSelectionFunction = new ElitismSelectionFunction<T>();
  /** The fitness function for determining fitness of individuals. */
  private FitnessFunction<T> fitnessFunction = null;
  /** The mutation function for this context. */
  private MutationFunction<T> mutationFunction = null;
  /** The probability of mutating an Individual. */
  private double mutationProbability = DEFAULT_MUTATION_PROBABILITY;
  /** The selection function for this context. */
  private IndependentSelectionFunction<T> selectionFunction = null;

  /**
   * Instantiates thie GeneticEvolutionContext with the specified initial
   * population.
   * 
   * @param initialPopulation
   *          The initial population.
   */
  public AbstractGeneticEvolutionContext(final List<T> initialPopulation) {
    super(initialPopulation);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#crossoverFunction()
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
   * @see jmona.GeneticEvolutionContext#crossoverProbability()
   */
  @Override
  public double crossoverProbability() {
    return this.crossoverProbability;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#elitism()
   */
  @Override
  public int elitism() {
    return this.elitism;
  }

  /**
   * Gets the ElitismSelectionFunction to select the most fit individuals in a
   * population.
   * 
   * @return The ElitismSelectionFunction to select the most fit individuals in
   *         a population.
   */
  protected MultipleSelectionFunction<T> elitismSelectionFunction() {
    return this.elitismSelectionFunction;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#fitnessFunction()
   */
  @Override
  public FitnessFunction<T> fitnessFunction() {
    return this.fitnessFunction;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#mutationFunction()
   */
  @Override
  public MutationFunction<T> mutationFunction() {
    return this.mutationFunction;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#mutationProbability()
   */
  @Override
  public double mutationProbability() {
    return this.mutationProbability;
  }

  /**
   * Perform some sanity checks, that is, check that all necessary properties
   * have been set.
   * 
   * The necessary properties are the FitnessFunction, the MutationFunction, the
   * SelectionFunction, and the CrossoverFunction. The functions are checked in
   * that order.
   * 
   * @throws NullPointerException
   *           If any of the necessary properties have not been set.
   */
  protected void sanityCheck() {
    if (this.fitnessFunction() == null) {
      throw new NullPointerException(Messages.getString("AbstractGeneticEvolutionContext.0")); //$NON-NLS-1$
    }
    if (this.mutationFunction() == null) {
      throw new NullPointerException(Messages.getString("AbstractGeneticEvolutionContext.1")); //$NON-NLS-1$
    }
    if (this.selectionFunction() == null) {
      throw new NullPointerException(Messages.getString("AbstractGeneticEvolutionContext.2")); //$NON-NLS-1$
    }
    if (this.crossoverFunction() == null) {
      throw new NullPointerException(Messages.getString("AbstractGeneticEvolutionContext.3")); //$NON-NLS-1$
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#selectionFunction()
   */
  @Override
  public IndependentSelectionFunction<T> selectionFunction() {
    return this.selectionFunction;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#setCrossoverFunction(jmona.CrossoverFunction)
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
   * @see jmona.GeneticEvolutionContext#setCrossoverProbability(double)
   */
  @Override
  public void setCrossoverProbability(final double newCrossoverProbability) {
    this.crossoverProbability = newCrossoverProbability;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newElitism
   *          {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#setElitism(int)
   */
  @Override
  public void setElitism(final int newElitism) {
    this.elitism = newElitism;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#setFitnessFunction(jmona.FitnessFunction)
   */
  @Override
  public void setFitnessFunction(final FitnessFunction<T> function) {
    this.fitnessFunction = function;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#setMutationFunction(MutationFunction)
   */
  @Override
  public void setMutationFunction(final MutationFunction<T> function) {
    this.mutationFunction = function;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newMutationProbability
   *          {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#setMutationProbability(double)
   */
  @Override
  public void setMutationProbability(final double newMutationProbability) {
    this.mutationProbability = newMutationProbability;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.GeneticEvolutionContext#setSelectionFunction(jmona.IndependentSelectionFunction)
   */
  @Override
  public void setSelectionFunction(
      final IndependentSelectionFunction<T> function) {
    this.selectionFunction = function;
  }
}
