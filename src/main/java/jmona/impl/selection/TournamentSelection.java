/**
 * TournamentSelection.java
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
package jmona.impl.selection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jmona.SelectionFunction;
import jmona.impl.Util;

/**
 * A tournament selection.
 * 
 * @param <T>
 *          The type of Individual to select.
 * @author Jeffrey Finkelstein
 */
public class TournamentSelection<T> implements SelectionFunction<T> {

  /**
   * The default number of Individuals to be chosen at random to compete in the
   * tournament.
   */
  public static final int DEFAULT_TOURNAMENT_SIZE = 5;
  /** A comparator for individuals based on their fitnesses. */
  private final FitnessComparator<T> fitnessComparator = new FitnessComparator<T>();
  /**
   * The number of Individuals to be chosen at random to compete in the
   * tournament.
   */
  private int tournamentSize = DEFAULT_TOURNAMENT_SIZE;

  /**
   * 
   * @param fitnesses
   *          {@inheritDoc}
   * @see jmona.SelectionFunction#select(java.util.Map)
   */
  // TODO documentation for this method
  @Override
  public T select(final Map<T, Double> fitnesses) {

    final Set<T> allIndividuals = new HashSet<T>();
    for (final T individual : fitnesses.keySet()) {
      allIndividuals.add(individual);
    }

    Set<T> competitors = null;
    if (fitnesses.size() <= this.tournamentSize) {
      competitors = allIndividuals;
    } else {
      // initialize a new empty set for the competitors in the tournament
      competitors = new HashSet<T>();

      // while the set of competitors is less than the tournament size
      T randomCompetitor = null;
      while (competitors.size() < this.tournamentSize) {

        // choose a random competitor from the list of all individuals
        randomCompetitor = Util.randomFromCollection(allIndividuals);

        // remove that individual from the list of all individuals, so it can't
        // be chosen again
        allIndividuals.remove(randomCompetitor);

        // add the chosen individual to the set of competitors
        competitors.add(randomCompetitor);
      }
    }

    this.fitnessComparator.setFitnesses(fitnesses);

    return Collections.max(competitors, this.fitnessComparator);
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
