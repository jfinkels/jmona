/**
 * SelectionFunction.java
 */
package jmona;

import java.util.Map;

/**
 * A class which provides a single method which selects a population given a
 * mapping from individuals in that population to their fitness scores.
 * 
 * @param <T>
 *          The type of Individual which will be selected.
 * @author jfinke
 */
public interface SelectionFunction<T extends Individual> {
  /**
   * Select a population based on the specified mapping from individuals in that
   * population to corresponding fitness scores.
   * 
   * @param fitnesses
   *          A mapping from individuals in a population to corresponding
   *          fitness scores.
   * @param numberOfIndividuals
   *          The number of individuals to select from the specified mapping.
   * @return A population based on the specified fitness scores.
   */
  Population<T> select(final Map<T, Double> fitnesses,
      final int numberOfIndividuals);
}
