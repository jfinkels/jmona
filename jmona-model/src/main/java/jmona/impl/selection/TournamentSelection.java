/**
 * TournamentSelection.java
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
package jmona.impl.selection;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import jmona.IndependentSelectionFunction;
import jmona.random.RandomUtils;

/**
 * A tournament selection which selects the best individual from a tournament of
 * specified size, with competitors chosen randomly from the entire population.
 * 
 * @param <T>
 *          The type of Individual to select.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class TournamentSelection<T> implements IndependentSelectionFunction<T> {

  /**
   * The default number of Individuals to be chosen at random to compete in the
   * tournament.
   */
  public static final int DEFAULT_TOURNAMENT_SIZE = 5;

  /**
   * The number of Individuals to be chosen at random to compete in the
   * tournament.
   */
  private int tournamentSize = DEFAULT_TOURNAMENT_SIZE;

  /**
   * Chooses the best individual from a tournament of uniformly randomly chosen
   * competitors from the specified population.
   * 
   * The tournament has size specified in the property {@link #tournamentSize}.
   * 
   * @param fitnesses
   *          {@inheritDoc}
   * @see jmona.IndependentSelectionFunction#select(java.util.Map)
   */
  @Override
  public T select(final Map<T, Double> fitnesses) {

    // create a comparator based on the fitnesses of the individuals as
    // specified in the map given as input to this method
    final Comparator<T> comparator = new ValueComparator<T, Double>(fitnesses);

    // create a SortedMap containing the subset of keys from the fitnesses map
    final SortedMap<T, Double> competitors = new TreeMap<T, Double>(comparator);

    // get a random subset of the population to compete in the "tournament"
    for (final Entry<T, Double> entry : RandomUtils.sample(
        fitnesses.entrySet(), this.tournamentSize)) {
      competitors.put(entry.getKey(), entry.getValue());
    }

    // return the competitor with the maximum adjusted fitness
    return competitors.lastKey();
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
