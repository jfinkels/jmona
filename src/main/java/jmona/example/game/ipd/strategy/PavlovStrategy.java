/**
 * PavlovStrategy.java
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

import jmona.ImmutablePair;

/**
 * A strategy which cooperates on the first iteration, cooperates whenever this
 * player and the adversary did the same thing on the previous iteration, and
 * defects whenever this player and the adversary did opposite things on the
 * previous iteration.
 * 
 * @author jfinkels
 */
public class PavlovStrategy extends IPDStrategy {

  /**
   * Returns a cooperate action if this strategy and the adversary did the same
   * action last round, and returns a defect action if this strategy and the
   * adversary did opposite actions last round.
   * 
   * @return An action based on whether the last pair of actions was the same or
   *         different.
   * @see jmona.example.game.ipd.strategy.IPDStrategy#nextAction()
   */
  @Override
  public Action nextAction() {

    // default to cooperate on the first round
    Action result = Action.COOPERATE;

    // if there is a history of past actions from which to read
    ImmutablePair<Action, Action> lastAction = null;
    if (this.memory().size() > 0) {

      // get the most recent pair of actions
      lastAction = this.memory().get(this.memory().size() - 1);

      // if this strategy and the adversary did the same action last round
      if (lastAction.left().equals(lastAction.right())) {
        result = Action.COOPERATE;
      } else { // this strategy and the adversary did opposite actions last time
        result = Action.DEFECT;
      }
    }

    return result;
  }

}
