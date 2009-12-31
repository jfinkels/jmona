/**
 * BinaryNodeTester.java
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
import static org.junit.Assert.assertTrue;
import jmona.gp.EvaluationException;
import jmona.gp.TerminalNode;
import jmona.gp.impl.example.ExampleBinaryOperation;
import jmona.gp.impl.example.IntegerNode;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BinaryNode class.
 * 
 * @author Jeffrey Finkelstein
 */
public class BinaryNodeTester {

  /** The value for the left child Node. */
  public static final int LEFT_VALUE = 1;
  /** The value for the right child Node. */
  public static final int RIGHT_VALUE = 2;

  /** The left child Node. */
  private TerminalNode<Integer> leftChild = null;
  /** The BinaryNode under test. */
  private BinaryNode<Integer> node = null;
  /** The operation for the Node. */
  private BinaryOperation<Integer, Integer, Integer> operation = null;
  /** The right child Node. */
  private TerminalNode<Integer> rightChild = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.leftChild = new IntegerNode(LEFT_VALUE);
    this.rightChild = new IntegerNode(RIGHT_VALUE);

    this.operation = new ExampleBinaryOperation();

    this.node = new BinaryNode<Integer>(this.operation);
    this.node.children().add(leftChild);
    this.node.children().add(rightChild);
    this.leftChild.setParent(this.node);
    this.rightChild.setParent(this.node);
  }

  /**
   * Test method for {@link jmona.gp.impl.BinaryNode#evaluate()}.
   */
  @Test
  public final void testEvaluate() {

    try {
      assertEquals(LEFT_VALUE, this.leftChild.evaluate().intValue());
      assertEquals(RIGHT_VALUE, this.rightChild.evaluate().intValue());

      final int result = node.evaluate();

      assertEquals(LEFT_VALUE - RIGHT_VALUE, result);

    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.BinaryNode#arity()}.
   */
  @Test
  public void testGetArity() {
    assertEquals(BinaryNode.ARITY, this.node.arity());
  }

  /**
   * Test method for {@link jmona.gp.impl.BinaryNode#left()}.
   */
  @Test
  public void testLeft() {
    assertSame(this.leftChild, this.node.left());
    assertSame(this.leftChild, this.node.children().get(0));
  }

  /**
   * Test method for {@link jmona.gp.impl.BinaryNode#right()}.
   */
  @Test
  public void testRight() {
    assertSame(this.rightChild, this.node.right());
    assertSame(this.rightChild, this.node.children().get(1));
  }

  /**
   * Test method for {@link jmona.gp.impl.BinaryNode#toString()}.
   */
  @Test
  public void testToString() {
    final BinaryNode<Object> emptyNode = new BinaryNode<Object>(null);

    assertTrue(emptyNode.toString().contains("jmona.gp.impl.BinaryNode"));

    assertEquals("(" + String.valueOf(LEFT_VALUE) + this.operation.toString()
        + String.valueOf(RIGHT_VALUE) + ")", this.node.toString());
  }

}
