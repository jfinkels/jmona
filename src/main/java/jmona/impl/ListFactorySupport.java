/**
 * DefaultListFactory.java
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

import jmona.Factory;

/**
 * A class which has access to a factory of type E, and which has a size
 * property.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element in the List to create.
 * @since 0.5
 */
public abstract class ListFactorySupport<E> {
  /** The default size of the List to create. */
  public static final int DEFAULT_SIZE = 20;
  /** The factory which creates elements of type E. */
  private Factory<E> elementFactory = null;
  /** The size of the List to create. */
  private int size = DEFAULT_SIZE;

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
   * @throws NullPointerException
   *           If any of the necessary properties have not been set.
   */
  protected void sanityCheck() {
    if (this.elementFactory() == null) {
      throw new NullPointerException(Messages.getString("ListFactorySupport.0")); //$NON-NLS-1$
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

  /**
   * Sets the size of the List to create.
   * 
   * @param newSize
   *          The size of the List to create.
   */
  public void setSize(final int newSize) {
    this.size = newSize;
  }

  /**
   * Gets the size of the List to create.
   * 
   * @return The size of the List to create.
   */
  public int size() {
    return this.size;
  }

}
