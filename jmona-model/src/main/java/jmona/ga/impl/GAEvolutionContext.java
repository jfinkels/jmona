/**
 * GAEvolutionContext.java
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
package jmona.ga.impl;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import jmona.CopyingException;
import jmona.CrossoverException;
import jmona.DeepCopyable;
import jmona.EvolutionException;
import jmona.IndependentSelectionFunction;
import jmona.MutationException;
import jmona.PropertyNotSetException;
import jmona.SelectionException;
import jmona.impl.context.AbstractGeneticEvolutionContext;
import jmona.random.RandomUtils;

import org.apache.log4j.Logger;

/**
 * A default implementation of the evolution context interface, which provides
 * basic functionality of a genetic algorithm. Not thread-safe.
 * 
 * @param <T>
 *          The type of individual on which the evolution occurs.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class GAEvolutionContext<T extends DeepCopyable<T>> extends
    AbstractGeneticEvolutionContext<T> {
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(GAEvolutionContext.class);

  /**
   * Instantiate this evolution context with the specified initial population by
   * calling the corresponding constructor of the superclass.
   * 
   * @param initialPopulation
   *          The initial population.
   * @see AbstractGeneticEvolutionContext#AbstractGeneticEvolutionContext(List)
   */
  public GAEvolutionContext(final List<T> initialPopulation) {
    super(initialPopulation);
  }

  /**
   * Evolve the next generation of the current population by using a standard
   * genetic algorithms evolution algorithm.
   * 
   * The generation step essentially proceeds in four phases, which repeat until
   * the next generation has been fully determined:
   * 
   * <ul>
   * <li>Phase 1: clone two individuals selected using the SelectionFunction
   * previously set on this object to produce two child individuals</li>
   * <li>Phase 2: perform crossover on those individuals with the specified
   * probability, using the CrossoverFunction previously set on this object</li>
   * <li>Phase 3: perform mutation on those individuals with the specified
   * probability, using the MutationFuncion previously set on this object</li>
   * <li>Phase 4: add those individuals to the list containing the next
   * generation
   * </ul>
   * 
   * Once the next generation has been fully determined by repeating these four
   * phases, that list of individuals is assigned to be the current population.
   * 
   * @throws EvolutionException
   *           {@inheritDoc}
   * @throws PropertyNotSetException
   *           If one of the necessary properties has not been set.
   */
  @Override
  protected void executeGenerationStep() throws EvolutionException {
    // perform a sanity check (i.e. make sure there are no null properties)
    this.sanityCheck();

    LOG.debug("Starting generation " + this.currentGeneration());

    // get a reference to the current population
    final List<T> currentPopulation = this.currentPopulation();

    // get the size of the current population
    final int currentSize = currentPopulation.size();

    // get a reference to the current adjusted fitnesses of individuals in the
    // current population
    final Map<T, Double> currentFitnesses = this.currentAdjustedFitnesses();

    // get a reference to the selection function for this evolution
    // TODO allow for a multiple selection function
    final IndependentSelectionFunction<T> selectionFunction = this
        .selectionFunction();

    // instantiate a population which will represent the next generation
    final List<T> nextPopulation = new Vector<T>();

    try {
      // if elitism is enabled, copy the best individuals to the next generation
      if (this.elitism() > 0) {
        nextPopulation.addAll(this.elitismSelectionFunction().select(
            currentFitnesses, this.elitism()));
      }

      // while the size of the next generation is less than the size of the
      // current generation
      while (nextPopulation.size() < currentSize) {

        LOG.debug("Selecting first individual...");

        // select an individual and copy it to create the offspring
        T individual1 = selectionFunction.select(currentFitnesses).deepCopy();
        T individual2 = null;

        LOG.debug("...done.");

        // perform crossover (if there is enough room in the next generation)
        // with certain probability
        if (nextPopulation.size() < currentSize - 1
            && RandomUtils.nextDouble() < this.crossoverProbability()) {

          LOG.debug("Selecting second individual...");

          // select another individual and copy it to create another offspring
          individual2 = selectionFunction.select(currentFitnesses).deepCopy();

          LOG.debug("...done.");

          LOG.debug("Performing crossover...");

          // perform crossover on the two offspring
          this.crossoverFunction().crossover(individual1, individual2);

          LOG.debug("...done.");
        }

        // get the probability of performing mutation
        final double mutationProbability = this.mutationProbability();

        // perform mutation with certain probability
        if (RandomUtils.nextDouble() < mutationProbability) {
          LOG.debug("Performing mutation on individual 1...");
          this.mutationFunction().mutate(individual1);
          LOG.debug("...done.");
        }

        // perform mutation with certain probability (if the second individual
        // is not null)
        if (individual2 != null
            && RandomUtils.nextDouble() < mutationProbability) {
          LOG.debug("Performing mutation on individual 2...");
          this.mutationFunction().mutate(individual2);
          LOG.debug("...done.");
        }

        // add the offspring to the next generation
        nextPopulation.add(individual1);
        if (individual2 != null) {
          nextPopulation.add(individual2);
        }
      }

      // set the current population to the next generation
      this.setCurrentPopulation(nextPopulation);

    } catch (final CrossoverException exception) {
      throw new EvolutionException(
          "Failed to perform crossover on two individuals.", exception);
    } catch (final MutationException exception) {
      throw new EvolutionException("Failed mutating an individual.", exception);
    } catch (final SelectionException exception) {
      throw new EvolutionException("Failed selecting an individual.", exception);
    } catch (final CopyingException exception) {
      throw new EvolutionException("Failed to copy an individual.", exception);
    }
  }
}
