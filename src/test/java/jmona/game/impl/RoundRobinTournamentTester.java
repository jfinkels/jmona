/**
 * RoundRobinTournamentTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import jfcommon.test.TestUtils;
import jmona.SelectionException;
import jmona.game.impl.example.ExampleGame;
import jmona.game.impl.example.ExampleStrategy;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the RoundRobinTournament class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class RoundRobinTournamentTester {

  /** The tournament under test in this class. */
  private RoundRobinTournament<ExampleStrategy> tournament = null;
  /** The list of Strategy objects on which to run the tournament. */
  private List<ExampleStrategy> population = null;
  /** A Strategy object in the tournament. */
  private ExampleStrategy strategy1 = null;
  /** A Strategy object in the tournament. */
  private ExampleStrategy strategy2 = null;

  /** The game with which to run the tournament. */
  private ExampleGame game = null;

  /** The number of tournaments to play. */
  public static final int NUM_TOURNAMENTS = 100;

  /** Establish a fixture for tests in this class. */
  @Before
  public void setUp() {
    this.tournament = new RoundRobinTournament<ExampleStrategy>();

    this.strategy1 = new ExampleStrategy(1);
    this.strategy2 = new ExampleStrategy(2);

    this.population = new ArrayList<ExampleStrategy>();
    this.population.add(this.strategy1);
    this.population.add(this.strategy2);

    this.game = new ExampleGame();
  }

  /**
   * Test method for
   * {@link jmona.game.impl.RoundRobinTournament#select(java.util.List, jmona.game.TwoPlayerGame)}
   * .
   */
  @Test
  public void testSelect() {
    this.tournament.setTournamentSize(2);
    for (int i = 0; i < NUM_TOURNAMENTS; ++i) {
      try {
        assertSame(this.strategy2,
            this.tournament.select(this.population, this.game));
      } catch (final SelectionException exception) {
        TestUtils.fail(exception);
      }
    }
  }

  /**
   * Test method for
   * {@link jmona.game.impl.RoundRobinTournament#setTournamentSize(int)}.
   */
  @Test
  public void testSetTournamentSize() {
    this.tournament.setTournamentSize(0);
    try {
      this.tournament.select(this.population, this.game);
      TestUtils.shouldHaveThrownException();
    } catch (final SelectionException exception) {
      // we expect this exception to be thrown
    }

    // tournament size of 1 is just a random strategy

    this.tournament.setTournamentSize(this.population.size() + 1);
    try {
      this.tournament.select(this.population, this.game);
      TestUtils.shouldHaveThrownException();
    } catch (final SelectionException exception) {
      // we expect this exception to be thrown
    }

    this.tournament.setTournamentSize(2);
    try {
      assertSame(this.strategy2,
          this.tournament.select(this.population, this.game));
    } catch (final SelectionException exception) {
      TestUtils.fail(exception);
    }

  }

}
