/**
 * DefaultTwoPlayerGameResult.java
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
package jmona.game.impl;

import jmona.game.Strategy;
import jmona.game.TwoPlayerGameResult;

/**
 * A class containing the results of a single game, including the winning
 * strategy and the scores for each strategy.
 * 
 * @param <S>
 *          The type of strategy employed in the game.
 * @author Jeffrey Finkelstein
 * @since 0.1
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
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public double scoreOfStrategy1() {
    return this.scoreOfStrategy1;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public double scoreOfStrategy2() {
    return this.scoreOfStrategy2;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newScoreOfStrategy1
   *          {@inheritDoc}
   */
  @Override
  public void setScoreOfStrategy1(final double newScoreOfStrategy1) {
    this.scoreOfStrategy1 = newScoreOfStrategy1;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newScoreOfStrategy2
   *          {@inheritDoc}
   */
  @Override
  public void setScoreOfStrategy2(final double newScoreOfStrategy2) {
    this.scoreOfStrategy2 = newScoreOfStrategy2;
  }

  /**
   * {@inheritDoc}
   * 
   * @param newWinner
   *          {@inheritDoc}
   */
  @Override
  public void setWinner(final S newWinner) {
    this.winner = newWinner;
  }

  /**
   * Get a String representation of the content of this GameResult.
   * 
   * @return A String representation of the content of this GameResult.
   */
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder();

    result.append("Result[");
    result.append("strategy1score=" + this.scoreOfStrategy1);
    result.append(",strategy2score=" + this.scoreOfStrategy2);
    result.append(",winner=" + this.winner);
    result.append("]");

    return result.toString();
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public S winner() {
    return this.winner;
  }

}
