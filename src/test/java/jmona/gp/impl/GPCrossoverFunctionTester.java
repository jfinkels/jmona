/**
 * GPCrossoverFunctionTester.java
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import jmona.InitializationException;
import jmona.functional.Range;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.ExampleTreeFactory;
import jmona.gp.impl.example.IntegerNode;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GPCrossoverFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class GPCrossoverFunctionTester {

  /** The number of tests to run. */
  public static final int NUM_TESTS = 1000;
  /** The function under test. */
  private GPCrossoverFunction function = null;
  /** The left child in the right Tree. */
  private Node leftChild = null;
  /** The right child in the right Tree. */
  private Node rightChild = null;
  /** The root of the left Tree. */
  private Node root1 = null;
  /** The root of the right Tree. */
  private Node root2 = null;
  /** The left Tree. */
  private Tree tree1 = null;
  /** The right Tree. */
  private Tree tree2 = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new GPCrossoverFunction();

    this.leftChild = new IntegerNode(2);
    this.rightChild = new IntegerNode(1);

    this.root1 = new ExampleBinaryNode();

    this.root1.children().add(this.leftChild);
    this.leftChild.setParent(this.root1);

    this.root1.children().add(this.rightChild);
    this.rightChild.setParent(this.root1);

    this.root2 = new IntegerNode(0);

    this.tree1 = new DefaultTree(this.root1);
    this.tree2 = new DefaultTree(this.root2);
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPCrossoverFunction#crossover(jmona.gp.Tree, jmona.gp.Tree)}
   * .
   */
  @Test
  public void testCrossover() {
    this.function.crossover(this.tree1, this.tree2);

    // the second tree must have changed its root, because it only had one node
    assertNotSame(this.tree2.root(), this.root2);
    assertTrue(this.tree2.root().equals(this.root1)
        || this.tree2.root().equals(this.leftChild)
        || this.tree2.root().equals(this.rightChild));

    if (this.tree2.root().equals(this.root1)) {
      assertSame(this.leftChild, this.tree2.root().children().get(0));
      assertSame(this.rightChild, this.tree2.root().children().get(1));
      assertSame(this.root2, this.tree1.root());
    } else if (this.tree2.root().equals(this.rightChild)) {
      assertSame(this.root1, this.tree1.root());
      assertSame(this.leftChild, this.tree1.root().children().get(0));
      assertSame(this.root2, this.tree1.root().children().get(1));
    } else if (this.tree2.root().equals(this.leftChild)) {
      assertSame(this.root1, this.tree1.root());
      assertSame(this.rightChild, this.tree1.root().children().get(1));
      assertSame(this.root2, this.tree1.root().children().get(0));
    }
  }

  /**
   * Test method for performing many crossovers on two random trees.
   */
  @Test
  public void testManyCrossoversOnRandomTrees() {
    // initialize a tree factory
    final ExampleTreeFactory factory = new ExampleTreeFactory();

    final int numRandomPairs = 3;

    for (final int i : new Range(numRandomPairs)) {
      try {
        // initialize two random trees
        final Tree leftTree = factory.createObject();
        final Tree rightTree = factory.createObject();

        // perform crossover on the two trees
        for (int j = 0; j < NUM_TESTS; ++j) {
          this.function.crossover(leftTree, rightTree);
        }

      } catch (final InitializationException exception) {
        Util.fail(exception);
      } catch (final NullPointerException exception) {
        Util.fail(exception);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPCrossoverFunction#crossover(Tree, Tree)} using
   * multiple crossovers on a single pair of Trees.
   */
  @Test
  public void testMultipleCrossoversSinglePair() {
    List<Node> preLeftNodes = Util.allNodes(this.tree1);
    List<Node> preRightNodes = Util.allNodes(this.tree2);

    List<Node> postLeftNodes = null;
    List<Node> postRightNodes = null;

    Node currentNode = null;
    for (final int i : new Range(NUM_TESTS)) {

      // perform the crossover
      this.function.crossover(this.tree1, this.tree2);

      // get the left nodes and right nodes after crossover
      postLeftNodes = Util.allNodes(this.tree1);
      postRightNodes = Util.allNodes(this.tree2);

      // if the roots switched
      if (preLeftNodes.get(0).equals(postRightNodes.get(0))) {
        assertTrue(Util.areEqual(postLeftNodes, preRightNodes));
        assertTrue(Util.areEqual(postRightNodes, preLeftNodes));
      }

      // iterate over each Node in the new left tree
      for (int j = 0; j < postLeftNodes.size(); ++j) {

        // get the current node
        currentNode = postLeftNodes.get(j);

        // assert that the current Node was in exactly one of the pre-Trees
        assertTrue(preLeftNodes.contains(currentNode)
            ^ preRightNodes.contains(currentNode));

        // if the current Node is a root
        if (currentNode.equals(this.tree1.root())
            || currentNode.equals(this.tree2.root())) {
          assertNull(currentNode.parent());
          if (currentNode.children() != null) {
            for (final Node child : currentNode.children()) {
              assertSame(currentNode, child.parent());
            }
          }
        } else {
          assertNotNull(currentNode.parent());
          assertTrue(currentNode.parent().equals(this.tree1.root())
              || currentNode.parent().equals(this.tree2.root()));
          assertNull(currentNode.children());
        }

      }
      // iterate over each Node in the new right tree
      for (int j = 0; j < postRightNodes.size(); ++j) {

        // get the current node
        currentNode = postRightNodes.get(j);

        // assert that the current Node was in exactly one of the pre-Trees
        assertTrue(preLeftNodes.contains(currentNode)
            ^ preRightNodes.contains(currentNode));

        // if the current Node is a root
        if (currentNode.equals(this.tree1.root())
            || currentNode.equals(this.tree2.root())) {
          assertNull(currentNode.parent());
          if (currentNode.children() != null) {
            for (final Node child : currentNode.children()) {
              assertSame(currentNode, child.parent());
            }
          }
        } else {
          assertNotNull(currentNode.parent());
          assertTrue(currentNode.parent().equals(this.tree1.root())
              || currentNode.parent().equals(this.tree2.root()));
          assertNull(currentNode.children());
        }
      }

      preLeftNodes = postLeftNodes;
      preRightNodes = postRightNodes;
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPCrossoverFunction#crossover(Tree, Tree)} using
   * crossover on a multiple pairs of Trees.
   */
  @Test
  public void testSingleCrossoverMultiplePairs() {
    List<Node> preLeftNodes = Util.allNodes(this.tree1);
    List<Node> preRightNodes = Util.allNodes(this.tree2);

    List<Node> postLeftNodes = null;
    List<Node> postRightNodes = null;

    Node currentNode = null;
    for (final int i : new Range(NUM_TESTS)) {
      this.setUp();

      preLeftNodes = Util.allNodes(this.tree1);
      preRightNodes = Util.allNodes(this.tree2);

      this.function.crossover(this.tree1, this.tree2);

      // get the left nodes and right nodes after crossover
      postLeftNodes = Util.allNodes(this.tree1);
      postRightNodes = Util.allNodes(this.tree2);

      // if the roots switched
      if (preLeftNodes.get(0).equals(postRightNodes.get(0))) {
        assertTrue(Util.areEqual(postLeftNodes, preRightNodes));
        assertTrue(Util.areEqual(postRightNodes, preLeftNodes));
      }

      // iterate over each Node in the new left tree
      for (int j = 0; j < postLeftNodes.size(); ++j) {

        // get the current node
        currentNode = postLeftNodes.get(j);

        // assert that the current Node was in exactly one of the pre-Trees
        assertTrue(preLeftNodes.contains(currentNode)
            ^ preRightNodes.contains(currentNode));

        // if the current Node is a root
        if (currentNode.equals(this.tree1.root())
            || currentNode.equals(this.tree2.root())) {
          assertNull(currentNode.parent());
          if (currentNode.children() != null) {
            for (final Node child : currentNode.children()) {
              assertSame(currentNode, child.parent());
            }
          }
        } else {
          assertNotNull(currentNode.parent());
          assertTrue(currentNode.parent().equals(this.tree1.root())
              || currentNode.parent().equals(this.tree2.root()));
          assertNull(currentNode.children());
        }

      }
      // iterate over each Node in the new right tree
      for (int j = 0; j < postRightNodes.size(); ++j) {

        // get the current node
        currentNode = postRightNodes.get(j);

        // assert that the current Node was in exactly one of the pre-Trees
        assertTrue(preLeftNodes.contains(currentNode)
            ^ preRightNodes.contains(currentNode));

        // if the current Node is a root
        if (currentNode.equals(this.tree1.root())
            || currentNode.equals(this.tree2.root())) {
          assertNull(currentNode.parent());
          if (currentNode.children() != null) {
            for (final Node child : currentNode.children()) {
              assertSame(currentNode, child.parent());
            }
          }
        } else {
          assertNotNull(currentNode.parent());
          assertTrue(currentNode.parent().equals(this.tree1.root())
              || currentNode.parent().equals(this.tree2.root()));
          assertNull(currentNode.children());
        }
      }

    }
  }

}
