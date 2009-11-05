/**
 * VariableNode.java
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

import jmona.gp.EvaluationException;
import jmona.gp.example.calc.functions.ConstantFunction;
import jmona.gp.example.calc.functions.SingleInputFunction;

/**
 * A Node which evaluates to the value of the specified input value.
 * 
 * @author jfinkels
 */
public class VariableNode extends AbstractCalcTerminalNode {

  /** The symbol which represents this Node. */
  public static final String VARIABLE_SYMBOL = "x";

  /** Instantiate this Node with the symbol at {@link #VARIABLE_SYMBOL}. */
  public VariableNode() {
    super(VARIABLE_SYMBOL);
  }

  /**
   * Evaluate to a ConstantFunction which outputs the value of the first input
   * parameter cast to a Double.
   * 
   * @param input
   *          The input parameters with which to evaluate; the first parameter
   *          will be cast to a Double.
   * @throws EvaluationException
   *           {@inheritDoc}
   * @see jmona.gp.Node#evaluate()
   */
  @Override
  public SingleInputFunction<Object, Double> evaluate(final Object... input)
      throws EvaluationException {
    return new ConstantFunction((Double) input[0]);
  }
}
