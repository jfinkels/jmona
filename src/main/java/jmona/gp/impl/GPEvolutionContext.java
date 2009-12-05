/**
 * GPEvolutionContext.java
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
package jmona.gp.impl;

import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.MutationException;
import jmona.Population;
import jmona.gp.Tree;
import jmona.impl.AbstractEvolutionContext;
import jmona.impl.DefaultPopulation;
import jmona.impl.Util;

/**
 * A default EvolutionContext for a Genetic Programming evolution.
 * 
 * @param <V>
 *          The type of value to which a Tree evaluates.
 * @author jfinkels
 */
public class GPEvolutionContext<V> extends AbstractEvolutionContext<Tree<V>> {

  /**
   * Instantiate this EvolutionContext by calling the corresponding constructor
   * of the superclass.
   * 
   * @param initialPopulation
   *          The initial population in the evolution.
   */
  public GPEvolutionContext(final Population<Tree<V>> initialPopulation) {
    super(initialPopulation);
  }

  /**
   * 
   * @throws EvolutionException
   *           {@inheritDoc}
   * @see jmona.EvolutionContext#stepGeneration()
   */
  // TODO documentation for this method
  @Override
  public void stepGeneration() throws EvolutionException {
    // perform sanity check
    try {
      this.sanityCheck();
    } catch (final NullPointerException exception) {
      throw new EvolutionException("Sanity check failed.", exception);
    }

    // get the size of the current population
    final int currentSize = this.currentPopulation().size();

    // if the size of the population is too small
    if (currentSize < 2) {
      throw new RuntimeException("The size of the population is less than 2.");
    }

    // initialize a new population to hold the next generation
    final Population<Tree<V>> nextGeneration = new DefaultPopulation<Tree<V>>();

    Tree<V> individual1 = null;
    Tree<V> individual2 = null;

    try {
      // while the size of the next generation is less than the size of the
      // current generation
      while (nextGeneration.size() < currentSize) {

        // select one individual, because each op. requires at least one
        individual1 = this.selectionFunction().select(this.currentFitnesses());

        // choose variation operation probabilistically
        // TODO I am ignoring the crossoverProbability property
        if (Util.RANDOM.nextDouble() < this.mutationProbability()
            || nextGeneration.size() >= currentSize - 1) {

          // perform mutation
          this.mutationFunction().mutate(individual1);

        } else { // variation operation is crossover

          // select another individual (different from the first!)
          do {
            individual2 = this.selectionFunction().select(
                this.currentFitnesses());
          } while (individual1.equals(individual2));

          // perform crossover
          this.crossoverFunction().crossover(individual1, individual2);

          // add the second individual to the next generation
          nextGeneration.add(individual2);
        }

        // add the original individual to the next generation
        nextGeneration.add(individual1);
      }

      // set the current population to the next generation
      this.setCurrentPopulation(nextGeneration);

      // recalculate the fitnesses of the current generation
      this.recalculateFitnesses();

    } catch (final FitnessException exception) {
      throw new EvolutionException(
          "Failed determining fitness of an individual.", exception);
    } catch (final MutationException exception) {
      throw new EvolutionException("Failed mutating an individual.", exception);
    }
    
    // increment the generation number
    this.incrementGeneration();
  }
}
