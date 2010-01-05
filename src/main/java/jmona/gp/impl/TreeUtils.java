/**
 * TreeUtils.java
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

import jmona.CopyingException;
import jmona.functional.Range;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.impl.Util;

/**
 * A utility class for this package containing a method which swaps Nodes within
 * two Trees.
 * 
 * @author Jeffrey Finkelstein
 */
public class TreeUtils {

  /**
   * Attach the specified child Node to the specified parent Node by setting the
   * child's parent to the specified parent Node and by adding the child Node to
   * the specified parent's List of child Nodes.
   * 
   * @param parent
   *          The parent Node.
   * @param child
   *          The child Node.
   */
  public static void attachChildToParent(final FunctionNode parent,
      final Node child) {
    parent.children().add(child);
    child.setParent(parent);
  }

  /**
   * Helper method for performing a deep copy on the specified List of children
   * Nodes, and attaching them to the specified parent.
   * 
   * @param clonedParent
   *          The parent of the specified children.
   * @param childrenToCopy
   *          The List of children to copy and attach to the specified parent
   *          Node.
   * @throws CopyingException
   *           If there is a problem copying one of the children.
   */
  public static void deepCopyChildren(final FunctionNode clonedParent,
      final List<Node> childrenToCopy) throws CopyingException {

    // copy each child node
    final List<Node> copiedChildren = Util.deepCopy(childrenToCopy);

    // iterate over each cloned child Node
    for (final Node clonedChild : copiedChildren) {

      // attach the cloned child to the cloned parent
      attachChildToParent(clonedParent, clonedChild);
    }
  }

  /**
   * Replace the specified existing Node in the specified Tree with the
   * specified new Node.
   * 
   * Currently, no check is performed to check whether the specified existing
   * Node is actually in the specified Tree, so something crazy could happen if
   * this were true.
   * 
   * @param tree
   *          The Tree containing the existing Node.
   * @param existingNode
   *          A Node in the specified Tree to swap out.
   * @param newNode
   *          The new Node which will be inserted where the specified existing
   *          Node used to be.
   */
  public static void replaceNode(final Tree tree, final Node existingNode,
      final Node newNode) {

    // get the parent of the existing Node
    final Node existingParent = existingNode.parent();

    // if the existing Node is the root of the tree, just set the new root
    if (tree.root().equals(existingNode)) {
      tree.setRoot(newNode);
    } else {

      // get the siblings of the existing Node
      final List<Node> siblings = existingParent.children();

      // iterate over each sibling
      for (final int i : new Range(siblings.size())) {

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
   * @param leftTree
   *          The left Tree.
   * @param leftNode
   *          A Node to swap from the left Tree to the right Tree.
   * @param rightTree
   *          The right Tree.
   * @param rightNode
   *          A Node to swap from the right Tree to the left Tree.
   */
  public static void swapNodes(final Tree leftTree, final Node leftNode,
      final Tree rightTree, final Node rightNode) {

    // get the parent of each of those Nodes
    final Node leftParent = leftNode.parent();
    final Node rightParent = rightNode.parent();

    List<Node> siblings = null;

    // if the Node from the left tree is the root, set the root to rightNode
    if (leftNode.equals(leftTree.root())) {
      leftTree.setRoot(rightNode);
    } else { // leftNode is not the root of this Tree

      // get the siblings of leftNode
      siblings = leftParent.children();

      // iterate over each sibling
      for (final int i : new Range(siblings.size())) {

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
      for (final int i : new Range(siblings.size())) {

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
  protected TreeUtils() {
    // intentionally unimplemented
  }
}
