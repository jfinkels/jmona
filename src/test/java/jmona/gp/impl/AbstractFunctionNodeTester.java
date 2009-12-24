/**
 * AbstractFunctionNodeTester.java
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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.Vector;

import jmona.gp.EvaluationException;
import jmona.gp.FunctionNode;
import jmona.gp.Node;
import jmona.gp.impl.example.ExampleBinaryNode;
import jmona.gp.impl.example.ExampleTerminalNode;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the AbstractFunctionNode class.
 * 
 * @author jfinkels
 */
public class AbstractFunctionNodeTester {

  /**
   * Test method for
   * {@link jmona.gp.impl.AbstractFunctionNode#attachChildToParent(jmona.gp.FunctionNode, jmona.gp.Node)}
   * .
   */
  @Test
  public void testAttachChildToParent() {
    final FunctionNode<Integer> parent = new ExampleBinaryNode();
    final Node<Integer> child1 = new ExampleTerminalNode();
    final Node<Integer> child2 = new ExampleTerminalNode();

    AbstractFunctionNode.attachChildToParent(parent, child1);

    assertSame(child1, parent.children().get(0));
    assertSame(parent, child1.parent());

    AbstractFunctionNode.attachChildToParent(parent, child2);

    assertSame(child2, parent.children().get(1));
    assertSame(parent, child2.parent());

  }

  /**
   * Test method for
   * {@link jmona.gp.impl.AbstractFunctionNode#deepCopyChildren(jmona.gp.FunctionNode, java.util.List)}
   * .
   */
  @Test
  public void testDeepCopyChildren() {
    final FunctionNode<Integer> parent = new ExampleBinaryNode();
    final Node<Integer> child1 = new ExampleTerminalNode();
    final Node<Integer> child2 = new ExampleTerminalNode();

    final List<Node<Integer>> children = new Vector<Node<Integer>>();

    children.add(child1);
    children.add(child2);

    AbstractFunctionNode.deepCopyChildren(parent, children);

    assertNotSame(parent.children().get(0), children.get(0));
    assertNotSame(parent.children().get(1), children.get(1));

    try {
      assertEquals(children.get(0).evaluate(), parent.children().get(0)
          .evaluate());
      assertEquals(children.get(1).evaluate().intValue(), parent.children()
          .get(1).evaluate().intValue());
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    }

    assertSame(parent, parent.children().get(0).parent());
    assertSame(parent, parent.children().get(1).parent());
  }

  /**
   * Test method for {@link jmona.gp.impl.AbstractFunctionNode#children()}.
   */
  @Test
  public void testChildren() {
    final AbstractFunctionNode<Integer> node = new ExampleBinaryNode();
    assertEquals(0, node.children().size());
    node.children().add(new ExampleTerminalNode());
    node.children().add(new ExampleTerminalNode());

    assertEquals(2, node.children().size());
  }

}
