/**
 * TwoPlayerGame.java
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
package jmona.game;

/**
 * A Game in which two Strategy objects compete.
 * 
 * @param <S>
 *          The type of Strategy which plays this Game.
 * @author Jeffrey Finkelstein
 */
public interface TwoPlayerGame<S extends Strategy> extends Game<S> {
  /**
   * Play this game with the two specified Strategy objects as adversaries.
   * 
   * @param strategy1
   *          A strategy for playing this Game.
   * @param strategy2
   *          Another strategy for playing this Game.
   * @return The result of playing this Game with the two specified Strategy
   *         objects as adversaries.
   * @throws GameplayException
   *           If there is a problem during gameplay.
   */
  TwoPlayerGameResult<S> play(final S strategy1, final S strategy2)
      throws GameplayException;
}
