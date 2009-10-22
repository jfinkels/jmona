/**
 * MaxSelectionFunction.java
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
