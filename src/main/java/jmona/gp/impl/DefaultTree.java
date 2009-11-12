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

import org.apache.log4j.Logger;

import jmona.gp.EvaluationException;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.impl.Util;

/**
 * A default implementation of a Tree.
 * 
 * @param <V>
 *          The type of value to which this Tree evaluates.
 * @author jfinkels
 */
public class DefaultTree<V> implements Tree<V> {

  /** The Logger for this class. */
  private static final Logger LOG = Logger.getLogger(DefaultTree.class);

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
   * Get all Nodes in this tree.
   * 
   * @return All Nodes in this tree.
   */
  protected List<Node<V>> allNodes() {
    if (this.root == null) {
      throw new NullPointerException("The root of this tree is null.");
    }

    // instantiate a list to hold all the nodes in this tree
    final List<Node<V>> result = new Vector<Node<V>>();

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

    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Tree#evaluate(java.lang.Object[])
   */
  @Override
  public V evaluate() throws EvaluationException {
    return this.root.evaluate();
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Tree#randomNode()
   */
  @Override
  public Node<V> randomNode() {
    // get all the nodes in this tree
    final List<Node<V>> allNodes = this.allNodes();

    // get a random node from the list of all nodes in the tree
    return allNodes.get(Util.RANDOM.nextInt(allNodes.size()));
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
   * Swap the specified Node from the left Tree with the specified Node from the
   * right Tree.
   * 
   * @param leftTree
   *          The left Tree.
   * @param leftNode
   *          A Node to swap from the left Tree to the right Tree.
   * @param rightTree
   *          The right Tree.
   * @param rightNode
   *          A Node to swap from the right Tree to the left Tree.
   */
  // TODO this should be static, except for the generic type arguments
  protected void swapNodes(final Tree<V> leftTree, final Node<V> leftNode,
      final Tree<V> rightTree, final Node<V> rightNode) {

    // get the parent of each of those Nodes
    final Node<V> leftParent = leftNode.parent();
    final Node<V> rightParent = rightNode.parent();

    List<Node<V>> siblings = null;

    // TODO this doesn't work; need to write test for TreeFactorys
    
    LOG.debug("left tree: " + leftTree);
    LOG.debug("left tree root: " + leftTree.root());
    LOG.debug("left node: " + leftNode);
    LOG.debug("left node parent: " + leftParent);
    
    // if the Node from this tree is the root, set this root to the rightNode
    if (leftNode.equals(leftTree.root())) {
      leftTree.setRoot(rightNode);
    } else { // leftNode is not the root of this Tree
      
      // get the siblings of leftNode
      siblings = leftParent.children();

      // iterate over each sibling
      for (int i = 0; i < siblings.size(); ++i) {

        // if the current Node is leftNode
        if (siblings.get(i).equals(leftNode)) {

          // remove leftNode from the list of children
          siblings.remove(i);

          // add the rightNode where leftNode used to be
          siblings.add(i, rightNode);
        }
      }
    }

    // if the rightNode is the root, set the right Tree's root to leftNode
    if (rightNode.equals(rightTree.root())) {
      rightTree.setRoot(leftNode);
    } else { // rightNode is not the root of the right Tree

      // get the siblings of the rightNode
      siblings = rightParent.children();

      // iterate over each sibling
      for (int i = 0; i < siblings.size(); ++i) {

        // if the current Node is the rightNode
        if (siblings.get(i).equals(leftNode)) {

          // remove the rightNode from the list of children
          siblings.remove(i);

          // add leftNode where the rightNode used to be
          siblings.add(i, leftNode);
        }
      }
    }

    // set the parents of each Node to the corresponding other Node's parent
    leftNode.setParent(rightParent);
    rightNode.setParent(leftParent);
  }

  /**
   * {@inheritDoc}
   * 
   * @param otherTree
   *          {@inheritDoc}
   * @see jmona.gp.Tree#swapNodesWith(jmona.gp.Tree)
   */
  @Override
  public void swapRandomNodesWith(final Tree<V> otherTree) {
    this.swapRandomWith(otherTree, otherTree.randomNode());
  }

  /**
   * {@inheritDoc}
   * 
   * @param otherTree
   *          {@inheritDoc}
   * @see jmona.gp.Tree#swapRandomNodeWithRootOf(jmona.gp.Tree)
   */
  @Override
  public void swapRandomNodeWithRootOf(final Tree<V> otherTree) {
    this.swapRandomWith(otherTree, otherTree.root());
  }

  /**
   * Swap a random Node in this Tree with the specified other Node from the
   * specified other Tree.
   * 
   * @param otherTree
   *          Another Tree.
   * @param otherNode
   *          The Node to swap with a random Node in this Tree.
   */
  protected void swapRandomWith(final Tree<V> otherTree, final Node<V> otherNode) {
    this.swapNodes(this, this.randomNode(), otherTree, otherNode);
  }

}
