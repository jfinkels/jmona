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
package jmona.example.game.ipd;

import static org.junit.Assert.fail;
import jmona.example.game.ipd.strategy.CooperativeStrategy;
import jmona.example.game.ipd.strategy.IPDStrategy;
import jmona.example.game.ipd.strategy.RuthlessStrategy;
import jmona.game.GameplayException;
import jmona.game.TwoPlayerGameResult;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Test class for the IPDGame class.
 * 
 * @author jfinkels
 */
public class IPDGameTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(IPDGameTester.class);

  /** The game under test. */
  private IPDGame game = null;

  /** Establish a fixture for tests in this class. */
  public final void setUp() {
    this.game = new IPDGame();
  }

  /**
   * Test method for
   * {@link jmona.example.game.ipd.IPDGame#play(jmona.example.game.ipd.strategy.IPDStrategy, jmona.example.game.ipd.strategy.IPDStrategy)}
   * .
   */
  @Test
  public void testPlay() {
    fail("Not yet implemented");
    IPDStrategy strategy1 = new CooperativeStrategy();
    IPDStrategy strategy2 = new CooperativeStrategy();
    TwoPlayerGameResult<IPDStrategy> result = null;
    try {
      result = this.game.play(strategy1, strategy2);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    LOG.debug(result);

    strategy2 = new RuthlessStrategy();
    try {
      result = this.game.play(strategy1, strategy2);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }
    
    strategy1 = new RuthlessStrategy();
    try {
      result = this.game.play(strategy1, strategy2);
    } catch (final GameplayException exception) {
      Util.fail(exception);
    }

    LOG.debug(result);
  }

  /**
   * Test method for {@link jmona.example.game.ipd.IPDGame#setIterations(int)}.
   */
  @Test
  public void testSetIterations() {
    fail("Not yet implemented");
    int newIterations = 0;
    this.game.setIterations(newIterations);

    newIterations = 1;
    this.game.setIterations(newIterations);

    newIterations = 2;
    this.game.setIterations(newIterations);
  }

  /**
   * Test method for
   * {@link jmona.example.game.ipd.IPDGame#setPunishmentForMutualDefection(int)}
   * .
   */
  @Test
  public void testSetPunishmentForMutualDefection() {
    fail("Not yet implemented");
    int punishmentPayoff = 0;
    this.game.setPunishmentForMutualDefection(punishmentPayoff);

    punishmentPayoff = 10;
    this.game.setPunishmentForMutualDefection(punishmentPayoff);
  }

  /**
   * Test method for
   * {@link jmona.example.game.ipd.IPDGame#setRewardForMutualCooperation(int)}.
   */
  @Test
  public void testSetRewardForMutualCooperation() {
    fail("Not yet implemented");
    int rewardPayoff = 0;
    this.game.setRewardForMutualCooperation(rewardPayoff);

    rewardPayoff = 10;
    this.game.setRewardForMutualCooperation(rewardPayoff);
  }

  /**
   * Test method for
   * {@link jmona.example.game.ipd.IPDGame#setSuckersPayoff(int)}.
   */
  @Test
  public void testSetSuckersPayoff() {
    fail("Not yet implemented");

    int newSuckersPayoff = 0;
    this.game.setSuckersPayoff(newSuckersPayoff);

    newSuckersPayoff = 10;
    this.game.setSuckersPayoff(newSuckersPayoff);
  }

  /**
   * Test method for
   * {@link jmona.example.game.ipd.IPDGame#setTemptationToDefect(int)}.
   */
  @Test
  public void testSetTemptationToDefect() {
    fail("Not yet implemented");

    int newTemptationPayoff = 0;
    this.game.setTemptationToDefect(newTemptationPayoff);

    newTemptationPayoff = 10;
    this.game.setTemptationToDefect(newTemptationPayoff);
  }

}
