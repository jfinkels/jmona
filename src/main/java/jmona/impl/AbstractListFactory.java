/**
 * AbstractListFactory.java
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

import java.util.List;

import jmona.Factory;
import jmona.PropertyNotSetException;

/**
 * A base class for a Factory which creates List objects.
 * 
 * Concrete implementing subclasses should use the Factory returned by the
 * {@link #elementFactory()} method to create objects to populate the List
 * created in the {@link #createObject()} method.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public abstract class AbstractListFactory<E, L extends List<E>> extends
    SizedFactory<L> {

  /** The factory which creates elements of type E. */
  private Factory<E> elementFactory = null;

  /**
   * Instantiates this factory to create Lists of the specified size.
   * 
   * @param initialSize
   *          The size of the List to create.
   */
  public AbstractListFactory(final int initialSize) {
    super(initialSize);
  }

  /**
   * Gets the factory which creates elements contained in a created List.
   * 
   * @return The factory which creates elements contained in a created List.
   */
  public Factory<E> elementFactory() {
    return this.elementFactory;
  }

  /**
   * Checks that all necessary properties have been set.
   * 
   * The only necessary property for this class is the element factory.
   * 
   * @throws PropertyNotSetException
   *           If any of the necessary properties have not been set.
   */
  protected void sanityCheck() {
    if (this.elementFactory == null) {
      throw new PropertyNotSetException("Element factory has not been set.");
    }
  }

  /**
   * Sets the factory which creates elements contained in a created List.
   * 
   * @param newElementFactory
   *          The factory which creates elements contained in a created List.
   */
  public void setElementFactory(final Factory<E> newElementFactory) {
    this.elementFactory = newElementFactory;
  }

}
