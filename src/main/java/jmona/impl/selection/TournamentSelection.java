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
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jmona.Individual;
import jmona.SelectionFunction;
import jmona.impl.Util;

/**
 * A tournament selection algorithm.
 * 
 * @param <T>
 *          The type of Individual to select.
 * @author jfinkels
 */
public class TournamentSelection<T extends Individual> implements
    SelectionFunction<T> {

  /**
   * The default number of Individuals to be chosen at random to compete in the
   * tournament.
   */
  public static final int DEFAULT_TOURNAMENT_SIZE = 10;
  /**
   * The number of Individuals to be chosen at random to compete in the
   * tournament.
   */
  private int tournamentSize = DEFAULT_TOURNAMENT_SIZE;

  /**
   * The default probability of selecting the top competitor in the tournament.
   */
  public static final double DEFAULT_SELECTION_PROBABILITY = 0.9;
  /**
   * The probability of selecting the top competitor in the tournament.
   * 
   * Let {@code p} be the value of this field. Then the probability of selecting
   * the second place competitor in the tournament is {@code p*(1-p)}, the
   * probability of selecting the third place competitor is {@code p*(p*(1-p))},
   * and so on.
   */
  // TODO is that math correct?
  private double selectionProbability = DEFAULT_SELECTION_PROBABILITY;

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

  /**
   * 
   * Currently chooses competitors for the tournament randomly
   * <em>with replacement</em>, so the one Individual could be in mutiple
   * competitor spots in the tournament.
   * 
   * @param fitnesses
   *          {@inheritDoc}
   * @see jmona.SelectionFunction#select(java.util.Map)
   */
  // TODO documentation for this method
  @Override
  public T select(final Map<T, Double> fitnesses) {
    // initialize a list of competitors for the tournament
    final List<T> competitors = new Vector<T>();

    // fill the list of competitors with random Individuals (with replacement)
    // TODO allow random without replacement
    while (competitors.size() < this.tournamentSize) {
      competitors.add(Util.randomFromSet(fitnesses.keySet()));
    }

    // sort the list of competitors based on their fitnesses
    Collections.sort(competitors, new FitnessComparator<T>(fitnesses));

    // choose a random double
    final double choice = Util.RANDOM.nextDouble();

    // the initial probability of choosing the top competitor
    double probability = this.selectionProbability;

    // the competitor which will eventually be selected
    int chosenCompetitor = 0;

    // until the chosen random number is less than 'probability'...
    while (choice > probability || chosenCompetitor >= competitors.size()) {
      // increment the probability
      // TODO double check this math!!! check out how JGAP does it
      probability += probability * (1 - this.selectionProbability);

      // increment the number of the competitor to select as winner
      chosenCompetitor += 1;
    }

    // if the chosen competitor is greater than the number of competitors, just
    // choose the last competitor
    if (chosenCompetitor >= competitors.size()) {
      chosenCompetitor = competitors.size() - 1;
    }

    return competitors.get(chosenCompetitor);
  }

}
