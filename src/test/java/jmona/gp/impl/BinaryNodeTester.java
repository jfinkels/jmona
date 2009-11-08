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
import jmona.gp.EvaluationException;
import jmona.gp.TerminalNode;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BinaryNode class.
 * 
 * @author jfinkels
 */
public class BinaryNodeTester {

  /** The BinaryNode under test. */
  private BinaryNode<Integer> node = null;
  /** The left child Node. */
  private TerminalNode<Integer> leftChild = null;
  /** The right child Node. */
  private TerminalNode<Integer> rightChild = null;
  /** The value for the left child Node. */
  public static final int LEFT_VALUE = 1;
  /** The value for the right child Node. */
  public static final int RIGHT_VALUE = 2;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.leftChild = new IntegerNode(LEFT_VALUE);
    this.rightChild = new IntegerNode(RIGHT_VALUE);

    this.node = new BinaryNode<Integer>(new ExampleBinaryOperation());
    this.node.children().add(leftChild);
    this.node.children().add(rightChild);
  }

  /**
   * Print the stack trace of the specified exception and fail the test.
   * 
   * @param exception
   *          The exception which caused the test failure.
   */
  protected static void fail(final Throwable exception) {
    exception.printStackTrace(System.err);
    org.junit.Assert.fail(exception.getMessage());
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
      fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.BinaryNode#getArity()}.
   */
  @Test
  public void testGetArity() {
    assertEquals(BinaryNode.ARITY, this.node.getArity());
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

}
