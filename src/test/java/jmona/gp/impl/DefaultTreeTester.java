/**
 * DefaultTreeTester.java
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmona.gp.EvaluationException;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.TerminalNode;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.IntegerNode;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultTree class.
 * 
 * @author jfinkels
 */
public class DefaultTreeTester {

  /** The number of times to choose a random node. */
  public static final int NUM_TESTS = 10000;

  /** A big tree. */
  private DefaultTree<Integer> bigTree = null;
  /** A function node in the big tree. */
  private FunctionNode<Integer> bigTreeNode1 = null;
  /** A terminal node in the big tree. */
  private TerminalNode<Integer> bigTreeNode2 = null;
  /** A terminal node in the big tree. */
  private TerminalNode<Integer> bigTreeNode3 = null;
  /** A small tree. */
  private DefaultTree<Integer> smallTree = null;
  /** A node in the small tree. */
  private TerminalNode<Integer> smallTreeNode = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.smallTreeNode = new IntegerNode(1);
    this.bigTreeNode1 = new ExampleBinaryNode();
    this.bigTreeNode2 = new IntegerNode(2);
    this.bigTreeNode3 = new IntegerNode(0);

    this.smallTree = new DefaultTree<Integer>(this.smallTreeNode);

    this.bigTreeNode1.children().add(this.bigTreeNode2);
    this.bigTreeNode2.setParent(this.bigTreeNode1);

    this.bigTreeNode1.children().add(this.bigTreeNode3);
    this.bigTreeNode3.setParent(this.bigTreeNode1);
    this.bigTree = new DefaultTree<Integer>(this.bigTreeNode1);
  }

  /**
   * Test method for {@link jmona.gp.impl.DefaultTree#allNodes()}.
   */
  @Test
  public void testAllNodes() {
    final List<Node<Integer>> allBigTreeNodes = this.bigTree.allNodes();

    assertTrue(allBigTreeNodes.contains(this.bigTreeNode1));
    assertTrue(allBigTreeNodes.contains(this.bigTreeNode2));
    assertTrue(allBigTreeNodes.contains(this.bigTreeNode3));
    assertEquals(3, allBigTreeNodes.size());

    final List<Node<Integer>> allSmallTreeNodes = this.smallTree.allNodes();

    assertTrue(allSmallTreeNodes.contains(this.smallTreeNode));
    assertEquals(1, allSmallTreeNodes.size());
  }

  /**
   * Test method for {@link jmona.gp.impl.DefaultTree#evaluate()}.
   */
  @Test
  public void testEvaluate() {
    try {
      assertEquals(1, this.smallTree.evaluate().intValue());
      assertEquals(2, this.bigTree.evaluate().intValue());
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.DefaultTree#randomNode()}.
   */
  @Test
  public void testRandomNode() {
    final Tree<Integer> emptyTree = new DefaultTree<Integer>(null);

    Node<Integer> node = null;
    try {
      node = emptyTree.randomNode();
      fail("Exception should have been thrown on the previous line.");
    } catch (final NullPointerException exception) {
      assertNull(node);
    }

    final Map<Node<Integer>, Integer> selectionsMap = new HashMap<Node<Integer>, Integer>();

    selectionsMap.put(this.bigTreeNode1, 0);
    selectionsMap.put(this.bigTreeNode2, 0);
    selectionsMap.put(this.bigTreeNode3, 0);

    Node<Integer> choice = null;
    Integer numSelections = null;
    for (int i = 0; i < NUM_TESTS; ++i) {
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
