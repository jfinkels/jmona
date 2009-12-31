/**
 * GrowTreeFactoryTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.InitializationException;
import jmona.gp.Node;
import jmona.gp.TerminalNode;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleFunctionNodeFactory;
import jmona.gp.impl.example.ExampleTerminalNodeFactory;
import jmona.impl.Range;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GrowTreeFactory class.
 * 
 * @author Jeffrey Finkelstein
 */
public class GrowTreeFactoryTester {

  /** The maximum depth of a created Tree. */
  public static final int DEPTH = 10;
  /** The number of tests to run. */
  public static final int NUM_TESTS = 1000;
  /** The factory under test. */
  private GrowTreeFactory<Integer> factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new GrowTreeFactory<Integer>();
    this.factory.setFunctionNodeFactory(new ExampleFunctionNodeFactory());
    this.factory.setTerminalNodeFactory(new ExampleTerminalNodeFactory());
  }

  /**
   * Test method for {@link jmona.gp.impl.GrowTreeFactory#createTree(int)}.
   */
  @Test
  public void testCreateTree() {
    this.factory.setMaxDepth(DEPTH);

    Tree<Integer> tree = null;
    try {
      tree = this.factory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    final int numNodes = Util.countNodes(tree);
    final int depth = this.factory.maxDepth();

    assertTrue(numNodes <= Math.pow(2, depth) - 1);

    // instantiate a list to hold all the nodes in this tree
    final List<Node<Integer>> allNodes = new Vector<Node<Integer>>();

    // add the root to the list
    allNodes.add(tree.root());

    // initialize the pointer representing the current node being examined
    int i = 0;

    // iterate over all nodes until each node has been examined
    List<Node<Integer>> children = null;
    Node<Integer> currentNode = null;
    Node<Integer> parent = null;
    int parentIndex = 0;
    while (i < allNodes.size()) {
      // get the current Node
      currentNode = allNodes.get(i);

      // if the current Node is not the root
      if (i > 0) {
        parent = currentNode.parent();
        assertNotNull(parent);
        assertTrue(allNodes.contains(parent));
        parentIndex = allNodes.indexOf(parent);
        assertTrue(parentIndex < i);
      }

      // get the children of the current node
      children = currentNode.children();

      // add this check for possible problematic Node.children() return values
      if (children != null && children.size() > 0) {
        for (final Node<Integer> child : children) {
          assertNotNull(child);
          assertFalse(allNodes.contains(child));
        }

        // add the children to the list
        allNodes.addAll(children);
      }

      // increment the number of nodes examined
      i += 1;
    }

    for (final Node<Integer> node : allNodes) {
      assertEquals(allNodes.indexOf(node), allNodes.lastIndexOf(node));
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GrowTreeFactory#setProbabilityTerminal(double)}.
   */
  @Test
  public void testSetProbabilityTerminal() {
    this.factory.setProbabilityTerminal(1);
    try {
      Tree<Integer> tree = null;
      for (final int i : new Range(NUM_TESTS)) {
        tree = this.factory.createObject();
        assertNull(tree.root().children());
        assertTrue(tree.root() instanceof TerminalNode<?>);
        assertEquals(1, Util.countNodes(tree));
      }

      this.factory.setProbabilityTerminal(0);

      final double epsilon = 0;

      for (final int i : new Range(NUM_TESTS)) {
        tree = this.factory.createObject();

        assertEquals(Math.pow(2, this.factory.maxDepth()) - 1, Util
            .countNodes(tree), epsilon);
      }
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }
  }

}
