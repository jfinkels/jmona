/**
 * DoEachNodeTester.java
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
package jmona.example.anttrail.nodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jmona.CopyingException;
import jmona.example.anttrail.Ant;
import jmona.example.anttrail.CoordinatePair;
import jmona.example.anttrail.DefaultAnt;
import jmona.example.anttrail.Orientation;
import jmona.example.anttrail.Trail;
import jmona.gp.ExecutionException;
import jmona.gp.impl.TreeUtils;
import jmona.test.Util;

import org.junit.Test;

/**
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class DoEachNodeTester {

  /**
   * Test method for
   * {@link jmona.example.anttrail.nodes.DoEachNode#DoEachNode(jmona.example.anttrail.Ant, int)}
   * .
   */
  @Test
  public void testDoEachNode() {
    final Ant ant = new DefaultAnt(new Trail(new boolean[2][2]));

    ant.setMaxSteps(4);

    final int arity = 2;

    final DoEachNode node = new DoEachNode(ant, arity);

    final AntNode leftChild = new MoveForwardNode(ant);
    final AntNode rightChild = new TurnRightNode(ant);

    TreeUtils.attachChildToParent(node, leftChild);
    TreeUtils.attachChildToParent(node, rightChild);

    assertEquals(arity, node.arity());

    DoEachNode clonedNode = null;
    try {
      clonedNode = node.deepCopy();
    } catch (final CopyingException exception) {
      Util.fail(exception);
    }

    assertNotSame(clonedNode, node);
    assertTrue(clonedNode instanceof DoEachNode);

    assertNotSame(node.children().get(0), clonedNode.children().get(0));
    assertEquals(node.children().get(0).getClass(), clonedNode.children()
        .get(0).getClass());
    assertNotSame(node.children().get(1), clonedNode.children().get(1));
    assertEquals(node.children().get(1).getClass(), clonedNode.children()
        .get(1).getClass());

    try {
      node.execute();
    } catch (final ExecutionException exception) {
      Util.fail(exception);
    }

    assertEquals(Orientation.west(), ant.orientation());
    assertEquals(new CoordinatePair(1, 1), ant.location());

    assertTrue(node.toString().startsWith(DoEachNode.SYMBOL));
  }
}
