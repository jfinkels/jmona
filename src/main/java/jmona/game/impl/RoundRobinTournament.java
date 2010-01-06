/**
 * RoundRobinTournament.java
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmona.SelectionException;
import jmona.game.GameplayException;
import jmona.game.Strategy;
import jmona.game.TwoPlayerGame;
import jmona.game.TwoPlayerGameResult;
import jmona.random.RandomUtils;

/**
 * A SelectionFunction which chooses the Strategy with the highest score after a
 * round-robin tournament of a random subset of the population of all Strategy
 * objects.
 * 
 * @author Jeffrey Finkelstein
 * @param <S>
 *          The Strategy in the tournament.
 * @since 0.3
 */
public class RoundRobinTournament<S extends Strategy> implements
    TournamentGameSelection<S> {

  /**
   * The default number of Individuals to be chosen at random to compete in the
   * tournament.
   */
  public static final int DEFAULT_TOURNAMENT_SIZE = 5;
  /**
   * The comparator which compares Strategy objects with respect to their
   * cumulative scores after playing a full tournament.
   */
  private final ScoreComparator<S> scoreComparator = new ScoreComparator<S>();

  /**
   * The number of Individuals to be chosen at random to compete in the
   * tournament.
   */
  private int tournamentSize = DEFAULT_TOURNAMENT_SIZE;

  /**
   * Choose a random subset of the specified population and play a round-robin
   * tournament of the specified game with the chosen subset, the return the
   * Strategy with the greatest cumulative score after all matches are complete.
   * 
   * This is a single round-robin tournament, so each competitor plays each
   * other competitor exactly once.
   * 
   * @param population
   *          The population of Strategy objects from which to select.
   * @param game
   *          The game to play in a round-robin tournament of a random subset of
   *          the specified population.
   * @return The Strategy with the greatest cumulative score after the
   *         round-robin tournament of the specified game is complete.
   * @throws SelectionException
   *           If there is a problem during gameplay.
   * @see jmona.game.impl.TournamentGameSelection#select(List, TwoPlayerGame)
   */
  @Override
  public S select(final List<S> population, final TwoPlayerGame<S> game)
      throws SelectionException {

    // get a random subset of competitors to compete in the round-robin tourn.
    List<S> competitors = null;
    try {
      competitors = RandomUtils.sample(population,
          this.tournamentSize);
    } catch (final IllegalArgumentException exception) {
      throw new SelectionException(
          "Tournament size must be less than or equal to the total size of the population.",
          exception);
    }

    // initialize the score of each strategy to 0
    final Map<S, Double> scores = new HashMap<S, Double>();
    for (final S competitor : competitors) {
      scores.put(competitor, 0.0);
    }

    try {

      // iterate over each pair of competitors once
      TwoPlayerGameResult<S> result = null;
      for (final S competitor1 : competitors) {
        for (final S competitor2 : competitors.subList(competitors
            .indexOf(competitor1), competitors.size())) {

          // play these two competitors
          result = game.play(competitor1, competitor2);

          // add the score of this game to their cumulative scores
          scores.put(competitor1, scores.get(competitor1)
              + result.scoreOfStrategy1());
          scores.put(competitor2, scores.get(competitor2)
              + result.scoreOfStrategy2());

          // reset the strategies to their initial state
          competitor1.reset();
          competitor2.reset();
        }
      }

    } catch (final GameplayException exception) {
      throw new SelectionException("Failed to play a game.", exception);
    }

    // set the scores so the comparator knows how to compare strategies
    this.scoreComparator.setScores(scores);

    // return the best strategy with respect to cumulative score
    return Collections.max(scores.keySet(), this.scoreComparator);
  }

  /**
   * Set the number of Individuals to be chosen at random to compete in the
   * tournament.
   * 
   * @param newTournamentSize
   *          The number of Individuals to be chosen at random to compete in the
   *          tournament.
   */
  public void setTournamentSize(final int newTournamentSize) {
    this.tournamentSize = newTournamentSize;
  }

}
