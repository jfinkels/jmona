/**
 * CalcTreeEvaluatorTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jfcommon.functional.Function;
import jfcommon.functional.MappingException;
import jfcommon.test.TestUtils;
import jmona.example.calc.nodes.NumberNode;
import jmona.example.calc.nodes.VariableNode;
import jmona.gp.EvaluationException;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.DefaultTree;

import org.junit.Test;

/**
 * Test class for the CalcTreeEvaluator class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class CalcTreeEvaluatorTester {

  /**
   * Test method for
   * {@link jmona.example.calc.CalcTreeEvaluator#execute(jmona.gp.Tree)}.
   */
  @Test
  public void testExecute() {
    final CalcTreeEvaluator evaluator = new CalcTreeEvaluator();

    final Node root = new VariableNode() {
      @Override
      public Function<Double, Double> evaluate() throws EvaluationException {
        throw new EvaluationException();
      }

    };
    final Tree tree = new DefaultTree(root);
    try {
      evaluator.execute(tree);
      TestUtils.shouldHaveThrownException();
    } catch (final MappingException exception) {
      assertTrue(exception.getCause() instanceof EvaluationException);
    }

    tree.setRoot(new NumberNode(0.0));

    try {
      assertEquals(0.0, evaluator.execute(tree).execute(0.0), ZERO_DELTA);
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    }
  }

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

}
