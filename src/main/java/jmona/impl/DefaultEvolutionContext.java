/**
 * DefaultEvolutionContext.java
 */
package jmona.impl;

import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.Individual;
import jmona.MutationException;
import jmona.Pair;
import jmona.Population;

import org.apache.log4j.Logger;

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
   * How many children are allowed beyond the desired population size during the
   * breeding stage.
   */
  public static final int CHILD_REPLACEMENT_FACTOR = 5;

  /**
   * The percentage of the population that will die off at the beginning of each
   * generation.
   */
  public static final double SELECTION_FACTOR = 0.75;

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
   * @throws EvolutionException
   *           If there is a problem during creation of the next generation.
   * @see jmona.EvolutionContext#stepGeneration()
   */
  @Override
  public void stepGeneration() throws EvolutionException {

    /**
     * Step 0: sanity check for all necessary functions
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
     * Step 1: kill off the least fit individuals from the current population
     */

    // get the initial selection size
    final int initialSelectionSize = (int) (this.currentPopulation().size() * SELECTION_FACTOR);

    // kill off the least fit individuals
    this.setCurrentPopulation(this.selectionFunction().select(
        this.currentFitnesses(), initialSelectionSize));

    /**
     * Step 2: breed the remaining individuals; Step 3: determine the fitnesses
     * of the offspring
     */

    // breed to make new individuals using recombination and mutation
    T parent1 = null, parent2 = null, leftChild = null, rightChild = null;
    Pair<T, T> children = null;
    int limit = this.desiredPopulationSize() + CHILD_REPLACEMENT_FACTOR;
    int size = this.currentPopulation().size();
    // TODO what if this.population.size() == limit - 1?
    while (size < limit - 1) {
      // the current population is less than two individuals, something's wrong
      if (this.currentPopulation().size() < 2) {
        throw new EvolutionException("The size of the current population is "
            + this.currentPopulation().size()
            + " but it should be greater than 1.");
      }

      // choose two members of the population to be parents
      parent1 = this.currentPopulation().get(Util.RANDOM.nextInt(size));
      parent2 = this.currentPopulation().get(Util.RANDOM.nextInt(size));

      // create a child from those two parents
      children = this.breedingFunction()
          .breed(new Pair<T, T>(parent1, parent2));

      // get the left child and the right child
      leftChild = children.left();
      rightChild = children.right();

      // mutate these children
      try {
        this.mutatorFunction().mutate(leftChild);
        this.mutatorFunction().mutate(rightChild);
      } catch (final MutationException exception) {
        throw new EvolutionException("Failed to mutate children.", exception);
      }

      // add these children to the population
      this.currentPopulation().add(leftChild);
      this.currentPopulation().add(rightChild);

      // add the fitnesses of these two new individuals to the map
      try {
        this.currentFitnesses().put(leftChild,
            this.fitnessFunction().fitness(leftChild));
        this.currentFitnesses().put(rightChild,
            this.fitnessFunction().fitness(rightChild));
      } catch (final FitnessException exception) {
        throw new EvolutionException(
            "Failed to determine fitness of children.", exception);
      }
      // get the new size of the population
      size = this.currentPopulation().size();
    }

    /**
     * Step 4: kill off the least fit individuals
     */

    // select the population for the next generation
    this.setCurrentPopulation(this.selectionFunction().select(
        this.currentFitnesses(), this.desiredPopulationSize()));

    /**
     * Step 5: increment the number of the current generation
     */

    // increment the generation number
    this.incrementGeneration();
  }

}
