/**
 * SizedFactory.java
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
 * A factory which creates an object with a size, like a List.
 * 
 * @param <E>
 *          The type of object which this factory creates.
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public abstract class SizedFactory<E> implements Factory<E> {

  /** The size of the object which this factory creates. */
  private final int size;

  /**
   * Instantiates this factory to create objects of the specified size.
   * 
   * @param initialSize
   *          The size of the object to create.
   */
  public SizedFactory(final int initialSize) {
    this.size = initialSize;
  }

  /**
   * Gets the size of the object to create.
   * 
   * @return The size of the object to create.
   */
  protected int size() {
    return this.size;
  }

}
