/**
 * MoveForwardNodeTester.java
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
import jmona.example.anttrail.Ant;
import jmona.example.anttrail.CoordinatePair;
import jmona.example.anttrail.DefaultAnt;
import jmona.example.anttrail.Orientation;
import jmona.example.anttrail.Trail;

import org.junit.Test;

/**
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class MoveForwardNodeTester {

  /**
   * Test method for
   * {@link jmona.example.anttrail.nodes.MoveForwardNode#MoveForwardNode(jmona.example.anttrail.Ant)}
   * .
   */
  @Test
  public void testMoveForwardNode() {
    final boolean[][] foodAt = new boolean[2][2];
    foodAt[0][1] = true;
    final Trail trail = new Trail(foodAt);
    final Ant ant = new DefaultAnt(trail);

    final MoveForwardNode node = new MoveForwardNode(ant);

    node.execute();

    assertEquals(1, ant.foodEaten());
    assertEquals(Orientation.east(), ant.orientation());
    assertEquals(new CoordinatePair(1, 0), ant.location());

    node.execute();

    assertEquals(1, ant.foodEaten());
    assertEquals(Orientation.east(), ant.orientation());
    assertEquals(new CoordinatePair(0, 0), ant.location());

    final MoveForwardNode clonedNode = node.deepCopy();

    assertNotSame(clonedNode, node);
    assertTrue(clonedNode instanceof MoveForwardNode);

    clonedNode.execute();

    assertEquals(new CoordinatePair(1, 0), ant.location());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.nodes.MoveForwardNode#toString()}.
   */
  @Test
  public void testToString() {
    final MoveForwardNode node = new MoveForwardNode(null);
    assertEquals(MoveForwardNode.SYMBOL, node.toString());
  }

}
