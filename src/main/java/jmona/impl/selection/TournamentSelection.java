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

import java.util.Collections;
import java.util.List;

import jmona.FitnessFunction;
import jmona.IndependentSelectionFunction;
import jmona.impl.fitness.StandardizedFitnessComparator;
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
  /** A comparator for individuals based on their standardized fitnesses. */
  private final StandardizedFitnessComparator<T> standardizedFitnessComparator = new StandardizedFitnessComparator<T>();
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
   * @param population
   *          {@inheritDoc}
   * @param fitnessFunction
   *          The FitnessFunction to use to determine the standardized fitness
   *          of individuals in the specified population.
   * @see jmona.IndependentSelectionFunction#select(List, FitnessFunction)
   */
  // TODO documentation for this method, i.e. formulae
  @Override
  public T select(final List<T> population,
      final FitnessFunction<T> fitnessFunction) {

    // get a random subset of the population to compete in the "tournament"
    final List<T> competitors = RandomUtils.sample(population,
        this.tournamentSize);

    // set the fitness function on the comparator so it known how to compare
    // competitors
    this.standardizedFitnessComparator.setFitnessFunction(fitnessFunction);

    // return the competitor with the minimum standardized fitness
    return Collections.min(competitors, this.standardizedFitnessComparator);
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
