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
package jmona.gp.example.calc.factories;

import jmona.InitializationException;
import jmona.gp.TerminalNode;
import jmona.gp.TerminalNodeFactory;
import jmona.gp.example.calc.functions.SingleInputFunction;
import jmona.gp.example.calc.nodes.NumberNode;
import jmona.gp.example.calc.nodes.VariableNode;
import jmona.impl.Util;

/**
 * A TerminalNodeFactory for the Calc example, which creates either
 * {@link VariableNode} or {@link NumberNode} objects.
 * 
 * @author jfinkels
 */
public class CalcTerminalNodeFactory implements
    TerminalNodeFactory<SingleInputFunction<Object, Double>> {

  /** The maximum value for a NumberNode. */
  public static final int MAX_VALUE = 255;

  /**
   * Create a TerminalNode of type {@link VariableNode} or {@link NumberNode}.
   * 
   * @return Either a {@code VariableNode} or a {@code NumberNode}.
   * @throws InitializationException
   *           Never throws this Exception.
   * @see jmona.gp.NodeFactory#createNode()
   */
  @Override
  public TerminalNode<SingleInputFunction<Object, Double>> createNode()
      throws InitializationException {
    TerminalNode<SingleInputFunction<Object, Double>> result = null;

    if (Util.RANDOM.nextBoolean()) {
      result = new VariableNode();
    } else {
      result = new NumberNode((double) Util.RANDOM.nextInt(MAX_VALUE));
    }

    return result;
  }

}
