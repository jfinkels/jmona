/**
 * MaxSelectionFunction.java
 */
package jmona.impl;

import java.util.Iterator;
import java.util.NavigableSet;

import jmona.Individual;

/**
 * A selection function which chooses individuals with maximum fitness.
 * 
 * @param <T>
 *          The type of individual to select.
 * @author jeff
 */
public class MaxSelectionFunction<T extends Individual> extends
    AbstractSelectionFunction<T> {

  /**
   * Get a descending iterator over the specified set of individuals.
   * 
   * @param individuals
   *          The set of individuals over which to get an iterator.
   * @return A descending iterator over the specified set of individuals.
   * @see jmona.impl.AbstractSelectionFunction#getIterator(java.util.NavigableSet)
   */
  @Override
  protected Iterator<T> getIterator(final NavigableSet<T> individuals) {
    return individuals.descendingIterator();
  }

}
