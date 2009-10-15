/**
 * DefaultSelectionFunction.java
 */
package jmona.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import jmona.Individual;
import jmona.Population;
import jmona.SelectionFunction;

/**
 * A base class for SelectionFunctions which select a specified number of
 * Individuals based on fitnesses.
 * 
 * Not thread safe.
 * 
 * @param <T>
 *          The type of the individual to select based on fitness.
 * @author jeff
 */
public abstract class AbstractSelectionFunction<T extends Individual>
    implements SelectionFunction<T> {

  /**
   * The comparator to use for creating navigable maps from individuals to
   * fitnesses.
   */
  private final FitnessComparator<T> comparator = new FitnessComparator<T>();

  /**
   * Get an iterator over the specified set. Individuals selected by this
   * iterator will be added to the selected population of individuals created in
   * the {@link #select(Map, int)} method.
   * 
   * @param individuals
   *          The navigable set of individuals over which to get an iterator.
   * @return An iterator which iterates over the individuals in the desired
   *         order (some number of the first individuals iterated over will be
   *         selected in the {@link #select(Map, int)} method).
   */
  protected abstract Iterator<T> getIterator(final NavigableSet<T> individuals);

  /**
   * Select the specified number of individuals with the greatest fitnesses as
   * specified in the fitness map.
   * 
   * @param fitnesses
   *          {@inheritDoc}
   * @param numberOfIndividuals
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jmona.SelectionFunction#select(java.util.Map, int)
   */
  @Override
  public Population<T> select(final Map<T, Double> fitnesses,
      final int numberOfIndividuals) {
    final Population<T> result = new DefaultPopulation<T>();

    // set the fitnesses property of the comparator
    this.comparator.setFitnesses(fitnesses);

    // initialize a new sorted set with respect to the specified fitnesses
    final NavigableSet<T> sortedIndividuals = new TreeSet<T>(this.comparator);

    // add all individuals to the sorted set
    sortedIndividuals.addAll(fitnesses.keySet());

    // get an iterator (for example, ascending or descending; maybe some other
    // order) over the set of individuals
    final Iterator<T> iterator = this.getIterator(sortedIndividuals);

    // get at most the specified number of individuals from the set
    int count = numberOfIndividuals;
    while (iterator.hasNext() && count > 0) {
      // add the current individual to the result population
      final T individual = iterator.next();
      result.add(individual);
      // result.add(iterator.next());

      // decrement the counter
      count -= 1;
    }

    return result;
  }
}
