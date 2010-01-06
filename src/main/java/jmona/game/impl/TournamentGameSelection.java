/**
 * TournamentGameSelection.java
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

import java.util.List;

import jmona.SelectionException;
import jmona.SelectionFunction;
import jmona.game.Strategy;
import jmona.game.TwoPlayerGame;

/**
 * A selection function which plays a tournament and selects the winner.
 * 
 * @author Jeffrey Finkelstein
 * @param <S>
 *          The type of Strategy which competes in this tournament.
 * @since 0.3
 */
// TODO move up to jmona.game
public interface TournamentGameSelection<S extends Strategy> extends
    SelectionFunction<S> {

  /**
   * Play a tournament of the specified game with competitors chosen from the
   * specified population and return the winner.
   * 
   * @param population
   *          The population from which to choose competitors for the
   *          tournament.
   * @param game
   *          The game to play.
   * @return The winner after playing a tournament of the specified game with
   *         competitors chosen from the specified population.
   * @throws SelectionException
   *           If there is a problem selecting the winner of a tournament.
   */
  public S select(final List<S> population, final TwoPlayerGame<S> game)
      throws SelectionException;

}
