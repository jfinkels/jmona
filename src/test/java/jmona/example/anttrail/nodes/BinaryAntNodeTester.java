/**
 * BinaryAntNodeTester.java
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
import static org.junit.Assert.assertSame;
import jmona.CopyingException;
import jmona.example.anttrail.Ant;
import jmona.example.anttrail.DefaultAnt;
import jmona.example.anttrail.Trail;
import jmona.gp.Node;

import org.junit.Test;

/**
 * Test class for the BinaryAntNode class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class BinaryAntNodeTester {

  /** Test method for BinaryAntNode methods. */
  @Test
  public void testBinaryAntNode() {
    final Ant ant = new DefaultAnt(new Trail(new boolean[1][1]));
    final BinaryAntNode node = new BinaryAntNode(ant) {

      @Override
      public void execute() {
      }

      @Override
      public Node deepCopy() throws CopyingException {
        return null;
      }

      @Override
      protected String symbol() {
        return null;
      }
    };

    assertEquals(BinaryAntNode.ARITY, node.arity());

    final AntNode leftChild = new MoveForwardNode(ant);
    final AntNode rightChild = new TurnLeftNode(ant);
    node.children().add(leftChild);
    node.children().add(rightChild);

    assertSame(leftChild, node.left());
    assertSame(rightChild, node.right());
  }

}
