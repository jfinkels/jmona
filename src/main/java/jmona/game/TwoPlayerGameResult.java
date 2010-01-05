/**
 * TwoPlayerGameResult.java
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
 * The result of a two player game.
 * 
 * @param <S>
 *          The type of Strategy which plays a Game.
 * @author Jeffrey Finkelstein
 */
public interface TwoPlayerGameResult<S extends Strategy> extends GameResult<S> {
  /**
   * Get the score of strategy 1 at the end of a game.
   * 
   * @return The score of strategy 1 at the end of a game.
   */
  double scoreOfStrategy1();

  /**
   * Get the score of strategy 2 at the end of a game.
   * 
   * @return The score of strategy 2 at the end of a game.
   */
  double scoreOfStrategy2();

  /**
   * Set the score of strategy 1 at the end of a game.
   * 
   * @param newScoreOfStrategy1
   *          The score of strategy 1 at the end of a game.
   */
  void setScoreOfStrategy1(final double newScoreOfStrategy1);

  /**
   * Set the score of strategy 2 at the end of a game.
   * 
   * @param newScoreOfStrategy2
   *          The score of strategy 2 at the end of a game.
   */
  void setScoreOfStrategy2(final double newScoreOfStrategy2);

  /*
   * void setStrategy1(final S newStrategy1); void setStrategy2(final S
   * newStrategy2); S strategy1(); S strategy2();
   */
  /**
   * Set the winning Strategy of the game.
   * 
   * @param newWinner
   *          The winning Strategy of the game.
   */
  void setWinner(final S newWinner);

  /**
   * Get the winning Strategy of the game.
   * 
   * @return The winning Strategy of the game.
   */
  S winner();
}
