/**
 * AntTrailExecutorTester.java
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
package jmona.example.anttrail.gp;

import static org.junit.Assert.assertEquals;
import jmona.MappingException;
import jmona.example.anttrail.Ant;
import jmona.example.anttrail.DefaultAnt;
import jmona.example.anttrail.Trail;
import jmona.example.anttrail.nodes.DoEachNode;
import jmona.example.anttrail.nodes.MoveForwardNode;
import jmona.gp.Tree;
import jmona.gp.impl.DefaultTree;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the AntTrailExecutor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntTrailExecutorTester {

  /**
   * Test method for
   * {@link jmona.example.anttrail.gp.AntTrailExecutor#execute(jmona.gp.Tree)}.
   */
  @Test
  public void testExecute() {
    final int rows = 1;
    final int cols = 4;

    final boolean[][] foodAt = new boolean[rows][cols];

    foodAt[0][1] = true;
    foodAt[0][2] = true;
    foodAt[0][3] = true;

    final Trail trail = new Trail(foodAt);
    final Ant ant = new DefaultAnt(trail);
    ant.setMaxSteps(4);

    final MoveForwardNode moveForwardNode = new MoveForwardNode(ant);

    final DoEachNode doEachNode = new DoEachNode(ant, 1);
    doEachNode.children().add(moveForwardNode);

    final Tree tree = new DefaultTree(doEachNode);

    final AntTrailExecutor executor = new AntTrailExecutor();

    try {
      assertEquals(3, executor.execute(tree).intValue());
    } catch (final MappingException exception) {
      Util.fail(exception);
    }
  }

}
