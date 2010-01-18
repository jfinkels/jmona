/**
 * UnmodifiableCollectionAggregator.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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

import java.util.Collection;
import java.util.Collections;

/**
 * A class which aggregates a Collection of elements.
 * 
 * This class implements the {@link #collection()} method to provide an
 * unmodifiable view of the Collection which this class aggregates.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element which this class aggregates.
 * @since 0.5
 */
public class UnmodifiableCollectionAggregator<E> implements Aggregator<E> {
  /** The collection of elements aggregated by this class. */
  private final Collection<E> collection;

  /**
   * Instantiates this class with the specified Collection of elements.
   * 
   * @param initialCollection
   *          A Collection of elements.
   */
  public UnmodifiableCollectionAggregator(final Collection<E> initialCollection) {
    this.collection = initialCollection;
  }

  /**
   * Gets an unmodifiable view of the Collection which this class aggregates.
   * 
   * @return An unmodifiable view of the Collection which this class aggregates.
   */
  @Override
  public Collection<E> collection() {
    return Collections.unmodifiableCollection(this.collection);
  }

}
