/**
 * ExampleNodeEvaluator.java
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
package jmona.gp.impl.example;

import jmona.Function;
import jmona.MappingException;
import jmona.gp.EvaluationException;
import jmona.gp.Node;

/**
 * A function which evaluates a Node to an integer value.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class ExampleNodeEvaluator implements Function<Node, Integer> {

  /**
   * Gets the evaluation of the specified Node.
   * 
   * @param node
   *          The Node to evaluate.
   * @return The result of evaluation of the specified Node.
   * @throws MappingException
   *           If there is a problem evaluating the specified Node.
   */
  public static Integer evaluate(final Node node) throws MappingException {
    final ExampleNodeEvaluator evaluator = new ExampleNodeEvaluator();
    return evaluator.execute(node);
  }

  /**
   * Gets the evaluation of the specified Node.
   * 
   * @param node
   *          The Node to evaluate.
   * @return The evaluation of the specified Node.
   * @throws MappingException
   *           If there is a problem evaluating the specified Node.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Integer execute(final Node node) throws MappingException {
    try {
      return ((ExampleNode) node).evaluate();
    } catch (final EvaluationException exception) {
      throw new MappingException("Failed to evaluate the specified Node.",
          exception);
    }
  }

}
