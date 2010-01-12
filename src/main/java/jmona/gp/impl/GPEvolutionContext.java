/**
 * GPEvolutionContext.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.CrossoverException;
import jmona.EvolutionException;
import jmona.FitnessFunction;
import jmona.IndependentSelectionFunction;
import jmona.MutationException;
import jmona.SelectionException;
import jmona.gp.Tree;
import jmona.impl.context.AbstractGeneticEvolutionContext;
import jmona.random.RandomUtils;

/**
 * A default EvolutionContext for a Genetic Programming evolution.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class GPEvolutionContext extends AbstractGeneticEvolutionContext<Tree> {

  /**
   * Instantiate this EvolutionContext by calling the corresponding constructor
   * of the superclass.
   * 
   * @param initialPopulation
   *          The initial population in the evolution.
   */
  public GPEvolutionContext(final List<Tree> initialPopulation) {
    super(initialPopulation);
  }

  /**
   * This implementation makes a deep copy of each Tree as soon as it is
   * selected for the next generation. Once copied, the copy of the selected
   * Tree gets mutated (or crossed over with another copied Tree), then added to
   * the next generation. The correctness of the evolution relies on the
   * correctness of the {@link Tree#deepCopy()} method.
   * 
   * @throws EvolutionException
   *           {@inheritDoc}
   * @see jmona.EvolutionContext#stepGeneration()
   */
  // TODO documentation for this method
  @Override
  protected void executeGenerationStep() throws EvolutionException {
    // perform sanity check
    try {
      this.sanityCheck();
    } catch (final NullPointerException exception) {
      throw new EvolutionException("Sanity check failed.", exception);
    }

    // get a reference to the current population
    final List<Tree> currentPopulation = this.currentPopulation();

    // get the size of the current population
    final int currentSize = currentPopulation.size();

    // if the size of the population is too small
    if (currentSize < 2) {
      throw new RuntimeException("The size of the population is less than 2.");
    }

    // initialize a new population to hold the next generation
    final List<Tree> nextPopulation = new Vector<Tree>();

    // get a reference to the fitness function for this evolution
    final FitnessFunction<Tree> fitnessFunction = this.fitnessFunction();

    // get a reference to the selection function for this evolution
    final IndependentSelectionFunction<Tree> selectionFunction = this
        .selectionFunction();

    try {
      // while the size of the next generation is less than the size of the
      // current generation
      Tree individual1 = null;
      Tree individual2 = null;
      while (nextPopulation.size() < currentSize) {

        // select one individual, because each op. requires at least one
        individual1 = selectionFunction.select(currentPopulation,
            fitnessFunction).deepCopy();

        // choose variation operation probabilistically
        // TODO I am ignoring the crossoverProbability property
        if (RandomUtils.nextDouble() < this.mutationProbability()
            || nextPopulation.size() >= currentSize - 1) {

          // perform mutation
          this.mutationFunction().mutate(individual1);

        } else { // variation operation is crossover

          // select another individual (different from the first!)
          individual2 = selectionFunction.select(currentPopulation,
              fitnessFunction).deepCopy();

          // perform crossover
          this.crossoverFunction().crossover(individual1, individual2);

          // add the second individual to the next generation
          nextPopulation.add(individual2);
        }

        // add the original individual to the next generation
        nextPopulation.add(individual1);
      }

      // set the current population to the next generation
      this.setCurrentPopulation(nextPopulation);

      // recalculate the fitnesses of the current generation
      // this.recalculateFitnesses();

    } catch (final CrossoverException exception) {
      throw new EvolutionException(
          "Failed to perform crossover on two Individuals.", exception);
      // } catch (final FitnessException exception) {
      // throw new EvolutionException(
      // "Failed determining fitness of an Individual.", exception);
    } catch (final MutationException exception) {
      throw new EvolutionException("Failed mutating an Individual.", exception);
    } catch (final SelectionException exception) {
      throw new EvolutionException("Failed to select an Individual.", exception);
    } catch (final CopyingException exception) {
      throw new EvolutionException("Failed to copy an Individual.", exception);
    }
  }
}
