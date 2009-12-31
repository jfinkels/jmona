/**
 * IPDStrategy.java
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

import java.util.List;
import java.util.Vector;

import jmona.game.Strategy;
import jmona.impl.Pair;

/**
 * A strategy for playing the iterated prisoner's dilemma game.
 * 
 * @author Jeffrey Finkelstein
 */
public abstract class IPDStrategy implements Strategy {
  /** The default length of this strategy's memory for past actions. */
  public static final int DEFAULT_MEMORY_LENGTH = 3;

  /**
   * The queue of most recent actions performed by this strategy and the
   * adversary.
   */
  private List<Pair<Action, Action>> memory = new Vector<Pair<Action, Action>>();

  /** The length of this strategy's memory for past actions. */
  private int memoryLength = DEFAULT_MEMORY_LENGTH;

  /**
   * Add the specified pair of actions to the memory of past actions, and forget
   * any older pairs of actions beyond the maximum memory length.
   * 
   * @param actions
   *          The pair of actions to remember.
   */
  public void addToMemory(final Pair<Action, Action> actions) {
    while (this.memory.size() >= this.memoryLength) {
      this.memory.remove(0);
    }

    this.memory.add(actions);
  }

  /**
   * Get the memory of past actions.
   * 
   * @return The memory of past actions.
   */
  protected List<Pair<Action, Action>> memory() {
    return this.memory;
  }

  /**
   * Get the length of this strategy's memory for past actions.
   * 
   * @return The length of this strategy's memory for past actions.
   */
  public int memoryLength() {
    return this.memoryLength;
  }

  /**
   * Get the next action to perform.
   * 
   * @return The next action to perform.
   */
  public abstract Action nextAction();

  /**
   * Resets this strategy to an initial state with no memory of previous
   * actions.
   */
  @Override
  public void reset() {
    this.memory.clear();
  }

  /**
   * Set the length of this strategy's memory for past actions.
   * 
   * @param newMemoryLength
   *          The length of this strategy's memory for past actions.
   */
  public void setMemoryLength(final int newMemoryLength) {
    this.memoryLength = newMemoryLength;
  }
}
