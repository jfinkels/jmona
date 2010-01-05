/**
 * ExampleTreeEvaluator.java
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
import jmona.gp.Tree;

/**
 * Returns the result of evaluating the root Node of a Tree.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class ExampleTreeEvaluator implements Function<Tree, Integer> {

  /** A Node evaluator. */
  public static final ExampleNodeEvaluator NODE_EVALUATOR = new ExampleNodeEvaluator();

  /**
   * Gets the evaluation of the specified Tree.
   * 
   * @param tree
   *          The Tree to evaluate.
   * @return The result of evaluation of the specified Tree.
   * @throws MappingException
   *           If there is a problem evaluating the specified Tree.
   */
  public static Integer evaluate(final Tree tree) throws MappingException {
    return ExampleNodeEvaluator.evaluate(tree.root());
  }

  /**
   * Evaluate the root Node of the specified Tree.
   * 
   * @param tree
   *          The Tree to evaluate.
   * @return The evaluation of the root of the specified Tree.
   * @throws MappingException
   *           If there is a problem evaluating the root.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Integer execute(final Tree tree) throws MappingException {
    return NODE_EVALUATOR.execute(tree.root());
  }

}
