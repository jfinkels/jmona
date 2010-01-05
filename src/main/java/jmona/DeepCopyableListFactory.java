/**
 * ListFactory.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
 * Create a new List containing elements of type E.
 * 
 * @param <E>
 *          The type of element in the List to create.
 * @author Jeffrey Finkelstein
 */
public interface DeepCopyableListFactory<E extends DeepCopyable<E>> extends
    Factory<List<E>> {
  /**
   * Set the factory which creates objects of type E.
   * 
   * @param newElementFactory
   *          The factory which creates objects of type E.
   */
  void setElementFactory(final Factory<E> newElementFactory);

  /**
   * Set the size of the List to create.
   * 
   * @param newSize
   *          The size of the List to create.
   */
  void setSize(final int newSize);
}
