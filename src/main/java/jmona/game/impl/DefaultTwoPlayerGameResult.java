/**
 * DefaultTwoPlayerGameResult.java
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
package jmona.game.impl;

import jmona.game.Strategy;
import jmona.game.TwoPlayerGameResult;

/**
 * A class containing the results of a single game, including the winning
 * strategy and the scores for each strategy.
 * 
 * @param <S>
 *          The type of strategy employed in the game.
 * @author jfinkels
 */
public class DefaultTwoPlayerGameResult<S extends Strategy> implements
    TwoPlayerGameResult<S> {
  /** The score of strategy 1 at the end of the game. */
  private double scoreOfStrategy1 = 0;
  /** The score of strategy 2 at the end of the game. */
  private double scoreOfStrategy2 = 0;

  /** The strategy which won the game. */
  private S winner;

  /**
   * Get the score of strategy 1 at the end of the game.
   * 
   * @return The score of strategy 1 at the end of the game.
   */
  @Override
  public double scoreOfStrategy1() {
    return this.scoreOfStrategy1;
  }

  /**
   * Get the score of strategy 2 at the end of the game.
   * 
   * @return The score of strategy 2 at the end of the game.
   */
  @Override
  public double scoreOfStrategy2() {
    return this.scoreOfStrategy2;
  }

  /**
   * Set the score of strategy 1 at the end of the game.
   * 
   * @param newScoreOfStrategy1
   *          The score of strategy 1 at the end of the game.
   */
  @Override
  public void setScoreOfStrategy1(final double newScoreOfStrategy1) {
    this.scoreOfStrategy1 = newScoreOfStrategy1;
  }

  /**
   * Set the score of strategy 2 at the end of the game.
   * 
   * @param newScoreOfStrategy2
   *          The score of strategy 2 at the end of the game.
   */
  @Override
  public void setScoreOfStrategy2(final double newScoreOfStrategy2) {
    this.scoreOfStrategy2 = newScoreOfStrategy2;
  }

  /**
   * Set the strategy which won the game.
   * 
   * @param newWinner
   *          The strategy which won the game.
   */
  @Override
  public void setWinner(final S newWinner) {
    this.winner = newWinner;
  }

  /**
   * Get the strategy which won the game.
   * 
   * @return The strategy which won the game.
   */
  @Override
  public S winner() {
    return this.winner;
  }

}
