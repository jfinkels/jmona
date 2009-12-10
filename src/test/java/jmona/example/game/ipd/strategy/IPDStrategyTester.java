/**
 * IPDStrategyTester.java
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
package jmona.example.game.ipd.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import jmona.ImmutablePair;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the IPDStrategy class.
 * 
 * @author jfinkels
 */
public class IPDStrategyTester {

  /** The strategy under test. */
  private IPDStrategy strategy = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.strategy = new CooperativeStrategy();
  }

  /**
   * Test method for
   * {@link jmona.example.game.ipd.strategy.IPDStrategy#addToMemory(jmona.ImmutablePair)}
   * .
   */
  @Test
  public void testAddToMemory() {
    final ImmutablePair<Action, Action> pair1 = new ImmutablePair<Action, Action>(
        Action.DEFECT, Action.DEFECT);
    final ImmutablePair<Action, Action> pair2 = new ImmutablePair<Action, Action>(
        Action.DEFECT, Action.COOPERATE);
    final ImmutablePair<Action, Action> pair3 = new ImmutablePair<Action, Action>(
        Action.COOPERATE, Action.COOPERATE);
    final ImmutablePair<Action, Action> pair4 = new ImmutablePair<Action, Action>(
        Action.COOPERATE, Action.DEFECT);

    this.strategy.addToMemory(pair1);
    this.strategy.addToMemory(pair2);
    this.strategy.addToMemory(pair3);

    // this test only makes sense if the memory length is three
    // TODO generalize this test to any memory length
    assertEquals(3, this.strategy.memoryLength());

    assertSame(pair1, this.strategy.memory().get(0));
    assertSame(pair2, this.strategy.memory().get(1));
    assertSame(pair3, this.strategy.memory().get(2));
    assertFalse(this.strategy.memory().contains(pair4));

    this.strategy.addToMemory(pair4);

    assertSame(pair2, this.strategy.memory().get(0));
    assertSame(pair3, this.strategy.memory().get(1));
    assertSame(pair4, this.strategy.memory().get(2));
    assertFalse(this.strategy.memory().contains(pair1));
  }

  /**
   * Test method for
   * {@link jmona.example.game.ipd.strategy.IPDStrategy#memory()}.
   */
  @Test
  public void testMemory() {
    assertTrue(this.strategy.memory().isEmpty());
    final ImmutablePair<Action, Action> pair = new ImmutablePair<Action, Action>(
        Action.COOPERATE, Action.COOPERATE);
    this.strategy.addToMemory(pair);
    assertTrue(this.strategy.memory().contains(pair));
  }

  /**
   * Test method for
   * {@link jmona.example.game.ipd.strategy.IPDStrategy#memoryLength()}.
   */
  @Test
  public void testMemoryLength() {
    assertEquals(IPDStrategy.DEFAULT_MEMORY_LENGTH, this.strategy
        .memoryLength());
    this.strategy.setMemoryLength(1);
    assertEquals(1, this.strategy.memoryLength());
  }

  /**
   * Test method for {@link jmona.example.game.ipd.strategy.IPDStrategy#reset()}
   * .
   */
  @Test
  public void testReset() {
    this.strategy.addToMemory(new ImmutablePair<Action, Action>(Action.DEFECT,
        Action.COOPERATE));
    assertFalse(this.strategy.memory().isEmpty());
    this.strategy.reset();
    assertTrue(this.strategy.memory().isEmpty());
  }

}
