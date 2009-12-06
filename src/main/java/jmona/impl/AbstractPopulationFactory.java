/**
 * AbstractPopulationFactory.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
 * 
 * This file is part of jmona.
 * 
 * jmona is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * jmona is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * jmona. If not, see <http://www.gnu.org/licenses/>.
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
 * @author jfinkels
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
