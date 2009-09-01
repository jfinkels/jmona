/**
 * BreedingFunction.java
 */
package jmona;

/**
 * A class which contains a single method for breeding two parent individuals to
 * produce a child individual.
 * 
 * @param <T>
 *          The type of Individual to breed.
 * @author jfinke
 */
public interface BreedingFunction<T extends Individual> {

  /**
   * Breed the two specified parents to produce two children. Implementing
   * classes should use a crossover function during execution of this method.
   * 
   * @param parents
   *          The parent individuals.
   * @return The child resulting from breeding the two specified parents.
   */
  Pair<T, T> breed(final Pair<T, T> parents);

  /**
   * Get the crossover function used by this context.
   * 
   * @return The crossover function used by this context.
   */
  CrossoverFunction<T> crossoverFunction();

  /**
   * Get the probability of crossover occurring during a breeding, between 0
   * (inclusive) and 1 (inclusive).
   * 
   * @return The probability of crossover occurring during a breeding, between 0
   *         (inclusive) and 1 (inclusive).
   */
  double crossoverProbability();

  /**
   * Set the selection function used by this context.
   * 
   * @param function
   *          The selection function used by this context.
   */
  void setCrossoverFunction(final CrossoverFunction<T> function);

  /**
   * Set the probability of crossover occurring during a breeding, between 0
   * (inclusive) and 1 (inclusive).
   * 
   * @param probability
   *          The probability of crossover occurring during a breeding, between
   *          0 (inclusive) and 1 (inclusive).
   */
  void setCrossoverProbability(final double probability);

}
