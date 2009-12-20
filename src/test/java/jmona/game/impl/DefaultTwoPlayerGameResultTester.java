/**
 * DefaultTwoPlayerGameResultTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import jmona.game.impl.example.ExampleStrategy;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultTwoPlayerGameResult class.
 * 
 * @author jfinkels
 */
public class DefaultTwoPlayerGameResultTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(DefaultTwoPlayerGameResultTester.class);
  /** The score for strategy 1. */
  public static final double SCORE1 = 2.0;
  /** The score for strategy 2. */
  public static final double SCORE2 = 1.0;
  /** An example strategy. */
  public static final ExampleStrategy STRATEGY1 = new ExampleStrategy();
  /** An example strategy. */
  public static final ExampleStrategy STRATEGY2 = new ExampleStrategy();
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;

  /** The GameResult under test. */
  private DefaultTwoPlayerGameResult<ExampleStrategy> gameResult = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.gameResult = new DefaultTwoPlayerGameResult<ExampleStrategy>();
    this.gameResult.setScoreOfStrategy1(SCORE1);
    this.gameResult.setScoreOfStrategy2(SCORE2);
    this.gameResult.setWinner(STRATEGY2);
  }

  /**
   * Test method for
   * {@link jmona.game.impl.DefaultTwoPlayerGameResult#scoreOfStrategy1()}.
   */
  @Test
  public void testScoreOfStrategy1() {
    assertEquals(SCORE1, this.gameResult.scoreOfStrategy1(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.game.impl.DefaultTwoPlayerGameResult#scoreOfStrategy2()}.
   */
  @Test
  public void testScoreOfStrategy2() {
    assertEquals(SCORE2, this.gameResult.scoreOfStrategy2(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.game.impl.DefaultTwoPlayerGameResult#setScoreOfStrategy1(double)}
   * .
   */
  @Test
  public void testSetScoreOfStrategy1() {
    final double newScore = 10.0;
    this.gameResult.setScoreOfStrategy1(newScore);
    assertEquals(newScore, this.gameResult.scoreOfStrategy1(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.game.impl.DefaultTwoPlayerGameResult#setScoreOfStrategy2(double)}
   * .
   */
  @Test
  public void testSetScoreOfStrategy2() {
    final double newScore = 14.0;
    this.gameResult.setScoreOfStrategy2(newScore);
    assertEquals(newScore, this.gameResult.scoreOfStrategy2(), ZERO_DELTA);
  }

  /**
   * Test method for
   * {@link jmona.game.impl.DefaultTwoPlayerGameResult#setWinner(jmona.game.Strategy)}
   * .
   */
  @Test
  public void testSetWinner() {
    final ExampleStrategy newWinner = new ExampleStrategy();
    this.gameResult.setWinner(newWinner);
    assertSame(newWinner, this.gameResult.winner());
  }

  /**
   * Test method for
   * {@link jmona.game.impl.DefaultTwoPlayerGameResult#toString()} .
   */
  @Test
  public void testToString() {
    LOG.debug(this.gameResult.toString());
  }

  /**
   * Test method for {@link jmona.game.impl.DefaultTwoPlayerGameResult#winner()}
   * .
   */
  @Test
  public void testWinner() {
    assertSame(STRATEGY2, this.gameResult.winner());
  }
}
