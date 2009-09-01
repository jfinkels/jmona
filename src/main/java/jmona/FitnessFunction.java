/**
 * FitnessFunction.java
 */
package jmona;

/**
 * A class which provides a single method for determining the fitness of an
 * Individual.
 * 
 * @param <T>
 *          The type of Individual for which to determine fitness.
 * @author jfinke
 */
public interface FitnessFunction<T extends Individual> {
  /**
   * Determine the fitness of the specified individual.
   * 
   * @param individual
   *          The individual for which to determine fitness.
   * @return The fitness of the specified individual.
   */
  double fitness(T individual);
}
