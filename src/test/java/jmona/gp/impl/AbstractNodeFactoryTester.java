/**
 * AbstractNodeFactoryTester.java
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
import static org.junit.Assert.assertTrue;
import jmona.InitializationException;
import jmona.gp.Node;
import jmona.gp.impl.example.ExampleNodeFactory;
import jmona.gp.impl.example.ExampleTerminalNode;
import jmona.gp.impl.example.IntegerNode;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractNodeFactory class.
 * 
 * @author jfinkels
 */
public class AbstractNodeFactoryTester {

  /** The factory under test. */
  private AbstractNodeFactory<Integer, Node<Integer>> factory = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    try {
      this.factory = new ExampleNodeFactory();
    } catch (final ClassNotFoundException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.gp.impl.AbstractNodeFactory#createNode()}.
   */
  @Test
  public void testCreateNode() {

    Node<Integer> node = null;
    try {
      node = this.factory.createNode();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    assertTrue(node instanceof IntegerNode);
  }

  /**
   * Test method for {@link jmona.gp.impl.AbstractNodeFactory#nodeClasses()}.
   */
  @Test
  public void testNodeClasses() {
    assertEquals(1, this.factory.nodeClasses().size());
    assertTrue(this.factory.nodeClasses().contains(ExampleTerminalNode.class));
  }

}
