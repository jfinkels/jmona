/**
 * MaxSelectionFunction.java
 */
package jmona.impl;

import java.util.Iterator;
import java.util.NavigableSet;

import jmona.Individual;

/**
 * @param <T>
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
