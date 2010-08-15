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

  public AbstractListFactory(final int initialSize) {
    super(initialSize);
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

  /** The factory which creates elements of type E. */
  private Factory<E> elementFactory = null;

  /**
   * Sets the factory which creates elements contained in a created List.
   * 
   * @param newElementFactory
   *          The factory which creates elements contained in a created List.
   */
  public void setElementFactory(final Factory<E> newElementFactory) {
    this.elementFactory = newElementFactory;
  }

  public Factory<E> elementFactory() {
    return this.elementFactory;
  }

}
