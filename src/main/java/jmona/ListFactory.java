/**
 * ListFactory.java
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
package jmona;

import java.util.List;

/**
 * A factory for a List containing elements of type E, each generated by a
 * factory for object of that type.
 * 
 * @param <E>
 *          The type of element in the created List.
 * @author Jeffrey Finkelstein
 */
public interface ListFactory<E> extends Factory<List<E>> {
  /**
   * Get the factory which creates elements contained in a created List.
   * 
   * @return The factory which creates elements contained in a created List.
   */
  Factory<E> elementFactory();

  /**
   * Set the factory which creates elements contained in a created List.
   * 
   * @param newElementFactory
   *          The factory which creates elements contained in a created List.
   */
  void setElementFactory(final Factory<E> newElementFactory);

  /**
   * Set the size of the List to create.
   * 
   * @param newSize
   *          The size of the List to create.
   */
  void setSize(final int newSize);

  /**
   * Get the size of the List to create.
   * 
   * @return The size of the List to create.
   */
  int size();
}
