/**
 * AbstractCalcFunctionNode.java
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
package jmona.gp.example.calc.nodes;

import jmona.gp.example.calc.functions.SingleInputFunction;
import jmona.gp.impl.BinaryNode;
import jmona.gp.impl.UniformBinaryOperation;

/**
 * A base class for FunctionNode objects in the Calc evolution.
 * 
 * @author jfinkels
 */
public abstract class AbstractCalcFunctionNode extends
    BinaryNode<SingleInputFunction<Double, Double>> {
  /** The symbol which represents this Node. */
  private final String symbol;

  /**
   * Instantiate this node with the specified BinaryOperation and symbol which
   * represents this Node.
   * 
   * @param initialOperation
   *          The BinaryOperation which this Node performs.
   * @param initialSymbol
   *          The symbol which represents this Node.
   */
  protected AbstractCalcFunctionNode(
      final UniformBinaryOperation<SingleInputFunction<Double, Double>> initialOperation,
      final String initialSymbol) {
    super(initialOperation);
    this.symbol = initialSymbol;
  }

  /**
   * Get the symbol which represents this Node.
   * 
   * @return The symbol which represents this Node.
   */
  public String toString() {
    return this.symbol;
  }
}