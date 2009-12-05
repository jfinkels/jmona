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
package jmona.test;

import java.util.List;
import java.util.Vector;

import jmona.gp.Node;
import jmona.gp.Tree;

/**
 * Utilities for testing, including a method which fails a test after outputting
 * an Exception's message.
 * 
 * @author jfinkels
 */
public class Util {
  /**
   * Get a List of all Nodes in the specified Tree.
   * 
   * @param <V>
   *          The type of value to which the specified Tree evaluates.
   * @param tree
   *          The tree whose Nodes will be returned.
   * @return A List of all Nodes in the specified Tree.
   */
  public static <V> List<Node<V>> allNodes(final Tree<V> tree) {

    // instantiate a list to hold all the nodes in this tree
    final List<Node<V>> result = new Vector<Node<V>>();

    if (tree.root() != null) {
      // add the root to the list
      result.add(tree.root());

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
   * Determine whether the two specified Lists contain the same elements in the
   * same order.
   * 
   * @param <E>
   *          The type of element contained in the Lists.
   * @param leftList
   *          A List.
   * @param rightList
   *          Another List.
   * @return Whether the two Lists contain the same elements in the same order.
   */
  public static <E> boolean areEqual(final List<E> leftList,
      final List<E> rightList) {

    // get the size of the left list
    final int leftListSize = leftList.size();

    // if the two lists do not have the same size, they cannot be equal
    if (leftListSize != rightList.size()) {
      return false;
    }

    // iterate over each element in the lists
    for (int i = 0; i < leftListSize; ++i) {

      // if the current element in the left list is not equal to the
      // corresponding element in the right list, the lists are not equal
      if (!leftList.get(i).equals(rightList.get(i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Count the number of Nodes in the specified Tree.
   * 
   * @param <V>
   *          The type of value to which the specified Tree evaluates.
   * @param tree
   *          A Tree.
   * @return The number of Nodes in the specified Tree.
   */
  public static <V> int countNodes(final Tree<V> tree) {

    // instantiate a list to hold all the nodes in this tree
    final List<Node<V>> result = new Vector<Node<V>>();

    // add the root to the list
    result.add(tree.root());

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

    return result.size();
  }

  /**
   * Print the stack trace of the specified exception and fail the test.
   * 
   * @param exception
   *          The exception which caused the test failure.
   */
  public static void fail(final Throwable exception) {
    exception.printStackTrace(System.err);
    org.junit.Assert.fail(exception.getMessage());
  }

  /** Instantiation disallowed except by subclasses. */
  protected Util() {
    // intentionally unimplemented
  }
}
