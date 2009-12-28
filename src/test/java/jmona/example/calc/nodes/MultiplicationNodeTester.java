/**
 * MultiplicationNodeTester.java
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
package jmona.example.calc.nodes;

import static org.junit.Assert.assertEquals;
import jmona.example.calc.nodes.MultiplicationNode;
import jmona.example.calc.nodes.NumberNode;
import jmona.exceptions.MappingException;
import jmona.gp.EvaluationException;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MultiplicationNode class.
 * 
 * @author jfinkels
 */
public class MultiplicationNodeTester {
  /** The value by which to increment the input to the function. */
  public static final double INCREMENT = 0.1;
  /** The value of the left Node. */
  public static final double LEFT_VALUE = 1.0;
  /** The maximum value of the input to the function. */
  public static final double MAX_VALUE = 100.0;
  /** The minimum value of the input to the function. */
  public static final double MIN_VALUE = 0.0;

  /** The value of the right Node. */
  public static final double RIGHT_VALUE = 2.0;

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The left child Node. */
  private NumberNode leftChild = null;

  /** The Node under test. */
  private MultiplicationNode node = null;
  /** The right child Node. */
  private NumberNode rightChild = null;
  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.node = new MultiplicationNode();
    this.leftChild = new NumberNode(LEFT_VALUE);
    this.rightChild = new NumberNode(RIGHT_VALUE);

    this.node.children().add(this.leftChild);
    this.node.children().add(this.rightChild);
    this.leftChild.setParent(this.node);
    this.rightChild.setParent(this.node);
  }

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.MultiplicationNode#MultiplicationNode()}
   * .
   */
  @Test
  public void testMultiplicationNode() {

    try {
      for (double x = MIN_VALUE; x < MAX_VALUE; x += INCREMENT) {
        assertEquals(LEFT_VALUE * RIGHT_VALUE, node.evaluate().execute(x),
            ZERO_DELTA);
      }
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    } catch (final MappingException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.MultiplicationNode#toString()}.
   */
  @Test
  public void testToString() {
    assertEquals("(" + LEFT_VALUE + "*" + RIGHT_VALUE + ")", this.node
        .toString());
  }

}
