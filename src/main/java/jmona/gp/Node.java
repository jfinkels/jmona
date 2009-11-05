/**
 * Node.java
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

import java.util.List;

/**
 * A Node which can be recursively evaluated.
 * 
 * @param <V>
 *          The return type of the {@link #evaluate(Object...)} method on this
 *          Node.
 * @author jfinkels
 */
public interface Node<V> {
  /**
   * Get a List of the children of this Node. The size of this List must be
   * exactly equal to the "arity" of this Node.
   * 
   * @return The children of this Node.
   */
  List<Node<V>> children();

  /**
   * Evaluate this Node with respect to its child Nodes.
   * 
   * @param input
   *          An array of input objects.
   * @return The overall evaluation of this Node with respect to its child
   *         Nodes.
   * @throws EvaluationException
   *           If there is a problem evaluating this Node (or one of its
   *           children).
   */
  V evaluate(final Object... input) throws EvaluationException;

  /**
   * Get the "arity" of this Node, that is, how many children it has.
   * 
   * @return The "arity" of this Node.
   */
  int getArity();

  /**
   * Get the parent Node of this Node.
   * 
   * @return The parent Node of this Node.
   */
  Node<V> parent();

  /**
   * Replace this Node with the specified new Node by replacing it in the parent
   * Node's Set of child Nodes.
   * 
   * @param newNode
   *          The Node with which to replace this one.
   */
  void replaceThisNodeWith(final Node<V> newNode);

  /**
   * Replace this Node with the specified new subtree by replacing it in the
   * parent Node's Set of child Nodes.
   * 
   * @param newTree
   *          The subtree with which to replace this Node.
   */
  void replaceThisNodeWith(final Tree<V> newTree);

  /**
   * Set the parent of this Node.
   * 
   * @param newParent
   *          The parent of this Node.
   */
  void setParent(final Node<V> newParent);

  /**
   * Swap this Node with the specified other Node.
   * 
   * @param otherNode
   *          The Node with which to swap.
   */
  void swapWith(final Node<V> otherNode);
}
