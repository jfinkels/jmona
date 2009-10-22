/**
 * FitnessComparator.java
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
package jmona.impl;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

import jmona.Individual;

/**
 * A comparator with access to a mapping from individual to corresponding
 * fitness.
 * 
 * This class is Serializable so that, for example, a TreeMap utilizing it can
 * also be Serializable.
 * 
 * @author jeff
 * @param <T>
 *          The type of the individual to compare.
 */
class FitnessComparator<T extends Individual> implements Comparator<T>,
    Serializable {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 7321144923519505979L;
  /** The mapping from individual to corresponding fitness. */
  private Map<T, Double> fitnesses = null;

  /**
   * Compare the fitnesses of the two specified individual based on the
   * fitnesses map.
   * 
   * @param individual1
   *          An individual.
   * @param individual2
   *          Another individual.
   * @return The comparison between the fitnesses of the two specified
   *         individuals, if they are both in the known mapping from individual
   *         to fitness.
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  // TODO figure out if this works
  public int compare(final T individual1, final T individual2) {

    // if the two individuals are the same, one must be overwritten, so return 0
    if (individual1.equals(individual2)) {
      return 0;
    }
    // otherwise, the two individuals are different

    final int fitnessComparison = this.fitnesses.get(individual1).compareTo(
        this.fitnesses.get(individual2));

    if (fitnessComparison != 0) {
      return fitnessComparison;
    }

    return individual1.hashCode() - individual2.hashCode();

  }

  /**
   * Set the fitnesses to use for comparison.
   * 
   * @param newFitnesses
   *          The fitnesses to use for comparison.
   */
  public void setFitnesses(final Map<T, Double> newFitnesses) {
    this.fitnesses = newFitnesses;
  }
}
