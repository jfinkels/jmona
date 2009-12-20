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
package jmona.impl.selection;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

import jmona.Individual;

/**
 * Class which compares the fitness of Individuals, given a mapping from
 * Individual to fitness.
 * 
 * @param <T>
 *          The type of Individual whose fitness will be compared.
 * @author jfinkels
 */
public class FitnessComparator<T extends Individual> implements Comparator<T>,
    Serializable {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -5790092957097751794L;

  /** The mapping from Individual to its corresponding fitness. */
  private Map<T, Double> fitnesses = null;

  /**
   * Instantiate this Comparator with the specified mapping from Individual to
   * its corresponding fitness in the context of an evolution.
   * 
   * @param initialFitnesses
   *          The mapping from Individual to its corresponding fitness.
   */
  public FitnessComparator(final Map<T, Double> initialFitnesses) {
    this.fitnesses = initialFitnesses;
  }

  /**
   * Return the comparison of the fitnesses of the two specified Individuals.
   * 
   * @param individual1
   *          An Individual.
   * @param individual2
   *          Another Individual.
   * @return The comparison of the fitnesses of the two specified Individuals.
   * @throws NullPointerException
   *           If one of the Individuals specified is not in the known mapping
   *           from Individual to corresponding fitness.
   * @see #fitnesses
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final T individual1, final T individual2) {

    // get the difference between the fitnesses of the two Individuals
    final double difference = this.fitnesses.get(individual1)
        - this.fitnesses.get(individual2);

    // translate a positive number to +1, negative to -1, and zero to 0
    int result = 0;
    if (difference > 0) {
      result = 1;
    } else if (difference < 0) {
      result = -1;
    } else {
      result = 0;
    }

    return result;
  }

}
