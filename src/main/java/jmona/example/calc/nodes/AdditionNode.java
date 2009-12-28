/**
 * AdditionNode.java
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

import jmona.SingleInputFunction;
import jmona.example.calc.operations.FunctionAddition;
import jmona.gp.impl.BinaryNode;

/**
 * A node representing addition of real numbers.
 * 
 * @author jfinkels
 */
public class AdditionNode extends
    BinaryNode<SingleInputFunction<Double, Double>> {

  /** Instantiate this Node using the FunctionAddition operation. */
  public AdditionNode() {
    super(FunctionAddition.newInstance());
  }

}