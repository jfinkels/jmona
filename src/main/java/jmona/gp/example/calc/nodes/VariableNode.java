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
import jmona.gp.impl.AbstractTerminalNode;

/**
 * @author jfinkels
 */
public class VariableNode extends AbstractTerminalNode<SingleInputFunction<Object, Double>> {
  
  /* (non-Javadoc)
   * @see jmona.gp.Node#evaluate()
   */
  @Override
  public SingleInputFunction<Object, Double> evaluate(final Object... input)
      throws EvaluationException {
    return new ConstantFunction((Double) input[0]);
  }
}
