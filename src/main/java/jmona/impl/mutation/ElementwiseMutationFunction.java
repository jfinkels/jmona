/**
 * ElementwiseMutationFunction.java
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
package jmona.impl.mutation;

import java.util.List;

import jmona.MutationFunction;

/**
 * A class which mutates elements in a List by mutating one element at random.
 * 
 * Concrete subclasses of this class should check if the
 * {@link #elementMutationFunction} member is null before attempting to
 * dereference it.
 * 
 * @author Jeffrey Finkelstein
 * @param <E>
 *          The type of element contained in the List to mutate.
 * @since 0.1
 */
public abstract class ElementwiseMutationFunction<E, L extends List<E>>
    implements ListMutationFunction<E, L> {

  /** The mutation function on elements of the List. */
  private MutationFunction<E> elementMutationFunction = null;

  /**
   * Sets the mutation function on elements of the List.
   * 
   * @param newFunction
   *          The mutation function on elements of the List.
   */
  public void setElementMutationFunction(final MutationFunction<E> newFunction) {
    this.elementMutationFunction = newFunction;
  }

  /**
   * Gets the mutation function on elements of the List.
   * 
   * @return The mutation function on elements of the List.
   */
  protected MutationFunction<E> elementMutationFunction() {
    return this.elementMutationFunction;
  }
}
