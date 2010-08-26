/**
 * IfFoodAheadNodeTester.java
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
 * Test class for the IfFoodAheadNode class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class IfFoodAheadNodeTester {

  /**
   * Test method for
   * {@link jmona.example.anttrail.nodes.IfFoodAheadNode#IfFoodAheadNode(jmona.example.anttrail.Ant)}
   * .
   */
  @Test
  public void testIfFoodAheadNode() {
    final boolean[][] foodAt = new boolean[2][2];
    foodAt[0][1] = true;
    final Trail trail = new Trail(foodAt);
    final Ant ant = new DefaultAnt(trail);

    final MoveForwardNode leftChild = new MoveForwardNode(ant);
    final TurnRightNode rightChild = new TurnRightNode(ant);

    final IfFoodAheadNode node = new IfFoodAheadNode(ant);

    TreeUtils.attachChildToParent(node, leftChild);
    TreeUtils.attachChildToParent(node, rightChild);

    try {
      node.execute();
    } catch (final ExecutionException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(1, ant.foodEaten());
    assertEquals(Orientation.east(), ant.orientation());
    assertEquals(new CoordinatePair(1, 0), ant.location());

    try {
      node.execute();
    } catch (final ExecutionException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(1, ant.foodEaten());
    assertEquals(Orientation.south(), ant.orientation());
    assertEquals(new CoordinatePair(1, 0), ant.location());

    IfFoodAheadNode clonedNode = null;
    try {
      clonedNode = node.deepCopy();
    } catch (final CopyingException exception) {
      TestUtils.fail(exception);
    }

    assertNotSame(clonedNode, node);
    assertTrue(clonedNode instanceof IfFoodAheadNode);

    assertNotSame(node.children().get(0), clonedNode.children().get(0));
    assertEquals(node.children().get(0).getClass(), clonedNode.children()
        .get(0).getClass());
    assertNotSame(node.children().get(1), clonedNode.children().get(1));
    assertEquals(node.children().get(1).getClass(), clonedNode.children()
        .get(1).getClass());

    assertTrue(node.toString().startsWith(IfFoodAheadNode.SYMBOL));
  }

}
