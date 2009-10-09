package jmona.impl;

import java.util.Comparator;
import java.util.Map;

import jmona.Individual;

/**
 * A comparator with access to a mapping from individual to corresponding
 * fitness.
 * 
 * @author jeff
 * @param <T>
 *          The type of the individual to compare.
 */
class FitnessComparator<T extends Individual> implements Comparator<T> {

  /** The mapping from individual to corresponding fitness. */
  private Map<T, Double> fitnesses = null;

  /**
   * Compare the fitnesses of the two specified individual based on the
   * fitnesses map.
   * 
   * @param individual1
   *          An individual.
   * @param individual2
   *          Another individual.
   * @return The comparison between the fitnesses of the two specified
   *         individuals, if they are both in the known mapping from individual
   *         to fitness.
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  // TODO figure out if this works
  public int compare(final T individual1, final T individual2) {

    // if the two individuals are the same, one must be overwritten, so return 0
    if (individual1.equals(individual2)) {
      return 0;
    }
    // otherwise, the two individuals are different

    final int fitnessComparison = this.fitnesses.get(individual1).compareTo(
        this.fitnesses.get(individual2));

    if (fitnessComparison != 0) {
      return fitnessComparison;
    }

    return individual1.hashCode() - individual2.hashCode();

  }

  /**
   * Set the fitnesses to use for comparison.
   * 
   * @param newFitnesses
   *          The fitnesses to use for comparison.
   */
  public void setFitnesses(final Map<T, Double> newFitnesses) {
    this.fitnesses = newFitnesses;
  }
}
