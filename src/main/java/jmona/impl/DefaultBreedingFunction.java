/**
 * DefaultBreedingFunction.java
 */
package jmona.impl;

import jmona.Individual;
import jmona.Pair;

/**
 * A breeding function which uses crossover (if a random number is under a
 * certain threshold) to produce children from specified parent individuals.
 * 
 * @param <T>
 *          The type of individual to breed.
 * @author jfinke
 */
public class DefaultBreedingFunction<T extends Individual> extends
    AbstractBreedingFunction<T> {

  /**
   * Create a new pair of individual from the result of breeding the specified
   * pair of individuals.
   * 
   * @param parents
   *          The pair of individuals to breed.
   * @return The children which are the result of breeding the specified
   *         parents.
   */
  @Override
  public Pair<T, T> breed(final Pair<T, T> parents) {

    if (Util.RANDOM.nextDouble() < this.crossoverProbability()) {
      return this.crossoverFunction().crossover(parents);
    }

    return parents;
  }

}
