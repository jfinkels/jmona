/**
 * CalcTreeEvaluator.java
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
package jmona.example.calc;

import jfcommon.functional.Function;
import jfcommon.functional.MappingException;
import jmona.example.calc.nodes.CalcNode;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;

/**
 * Evaluates the root of a Tree containing CalcNodes.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class CalcTreeEvaluator implements
    Function<Tree, Function<Double, Double>> {

  /**
   * Returns the evaluation of the root of the specified Tree (assuming the root
   * is a CalcNode).
   * 
   * @param tree
   *          The Tree to evaluate.
   * @return The result of evaluatinf the root of the specified Tree.
   * @throws MappingException
   *           If there is a problem evaluating the root.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Function<Double, Double> execute(final Tree tree)
      throws MappingException {
    try {
      return ((CalcNode) tree.root()).evaluate();
    } catch (final EvaluationException exception) {
      throw new MappingException("Failed to evaluate root of Tree.", exception);
    }
  }

}
