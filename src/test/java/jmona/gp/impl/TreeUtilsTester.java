/**
 * TreeUtilsTester.java
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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.ExampleTerminalNode;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the TreeUtils class.
 * 
 * @author Jeffrey Finkelstein
 */
public class TreeUtilsTester {

  /** The left Tree. */
  private Tree leftTree = null;
  /** The left child Node in the left Tree. */
  private Node leftTreeLeftChild = null;
  /** The right child Node in the left Tree. */
  private Node leftTreeRightChild = null;
  /** The root Node of the left Tree. */
  private Node leftTreeRoot = null;
  /** The right Tree. */
  private Tree rightTree = null;
  /** The left child Node in the right Tree. */
  private Node rightTreeLeftChild = null;
  /** The right child Node in the right Tree. */
  private Node rightTreeRightChild = null;
  /** The root Node in the right Tree. */
  private Node rightTreeRoot = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.leftTreeRoot = new ExampleBinaryNode();
    this.leftTreeLeftChild = new ExampleTerminalNode();
    this.leftTreeRightChild = new ExampleTerminalNode();
    this.rightTreeRoot = new ExampleBinaryNode();
    this.rightTreeLeftChild = new ExampleTerminalNode();
    this.rightTreeRightChild = new ExampleTerminalNode();

    this.leftTreeRoot.children().add(this.leftTreeLeftChild);
    this.leftTreeRoot.children().add(this.leftTreeRightChild);
    this.rightTreeRoot.children().add(this.rightTreeLeftChild);
    this.rightTreeRoot.children().add(this.rightTreeRightChild);

    this.leftTreeLeftChild.setParent(this.leftTreeRoot);
    this.leftTreeRightChild.setParent(this.leftTreeRoot);
    this.rightTreeLeftChild.setParent(this.rightTreeRoot);
    this.rightTreeRightChild.setParent(this.rightTreeRoot);

    this.leftTree = new DefaultTree(this.leftTreeRoot);
    this.rightTree = new DefaultTree(this.rightTreeRoot);
  }

  /** Test for performing multiple swaps on the same Trees. */
  @Test
  public void testMultipleSwaps() {

    // swap the left tree root with the right tree root
    TreeUtils.swapNodes(this.leftTree, this.leftTree.root(), this.rightTree,
        this.rightTree.root());

    assertSame(this.leftTreeRoot, this.rightTree.root());
    assertSame(this.leftTreeLeftChild, this.rightTree.root().children().get(0));
    assertSame(this.leftTreeRightChild, this.rightTree.root().children().get(1));

    assertSame(this.rightTreeRoot, this.leftTree.root());
    assertSame(this.rightTreeLeftChild, this.leftTree.root().children().get(0));
    assertSame(this.rightTreeRightChild, this.leftTree.root().children().get(1));

    assertSame(this.leftTreeRoot, this.rightTree.root().children().get(0)
        .parent());
    assertSame(this.leftTreeRoot, this.rightTree.root().children().get(1)
        .parent());
    assertSame(this.rightTreeRoot, this.leftTree.root().children().get(0)
        .parent());
    assertSame(this.rightTreeRoot, this.leftTree.root().children().get(1)
        .parent());

    assertNull(this.rightTree.root().parent());
    assertNull(this.leftTree.root().parent());

    // swap left child in the left tree with right child in the right tree
    TreeUtils.swapNodes(this.leftTree, this.leftTree.root().children().get(0),
        this.rightTree, this.rightTree.root().children().get(1));

    assertSame(this.leftTreeRoot, this.rightTree.root());
    assertSame(this.leftTreeLeftChild, this.rightTree.root().children().get(0));
    assertSame(this.rightTreeLeftChild, this.rightTree.root().children().get(1));
    assertSame(this.rightTreeRoot, this.leftTree.root());
    assertSame(this.leftTreeRightChild, this.leftTree.root().children().get(0));
    assertSame(this.rightTreeRightChild, this.leftTree.root().children().get(1));

    assertSame(this.leftTreeRoot, this.rightTree.root().children().get(0)
        .parent());
    assertSame(this.leftTreeRoot, this.rightTree.root().children().get(1)
        .parent());
    assertSame(this.rightTreeRoot, this.leftTree.root().children().get(0)
        .parent());
    assertSame(this.rightTreeRoot, this.leftTree.root().children().get(1)
        .parent());

    assertNull(this.rightTree.root().parent());
    assertNull(this.leftTree.root().parent());

    // swap the right child of the left tree with the root of the right tree
    TreeUtils.swapNodes(this.leftTree, this.leftTree.root().children().get(1),
        this.rightTree, this.rightTree.root());

    assertSame(this.rightTreeRoot, this.leftTree.root());
    assertSame(this.rightTreeRightChild, this.rightTree.root());
    assertSame(this.leftTreeRightChild, this.leftTree.root().children().get(0));
    assertSame(this.leftTreeRoot, this.leftTree.root().children().get(1));
    assertSame(this.leftTreeLeftChild, this.leftTree.root().children().get(1)
        .children().get(0));
    assertSame(this.rightTreeLeftChild, this.leftTree.root().children().get(1)
        .children().get(1));

    assertSame(this.rightTreeRoot, this.leftTree.root().children().get(0)
        .parent());
    assertSame(this.rightTreeRoot, this.leftTree.root().children().get(1)
        .parent());
    assertSame(this.leftTreeRoot, this.leftTree.root().children().get(1)
        .children().get(0).parent());
    assertSame(this.leftTreeRoot, this.leftTree.root().children().get(1)
        .children().get(1).parent());

    assertNull(this.rightTree.root().parent());
    assertNull(this.leftTree.root().parent());
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.TreeUtils#replaceNode(jmona.gp.Tree, jmona.gp.Node, jmona.gp.Node)}
   * .
   */
  @Test
  public void testReplaceNode() {
    TreeUtils.replaceNode(this.leftTree, this.leftTreeRoot, this.rightTreeRoot);
    assertSame(this.rightTreeRoot, this.leftTree.root());
    assertSame(this.rightTreeLeftChild, this.leftTree.root().children().get(0));
    assertSame(this.rightTreeRightChild, this.leftTree.root().children().get(1));
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.TreeUtils#replaceNode(jmona.gp.Tree, jmona.gp.Node, jmona.gp.Node)}
   * .
   */
  @Test
  public void testReplaceNode2() {
    TreeUtils.replaceNode(this.leftTree, this.leftTreeLeftChild,
        this.rightTreeRoot);
    assertSame(this.leftTreeRoot, this.leftTree.root());
    assertSame(this.rightTreeRoot, this.leftTree.root().children().get(0));
    assertSame(this.leftTreeRightChild, this.leftTree.root().children().get(1));
    assertSame(this.rightTreeLeftChild, this.leftTree.root().children().get(0)
        .children().get(0));

    assertSame(this.rightTreeRightChild, this.leftTree.root().children().get(0)
        .children().get(1));

  }

  /**
   * Test method for
   * {@link jmona.gp.impl.TreeUtils#replaceNode(jmona.gp.Tree, jmona.gp.Node, jmona.gp.Node)}
   * .
   */
  @Test
  public void testReplaceNode3() {
    TreeUtils.replaceNode(this.leftTree, this.leftTreeLeftChild,
        this.rightTreeRightChild);
    assertSame(this.leftTreeRoot, this.leftTree.root());
    assertSame(this.rightTreeRightChild, this.leftTree.root().children().get(0));
    assertSame(this.leftTreeRightChild, this.leftTree.root().children().get(1));
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.TreeUtils#swapNodes(jmona.gp.Tree, jmona.gp.Node, jmona.gp.Tree, jmona.gp.Node)}
   * .
   */
  @Test
  public void testSwapNodes() {
    TreeUtils.swapNodes(this.leftTree, this.leftTreeRoot, this.rightTree,
        this.rightTreeRoot);
    assertSame(this.rightTreeRoot, this.leftTree.root());
    assertSame(this.leftTreeRoot, this.rightTree.root());
    assertNull(this.leftTree.root().parent());
    assertNull(this.rightTree.root().parent());
  }

  /** Test method for {@link jmona.gp.impl.TreeUtils#TreeUtils()}. */
  @Test
  public void testTreeUtils() {
    new TreeUtils();
  }
}
