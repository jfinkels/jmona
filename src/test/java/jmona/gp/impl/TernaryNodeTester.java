/**
 * TernaryNodeTester.java
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
import static org.junit.Assert.assertSame;
import jmona.gp.EvaluationException;
import jmona.gp.Node;
import jmona.gp.impl.example.ExampleTernaryNode;
import jmona.gp.impl.example.IntegerNode;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the TernaryNode class.
 * 
 * @author jfinkels
 */
public class TernaryNodeTester {

  /** The value of the left child Node. */
  public static final int LEFT_VALUE = 1;
  /** The value of the middle child Node. */
  public static final int MIDDLE_VALUE = 2;
  /** The value of the right child Node. */
  public static final int RIGHT_VALUE = 3;
  /** The left child Node. */
  private Node<Integer> leftChild = null;
  /** The middle child Node. */
  private Node<Integer> middleChild = null;
  /** The Node under test. */
  private TernaryNode<Integer> node = null;
  /** The right child Node. */
  private Node<Integer> rightChild = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.node = new ExampleTernaryNode();
    this.leftChild = new IntegerNode(LEFT_VALUE);
    this.middleChild = new IntegerNode(MIDDLE_VALUE);
    this.rightChild = new IntegerNode(RIGHT_VALUE);
    
    this.node.children().add(this.leftChild);
    this.node.children().add(this.middleChild);
    this.node.children().add(this.rightChild);
    
    this.leftChild.setParent(this.node);
    this.middleChild.setParent(this.node);
    this.rightChild.setParent(this.node);
  }

  /**
   * Test method for {@link jmona.gp.impl.TernaryNode#arity()}.
   */
  @Test
  public void testArity() {
    assertEquals(3, this.node.arity());
    assertEquals(TernaryNode.ARITY, this.node.arity());
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.TernaryNode#evaluate(java.lang.Object[])}.
   */
  @Test
  public void testEvaluate() {
    try {
      assertEquals(LEFT_VALUE + MIDDLE_VALUE + RIGHT_VALUE, this.node.evaluate().intValue());
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.TernaryNode#left()}.
   */
  @Test
  public void testLeft() {
    assertSame(this.leftChild, this.node.left());
  }

  /**
   * Test method for {@link jmona.gp.impl.TernaryNode#middle()}.
   */
  @Test
  public void testMiddle() {
    assertSame(this.middleChild, this.node.middle());
  }

  /**
   * Test method for {@link jmona.gp.impl.TernaryNode#right()}.
   */
  @Test
  public void testRight() {
    assertSame(this.rightChild, this.node.right());
  }

}
