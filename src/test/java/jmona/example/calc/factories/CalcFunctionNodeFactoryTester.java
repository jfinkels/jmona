/**
 * CalcFunctionNodeFactory.java
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
package jmona.example.calc.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jmona.Function;
import jmona.example.calc.nodes.AdditionNode;
import jmona.example.calc.nodes.DivisionNode;
import jmona.example.calc.nodes.MultiplicationNode;
import jmona.example.calc.nodes.SubtractionNode;
import jmona.functional.Range;
import jmona.gp.FunctionNode;

import org.junit.Test;

/**
 * Test class for the CalcFunctionNodeFactory class.
 * 
 * @author Jeffrey Finkelstein
 */
public class CalcFunctionNodeFactoryTester {

  /** The number of nodes to create. */
  public static final int NUM_NODES = 100000;

  /**
   * Test method for
   * {@link jmona.example.calc.factories.CalcFunctionNodeFactory#createObject()}
   * .
   */
  @Test
  public void testCreateNode() {
    final CalcFunctionNodeFactory factory = new CalcFunctionNodeFactory();

    int additionCount = 0;
    int subtractionCount = 0;
    int multiplicationCount = 0;
    int divisionCount = 0;

    FunctionNode node = null;
    for (final int i : new Range(NUM_NODES)) {
      node = factory.createObject();

      if (node instanceof AdditionNode) {
        additionCount += 1;
      } else if (node instanceof SubtractionNode) {
        subtractionCount += 1;
      } else if (node instanceof MultiplicationNode) {
        multiplicationCount += 1;
      } else if (node instanceof DivisionNode) {
        divisionCount += 1;
      } else {
        fail("Something crazy happened.");
      }
    }

    final int numClasses = 4;
    final int expected = NUM_NODES / numClasses;
    final double delta = expected * 0.1;
    assertEquals(expected, additionCount, delta);
    assertEquals(expected, subtractionCount, delta);
    assertEquals(expected, multiplicationCount, delta);
    assertEquals(expected, divisionCount, delta);

  }

}
