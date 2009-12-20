/**
 * GameEvolutionContext.java
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

import jmona.EvolutionException;
import jmona.Population;
import jmona.SelectionException;
import jmona.game.GameplayException;
import jmona.game.Strategy;
import jmona.game.TwoPlayerGame;
import jmona.game.TwoPlayerGameResult;
import jmona.impl.AbstractEvolutionContext;
import jmona.impl.DefaultPopulation;

/**
 * A context for playing Strategy objects against one another and reproducing
 * the most successful ones, without mutation or crossover.
 * 
 * @param <S>
 *          The type of Strategy to play a game against one another.
 * @author jfinkels
 */
public class GameEvolutionContext<S extends Strategy> extends
    AbstractEvolutionContext<S> {

  /** The game to play in this evolution. */
  private TwoPlayerGame<S> game = null;

  /**
   * Instantiate this EvolutionContext with the specified initial Population by
   * calling the corresponding constructor of the superclass.
   * 
   * @param initialPopulation
   *          The initial Population for the evolution.
   */
  public GameEvolutionContext(final Population<S> initialPopulation) {
    super(initialPopulation);
  }

  /**
   * Perform some sanity checks, that is, check that all necessary properties
   * have been set.
   * 
   * The necessary properties are the game, and the SelectionFunction.
   */
  @Override
  protected void sanityCheck() {
    if (this.game == null) {
      throw new NullPointerException("Game has not been set.");
    }
    if (this.selectionFunction() == null) {
      throw new NullPointerException("Selection function has not been set.");
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
   * A GameEvolution only requires a selection function; it doesn't use a
   * crossover function or a mutation function.
   * 
   * @throws EvolutionException
   *           {@inheritDoc}
   */
  @Override
  // TODO documentation
  protected void executeGenerationStep() throws EvolutionException {
    // perform a sanity check (i.e. make sure there are no null properties)
    try {
      this.sanityCheck();
    } catch (final NullPointerException exception) {
      throw new EvolutionException("Sanity check failed.", exception);
    }

    // each strategy plays each other strategy in the population once
    TwoPlayerGameResult<S> gameResult = null;
    S strategy1 = null;
    S strategy2 = null;
    for (int i = 0; i < this.currentPopulation().size(); ++i) {
      for (int j = i + 1; j < this.currentPopulation().size(); ++j) {

        strategy1 = this.currentPopulation().get(i);
        strategy2 = this.currentPopulation().get(j);

        try {
          gameResult = this.game.play(strategy1, strategy2);
        } catch (final GameplayException exception) {
          throw new EvolutionException("Failed to complete a game.", exception);
        }

        // at the end of the game, reset the strategies
        strategy1.reset();
        strategy2.reset();

        Double currentScore = null;
        if (this.currentFitnesses().containsKey(strategy1)) {
          // get the current score of the strategy
          currentScore = this.currentFitnesses().get(strategy1);

          // add the current score to the score just earned
          this.currentFitnesses().put(strategy1,
              currentScore + gameResult.scoreOfStrategy1());
        } else {
          this.currentFitnesses().put(strategy1, gameResult.scoreOfStrategy1());
        }

        if (this.currentFitnesses().containsKey(strategy2)) {
          // get the current score of the strategy
          currentScore = this.currentFitnesses().get(strategy2);

          // add the current score to the score just earned
          this.currentFitnesses().put(strategy2,
              currentScore + gameResult.scoreOfStrategy2());
        } else {
          this.currentFitnesses().put(strategy2, gameResult.scoreOfStrategy2());
        }
      }
    }

    // initialize a population to hold the selections for the next generation
    Population<S> nextPopulation = new DefaultPopulation<S>();

    try {
      // select strategies from the current population to go to the next one
      // TODO is it right to clone every strategy?
      while (nextPopulation.size() < this.currentPopulation().size()) {
        nextPopulation.add((S) this.selectionFunction().select(
            this.currentFitnesses()).deepCopy());
      }
    } catch (final SelectionException exception) {
      throw new EvolutionException("Failed to select an Individual.", exception);
    }

    // set the population for the next generation
    this.setCurrentPopulation(nextPopulation);

    // reset the current known fitnesses for the next generation
    this.currentFitnesses().clear();
  }

}
