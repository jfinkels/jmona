/**
 * ScoreComparator.java
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

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

import jmona.game.Strategy;

/**
 * Compare two Strategy objects with respect to their cumulative scores.
 * 
 * @author Jeffrey Finkelstein
 * @param <S>
 *          The type of Strategy whose cumulative scores will be compared.
 * @since 0.3
 */
public class ScoreComparator<S extends Strategy> implements Comparator<S>,
    Serializable {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -4878374316552575890L;
  
  /** The mapping from Strategy to cumulative score. */
  private Map<S, Double> scores = null;

  /**
   * Compares the cumulative scores of the specified Strategy objects.
   * 
   * @param strategy1
   *          A Strategy.
   * @param strategy2
   *          Another Strategy.
   * @return The result of comparing the cumulative scores of the two specified
   *         Strategy objects.
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final S strategy1, final S strategy2) {
    return this.scores.get(strategy1).compareTo(this.scores.get(strategy2));
  }

  /**
   * Sets the mapping from Strategy to cumulative score.
   * 
   * @param newScores
   *          The mapping from Strategy to cumulative score.
   */
  public void setScores(final Map<S, Double> newScores) {
    this.scores = newScores;
  }

}
