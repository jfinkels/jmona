/**
 * CalcFunctionNodeTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.example.calc.nodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jmona.CopyingException;
import jmona.MappingException;
import jmona.gp.EvaluationException;
import jmona.gp.impl.TreeUtils;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the CalcFunctionNode class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class CalcFunctionNodeTester {
  /** The Node under test. */
  private CalcFunctionNode node = null;
  /** The left child of the Node under test. */
  private CalcNode child1 = null;
  /** The right child of the Node under test. */
  private CalcNode child2 = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.node = new AdditionNode();
    this.child1 = new NumberNode(CHILD1_VALUE);
    this.child2 = new NumberNode(CHILD2_VALUE);

    TreeUtils.attachChildToParent(this.node, this.child1);
    TreeUtils.attachChildToParent(this.node, this.child2);
  }

  /** The value of the left child. */
  public static final double CHILD1_VALUE = 1.0;
  /** The value of the right child. */
  public static final double CHILD2_VALUE = 2.0;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.CalcFunctionNode#evaluate()}.
   */
  @Test
  public void testEvaluate() {
    try {
      assertEquals(CHILD1_VALUE + CHILD2_VALUE, this.node.evaluate().execute(
          null), ZERO_DELTA);
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    } catch (EvaluationException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.CalcFunctionNode#left()}.
   */
  @Test
  public void testLeft() {
    assertSame(this.child1, this.node.left());
  }

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.CalcFunctionNode#right()}.
   */
  @Test
  public void testRight() {
    assertSame(this.child2, this.node.right());
  }

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.CalcFunctionNode#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    CalcFunctionNode clonedNode = null;
    try {
      clonedNode = this.node.deepCopy();
    } catch (final CopyingException exception) {
      TestUtils.fail(exception);
    }

    assertTrue(clonedNode instanceof CalcFunctionNode);
    assertNotSame(clonedNode, this.node);
    assertNotSame(clonedNode.left(), this.node.left());
    assertNotSame(clonedNode.right(), this.node.right());

    try {
      assertEquals(clonedNode.evaluate().execute(null), this.node.evaluate()
          .execute(null), ZERO_DELTA);
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    } catch (EvaluationException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.CalcFunctionNode#toString()}.
   */
  @Test
  public void testToString() {
    final String result = this.node.toString();
    assertTrue(result.contains(this.node.left().toString()));
    assertTrue(result.contains(this.node.right().toString()));
    assertTrue(result.contains("("));
    assertTrue(result.contains(")"));
  }

}
