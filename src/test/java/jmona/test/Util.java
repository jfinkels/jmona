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
   * Print the stack trace of the specified exception and fail the test.
   * 
   * @param exception
   *          The exception which caused the test failure.
   */
  public static void fail(final Throwable exception) {
    exception.printStackTrace(System.err);
    org.junit.Assert.fail(exception.getMessage());
  }

  /**
   * Count the number of Nodes in the specified Tree.
   * 
   * @param tree
   *          A Tree.
   * @return The number of Nodes in the specified Tree.
   */
  @SuppressWarnings("unchecked")
  public static int countNodes(final Tree tree) {

    // instantiate a list to hold all the nodes in this tree
    final List<Node> result = new Vector<Node>();

    // add the root to the list
    result.add(tree.root());

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

    return result.size();
  }

  /** Instantiation disallowed except by subclasses. */
  protected Util() {
    // intentionally unimplemented
  }
}
