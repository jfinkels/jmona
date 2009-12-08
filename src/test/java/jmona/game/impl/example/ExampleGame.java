/**
 * ExampleGame.java
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
package jmona.game.impl.example;

import jmona.game.TwoPlayerGame;
import jmona.game.TwoPlayerGameResult;
import jmona.game.impl.DefaultTwoPlayerGameResult;

/**
 * An example game.
 * 
 * @author jfinkels
 */
public class ExampleGame implements TwoPlayerGame<ExampleStrategy> {

  /**
   * Returns a result containing the two score properties of the specified
   * ExampleStrategy objects, with the winner being the ExampleStrategy with the
   * greater score.
   * 
   * @param strategy1
   *          A Strategy.
   * @param strategy2
   *          Another Strategy.
   * @return A GameResult containing the two score properties of the specified
   *         ExampleStrategy objects, with the winner being the ExampleStrategy
   *         with the greater score.
   * @see jmona.game.TwoPlayerGame#play(jmona.game.Strategy,
   *      jmona.game.Strategy)
   */
  @Override
  public TwoPlayerGameResult<ExampleStrategy> play(
      final ExampleStrategy strategy1, final ExampleStrategy strategy2) {
    final TwoPlayerGameResult<ExampleStrategy> result = new DefaultTwoPlayerGameResult<ExampleStrategy>();

    ExampleStrategy winner = null;

    if (strategy1.score() >= strategy2.score()) {
      winner = strategy1;
    } else {
      winner = strategy2;
    }

    result.setScoreOfStrategy1(strategy1.score());
    result.setScoreOfStrategy2(strategy2.score());
    result.setWinner(winner);

    return result;
  }

}
