/**
 * AbstractTreeFactoryTester.java
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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jmona.Factory;
import jmona.InitializationException;
import jmona.gp.EvaluationException;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.TerminalNode;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleFunctionNodeFactory;
import jmona.gp.impl.example.ExampleTerminalNodeFactory;
import jmona.gp.impl.example.ExampleTreeFactory;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractTreeFactory class.
 * 
 * @author jfinkels
 */
public class AbstractTreeFactoryTester {
  /** The depth of a created tree. */
  public static final int DEPTH = 3;
  /** The number of times to create an Individual. */
  public static final int NUM_TESTS = 100;
  /** The AbstractTreeFactory under test. */
  private AbstractTreeFactory<Integer> factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new ExampleTreeFactory();
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.AbstractTreeFactory#createIndividual()}.
   */
  @Test
  public void testCreateIndividual() {
    try {

      Tree<Integer> individual = null;
      for (int i = 0; i < NUM_TESTS; ++i) {
        individual = this.factory.createObject();
      }

      assertTrue(individual instanceof DefaultTree<?>);
      assertTrue(individual.evaluate() instanceof Integer);

    } catch (final EvaluationException exception) {
      Util.fail(exception);
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.AbstractTreeFactory#createTree(int)}.
   */
  @Test
  public void testCreateTree() {

    try {
      this.factory.createTree(0);
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    Node<Integer> node = null;
    try {
      node = this.factory.createTree(1);
      assertNull(node.children());
      assertTrue(node instanceof TerminalNode<?>);
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    try {
      node = this.factory.createTree(2);
      assertEquals(2, node.children().size());
      assertEquals(node.arity(), node.children().size());

      assertNull(node.children().get(0).children());
      assertTrue(node.children().get(0) instanceof TerminalNode<?>);

      assertNull(node.children().get(1).children());
      assertTrue(node.children().get(1) instanceof TerminalNode<?>);

    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    try {
      node = this.factory.createTree(DEPTH);
      assertEquals(2, node.children().size());
      assertEquals(node.arity(), node.children().size());

      assertNotNull(node.children().get(0).children());
      assertEquals(node.children().get(0).arity(), node.children().get(0)
          .children().size());
      assertFalse(node.children().get(0) instanceof TerminalNode<?>);
      assertTrue(node.children().get(0) instanceof FunctionNode<?>);

      assertNotNull(node.children().get(1).children());
      assertEquals(node.children().get(1).arity(), node.children().get(1)
          .children().size());
      assertFalse(node.children().get(1) instanceof TerminalNode<?>);
      assertTrue(node.children().get(1) instanceof FunctionNode<?>);

      final Tree<Integer> tree = new DefaultTree<Integer>(node);
      for (final Node<Integer> treeNode : Util.allNodes(tree)) {
        if (treeNode.equals(tree.root())) {
          assertNull(treeNode.parent());
        } else if (treeNode instanceof TerminalNode<?>) {
          assertTrue(treeNode.children() == null
              || treeNode.children().size() == 0);
        }
      }

    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

  }

  /**
   * Test method for
   * {@link jmona.gp.impl.AbstractTreeFactory#functionNodeFactory()}.
   */
  @Test
  public void testFunctionNodeFactory() {
    assertNull(this.factory.functionNodeFactory());

    this.factory.setFunctionNodeFactory(new ExampleFunctionNodeFactory());
  }

  /**
   * Test method for {@link jmona.gp.impl.AbstractTreeFactory#maxDepth()}.
   */
  @Test
  public void testMaxDepth() {
    assertEquals(AbstractTreeFactory.DEFAULT_MAX_DEPTH, this.factory.maxDepth());

    final int newMaxDepth = 10;

    this.factory.setMaxDepth(newMaxDepth);
    assertEquals(newMaxDepth, this.factory.maxDepth());
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.AbstractTreeFactory#setFunctionNodeFactory(jmona.gp.FunctionNodeFactory)}
   * .
   */
  @Test
  public void testSetFunctionNodeFactory() {
    assertNull(this.factory.functionNodeFactory());
    final Factory<FunctionNode<Integer>> nodeFactory = new ExampleFunctionNodeFactory();
    this.factory.setFunctionNodeFactory(nodeFactory);
    assertSame(nodeFactory, this.factory.functionNodeFactory());
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.AbstractTreeFactory#setTerminalNodeFactory(jmona.gp.TerminalNodeFactory)}
   * .
   */
  @Test
  public void testSetTerminalNodeFactory() {
    assertNull(this.factory.terminalNodeFactory());
    final Factory<TerminalNode<Integer>> nodeFactory = new ExampleTerminalNodeFactory();
    this.factory.setTerminalNodeFactory(nodeFactory);
    assertSame(nodeFactory, this.factory.terminalNodeFactory());
  }

  /** Test for a factory method which throws an Exception. */
  @Test
  public void testThrownException() {
    final AbstractTreeFactory<Integer> badFactory = new AbstractTreeFactory<Integer>() {
      /**
       * Always throws an InitializationException.
       * 
       * @param currentDepth
       *          This parameter is ignored.
       * @return Never returns anything.
       * @throws InitializationException
       *           Always throws this Exception.
       */
      @Override
      protected Node<Integer> createTree(final int currentDepth)
          throws InitializationException {
        throw new InitializationException();
      }
    };

    try {
      badFactory.createObject();
      Util.shouldHaveThrownException();
    } catch (final InitializationException exception) {
      assertTrue(exception instanceof InitializationException);
    }

  }
}
