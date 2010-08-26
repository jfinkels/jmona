/**
 * AdditionNodeTester.java
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
package jmona.example.calc.nodes;

import static org.junit.Assert.assertEquals;
import jmona.MappingException;
import jmona.gp.EvaluationException;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the AdditionNode class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class AdditionNodeTester {
  /** The value by which to increment the input to the function. */
  public static final double INCREMENT = 0.1;
  /** The maximum value of the input to the function. */
  public static final double MAX_VALUE = 100.0;
  /** The minimum value of the input to the function. */
  public static final double MIN_VALUE = 0.0;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.AdditionNode#AdditionNode()}.
   */
  @Test
  public void testAdditionNode() {
    final AdditionNode node = new AdditionNode();

    final double leftValue = 1.0;
    final double rightValue = 2.0;

    node.children().add(new NumberNode(leftValue));
    node.children().add(new NumberNode(rightValue));

    try {
      for (double x = MIN_VALUE; x < MAX_VALUE; x += INCREMENT) {
        assertEquals(leftValue + rightValue, node.evaluate().execute(x),
            ZERO_DELTA);
      }
    } catch (final EvaluationException exception) {
      TestUtils.fail(exception);
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    }
  }

}
