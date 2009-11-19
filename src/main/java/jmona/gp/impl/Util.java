/**
 * Util.java
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

import jmona.gp.Node;
import jmona.gp.Tree;

/**
 * A utility class for this package containing a method which swaps Nodes within
 * two Trees.
 * 
 * @author jfinkels
 */
public class Util {

  /**
   * Replace the specified existing Node in the specified Tree with the
   * specified new Node.
   * 
   * Currently, no check is performed to check whether the specified existing
   * Node is actually in the specified Tree, so something crazy could happen if
   * this were true.
   * 
   * @param <V>
   *          The type of value to which each Node and the Tree evaluates.
   * @param tree
   *          The Tree containing the existing Node.
   * @param existingNode
   *          A Node in the specified Tree to swap out.
   * @param newNode
   *          The new Node which will be inserted where the specified existing
   *          Node used to be.
   */
  public static <V> void replaceNode(final Tree<V> tree,
      final Node<V> existingNode, final Node<V> newNode) {

    // get the parent of the existing Node
    final Node<V> existingParent = existingNode.parent();

    // if the existing Node is the root of the tree, just set the new root
    if (tree.root().equals(existingNode)) {
      tree.setRoot(newNode);
    } else {

      // TODO got a NullPointerException here from a mutation

      // get the siblings of the existing Node
      final List<Node<V>> siblings = existingParent.children();

      // iterate over each sibling
      for (int i = 0; i < siblings.size(); ++i) {

        // if the current Node is the existing Node
        if (siblings.get(i).equals(existingNode)) {

          // remove the existing Node from the list of siblings
          siblings.remove(i);

          // add the new Node where the existing Node used to be
          siblings.add(i, newNode);
        }
      }
    }

    // set the parent of the new Node to the parent of the existing Node,
    // possibly null if the existing Node was the root of the Tree.
    newNode.setParent(existingParent);
  }

  /**
   * Swap the specified Node from the left Tree with the specified Node from the
   * right Tree.
   * 
   * @param <V>
   *          The type of value to which each Node and each Tree evaluates.
   * @param leftTree
   *          The left Tree.
   * @param leftNode
   *          A Node to swap from the left Tree to the right Tree.
   * @param rightTree
   *          The right Tree.
   * @param rightNode
   *          A Node to swap from the right Tree to the left Tree.
   */
  public static <V> void swapNodes(final Tree<V> leftTree,
      final Node<V> leftNode, final Tree<V> rightTree, final Node<V> rightNode) {

    // get the parent of each of those Nodes
    final Node<V> leftParent = leftNode.parent();
    final Node<V> rightParent = rightNode.parent();

    List<Node<V>> siblings = null;

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
        if (siblings.get(i).equals(rightNode)) {

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

  /** Instantiation disallowed. */
  protected Util() {
    // intentionally unimplemented
  }
}
