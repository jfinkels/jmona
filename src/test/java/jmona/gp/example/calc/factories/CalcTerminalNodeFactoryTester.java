/**
 * CalcTerminalNodeFactoryTester.java
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
package jmona.gp.example.calc.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jmona.InitializationException;
import jmona.gp.TerminalNode;
import jmona.gp.example.calc.functions.SingleInputFunction;
import jmona.gp.example.calc.nodes.NumberNode;
import jmona.gp.example.calc.nodes.VariableNode;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the CalcTerminalNodeFactory class.
 * 
 * @author jfinkels
 */
public class CalcTerminalNodeFactoryTester {

  /**
   * Test method for
   * {@link jmona.gp.example.calc.factories.CalcTerminalNodeFactory#createNode()}
   * .
   */
  @Test
  public void testCreateNode() {
    final CalcTerminalNodeFactory factory = new CalcTerminalNodeFactory();

    TerminalNode<SingleInputFunction<Double, Double>> node = null;
    try {
      node = factory.createNode();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }
    
    assertTrue(node instanceof VariableNode || node instanceof NumberNode);
    assertEquals(0, node.arity());
    assertNull(node.children());
    
  }

}
