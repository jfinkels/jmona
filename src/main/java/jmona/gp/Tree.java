/**
 * Tree.java
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
package jmona.gp;

import jmona.Individual;

/**
 * A Tree of Nodes, which is also an Individual.
 * 
 * @param <V>
 *          The return type of the {@link #evaluate()} method on this Tree.
 * @author jfinkels
 */
public interface Tree<V> extends Individual {
  /**
   * Evaluate this tree by recursively evaluating each Node starting from the
   * root.
   * 
   * This method is equivalent to calling {@link Node#evaluate()} on the result
   * of the {@link Tree#root()} method.
   * 
   * @return The overall evaluation of this tree by recursively evaluating each
   *         Node starting from the root.
   * @throws EvaluationException
   *           If there is a problem evaluating the Nodes of this Tree.
   */
  V evaluate() throws EvaluationException;

  /**
   * Get a random Node in the Tree.
   * 
   * @return A random Node in the Tree.
   */
  Node<V> randomNode();

  /**
   * Get the root Node of this Tree.
   * 
   * @return The root Node of this Tree.
   */
  Node<V> root();

  /**
   * Set the root Node of this Tree.
   * 
   * @param newRoot
   *          The root Node of this Tree.
   */
  void setRoot(final Node<V> newRoot);

  /**
   * Swap a random Node in this tree with a random Node in the specified other
   * Tree.
   * 
   * @param otherTree
   *          The target Tree with which to swap a random Node.
   */
  void swapRandomNodesWith(final Tree<V> otherTree);

  /**
   * Swap a random Node in this Tree with the root of the specified other Tree.
   * 
   * @param otherTree
   *          The target Tree with which to swap a random Node in this Tree for
   *          its root.
   */
  void swapRandomNodeWithRootOf(final Tree<V> otherTree);
}
