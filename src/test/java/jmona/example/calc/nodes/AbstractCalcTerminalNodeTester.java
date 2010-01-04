/**
 * AbstractCalcTerminalNodeTester.java
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
import jmona.CopyingException;
import jmona.Function;
import jmona.gp.EvaluationException;
import jmona.gp.Node;

import org.junit.Test;

/**
 * Test class for the AbstractCalcTerminalNode class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AbstractCalcTerminalNodeTester {

  /** The symbol of the AbstractCalcTerminalNode under test. */
  public static final String SYMBOL = "Hello, world!";

  /**
   * Test method for
   * {@link jmona.example.calc.nodes.AbstractCalcTerminalNode#AbstractCalcTerminalNode(java.lang.String)}
   * and {@link jmona.example.calc.nodes.AbstractCalcTerminalNode#toString()}.
   */
  @Test
  public void testAbstractCalcTerminalNode() {
    final AbstractCalcTerminalNode node = new AbstractCalcTerminalNode(SYMBOL) {

      @Override
      public Function<Double, Double> evaluate() throws EvaluationException {
        return null;
      }

      @Override
      public Node deepCopy() throws CopyingException {
        return null;
      }
    };

    assertEquals(SYMBOL, node.toString());
  }

}
