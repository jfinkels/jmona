/**
 * PavlovStrategyTester.java
 * 
 * Copyright 2009 Jeffrey Finkelstein
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
import static org.junit.Assert.assertSame;
import jmona.Pair;
import jmona.example.ipd.strategy.Action;
import jmona.example.ipd.strategy.PavlovStrategy;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PavlovStrategy class.
 * 
 * @author jfinkels
 */
public class PavlovStrategyTester {

  /** The strategy under test. */
  private PavlovStrategy strategy = null;

  /** Establish a fixture for test in this class. */
  @Before
  public final void setUp() {
    this.strategy = new PavlovStrategy();
  }

  /**
   * Test method for
   * {@link jmona.example.ipd.strategy.PavlovStrategy#deepCopy()}.
   */
  @Test
  public void testDeepCopy() {
    final Pair<Action, Action> pair = new Pair<Action, Action>(
        Action.DEFECT, Action.COOPERATE);
    strategy.addToMemory(pair);

    final PavlovStrategy clone = strategy.deepCopy();
    assertNotSame(clone, strategy);
    assertEquals(1, clone.memory().size());
    assertSame(pair, clone.memory().get(0));
  }

  /**
   * Test method for
   * {@link jmona.example.ipd.strategy.PavlovStrategy#nextAction()}.
   */
  @Test
  public void testNextAction() {

    // first action should always be cooperate

    assertEquals(Action.COOPERATE, this.strategy.nextAction());

    // next action should cooperate if the previous actions were the same

    this.strategy.addToMemory(new Pair<Action, Action>(Action.DEFECT,
        Action.DEFECT));
    assertEquals(Action.COOPERATE, this.strategy.nextAction());

    this.strategy.addToMemory(new Pair<Action, Action>(
        Action.COOPERATE, Action.COOPERATE));
    assertEquals(Action.COOPERATE, this.strategy.nextAction());

    // next action should defect if the previous actions were different

    this.strategy.addToMemory(new Pair<Action, Action>(Action.DEFECT,
        Action.COOPERATE));
    assertEquals(Action.DEFECT, this.strategy.nextAction());

    this.strategy.addToMemory(new Pair<Action, Action>(
        Action.COOPERATE, Action.DEFECT));
    assertEquals(Action.DEFECT, this.strategy.nextAction());
  }

}
