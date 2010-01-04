/**
 * CalcTerminalNodeFactory.java
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

import java.util.HashSet;
import java.util.Set;

import jmona.Factory;
import jmona.example.calc.nodes.AdditionNode;
import jmona.example.calc.nodes.DivisionNode;
import jmona.example.calc.nodes.MultiplicationNode;
import jmona.example.calc.nodes.SubtractionNode;
import jmona.gp.FunctionNode;
import jmona.impl.Util;

/**
 * A FunctionNodeFactory for the Calc evolution, which randomly generates an
 * AdditionNode, a SubtractionNode, a MultiplicationNode, or a DivisionNode.
 * 
 * @author Jeffrey Finkelstein
 */
public class CalcFunctionNodeFactory implements Factory<FunctionNode> {

  /**
   * The operations which the FunctionNodes represent.
   * 
   * @author Jeffrey Finkelstein
   */
  private static enum Operation {
    /** The addition operation. */
    ADDITION,
    /** The division operation. */
    DIVISION,
    /** The multiplication operation. */
    MULTIPLICATION,
    /** The subtraction operation. */
    SUBTRACTION
  }

  /** The operations from which to choose when randomly generating a new Node. */
  private final Set<Operation> operations = new HashSet<Operation>();

  /**
   * Instantiate this factory and add the possible operations to the Set of
   * operations from which to choose when randomly generating a new Node.
   */
  public CalcFunctionNodeFactory() {
    this.operations.add(Operation.ADDITION);
    this.operations.add(Operation.SUBTRACTION);
    this.operations.add(Operation.MULTIPLICATION);
    this.operations.add(Operation.DIVISION);
  }

  /**
   * Create a FunctionNode, randomly chosen from addition, subtraction,
   * multiplication, and division.
   * 
   * @return A FunctionNode, randomly chosen from addition, subtraction,
   *         multiplication, and division.
   */
  @Override
  public FunctionNode createObject() {
    FunctionNode result = null;

    switch (Util.randomFromCollection(this.operations)) {
    case ADDITION:
    default:
      result = new AdditionNode();
      break;
    case MULTIPLICATION:
      result = new MultiplicationNode();
      break;
    case DIVISION:
      result = new DivisionNode();
      break;
    case SUBTRACTION:
      result = new SubtractionNode();
      break;
    }

    return result;
  }

}
