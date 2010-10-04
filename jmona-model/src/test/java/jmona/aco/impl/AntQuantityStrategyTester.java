/**
 * AntQuantityStrategyTester.java
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
package jmona.aco.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the AntQuantityStrategy class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class AntQuantityStrategyTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /**
   * Test method for
   * {@link jmona.aco.impl.AntQuantityStrategy#pheromoneToAddFullCycle(double)}
   * and
   * {@link jmona.aco.impl.AntQuantityStrategy#pheromoneToAddSingleEdge(double)}
   * .
   */
  @Test
  public void testPheromoneToAddFullCycle() {
    final AntQuantityStrategy strategy = new AntQuantityStrategy();
    final double distance = 100;

    assertEquals(0, strategy.pheromoneToAddFullCycle(distance), ZERO_DELTA);

    assertEquals(strategy.pheromoneQuantityIndex() / distance, strategy
        .pheromoneToAddSingleEdge(distance), ZERO_DELTA);
  }
}
