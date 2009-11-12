/**
 * GPCrossoverFunctionTester.java
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

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.IntegerNode;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GPCrossoverFunction class.
 * 
 * @author jfinkels
 */
public class GPCrossoverFunctionTester {

  /** The function under test. */
  private GPCrossoverFunction<Integer> function = null;
  /** The left child in the right Tree. */
  private Node<Integer> leftChild = null;
  /** The right child in the right Tree. */
  private Node<Integer> rightChild = null;
  /** The root of the left Tree. */
  private Node<Integer> root1 = null;
  /** The root of the right Tree. */
  private Node<Integer> root2 = null;
  /** The left Tree. */
  private Tree<Integer> tree1 = null;
  /** The right Tree. */
  private Tree<Integer> tree2 = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new GPCrossoverFunction<Integer>();

    this.leftChild = new IntegerNode(2);
    this.rightChild = new IntegerNode(1);

    this.root1 = new ExampleBinaryNode();
    
    this.root1.children().add(this.leftChild);
    this.leftChild.setParent(root1);
    
    this.root1.children().add(this.rightChild);
    this.rightChild.setParent(this.root1);

    this.root2 = new IntegerNode(0);

    this.tree1 = new DefaultTree<Integer>(this.root1);
    this.tree2 = new DefaultTree<Integer>(this.root2);
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

}
