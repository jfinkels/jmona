/**
 * TwoPlayerGameEvolutionContext.java
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

import java.util.List;
import java.util.Vector;

import jmona.CopyingException;
import jmona.DeepCopyable;
import jmona.EvolutionException;
import jmona.PropertyNotSetException;
import jmona.SelectionException;
import jmona.game.Strategy;
import jmona.game.TournamentGameSelection;
import jmona.game.TwoPlayerGame;
import jmona.impl.context.AbstractPopulationEvolutionContext;

/**
 * A context for playing Strategy objects against one another and reproducing
 * the most successful ones, without mutation or crossover.
 * 
 * @param <S>
 *          The type of Strategy to play a game against one another.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class TwoPlayerGameEvolutionContext<S extends Strategy & DeepCopyable<S>>
    extends AbstractPopulationEvolutionContext<S> {

  /** The game to play in this evolution. */
  private TwoPlayerGame<S> game = null;
  /** The tournament selection function. */
  private TournamentGameSelection<S> tournament = null;

  /**
   * Instantiate this EvolutionContext with the specified initial Population by
   * calling the corresponding constructor of the superclass.
   * 
   * @param initialPopulation
   *          The initial Population for the evolution.
   */
  public TwoPlayerGameEvolutionContext(final List<S> initialPopulation) {
    super(initialPopulation);
  }

  /**
   * Select Strategy objects for the next generation by playing tournaments and
   * selecting the winners.
   * 
   * The next generation is comprised of deep copied Strategy objects which were
   * tournament winners.
   * 
   * @throws EvolutionException
   *           {@inheritDoc}
   * @throws PropertyNotSetException
   *           If one of the necessary properties has not been set.
   */
  @Override
  protected void executeGenerationStep() throws EvolutionException {
    // perform a sanity check (i.e. make sure there are no null properties)
    this.sanityCheck();

    // initialize a population to hold the selections for the next generation
    final List<S> nextPopulation = new Vector<S>();

    try {
      // select strategies from the current population to go to the next one
      while (nextPopulation.size() < this.currentPopulation().size()) {
        nextPopulation.add(this.tournament.select(this.currentPopulation(),
            this.game).deepCopy());
      }
    } catch (final SelectionException exception) {
      throw new EvolutionException("Failed to select a Strategy.", exception);
    } catch (final CopyingException exception) {
      throw new EvolutionException("Failed to copy a Strategy.", exception);
    }

    // set the population for the next generation
    this.setCurrentPopulation(nextPopulation);
  }

  /**
   * Perform some sanity checks, that is, check that all necessary properties
   * have been set.
   * 
   * The necessary properties are the game, and the TournamentGameSelection.
   * 
   * @throws PropertyNotSetException
   *           If one of the necessary properties have not been set on this
   *           object.
   */
  protected void sanityCheck() {
    if (this.game == null) {
      throw new PropertyNotSetException("Game has not been set.");
    }

    if (this.tournament == null) {
      throw new PropertyNotSetException("Tournament has not been set.");
    }
  }

  /**
   * Set the game to play during this evolution.
   * 
   * @param newGame
   *          The game to play during this evolution.
   */
  public void setGame(final TwoPlayerGame<S> newGame) {
    this.game = newGame;
  }

  /**
   * Set the tournament used to select Strategy objects.
   * 
   * @param newTournament
   *          The tournament used to select Strategy objects.
   */
  public void setTournament(final TournamentGameSelection<S> newTournament) {
    this.tournament = newTournament;
  }

}
