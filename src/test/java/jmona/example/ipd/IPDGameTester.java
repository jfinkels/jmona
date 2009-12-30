/**
 * IPDGameTester.java
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
package jmona.example.ipd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import jmona.example.ipd.strategy.CooperativeStrategy;
import jmona.example.ipd.strategy.IPDStrategy;
import jmona.example.ipd.strategy.RuthlessStrategy;
import jmona.game.GameplayException;
import jmona.game.TwoPlayerGameResult;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the IPDGame class.
 * 
 * @author jfinkels
 */
public class IPDGameTester {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** A strategy. */
  private IPDStrategy cooperativeStrategy = null;
  /** The game under test. */
  private IPDGame game = null;
  /** Another strategy. */
  private IPDStrategy ruthlessStrategy = null;
  /** A payoff value. */
  public static final int PAYOFF = 10;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.game = new IPDGame();

    // only do one iteration
    this.game.setIterations(1);

    this.cooperativeStrategy = new CooperativeStrategy();
    this.ruthlessStrategy = new RuthlessStrategy();
  }

  /**
   * Test method for
   * {@link jmona.example.ipd.IPDGame#play(jmona.example.ipd.strategy.IPDStrategy, jmona.example.ipd.strategy.IPDStrategy)}
   * .
   */
  @Test
  public void testPlay() {
    TwoPlayerGameResult<IPDStrategy> result = null;
    try {
      result = this.game.play(this.cooperativeStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(IPDGame.DEFAULT_SUCKERS_PAYOFF, result.scoreOfStrategy1(),
        ZERO_DELTA);
    assertEquals(IPDGame.DEFAULT_TEMPTATION, result.scoreOfStrategy2(),
        ZERO_DELTA);
    assertSame(this.ruthlessStrategy, result.winner());

    try {
      result = this.game.play(this.ruthlessStrategy, this.cooperativeStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(IPDGame.DEFAULT_TEMPTATION, result.scoreOfStrategy1(),
        ZERO_DELTA);
    assertEquals(IPDGame.DEFAULT_SUCKERS_PAYOFF, result.scoreOfStrategy2(),
        ZERO_DELTA);
    assertSame(this.ruthlessStrategy, result.winner());

    try {
      result = this.game.play(this.cooperativeStrategy,
          this.cooperativeStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(result.scoreOfStrategy1(), result.scoreOfStrategy2(),
        ZERO_DELTA);

    try {
      result = this.game.play(this.ruthlessStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(result.scoreOfStrategy1(), result.scoreOfStrategy2(),
        ZERO_DELTA);
  }

  /**
   * Test method for {@link jmona.example.ipd.IPDGame#setIterations(int)}.
   */
  @Test
  public void testSetIterations() {
    int newIterations = 0;
    this.game.setIterations(newIterations);

    TwoPlayerGameResult<IPDStrategy> result = null;
    try {
      result = this.game.play(this.cooperativeStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(0.0, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(0.0, result.scoreOfStrategy2(), ZERO_DELTA);

    this.game.setIterations(newIterations);
    try {
      result = this.game.play(this.cooperativeStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(0.0, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(IPDGame.DEFAULT_TEMPTATION * newIterations, result
        .scoreOfStrategy2(), ZERO_DELTA);
    assertSame(this.ruthlessStrategy, result.winner());

    newIterations = 2;
    this.game.setIterations(newIterations);
    try {
      result = this.game.play(this.cooperativeStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(0.0, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(IPDGame.DEFAULT_TEMPTATION * newIterations, result
        .scoreOfStrategy2(), ZERO_DELTA);
    assertSame(this.ruthlessStrategy, result.winner());
  }

  /**
   * Test method for
   * {@link jmona.example.ipd.IPDGame#setPunishmentForMutualDefection(int)} .
   */
  @Test
  public void testSetPunishmentForMutualDefection() {

    int punishmentPayoff = 0;
    this.game.setPunishmentForMutualDefection(punishmentPayoff);

    TwoPlayerGameResult<IPDStrategy> result = null;
    try {
      result = this.game.play(this.ruthlessStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(punishmentPayoff, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(punishmentPayoff, result.scoreOfStrategy2(), ZERO_DELTA);

    punishmentPayoff = PAYOFF;
    this.game.setPunishmentForMutualDefection(punishmentPayoff);
    try {
      result = this.game.play(this.ruthlessStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(punishmentPayoff, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(punishmentPayoff, result.scoreOfStrategy2(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.example.ipd.IPDGame#setRewardForMutualCooperation(int)}.
   */
  @Test
  public void testSetRewardForMutualCooperation() {
    int rewardPayoff = 0;
    this.game.setRewardForMutualCooperation(rewardPayoff);

    TwoPlayerGameResult<IPDStrategy> result = null;
    try {
      result = this.game.play(this.cooperativeStrategy,
          this.cooperativeStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(rewardPayoff, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(rewardPayoff, result.scoreOfStrategy2(), ZERO_DELTA);

    rewardPayoff = PAYOFF;
    this.game.setRewardForMutualCooperation(rewardPayoff);
    try {
      result = this.game.play(this.cooperativeStrategy,
          this.cooperativeStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(rewardPayoff, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(rewardPayoff, result.scoreOfStrategy2(), ZERO_DELTA);
  }

  /**
   * Test method for {@link jmona.example.ipd.IPDGame#setSuckersPayoff(int)}.
   */
  @Test
  public void testSetSuckersPayoff() {
    int newSuckersPayoff = 0;
    this.game.setSuckersPayoff(newSuckersPayoff);

    TwoPlayerGameResult<IPDStrategy> result = null;
    try {
      result = this.game.play(this.cooperativeStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(newSuckersPayoff, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(IPDGame.DEFAULT_TEMPTATION, result.scoreOfStrategy2(),
        ZERO_DELTA);

    newSuckersPayoff = PAYOFF;
    this.game.setSuckersPayoff(newSuckersPayoff);
    try {
      result = this.game.play(this.cooperativeStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(newSuckersPayoff, result.scoreOfStrategy1(), ZERO_DELTA);
    assertEquals(IPDGame.DEFAULT_TEMPTATION, result.scoreOfStrategy2(),
        ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.example.ipd.IPDGame#setTemptationToDefect(int)}.
   */
  @Test
  public void testSetTemptationToDefect() {
    int newTemptationPayoff = 0;
    this.game.setTemptationToDefect(newTemptationPayoff);

    TwoPlayerGameResult<IPDStrategy> result = null;
    try {
      result = this.game.play(this.cooperativeStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(IPDGame.DEFAULT_SUCKERS_PAYOFF, result.scoreOfStrategy1(),
        ZERO_DELTA);
    assertEquals(newTemptationPayoff, result.scoreOfStrategy2(), ZERO_DELTA);

    newTemptationPayoff = PAYOFF;
    this.game.setTemptationToDefect(newTemptationPayoff);

    try {
      result = this.game.play(this.cooperativeStrategy, this.ruthlessStrategy);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    assertEquals(IPDGame.DEFAULT_SUCKERS_PAYOFF, result.scoreOfStrategy1(),
        ZERO_DELTA);
    assertEquals(newTemptationPayoff, result.scoreOfStrategy2(), ZERO_DELTA);
  }

}
