/**
 * AntTrailFitnessFunctionTester.java
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
import jmona.FitnessException;
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
 * Test class for the AntTrailFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AntTrailFitnessFunctionTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.example.anttrail.gp.AntTrailFitnessFunction#AntTrailFitnessFunction(double)}
   * .
   */
  @Test
  public void testAntTrailFitnessFunction() {
    final int max = 89;
    final AntTrailFitnessFunction function = new AntTrailFitnessFunction(max);
    assertEquals((double) max, function.extremum(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.example.anttrail.gp.AntTrailFitnessFunction#rawFitness(jmona.gp.Tree)}
   * .
   */
  @Test
  public void testRawFitness() {
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

    final AntTrailFitnessFunction function = new AntTrailFitnessFunction(3);

    try {
      assertEquals(3, function.rawFitness(tree), ZERO_DELTA);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }
  }

}
