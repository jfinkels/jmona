/**
 * MaxSelectionFunction.java
 */
package jmona.impl;

import java.util.Iterator;
import java.util.NavigableSet;

import jmona.Individual;

/**
 * A class which provides ascending iterator access to a specified set of
 * Individuals.
 * 
 * @param <T>
 *          The type of Individual over which to iterate.
 * @author jeff
 */
public class MinSelectionFunction<T extends Individual> extends
    AbstractSelectionFunction<T> {

  /**
   * Get an ascending iterator over the specified set of individuals.
   * 
   * @param individuals
   *          The set of individuals over which to get an iterator.
   * @return An ascending iterator over the specified set of individuals.
   * @see jmona.impl.AbstractSelectionFunction#getIterator(java.util.NavigableSet)
   */
  @Override
  protected Iterator<T> getIterator(final NavigableSet<T> individuals) {
    return individuals.iterator();
  }

}
