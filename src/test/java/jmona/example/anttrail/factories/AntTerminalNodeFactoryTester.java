/**
 * AntTerminalNodeFactoryTester.java
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
package jmona.example.anttrail.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jmona.example.anttrail.Ant;
import jmona.example.anttrail.DefaultAnt;
import jmona.example.anttrail.Trail;
import jmona.example.anttrail.nodes.AntNode;
import jmona.example.anttrail.nodes.DoEachNode;
import jmona.example.anttrail.nodes.MoveForwardNode;
import jmona.example.anttrail.nodes.TurnLeftNode;
import jmona.example.anttrail.nodes.TurnRightNode;
import jmona.functional.Range;
import jmona.gp.TerminalNode;

import org.junit.Test;

/**
 * Test class for the AntTerminalNodeFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntTerminalNodeFactoryTester {

  /** The number of nodes to create. */
  public static final int NUM_NODES = 10000;

  /**
   * Test method for
   * {@link jmona.example.anttrail.factories.AntTerminalNodeFactory#createObject()}
   * .
   */
  @Test
  public void testCreateObject() {
    final Ant ant = new DefaultAnt(new Trail(new boolean[2][2]));
    final AntTerminalNodeFactory factory = new AntTerminalNodeFactory();
    factory.setAnt(ant);

    int numForward = 0;
    int numLeft = 0;
    int numRight = 0;
    TerminalNode node = null;
    for (final int i : new Range(NUM_NODES)) {
      node = factory.createObject();
      assertTrue(node instanceof MoveForwardNode
          || node instanceof TurnRightNode || node instanceof TurnLeftNode);

      if (node instanceof MoveForwardNode) {
        numForward += 1;
      } else if (node instanceof TurnLeftNode) {
        numLeft += 1;
      } else {
        numRight += 1;
      }

      assertSame(ant, ((AntNode) node).ant());
    }

    final int expected = NUM_NODES / 3;
    final double delta = expected * 0.1;

    assertEquals(expected, numForward, delta);
    assertEquals(expected, numLeft, delta);
    assertEquals(expected, numRight, delta);
  }

}
