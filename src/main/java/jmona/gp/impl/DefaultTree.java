/**
 * DefaultTree.java
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
package jmona.gp.impl;

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.gp.EvaluationException;
import jmona.gp.Node;
import jmona.gp.Tree;

/**
 * A default implementation of a Tree.
 * 
 * @param <V>
 *          The type of value to which this Tree evaluates.
 * @author Jeffrey Finkelstein
 */
public class DefaultTree<V> implements Tree<V> {

  /** The root Node of this Tree. */
  private Node<V> root = null;

  /**
   * Instantiate this Tree with the specified root Node.
   * 
   * @param initialRoot
   *          The root Node of this Tree.
   */
  public DefaultTree(final Node<V> initialRoot) {
    this.root = initialRoot;
  }

  /**
   * Get a List of all Nodes in this tree.
   * 
   * Returns an empty List if the tree has no root.
   * 
   * @return A List of all Nodes in this tree.
   */
  protected List<Node<V>> allNodes() {
    // instantiate a list to hold all the nodes in this tree
    final List<Node<V>> result = new Vector<Node<V>>();

    // if the root is null, return an empty list
    if (this.root != null) {
      // add the root to the list
      result.add(this.root);

      // initialize the pointer representing the current node being examined
      int i = 0;

      // iterate over all nodes until each node has been examined
      List<Node<V>> children = null;
      while (i < result.size()) {
        // get the children of the current node
        children = result.get(i).children();

        // add this check for possible problematic Node.children() return values
        if (children != null && children.size() > 0) {
          // add the children to the list
          result.addAll(children);
        }

        // increment the number of nodes examined
        i += 1;
      }
    }

    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws CopyingException
   *           {@inheritDoc}
   * @see jmona.gp.Tree#deepCopy()
   */
  public Tree<V> deepCopy() throws CopyingException {
    return new DefaultTree<V>(this.root.deepCopy());
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Tree#evaluate()
   */
  @Override
  public V evaluate() throws EvaluationException {
    return this.root.evaluate();
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws IllegalArgumentException
   *           If this Tree is empty.
   * @see jmona.gp.Tree#randomNode()
   */
  @Override
  public Node<V> randomNode() {
    // get all the nodes in this tree
    final List<Node<V>> allNodes = this.allNodes();

    // get a random node from the list of all nodes in the tree
    return allNodes.get(jmona.impl.Util.RANDOM.nextInt(allNodes.size()));
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Tree#root()
   */
  @Override
  public Node<V> root() {
    return this.root;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newRoot
   *          {@inheritDoc}
   * @see jmona.gp.Tree#setRoot(Node)
   */
  @Override
  public void setRoot(final Node<V> newRoot) {
    this.root = newRoot;
  }

  /**
   * Return the String representation of this Tree.
   * 
   * @return The String representation of this Tree.
   */
  @Override
  public String toString() {
    return "Tree[" + this.root + "]";
  }
}
