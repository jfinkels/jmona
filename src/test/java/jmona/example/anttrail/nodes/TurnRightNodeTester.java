/**
 * TurnRightNodeTester.java
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

import static org.junit.Assert.*;
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
public class TurnRightNodeTester {

  /**
   * Test method for
   * {@link jmona.example.anttrail.nodes.TurnRightNode#TurnRightNode(jmona.example.anttrail.Ant)}
   * .
   */
  @Test
  public void testTurnRightNode() {
    final boolean[][] foodAt = new boolean[2][2];
    foodAt[0][1] = true;
    final Trail trail = new Trail(foodAt);
    final Ant ant = new DefaultAnt(trail);

    final TurnRightNode node = new TurnRightNode(ant);

    node.execute();

    assertEquals(Orientation.south(), ant.orientation());
    assertEquals(new CoordinatePair(0, 0), ant.location());

    node.execute();

    assertEquals(Orientation.west(), ant.orientation());
    assertEquals(new CoordinatePair(0, 0), ant.location());

    final TurnRightNode clonedNode = node.deepCopy();

    assertNotSame(clonedNode, node);
    assertTrue(clonedNode instanceof TurnRightNode);

    clonedNode.execute();

    assertEquals(Orientation.north(), ant.orientation());
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.nodes.TurnRightNode#toString()}.
   */
  @Test
  public void testToString() {
    final TurnRightNode node = new TurnRightNode(null);
    assertEquals(TurnRightNode.SYMBOL, node.toString());
  }
}
