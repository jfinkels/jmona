/**
 * AntFunctionNodeFactoryTester.java
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
import jfcommon.functional.Range;
import jmona.example.anttrail.Ant;
import jmona.example.anttrail.DefaultAnt;
import jmona.example.anttrail.Trail;
import jmona.example.anttrail.nodes.AntNode;
import jmona.example.anttrail.nodes.DoEachNode;
import jmona.example.anttrail.nodes.IfFoodAheadNode;
import jmona.gp.FunctionNode;

import org.junit.Test;

/**
 * Test class for the AntFunctionNodeFactory class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntFunctionNodeFactoryTester {

  /** The number of nodes to create. */
  public static final int NUM_NODES = 10000;

  /**
   * Test method for
   * {@link jmona.example.anttrail.factories.AntFunctionNodeFactory#createObject()}
   * .
   */
  @Test
  public void testCreateObject() {
    final Ant ant = new DefaultAnt(new Trail(new boolean[2][2]));
    final AntFunctionNodeFactory factory = new AntFunctionNodeFactory();
    factory.setAnt(ant);

    int numDoEachNodes = 0;
    int numIfFoodAheadNodes = 0;
    FunctionNode node = null;
    for (final int i : new Range(NUM_NODES)) {
      node = factory.createObject();
      assertTrue(node instanceof DoEachNode || node instanceof IfFoodAheadNode);

      if (node instanceof DoEachNode) {
        numDoEachNodes += 1;
      } else {
        numIfFoodAheadNodes += 1;
      }

      assertSame(ant, ((AntNode) node).ant());
    }

    final int expected = NUM_NODES / 2;
    final double delta = expected * 0.1;

    assertEquals(expected, numDoEachNodes, delta);
    assertEquals(expected, numIfFoodAheadNodes, delta);
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.factories.AntFunctionNodeFactory#setMaxArity(int)}
   * and
   * {@link jmona.example.anttrail.factories.AntFunctionNodeFactory#setMinArity(int)}
   * .
   */
  @Test
  public void testSetMaxArity() {
    final Ant ant = new DefaultAnt(new Trail(new boolean[2][2]));
    final AntFunctionNodeFactory factory = new AntFunctionNodeFactory();
    factory.setAnt(ant);

    final int newMaxArity = 100;
    final int newMinArity = 90;
    factory.setMaxArity(newMaxArity);
    factory.setMinArity(newMinArity);

    FunctionNode node = null;
    for (final int i : new Range(NUM_NODES)) {
      node = factory.createObject();

      if (node instanceof DoEachNode) {
        assertTrue(node.arity() >= newMinArity);
        assertTrue(node.arity() <= newMaxArity);
      }
    }
  }

}
