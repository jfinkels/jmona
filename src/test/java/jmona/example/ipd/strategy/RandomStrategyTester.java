/**
 * RandomStrategyTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import jmona.functional.Range;

import org.junit.Test;

/**
 * Test class for the RandomStrategy class.
 * 
 * @author Jeffrey Finkelstein
 */
public class RandomStrategyTester {

  /** The number of actions to get from a strategy. */
  public static final int NUM_ACTIONS = 10000;

  /**
   * Test method for
   * {@link jmona.example.ipd.strategy.RandomStrategy#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final RandomStrategy strategy = new RandomStrategy();
    final RandomStrategy clone = strategy.deepCopy();
    assertNotSame(strategy, clone);
    assertTrue(clone instanceof RandomStrategy);
  }

  /**
   * Test method for
   * {@link jmona.example.ipd.strategy.RandomStrategy#nextAction()}.
   */
  @Test
  public void testNextAction() {
    final RandomStrategy strategy = new RandomStrategy();

    int defectCount = 0;
    int cooperateCount = 0;

    Action action = null;
    for (final int i : new Range(NUM_ACTIONS)) {
      action = strategy.nextAction();
      if (action.equals(Action.COOPERATE)) {
        cooperateCount += 1;
      } else {
        defectCount += 1;
      }
    }

    final int expected = NUM_ACTIONS / 2;
    final double delta = expected * 0.1;
    assertEquals(expected, cooperateCount, delta);
    assertEquals(expected, defectCount, delta);
  }

}
