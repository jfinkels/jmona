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

import jmona.BreedingException;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.Individual;
import jmona.MutationException;
import jmona.Pair;
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
   * The default percentage of children allowed beyond the desired population
   * size during the breeding stage.
   */
  public static final double DEFAULT_CHILD_REPLACEMENT_FACTOR = .2;
  /**
   * The default percentage of the population that will be selected as parents
   * to create the offspring for each subsequent generation of the evolution.
   */
  public static final double DEFAULT_SELECTION_FACTOR = 0.75;
  /**
   * The percentage of children allowed beyond the desired population size
   * during the breeding stage.
   */
  private double childReplacementFactor = DEFAULT_CHILD_REPLACEMENT_FACTOR;
  /**
   * The percentage of the population that will be selected as parents to create
   * the offspring for each subsequent generation of the evolution.
   */
  private double selectionFactor = DEFAULT_SELECTION_FACTOR;

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
   * Set the percentage of children allowed beyond the desired population size
   * during the breeding stage.
   * 
   * @param newChildReplacementFactor
   *          The percentage of children allowed beyond the desired population
   *          size during the breeding stage.
   */
  public void setChildReplacementFactor(final double newChildReplacementFactor) {
    this.childReplacementFactor = newChildReplacementFactor;
  }

  /**
   * Set the percentage of the population that will be selected as parents to
   * create the offspring for each subsequent generation of the evolution.
   * 
   * @param newSelectionFactor
   *          The percentage of the population that will be selected as parents
   *          to create the offspring for each subsequent generation of the
   *          evolution.
   */
  public void setSelectionFactor(final double newSelectionFactor) {
    this.selectionFactor = newSelectionFactor;
  }

  /**
   * Perform the selection and variation on the current generation to get the
   * next generation. On each generation of the evolution, do the following:
   * <ol>
   * <li>kill off the least fit individuals in the population</li>
   * <li>breed the remaining individuals</li>
   * <li>determine the fitnesses of the offspring of the chosen individuals</li>
   * <li>replace least-fit individuals in the population with the offspring if
   * the offspring are more fit</li>
   * <li>increment the generation number</li>
   * </ol>
   * 
   * A FitnessFunction, BreedingFunction (with a collaborating
   * CrossoverFunction), MutatorFunction, and SelectionFunction must all be set
   * on an object of this class before this method can be executed. Otherwise,
   * an EvolutionException will be thrown.
   * 
   * @throws EvolutionException
   *           If there is a problem during creation of the next generation.
   * @see jmona.EvolutionContext#stepGeneration()
   */
  @Override
  public void stepGeneration() throws EvolutionException {

    /**
     * Step 0: Do sanity check for necessary functions.
     */

    if (this.fitnessFunction() == null) {
      throw new EvolutionException("Fitness function has not been set.");
    }
    if (this.breedingFunction() == null) {
      throw new EvolutionException("Breeding function has not been set.");
    }
    if (this.mutatorFunction() == null) {
      throw new EvolutionException("Mutator function has not been set.");
    }
    if (this.selectionFunction() == null) {
      throw new EvolutionException("Selection function has not been set.");
    }

    /**
     * Step 1: Select parents from the current population.
     */

    // get the initial selection size
    final int initialSelectionSize = (int) (this.currentPopulation().size() * this.selectionFactor);

    // kill off the least fit individuals
    this.setCurrentPopulation(this.selectionFunction().select(
        this.currentFitnesses(), initialSelectionSize));

    // get the maximum size of the intermediate population
    final int limit = (int) (this.desiredPopulationSize() * (1 + this.childReplacementFactor));

    // get the current size of the population
    int size = this.currentPopulation().size();

    // breed to make new individuals using recombination and mutation
    T parent1 = null, parent2 = null, leftChild = null, rightChild = null;
    Pair<T, T> children = null;
    // TODO what if this.population.size() == limit - 1?
    while (this.currentPopulation().size() < limit - 1) {
      // the current population is less than two individuals, something's wrong
      if (this.currentPopulation().size() < 2) {
        throw new EvolutionException("The size of the current population is "
            + this.currentPopulation().size()
            + " but it should be greater than 1.");
      }

      // choose two members of the population to be parents
      parent1 = this.currentPopulation().get(Util.RANDOM.nextInt(size));
      parent2 = this.currentPopulation().get(Util.RANDOM.nextInt(size));

      try {
        /**
         * Step 2: Recombine two parents to yield a pair of children.
         */

        // create a child from those two parents
        children = this.breedingFunction().breed(
            new Pair<T, T>(parent1, parent2));

        // get the left child and the right child
        leftChild = children.left();
        rightChild = children.right();

        /**
         * Step 3: Mutate the created children.
         */

        // mutate these children
        this.mutatorFunction().mutate(leftChild);
        this.mutatorFunction().mutate(rightChild);

        /**
         * Step 4: Add the offspring to the current population.
         */

        // add these children to the population
        this.currentPopulation().add(leftChild);
        this.currentPopulation().add(rightChild);

        /**
         * Step 5: Determine the fitnesses of the new offspring.
         */

        // add the fitnesses of these two new individuals to the map
        this.currentFitnesses().put(leftChild,
            this.fitnessFunction().fitness(leftChild));
        this.currentFitnesses().put(rightChild,
            this.fitnessFunction().fitness(rightChild));

      } catch (final FitnessException exception) {
        throw new EvolutionException(
            "Failed to determine fitness of children.", exception);
      } catch (final BreedingException exception) {
        throw new EvolutionException("Failed to breed parents.", exception);
      } catch (final MutationException exception) {
        throw new EvolutionException("Failed to mutate children.", exception);
      }
    }

    /**
     * Step 6: Kill the least fit individuals in the current population.
     */

    // select the population for the next generation
    this.setCurrentPopulation(this.selectionFunction().select(
        this.currentFitnesses(), this.desiredPopulationSize()));

    /**
     * Step 7: Increment the number of the current generation
     */

    // increment the generation number
    this.incrementGeneration();
  }

}
