/**
 * DefaultTreeTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmona.CopyingException;
import jmona.MappingException;
import jmona.functional.Range;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.TerminalNode;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.ExampleNodeEvaluator;
import jmona.gp.impl.example.IntegerNode;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultTree class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class DefaultTreeTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(DefaultTreeTester.class);

  /** The number of times to choose a random node. */
  public static final int NUM_TESTS = 10000;
  /** A big tree. */
  private DefaultTree bigTree = null;
  /** A function node in the big tree. */
  private FunctionNode bigTreeNode1 = null;
  /** A terminal node in the big tree. */
  private TerminalNode bigTreeNode2 = null;
  /** A terminal node in the big tree. */
  private TerminalNode bigTreeNode3 = null;
  /** A small tree. */
  private DefaultTree smallTree = null;
  /** A node in the small tree. */
  private TerminalNode smallTreeNode = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.smallTreeNode = new IntegerNode(1);
    this.bigTreeNode1 = new ExampleBinaryNode();
    this.bigTreeNode2 = new IntegerNode(2);
    this.bigTreeNode3 = new IntegerNode(0);

    this.smallTree = new DefaultTree(this.smallTreeNode);

    this.bigTreeNode1.children().add(this.bigTreeNode2);
    this.bigTreeNode2.setParent(this.bigTreeNode1);

    this.bigTreeNode1.children().add(this.bigTreeNode3);
    this.bigTreeNode3.setParent(this.bigTreeNode1);
    this.bigTree = new DefaultTree(this.bigTreeNode1);
  }

  /**
   * Test method for {@link jmona.gp.impl.DefaultTree#allNodes()}.
   */
  @Test
  public void testAllNodes() {
    final List<Node> allBigTreeNodes = this.bigTree.allNodes();

    final int numNodes = 3;

    assertTrue(allBigTreeNodes.contains(this.bigTreeNode1));
    assertTrue(allBigTreeNodes.contains(this.bigTreeNode2));
    assertTrue(allBigTreeNodes.contains(this.bigTreeNode3));
    assertEquals(numNodes, allBigTreeNodes.size());

    final List<Node> allSmallTreeNodes = this.smallTree.allNodes();

    assertTrue(allSmallTreeNodes.contains(this.smallTreeNode));
    assertEquals(1, allSmallTreeNodes.size());
  }

  /** Test method for {@link jmona.gp.impl.DefaultTree#deepCopy()}. */
  @Test
  public void testDeepCopy() {
    // copy the original tree
    Tree copy = null;
    try {
      copy = this.bigTree.deepCopy();
    } catch (final CopyingException exception) {
      Util.fail(exception);
    }

    // the copy should not refer to the same object as the original
    assertNotSame(copy, this.bigTree);

    // get all the nodes
    final List<Node> allCopyNodes = Util.allNodes(copy);
    final List<Node> allOriginalNodes = Util.allNodes(bigTree);

    // the copied tree and original tree should have the same number of nodes
    assertEquals(allOriginalNodes.size(), allCopyNodes.size());

    try {

      // iterate over all nodes in the trees
      Node originalNode = null;
      Node copyNode = null;
      for (final int i : new Range(allOriginalNodes.size())) {

        // get the original node and the copy
        originalNode = allOriginalNodes.get(i);
        copyNode = allCopyNodes.get(i);

        LOG.debug("Original node (hashcode " + originalNode.hashCode() + " ): "
            + originalNode);
        LOG.debug("Copy node (hashcode " + copyNode.hashCode() + " ): "
            + copyNode);

        // assert that the original and the copy do not refer to the same object
        assertNotSame(originalNode, copyNode);
      }

      // assert that their contents are equal
      assertEquals(ExampleNodeEvaluator.evaluate(originalNode),
          ExampleNodeEvaluator.evaluate(copyNode));
    } catch (final MappingException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.DefaultTree#randomNode()}.
   */
  @Test
  public void testRandomNode() {
    final Tree emptyTree = new DefaultTree(null);

    Node node = null;
    try {
      node = emptyTree.randomNode();
      fail("Exception should have been thrown on the previous line.");
    } catch (final IllegalArgumentException exception) {
      assertNull(node);
    }

    final Map<Node, Integer> selectionsMap = new HashMap<Node, Integer>();

    selectionsMap.put(this.bigTreeNode1, 0);
    selectionsMap.put(this.bigTreeNode2, 0);
    selectionsMap.put(this.bigTreeNode3, 0);

    Node choice = null;
    Integer numSelections = null;
    for (final int i : new Range(NUM_TESTS)) {
      // every randomNode() call on a single-Node tree returns the root
      assertSame(this.smallTreeNode, this.smallTree.randomNode());
      assertSame(this.smallTree.root(), this.smallTree.randomNode());
      assertSame(this.smallTree.root(), this.smallTreeNode);

      // choose a random node from the non-trivial tree
      choice = this.bigTree.randomNode();

      // the result of randomNode() should not be null
      assertNotNull(choice);

      // ensure the parent of the selected Node makes sense
      if (choice == this.bigTreeNode1) {
        assertSame(choice, this.bigTree.root());
        assertNull(choice.parent());
      } else {
        assertTrue(choice == this.bigTreeNode2 || choice == this.bigTreeNode3);
        assertNotNull(choice.parent());
      }

      // get the number of times this node has already been selected
      numSelections = selectionsMap.get(choice);

      // increment the number of times this node has already been selected
      selectionsMap.put(choice, numSelections + 1);
    }

    // get the approximate expected number of selections for each node
    final double expected = NUM_TESTS / selectionsMap.size();
    final double epsilon = expected * 0.10;

    for (final Integer count : selectionsMap.values()) {
      assertEquals(expected, count, epsilon);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.DefaultTree#root()}.
   */
  @Test
  public void testRoot() {
    assertSame(this.bigTreeNode1, this.bigTree.root());
    assertSame(this.smallTreeNode, this.smallTree.root());

    assertNull(this.bigTree.root().parent());
    assertNull(this.smallTree.root().parent());
  }

}
