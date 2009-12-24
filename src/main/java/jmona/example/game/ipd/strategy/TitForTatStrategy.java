/**
 * TitForTatStrategy.java
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

import jmona.Pair;

/**
 * A strategy which cooperates on the first iteration, then does whatever the
 * adversary did on the previous iteration.
 * 
 * @author jfinkels
 */
public class TitForTatStrategy extends IPDStrategy {

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public TitForTatStrategy deepCopy() {
    final TitForTatStrategy result = new TitForTatStrategy();
    for (final Pair<Action, Action> actions : this.memory()) {
      result.addToMemory(actions);
    }
    return result;
  }

  /**
   * Returns whatever action the adversary did in the previous iteration.
   * 
   * @return Whatever action the adversary did in the previous iteration.
   * @see jmona.example.game.ipd.strategy.IPDStrategy#nextAction()
   */
  @Override
  public Action nextAction() {

    // default to cooperate on the first round
    Action result = Action.COOPERATE;

    // if there is a history of past actions from which to read
    Pair<Action, Action> lastAction = null;
    if (this.memory().size() > 0) {

      // get the most recent pair of actions
      lastAction = this.memory().get(this.memory().size() - 1);

      // get the adversary's most recent action
      result = lastAction.right();
    }

    return result;
  }

}
