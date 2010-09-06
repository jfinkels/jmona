/**
 * CooperativeStrategyTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.example.ipd.strategy;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Test class for the CooperativeStrategy class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class CooperativeStrategyTester {

  /** The number of actions to get from a strategy. */
  public static final int NUM_ACTIONS = 100;

  /**
   * Test method for
   * {@link jmona.example.ipd.strategy.CooperativeStrategy#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final CooperativeStrategy strategy = new CooperativeStrategy();
    final CooperativeStrategy clone = strategy.deepCopy();
    assertNotSame(strategy, clone);
    for (int i = 0; i < NUM_ACTIONS; ++i) {
      assertSame(strategy.nextAction(), clone.nextAction());
    }

  }

  /**
   * Test method for
   * {@link jmona.example.ipd.strategy.CooperativeStrategy#nextAction()}.
   */
  @Test
  public void testNextAction() {
    final CooperativeStrategy strategy = new CooperativeStrategy();
    for (int i = 0; i < NUM_ACTIONS; ++i) {
      assertSame(Action.COOPERATE, strategy.nextAction());
    }
  }

}
