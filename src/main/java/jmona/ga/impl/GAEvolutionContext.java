/**
 * GAEvolutionContext.java
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
package jmona.ga.impl;

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.CrossoverException;
import jmona.DeepCopyable;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.MutationException;
import jmona.SelectionException;
import jmona.impl.AbstractEvolutionContext;

/**
 * A default implementation of the evolution context interface, which provides
 * basic functionality of a genetic algorithm. Not thread-safe.
 * 
 * @param <T>
 *          The type of individual on which the evolution occurs.
 * @author jfinkels
 */
public class GAEvolutionContext<T extends DeepCopyable<T>> extends
    AbstractEvolutionContext<T> {

  /**
   * Instantiate this evolution context with the specified initial population by
   * calling the corresponding constructor of the superclass.
   * 
   * @param initialPopulation
   *          The initial population.
   * @see AbstractEvolutionContext#AbstractEvolutionContext(Population)
   */
  public GAEvolutionContext(final List<T> initialPopulation) {
    super(initialPopulation);
  }

  /**
   * 
   * @throws EvolutionException
   *           {@inheritDoc}
   */
  // TODO documentation for this method
  @Override
  public void executeGenerationStep() throws EvolutionException {
    // perform a sanity check (i.e. make sure there are no null properties)
    try {
      this.sanityCheck();
    } catch (final NullPointerException exception) {
      throw new EvolutionException("Sanity check failed.", exception);
    }

    // instantiate a population which will represent the next generation
    final List<T> nextPopulation = new Vector<T>();

    // get the size of the current population
    final int currentSize = this.currentPopulation().size();

    // if the size of the population is too small
    if (currentSize < 2) {
      throw new RuntimeException("The size of the population is less than 2.");
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
        individual1 = (T) this.selectionFunction().select(
            this.currentFitnesses()).deepCopy();
        individual2 = (T) this.selectionFunction().select(
            this.currentFitnesses()).deepCopy();

        /**
         * Step 2: Perform crossover with probability p_crossover
         */
        if (Math.random() < this.crossoverProbability()) {
          this.crossoverFunction().crossover(individual1, individual2);
        }

        /**
         * Step 3: Perform mutation with probability p_mutation
         */
        if (Math.random() < this.mutationProbability()) {
          this.mutationFunction().mutate(individual1);
        }
        if (Math.random() < this.mutationProbability()) {
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
        nextPopulation.add((T) this.selectionFunction().select(
            this.currentFitnesses()).deepCopy());
      }

      // set the current population to the next generation
      this.setCurrentPopulation(nextPopulation);

      // recalculate the fitnesses of the current generation
      this.recalculateFitnesses();

    } catch (final CrossoverException exception) {
      throw new EvolutionException(
          "Failed to perform crossover on two Individuals.", exception);
    } catch (final FitnessException exception) {
      throw new EvolutionException(
          "Failed determining fitness of an individual.", exception);
    } catch (final MutationException exception) {
      throw new EvolutionException("Failed mutating an individual.", exception);
    } catch (final SelectionException exception) {
      throw new EvolutionException("Failed selecting an Individual.", exception);
    } catch (final CopyingException exception) {
      throw new EvolutionException("Failed to copy an Individual.", exception);
    }
  }
}
