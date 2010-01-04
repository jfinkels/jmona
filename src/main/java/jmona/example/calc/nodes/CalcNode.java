/**
 * CalcNode.java
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

import jmona.Function;
import jmona.gp.EvaluationException;
import jmona.gp.Node;

/**
 * A node in the "calc" evolution, which can be evaluated to a function.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public interface CalcNode extends Node {
  /**
   * Evaluate this Node to a function.
   * 
   * @return The evaluation of this Node with respect to the evaluation of its
   *         child Nodes.
   * @throws EvaluationException
   *           If there is a problem evaluating this Node.
   */
  Function<Double, Double> evaluate() throws EvaluationException;
}
