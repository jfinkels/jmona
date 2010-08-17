/**
 * AbstractPheromoneUpdateStrategy.java
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
 * Test class for the AbstractPheromoneUpdateStrategy class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class AbstractPheromoneUpdateStrategyTester {

  /**
   * Test method for
   * {@link jmona.aco.impl.AbstractPheromoneUpdateStrategy#pheromoneQuantityIndex()}
   * and
   * {@link jmona.aco.impl.AbstractPheromoneUpdateStrategy#setPheromoneQuantityIndex(int)}
   * .
   */
  @Test
  public void testPheromoneQuantityIndex() {
    final AbstractPheromoneUpdateStrategy strategy = new AbstractPheromoneUpdateStrategy() {

      @Override
      public double pheromoneToAddFullCycle(final double totalDistance) {
        return 0;
      }

      @Override
      public double pheromoneToAddSingleEdge(final double edgeDistance) {
        return 0;
      }
    };

    assertEquals(AbstractPheromoneUpdateStrategy.DEFAULT_TRAIL_QUANTITY_INDEX,
        strategy.pheromoneQuantityIndex());

    final int newPheromoneQuantityIndex = 10;
    strategy.setPheromoneQuantityIndex(newPheromoneQuantityIndex);

    assertEquals(newPheromoneQuantityIndex, strategy.pheromoneQuantityIndex());
  }

}
