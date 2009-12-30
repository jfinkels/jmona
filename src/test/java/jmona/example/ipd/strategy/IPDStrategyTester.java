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
package jmona.example.ipd.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.impl.Pair;
import jmona.impl.Range;

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
   * {@link jmona.example.ipd.strategy.IPDStrategy#addToMemory(jmona.impl.Pair)}
   * .
   */
  @Test
  public void testAddToMemory() {
    final int memoryLength = this.strategy.memoryLength();

    final List<Pair<Action, Action>> allPairs = new Vector<Pair<Action, Action>>();

    final int numberOfPairs = 10;

    // there are four possible permutations of two actions
    final int numberOfPermutations = 4;

    Action left = null;
    Action right = null;
    for (final int i : new Range(numberOfPairs)) {

      left = Action.COOPERATE;
      right = Action.COOPERATE;

      if (i % numberOfPermutations == 0) {
        right = Action.DEFECT;
      } else if (i % numberOfPermutations == 1) {
        left = Action.DEFECT;
      } else if (i % numberOfPermutations == 2) {
        left = Action.DEFECT;
        right = Action.DEFECT;
      }

      allPairs.add(new Pair<Action, Action>(left, right));
    }

    for (final int i : new Range(allPairs.size())) {

      this.strategy.addToMemory(allPairs.get(i));

      assertTrue(this.strategy.memory().size() <= this.strategy.memoryLength());

      for (int j = 0; j < memoryLength && i - j >= 0; ++j) {
        assertSame(allPairs.get(i - j), this.strategy.memory().get(
            this.strategy.memory().size() - j - 1));
      }
    }


    for (final int i : new Range(allPairs.size() - memoryLength)) {
      assertFalse(this.strategy.memory().contains(allPairs.get(i)));
    }
  }

  /**
   * Test method for {@link jmona.example.ipd.strategy.IPDStrategy#memory()}.
   */
  @Test
  public void testMemory() {
    assertTrue(this.strategy.memory().isEmpty());
    final Pair<Action, Action> pair = new Pair<Action, Action>(
        Action.COOPERATE, Action.COOPERATE);
    this.strategy.addToMemory(pair);
    assertTrue(this.strategy.memory().contains(pair));
  }

  /**
   * Test method for
   * {@link jmona.example.ipd.strategy.IPDStrategy#memoryLength()}.
   */
  @Test
  public void testMemoryLength() {
    assertEquals(IPDStrategy.DEFAULT_MEMORY_LENGTH, this.strategy
        .memoryLength());
    this.strategy.setMemoryLength(1);
    assertEquals(1, this.strategy.memoryLength());
  }

  /**
   * Test method for {@link jmona.example.ipd.strategy.IPDStrategy#reset()} .
   */
  @Test
  public void testReset() {
    this.strategy.addToMemory(new Pair<Action, Action>(Action.DEFECT,
        Action.COOPERATE));
    assertFalse(this.strategy.memory().isEmpty());
    this.strategy.reset();
    assertTrue(this.strategy.memory().isEmpty());
  }

}
