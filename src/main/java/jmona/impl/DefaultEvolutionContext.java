/**
 * DefaultEvolutionContext.java
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

import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.Individual;
import jmona.MutationException;
import jmona.Population;

/**
 * A default implementation of the evolution context interface, which provides
 * basic functionality of a genetic algorithm. Not thread-safe.
 * 
 * @param <T>
 *          The type of individual on which the evolution occurs.
 * @author jfinke
 */
public class DefaultEvolutionContext<T extends Individual> extends
    AbstractEvolutionContext<T> {

  /**
   * Instantiate this evolution context with the specified initial population by
   * calling the corresponding constructor of the superclass.
   * 
   * @param initialPopulation
   *          The initial population.
   * @see AbstractEvolutionContext#AbstractEvolutionContext(Population)
   */
  public DefaultEvolutionContext(final Population<T> initialPopulation) {
    super(initialPopulation);
  }

  /**
   * 
   * @throws EvolutionException
   *           If there is a problem during creation of the next generation.
   * @see jmona.EvolutionContext#stepGeneration()
   */
  // TODO documentation for this method
  @Override
  public void stepGeneration() throws EvolutionException {

    /**
     * Step 0: Do sanity check for necessary functions.
     */
    if (this.fitnessFunction() == null) {
      throw new EvolutionException("Fitness function has not been set.");
    }
    if (this.mutationFunction() == null) {
      throw new EvolutionException("Mutator function has not been set.");
    }
    if (this.selectionFunction() == null) {
      throw new EvolutionException("Selection function has not been set.");
    }
    if (this.crossoverFunction() == null) {
      throw new EvolutionException("Crossover function has not been set.");
    }

    // instantiate a population which will represent the next generation
    final Population<T> nextPopulation = new DefaultPopulation<T>();

    // get the size of the current population
    final int currentSize = this.currentPopulation().size();

    if (currentSize < 2) {
      throw new RuntimeException(
          "The size of the population is currently less than 2.");
    }

    try {
      // while the size of the next generation is less than the size of the
      // current generation
      T individual1 = null;
      T individual2 = null;
      while (nextPopulation.size() < currentSize - 1) {
        /**
         * Step 1: select two individuals
         */
        individual1 = this.selectionFunction().select(this.currentFitnesses());
        individual2 = this.selectionFunction().select(this.currentFitnesses());

        /**
         * Step 2: Perform crossover with probability p_crossover
         */
        if (Util.RANDOM.nextDouble() < this.crossoverProbability()) {
          this.crossoverFunction().crossover(individual1, individual2);
        }

        /**
         * Step 3: Perform mutation with probability p_mutation
         */
        if (Util.RANDOM.nextDouble() < this.mutationProbability()) {
          this.mutationFunction().mutate(individual1);
        }
        if (Util.RANDOM.nextDouble() < this.mutationProbability()) {
          this.mutationFunction().mutate(individual2);
        }

        /**
         * Step 4: Add offspring to next generation
         */
        nextPopulation.add(individual1);
        nextPopulation.add(individual2);
      }

      // if the population size was an odd number, just add one more individual
      if ((currentSize & 1) == 1) {
        nextPopulation.add(this.selectionFunction().select(
            this.currentFitnesses()));
      }

      // set the current population to the next generation
      this.setCurrentPopulation(nextPopulation);

      // recalculate the fitnesses of the current generation
      this.recalculateFitnesses();
    } catch (final MutationException exception) {
      throw new EvolutionException("Failed mutating an individual.", exception);
    } catch (final FitnessException exception) {
      throw new EvolutionException(
          "Failed determining fitness of an individual.", exception);
    }

    // increment the generation number
    this.incrementGeneration();
  }
}
