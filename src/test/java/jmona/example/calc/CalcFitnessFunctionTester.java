/**
 * CalcFitnessFunctionTester.java
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

import java.util.HashSet;
import java.util.Set;

import jmona.CopyingException;
import jmona.FitnessException;
import jmona.Function;
import jmona.MappingException;
import jmona.example.calc.functions.ConstantFunction;
import jmona.example.calc.nodes.AbstractCalcTerminalNode;
import jmona.example.calc.nodes.NumberNode;
import jmona.gp.EvaluationException;
import jmona.gp.Node;
import jmona.gp.Tree;
import jmona.gp.impl.DefaultTree;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the CalcFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class CalcFitnessFunctionTester {

  /**
   * Test method for
   * {@link jmona.example.calc.CalcFitnessFunction#rawFitness(jmona.gp.Tree)}.
   */
  @Test
  public void testRawFitness() {
    final CalcFitnessFunction function = new CalcFitnessFunction(2);
    final Node root = new AbstractCalcTerminalNode("x") {
      @Override
      public Function<Double, Double> evaluate() throws EvaluationException {
        throw new EvaluationException();
      }

      @Override
      public Node deepCopy() throws CopyingException {
        return null;
      }
    };
    final Tree tree = new DefaultTree(root);

    try {
      function.rawFitness(tree);
      Util.shouldHaveThrownException();
    } catch (final FitnessException exception) {
      assertTrue(exception.getCause() instanceof MappingException);
      assertTrue(exception.getCause().getCause() instanceof EvaluationException);
      tree.setRoot(new NumberNode(1.0));
    }

    final Set<Double> inputs = new HashSet<Double>();
    inputs.add(0.0);
    inputs.add(1.0);

    function.setEvaluationInputs(inputs);
    function.setTarget(new ConstantFunction<Double, Double>(1.0));

    try {
      assertEquals(2, function.rawFitness(tree), ZERO_DELTA);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }
  }

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

}
