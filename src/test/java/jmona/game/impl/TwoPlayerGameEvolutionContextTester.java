/**
 * TwoPlayerGameEvolutionContextTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.EvolutionException;
import jmona.PropertyNotSetException;
import jmona.SelectionException;
import jmona.game.GameplayException;
import jmona.game.TournamentGameSelection;
import jmona.game.TwoPlayerGame;
import jmona.game.impl.example.ExampleBadGame;
import jmona.game.impl.example.ExampleGame;
import jmona.game.impl.example.ExampleStrategy;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the TwoPlayerGameEvolutionContext class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class TwoPlayerGameEvolutionContextTester {

  /** The context under test. */
  private TwoPlayerGameEvolutionContext<ExampleStrategy> context = null;
  /** The population in the EvolutionContext. */
  private List<ExampleStrategy> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.population = new Vector<ExampleStrategy>();
    this.population.add(new ExampleStrategy());
    this.population.add(new ExampleStrategy());

    this.context = new TwoPlayerGameEvolutionContext<ExampleStrategy>(
        this.population);

    this.context.setGame(new ExampleGame());

    final RoundRobinTournament<ExampleStrategy> tournament = new RoundRobinTournament<ExampleStrategy>();
    tournament.setTournamentSize(this.population.size());
    this.context.setTournament(tournament);

  }

  /**
   * Test method for
   * {@link jmona.game.impl.TwoPlayerGameEvolutionContext#sanityCheck()}.
   */
  @Test
  public void testSanityCheck() {
    this.context = new TwoPlayerGameEvolutionContext<ExampleStrategy>(
        this.population);
    try {
      this.context.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final PropertyNotSetException exception) {
      this.context.setGame(new ExampleGame());
    }

    try {
      this.context.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final PropertyNotSetException exception) {
      // tournament has not been set
      this.context.setTournament(new RoundRobinTournament<ExampleStrategy>());
    }

    try {
      this.context.sanityCheck();
    } catch (final PropertyNotSetException exception) {
      TestUtils.fail(exception);
    }

    this.context.setGame(null);
    try {
      this.context.stepGeneration();
      Util.shouldHaveThrownException();
    } catch (final PropertyNotSetException exception) {
      assertTrue(exception instanceof PropertyNotSetException);
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.game.impl.TwoPlayerGameEvolutionContext#setGame(jmona.game.TwoPlayerGame)}
   * .
   */
  @Test
  public void testSetGame() {
    this.context.setGame(new ExampleBadGame());
    try {
      this.context.executeGenerationStep();
      Util.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      assertTrue(exception.getCause() instanceof SelectionException);
      assertTrue(exception.getCause().getCause() instanceof GameplayException);
    }
  }

  /**
   * Test method for
   * {@link jmona.game.impl.TwoPlayerGameEvolutionContext#stepGeneration()}.
   */
  @Test
  public void testStepGeneration() {

    int beforeSize = this.context.currentPopulation().size();

    try {
      this.context.executeGenerationStep();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

    assertEquals(beforeSize, this.context.currentPopulation().size());

    this.context.setTournament(new TournamentGameSelection<ExampleStrategy>() {
      @Override
      public ExampleStrategy select(final List<ExampleStrategy> aPopulation,
          final TwoPlayerGame<ExampleStrategy> game) throws SelectionException {
        throw new SelectionException();
      }
    });

    try {
      this.context.executeGenerationStep();
      Util.shouldHaveThrownException();
    } catch (final EvolutionException exception) {
      assertTrue(exception.getCause() instanceof SelectionException);
      final RoundRobinTournament<ExampleStrategy> tournament = new RoundRobinTournament<ExampleStrategy>();
      tournament.setTournamentSize(this.population.size());
      this.context.setTournament(tournament);
    }

    try {
      this.context.executeGenerationStep();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }
}
