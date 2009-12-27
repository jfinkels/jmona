/**
 * NumberNodeTester.java
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
import jmona.MappingException;
import jmona.example.calc.nodes.NumberNode;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the NumberNode class.
 * 
 * @author jfinkels
 */
public class NumberNodeTester {
  /** The value by which to increment the input to the function. */
  public static final double INCREMENT = 0.1;
  /** The maximum value of the input to the function. */
  public static final double MAX_VALUE = 100.0;
  /** The minimum value of the input to the function. */
  public static final double MIN_VALUE = 0.0;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for {@link jmona.example.calc.nodes.NumberNode#evaluate()} .
   */
  @Test
  public void testEvaluate() {

    final double value = 10.0;
    final NumberNode node = new NumberNode(value);
    try {
      for (double x = MIN_VALUE; x < MAX_VALUE; x += INCREMENT) {
        assertEquals(value, node.evaluate().execute(x), ZERO_DELTA);
      }
    } catch (final MappingException exception) {
      Util.fail(exception);
    }
  }

}
