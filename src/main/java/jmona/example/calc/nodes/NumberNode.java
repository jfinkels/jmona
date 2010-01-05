/**
 * NumberNode.java
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

import jmona.Function;
import jmona.example.calc.functions.DoubleConstantFunction;

/**
 * A Node containing a single value as a Double.
 * 
 * @author Jeffrey Finkelstein
 */
public class NumberNode extends AbstractCalcTerminalNode implements CalcNode {

  /** The value of this Node. */
  private final DoubleConstantFunction constantFunction;

  /**
   * Instantiate this Node with the specified initial value.
   * 
   * @param initialValue
   *          The initial value of this Node.
   */
  public NumberNode(final Double initialValue) {
    super(initialValue.toString());
    this.constantFunction = new DoubleConstantFunction(initialValue);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.gp.Node#deepCopy()
   */
  @Override
  public NumberNode deepCopy() {
    // instantiate a new NumberNode with the value of the constant function
    return new NumberNode(this.constantFunction.execute(null));
  }

  /**
   * Get the (constant) value of this Node.
   * 
   * @return The value of this Node.
   * @see jmona.gp.Node#evaluate()
   */
  @Override
  public Function<Double, Double> evaluate() {
    return this.constantFunction;
  }

}
