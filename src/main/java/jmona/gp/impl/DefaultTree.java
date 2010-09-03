/**
 * DefaultTree.java
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
package jmona.gp.impl;

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.random.RandomUtils;

/**
 * A default implementation of a Tree.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class DefaultTree implements Tree {

  /** The root Node of this Tree. */
  private Node root = null;

  /**
   * Instantiate this Tree with the specified root Node.
   * 
   * @param initialRoot
   *          The root Node of this Tree.
   */
  public DefaultTree(final Node initialRoot) {
    this.root = initialRoot;
  }

  /**
   * Get a List of all Nodes in this tree.
   * 
   * Returns an empty List if the tree has no root.
   * 
   * @return A List of all Nodes in this tree.
   */
  protected List<Node> allNodes() {
    // instantiate a list to hold all the nodes in this tree
    final List<Node> result = new Vector<Node>();

    // if the root is null, return an empty list
    if (this.root != null) {
      // add the root to the list
      result.add(this.root);

      // initialize the pointer representing the current node being examined
      int i = 0;

      // iterate over all nodes until each node has been examined
      List<Node> children = null;
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
  @Override
  public Tree deepCopy() throws CopyingException {
    return new DefaultTree(this.root.deepCopy());
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
  public Node randomNode() {
    return RandomUtils.choice(this.allNodes());
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Tree#root()
   */
  @Override
  public Node root() {
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
  public void setRoot(final Node newRoot) {
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
