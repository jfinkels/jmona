/**
 * IPDGame.java
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
package jmona.example.game.ipd;

import jmona.ImmutablePair;
import jmona.example.game.ipd.strategy.Action;
import jmona.example.game.ipd.strategy.IPDStrategy;
import jmona.game.GameplayException;
import jmona.game.TwoPlayerGame;
import jmona.game.TwoPlayerGameResult;
import jmona.game.impl.DefaultTwoPlayerGameResult;

/**
 * Perform the iterated prisoner's dilemma game for a specified number of
 * iterations.
 * 
 * <p>
 * In the non-iterative prisoner's dilemma game, two strategies simultaneously
 * choose exactly one action to perform, either "cooperate" or "defect". Each
 * strategy receives a payoff dependent on the actions of <em>both</em>
 * strategies. In the iterated prisoner's dilemma game, the prisoner's dilemma
 * game is played multiple times, and each strategy may have a memory for past
 * actions by itself and its adversary.
 * </p>
 * 
 * <p>
 * See the figure below for the payoff matrix for actions performed on each
 * iteration of the game.
 * 
 * <table style="border-collapse:collapse; margin: 0em auto 0em auto; border:thin solid black;">
 * <tbody>
 * <tr>
 * <td style="font-style:italic; text-align: center;">payoffs</td>
 * <th style="border-left:thin solid black; border-right:thin solid black;">
 * strategy 2<br/>
 * cooperates</th>
 * <th style="border-left:thin solid black; border-right:thin solid black;">
 * strategy 2<br/>
 * defects</th>
 * </tr>
 * <tr style="border-top:thin solid black; border-bottom:thin solid black;">
 * <th style="border-right:thin solid black;">
 * strategy 1<br/>
 * cooperates</th>
 * <td style="text-align:center; font-style=italic; border-right:thin solid black;">
 * (r,r)</td>
 * <td style="text-align:center; font-style=italic; border-right:thin solid black;">
 * (s,t)</td>
 * </tr>
 * <tr style="border-top:thin solid black; border-bottom:thin solid black;">
 * <th style="border-right:thin solid black;">
 * strategy 1<br/>
 * defects</th>
 * <td style="text-align:center; font-style=italic; border-right:thin solid black;">
 * (t,s)</td>
 * <td style="text-align:center; font-style=italic; border-right: thin solid black;">
 * (p,p)</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * In this matrix, the payoff for strategy 1 is the first element in each tuple,
 * and the payoff for strategy 2 is the second element in each tuple. The payoff
 * <em>r</em> is the reward for mutual cooperation, <em>s</em> is the sucker's
 * payoff, <em>t</em> is the temptation to defect, and <em>p</em> is the
 * punishment for mutual defection.
 * </p>
 * 
 * <p>
 * Note that some invariants should hold for payoffs in the iterated prisoner's
 * dilemma:
 * <ol>
 * <li><em>t > r > p > s</em></li>
 * <li><em>2r > t + s</em></li>
 * </ol>
 * </p>
 * 
 * <p>
 * For more information, see <a
 * href="http://en.wikipedia.org/wiki/Prisoner%27s_dilemma#Generalized_form"
 * >Wikipedia's article on the prisoner's dilemma</a>.
 * </p>
 * 
 * @author jfinkels
 */
public class IPDGame implements TwoPlayerGame<IPDStrategy> {

  /** The default number of iterations over which to play the game. */
  public static final int DEFAULT_ITERATIONS = 50;
  /** The default punishment payoff for mutual defection. */
  public static final int DEFAULT_PUNISHMENT = 1;
  /** The default reward payoff for mutual cooperation. */
  public static final int DEFAULT_REWARD = 3;
  /** The default sucker's payoff for cooperating when the adversary defects. */
  public static final int DEFAULT_SUCKERS_PAYOFF = 0;
  /** The default temptation payoff to defect when the adversary cooperates. */
  public static final int DEFAULT_TEMPTATION = 5;
  /** The number of iterations over which to play the game. */
  private int iterations = DEFAULT_ITERATIONS;
  /** The punishment payoff for mutual defection. */
  private int punishmentForMutualDefection = DEFAULT_PUNISHMENT;
  /** The reward payoff for mutual cooperation. */
  private int rewardForMutualCooperation = DEFAULT_REWARD;
  /** The sucker's payoff for cooperating when the adversary defects. */
  private int suckersPayoff = DEFAULT_SUCKERS_PAYOFF;
  /** The temptation payoff to defect when the adversary cooperates. */
  private int temptationPayoff = DEFAULT_TEMPTATION;

  /**
   * 
   * @param strategy1
   *          A strategy.
   * @param strategy2
   *          Another strategy.
   * @return The result of the game, including the scores of the strategies and
   *         the winner.
   * @throws GameplayException
   *           If the actions received from one of the strategies is not a known
   *           action.
   * @see jmona.game.TwoPlayerGame#play(jmona.game.Strategy,
   *      jmona.game.Strategy)
   */
  @Override
  public TwoPlayerGameResult<IPDStrategy> play(final IPDStrategy strategy1,
      final IPDStrategy strategy2) throws GameplayException {

    // initialize the score for each strategy to 0
    int scoreOfStrategy1 = 0;
    int scoreOfStrategy2 = 0;

    // perform the prisoner's dilemma game over multiple iterations
    Action actionOfStrategy1 = null;
    Action actionOfStrategy2 = null;
    ImmutablePair<Action, Action> actionsPerformedStrategy1 = null;
    ImmutablePair<Action, Action> actionsPerformedStrategy2 = null;
    for (int i = 0; i < this.iterations; ++i) {
      actionOfStrategy1 = strategy1.nextAction();
      actionOfStrategy2 = strategy2.nextAction();

      // both strategies receive the reward for mutual cooperation
      if (actionOfStrategy1.equals(Action.COOPERATE)
          && actionOfStrategy2.equals(Action.COOPERATE)) {

        scoreOfStrategy1 += this.rewardForMutualCooperation;
        scoreOfStrategy2 += this.rewardForMutualCooperation;

        // strategy 1 gets the suckers payoff, strategy 2 gets the temptation
      } else if (actionOfStrategy1.equals(Action.COOPERATE)
          && actionOfStrategy2.equals(Action.DEFECT)) {

        scoreOfStrategy1 += this.suckersPayoff;
        scoreOfStrategy2 += this.temptationPayoff;

        // strategy 1 gets the temptation, strategy 2 gets the suckers payoff
      } else if (actionOfStrategy1.equals(Action.DEFECT)
          && actionOfStrategy2.equals(Action.COOPERATE)) {

        scoreOfStrategy1 += this.temptationPayoff;
        scoreOfStrategy2 += this.suckersPayoff;

        // both strategies get the punishment for mutual defection
      } else if (actionOfStrategy1.equals(Action.DEFECT)
          && actionOfStrategy2.equals(Action.DEFECT)) {

        scoreOfStrategy1 += this.punishmentForMutualDefection;
        scoreOfStrategy2 += this.punishmentForMutualDefection;

      } else {
        throw new GameplayException("Failed to get an understandable action.");
      }

      // make pairs of actions performed to give to the memory of each strategy
      actionsPerformedStrategy1 = new ImmutablePair<Action, Action>(
          actionOfStrategy1, actionOfStrategy2);
      actionsPerformedStrategy2 = new ImmutablePair<Action, Action>(
          actionOfStrategy2, actionOfStrategy1);

      // add the actions to the memory of each strategy
      strategy1.addToMemory(actionsPerformedStrategy1);
      strategy2.addToMemory(actionsPerformedStrategy2);
    }

    // the strategy with the highest score is the winner of the game; in the
    // case of a tie, just choose one, because it doesn't matter
    IPDStrategy winner = null;
    if (scoreOfStrategy1 > scoreOfStrategy2) {
      winner = strategy1;
    } else {
      winner = strategy2;
    }

    // create a game result object to store the scores and the winner
    final DefaultTwoPlayerGameResult<IPDStrategy> result = new DefaultTwoPlayerGameResult<IPDStrategy>();
    result.setScoreOfStrategy1(scoreOfStrategy1);
    result.setScoreOfStrategy2(scoreOfStrategy2);
    result.setWinner(winner);

    return result;
  }

  /**
   * Set the number of iterations over which to play the game.
   * 
   * @param newIterations
   *          The number of iterations over which to play the game.
   */
  public void setIterations(final int newIterations) {
    this.iterations = newIterations;
  }

  /**
   * Set the punishment for mutual defection, that is, the payoff for both
   * strategies when both defect.
   * 
   * @param punishmentPayoff
   *          The payoff for both strategies when both defect.
   */
  public void setPunishmentForMutualDefection(final int punishmentPayoff) {
    this.punishmentForMutualDefection = punishmentPayoff;
  }

  /**
   * Set the reward for mutual cooperation, that is, the payoff for both
   * strategies when both cooperate.
   * 
   * @param rewardPayoff
   *          The payoff for both strategies when both cooperate.
   */
  public void setRewardForMutualCooperation(final int rewardPayoff) {
    this.rewardForMutualCooperation = rewardPayoff;
  }

  /**
   * Set the payoff for when a strategy cooperates and the adversary defects.
   * 
   * @param newSuckersPayoff
   *          The payoff for when a strategy cooperates and the adversary
   *          defects.
   */
  public void setSuckersPayoff(final int newSuckersPayoff) {
    this.suckersPayoff = newSuckersPayoff;
  }

  /**
   * Set the payoff for when a strategy defects and the adversary cooperates.
   * 
   * @param newTemptationPayoff
   *          The payoff for when a strategy defects and the adversary
   *          cooperates.
   */
  public void setTemptationToDefect(final int newTemptationPayoff) {
    this.temptationPayoff = newTemptationPayoff;
  }

}
