/**
 * PartialDeepCopyableListFactory.java
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

import jmona.DeepCopyableList;
import jmona.Factory;
import jmona.InitializationException;
import jmona.ListFactory;
import jmona.functional.Range;

/**
 * A ListFactory which aggregates objects created from a Factory which creates
 * objects of type E.
 * 
 * This factory creates a List which can be deep copied, but the elements
 * contained in the List will not be deep copied (that is, only their references
 * will be copied because they are assumed to be immutable).
 * 
 * @param <E>
 *          The type of element in the List to create.
 * @author Jeffrey Finkelstein
 */
public class PartialDeepCopyableListFactory<E> implements ListFactory<E> {
  /** The default size of the List to create. */
  public static final int DEFAULT_SIZE = 20;
  /** The factory which creates elements of type E. */
  private Factory<E> elementFactory = null;
  /** The size of the List to create. */
  private int size = DEFAULT_SIZE;

  /**
   * Create a List of elements generated by the {@link #elementFactory}
   * property.
   * 
   * @return A List of elements generated by the {@link #elementFactory}
   *         property.
   * @throws InitializationException
   *           If an Exception is thrown when generating an element.
   * @see jmona.ListFactory#createObject()
   */
  @Override
  public DeepCopyableList<E> createObject() throws InitializationException {
    if (this.elementFactory() == null) {
      throw new InitializationException("Element factory has not been set.");
    }

    final DeepCopyableList<E> result = new PartialDeepCopyableVector<E>();

    for (final int i : new Range(this.size)) {
      result.add(this.elementFactory().createObject());
    }

    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public Factory<E> elementFactory() {
    return this.elementFactory;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newElementFactory
   *          {@inheritDoc}
   */
  @Override
  public void setElementFactory(final Factory<E> newElementFactory) {
    this.elementFactory = newElementFactory;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newSize
   *          {@inheritDoc}
   */
  @Override
  public void setSize(final int newSize) {
    this.size = newSize;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public int size() {
    return this.size;
  }

}
