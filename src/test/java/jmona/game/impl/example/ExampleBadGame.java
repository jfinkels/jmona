/**
 * ExampleBadGame.java
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
package jmona.game.impl.example;

import jmona.game.GameplayException;
import jmona.game.TwoPlayerGame;
import jmona.game.TwoPlayerGameResult;

/**
 * An example game which always throws an Exception.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExampleBadGame implements TwoPlayerGame<ExampleStrategy> {

  /**
   * Always throws a GameplayException.
   * 
   * @param strategy1
   *          This parameter is ignored.
   * @param strategy2
   *          This parameter is ignored.
   * @return Never returns.
   * @throws GameplayException
   *           Always throws this Exception.
   * @see jmona.game.TwoPlayerGame#play(jmona.game.Strategy,
   *      jmona.game.Strategy)
   */
  @Override
  public TwoPlayerGameResult<ExampleStrategy> play(
      final ExampleStrategy strategy1, final ExampleStrategy strategy2)
      throws GameplayException {
    throw new GameplayException();
  }

}
