/**
 * DefaultBreedingFunction.java
 */
package jmona.impl;

import jmona.BreedingException;
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
   * {@inheritDoc}
   * 
   * @param parents
   *          {@inheritDoc}
   * @throws BreedingException
   *           {@inheritDoc}
   * @return The children which are the result of breeding the specified
   *         parents.
   */
  // TODO decide on the contracts for which methods contain the cloning!
  // TODO my initial feeling is that it should be the first action in this one
  @Override
  public Pair<T, T> breed(final Pair<T, T> parents) throws BreedingException {

    if (this.crossoverFunction() == null) {
      throw new BreedingException("No crossover function has been set.");
    }

    if (Util.RANDOM.nextDouble() < this.crossoverProbability()) {
      return this.crossoverFunction().crossover(parents);
    }

    // create a cloned copy of the parents
    // TODO this is kludgy but necessary for compile-time type checking
    final Individual left = parents.left().copy();
    final Individual right = parents.right().copy();
    return new Pair<T, T>((T) left, (T) right);
  }

}
