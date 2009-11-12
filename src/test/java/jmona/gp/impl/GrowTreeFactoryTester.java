/**
 * GrowTreeFactoryTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jmona.InitializationException;
import jmona.gp.TerminalNode;
import jmona.gp.Tree;
import jmona.gp.impl.example.ExampleFunctionNodeFactory;
import jmona.gp.impl.example.ExampleTerminalNodeFactory;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GrowTreeFactory class.
 * 
 * @author jfinkels
 */
public class GrowTreeFactoryTester {

  /** The number of tests to run. */
  public static final int NUM_TESTS = 1000;

  /** The factory under test. */
  private GrowTreeFactory<Integer> factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.factory = new GrowTreeFactory<Integer>();
    this.factory.setFunctionNodeFactory(new ExampleFunctionNodeFactory());
    this.factory.setTerminalNodeFactory(new ExampleTerminalNodeFactory());
  }

  /**
   * Test method for {@link jmona.gp.impl.GrowTreeFactory#createTree(int)}.
   */
  @Test
  public void testCreateTree() {
    Tree<Integer> tree = null;
    try {
      tree = this.factory.createIndividual();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    final int numNodes = Util.countNodes(tree);
    final int depth = this.factory.maxDepth();

    assertTrue(numNodes <= Math.pow(2, depth) - 1);
  }

  /**
   * Test method for
   * {@link jmona.gp.impl.GrowTreeFactory#setProbabilityTerminal(double)}.
   */
  @Test
  public void testSetProbabilityTerminal() {
    this.factory.setProbabilityTerminal(1);
    try {
      Tree<Integer> tree = null;
      for (int i = 0; i < NUM_TESTS; ++i) {
        tree = this.factory.createIndividual();
        assertNull(tree.root().children());
        assertTrue(tree.root() instanceof TerminalNode<?>);
        assertEquals(1, Util.countNodes(tree));
      }

      this.factory.setProbabilityTerminal(0);

      final double epsilon = 0;

      for (int i = 0; i < NUM_TESTS; ++i) {
        tree = this.factory.createIndividual();

        assertEquals(Math.pow(2, this.factory.maxDepth()) - 1, Util
            .countNodes(tree), epsilon);
      }
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }
  }

}
