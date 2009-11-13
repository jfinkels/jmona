/**
 * UtilTester.java
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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.ExampleTerminalNode;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Util class.
 * 
 * @author jfinkels
 */
public class UtilTester {

  /** The left Tree. */
  private Tree<Integer> leftTree = null;
  /** The left child Node in the left Tree. */
  private Node<Integer> leftTreeLeftChild = null;
  /** The right child Node in the left Tree. */
  private Node<Integer> leftTreeRightChild = null;
  /** The root Node of the left Tree. */
  private Node<Integer> leftTreeRoot = null;
  /** The right Tree. */
  private Tree<Integer> rightTree = null;
  /** The left child Node in the right Tree. */
  private Node<Integer> rightTreeLeftChild = null;
  /** The right child Node in the right Tree. */
  private Node<Integer> rightTreeRightChild = null;
  /** The root Node in the right Tree. */
  private Node<Integer> rightTreeRoot = null;

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
    
    this.leftTree = new DefaultTree<Integer>(this.leftTreeRoot);
    this.rightTree = new DefaultTree<Integer>(this.rightTreeRoot);
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.Util#replaceNode(jmona.gp.Tree, jmona.gp.Node, jmona.gp.Node)}
   * .
   */
  @Test
  public void testReplaceNode() {
    Util.replaceNode(this.leftTree, this.leftTreeRoot, this.rightTreeRoot);
    assertSame(this.leftTree.root(), this.rightTreeRoot);
    assertSame(this.leftTree.root().children().get(0), this.rightTreeLeftChild);
    assertSame(this.leftTree.root().children().get(1), this.rightTreeRightChild);
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.Util#replaceNode(jmona.gp.Tree, jmona.gp.Node, jmona.gp.Node)}
   * .
   */
  @Test
  public void testReplaceNode2() {
    Util.replaceNode(this.leftTree, this.leftTreeLeftChild, this.rightTreeRoot);
    assertSame(this.leftTree.root(), this.leftTreeRoot);
    assertSame(this.leftTree.root().children().get(0), this.rightTreeRoot);
    assertSame(this.leftTree.root().children().get(1), this.leftTreeRightChild);
    assertSame(this.leftTree.root().children().get(0).children().get(0),
        this.rightTreeLeftChild);
    assertSame(this.leftTree.root().children().get(0).children().get(1),
        this.rightTreeRightChild);
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.Util#replaceNode(jmona.gp.Tree, jmona.gp.Node, jmona.gp.Node)}
   * .
   */
  @Test
  public void testReplaceNode3() {
    Util.replaceNode(this.leftTree, this.leftTreeLeftChild,
        this.rightTreeRightChild);
    assertSame(this.leftTree.root(), this.leftTreeRoot);
    assertSame(this.leftTree.root().children().get(0), this.rightTreeRightChild);
    assertSame(this.leftTree.root().children().get(1), this.leftTreeRightChild);
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.Util#swapNodes(jmona.gp.Tree, jmona.gp.Node, jmona.gp.Tree, jmona.gp.Node)}
   * .
   */
  @Test
  public void testSwapNodes() {
    Util.swapNodes(this.leftTree, this.leftTreeRoot, this.rightTree,
        this.rightTreeRoot);
    assertSame(this.leftTree.root(), this.rightTreeRoot);
    assertSame(this.rightTree.root(), this.leftTreeRoot);
    assertNull(this.leftTree.root().parent());
    assertNull(this.rightTree.root().parent());
  }

}
