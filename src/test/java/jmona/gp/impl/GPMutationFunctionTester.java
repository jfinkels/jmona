/**
 * GPMutationFunctionTester.java
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
package jmona.gp.impl;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jmona.MutationException;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleTerminalNode;
import jmona.gp.impl.example.ExampleTreeFactory;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GPMutationFunction class.
 * 
 * @author jfinkels
 */
public class GPMutationFunctionTester {

  /** The GPMutationFunction under test. */
  private GPMutationFunction<Integer> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new GPMutationFunction<Integer>();
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPMutationFunction#setTreeFactory(jmona.gp.TreeFactory)}
   * .
   */
  @Test
  public void testSetTreeFactory() {
    final ExampleTreeFactory factory = new ExampleTreeFactory();
   
    try {
      factory.setMaxDepth(0);

      this.function.setTreeFactory(factory);

      final ExampleTerminalNode root = new ExampleTerminalNode();
      final Tree<Integer> tree = new DefaultTree<Integer>(root);

      this.function.mutate(tree);

      assertNotSame(root, tree.root());
      assertTrue(tree.root() instanceof ExampleTerminalNode);
      assertNotSame(root.evaluate(), tree.root().evaluate());

    } catch (final EvaluationException exception) {
      Util.fail(exception);
    } catch (final MutationException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GPMutationFunction#mutate(jmona.gp.Tree)}.
   */
  @Test
  public void testMutate() {
    fail("Not yet implemented");
  }

}
