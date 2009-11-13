/**
 * GPMutationFunctionTester.java
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import jmona.MutationException;
import jmona.gp.EvaluationException;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.ExampleTerminalNode;
import jmona.gp.impl.example.ExampleTreeFactory;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GPMutationFunction class.
 * 
 * @author jfinkels
 */
public class GPMutationFunctionTester {

  /** The GPMutationFunction under test. */
  private GPMutationFunction<Integer> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new GPMutationFunction<Integer>();
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPMutationFunction#setTreeFactory(jmona.gp.TreeFactory)}
   * .
   */
  @Test
  public void testSetTreeFactory() {
    final ExampleTreeFactory factory = new ExampleTreeFactory();

    try {
      factory.setMaxDepth(0);

      this.function.setTreeFactory(factory);

      final ExampleTerminalNode root = new ExampleTerminalNode();
      final Tree<Integer> tree = new DefaultTree<Integer>(root);

      this.function.mutate(tree);

      assertNotSame(root, tree.root());
      assertTrue(tree.root() instanceof ExampleTerminalNode);
      assertNotSame(root.evaluate(), tree.root().evaluate());

    } catch (final EvaluationException exception) {
      Util.fail(exception);
    } catch (final MutationException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPMutationFunction#mutate(jmona.gp.Tree)}.
   */
  @Test
  public void testMutate() {
    this.function.setTreeFactory(new ExampleTreeFactory());

    // create three nodes to put into a tree
    final Node<Integer> root = new ExampleBinaryNode();
    final Node<Integer> leftChild = new ExampleTerminalNode();
    final Node<Integer> rightChild = new ExampleTerminalNode();

    // add the child Nodes to the list of children of the root node
    root.children().add(leftChild);
    root.children().add(rightChild);

    // set the parent of each child Node to the root Node
    leftChild.setParent(root);
    rightChild.setParent(root);

    // instantiate a new tree with the given root Node
    final Tree<Integer> tree = new DefaultTree<Integer>(root);

    // mutate the Tree
    try {
      this.function.mutate(tree);
    } catch (final MutationException exception) {
      Util.fail(exception);
    }

    if (tree.root().equals(root)
        && tree.root().children().get(0).equals(leftChild)) {
      assertNotNull(tree.root().children().get(1));
      assertNotSame(rightChild, tree.root().children().get(1));
    } else if (tree.root().equals(root)
        && tree.root().children().get(1).equals(rightChild)) {
      assertNotNull(tree.root().children().get(0));
      assertNotSame(leftChild, tree.root().children().get(0));
    } else {
      assertNotSame(tree.root(), root);
    }

  }

}
