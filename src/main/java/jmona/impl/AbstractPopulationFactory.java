/**
 * AbstractPopulationFactory.java
 */
package jmona.impl;

import jmona.Individual;
import jmona.IndividualFactory;
import jmona.PopulationFactory;

/**
 * A base class for PopulationFactory objects.
 * 
 * @param <T>
 *          The type of Individual in the population to create.
 * @author jeff
 */
public abstract class AbstractPopulationFactory<T extends Individual>
    implements PopulationFactory<T> {

  /** The default initial size of the population. */
  public static final int DEFAULT_SIZE = 32;
  /** The factory for creating individuals of this population. */
  private IndividualFactory<T> individualFactory = null;
  /** The size of the created population. */
  private int size = DEFAULT_SIZE;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  public IndividualFactory<T> individualFactory() {
    return this.individualFactory;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newIndividualFactory
   *          {@inheritDoc}
   * @see jmona.PopulationFactory#setIndividualFactory(jmona.IndividualFactory)
   */
  @Override
  public void setIndividualFactory(
      final IndividualFactory<T> newIndividualFactory) {
    this.individualFactory = newIndividualFactory;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newSize
   *          {@inheritDoc}
   */
  public void setSize(final int newSize) {
    this.size = newSize;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  public int size() {
    return this.size;
  }

}
