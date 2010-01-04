/**
 * FitnessComparator.java
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
package jmona.impl.fitness;

import java.io.Serializable;
import java.util.Comparator;

import jmona.ComparisonException;
import jmona.FitnessException;

/**
 * A Comparator for individuals in an evolution based on their standardized
 * fitnesses.
 * 
 * @param <T>
 *          The type of individual to compare based on fitness.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class StandardizedFitnessComparator<T> extends
    FitnessFunctionCollaborator<T> implements Comparator<T>, Serializable {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 8269989948538378807L;

  /**
   * Compare the standardized fitness of the specified individuals as determined
   * by the FitnessFunction set in the {@link #fitnessFunction} property.
   * 
   * @param individual1
   *          An individual.
   * @param individual2
   *          Another individual.
   * @return The result of the comparison of their fitnesses using the
   *         {@link Double#compare(double, double)} method.
   * @throws ComparisonException
   *           If the FitnessFunction throws an Exception when determining the
   *           fitness of an individual.
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final T individual1, final T individual2) {
    try {
      return Double
          .compare(this.fitnessFunction().standardizedFitness(individual1),
              this.fitnessFunction().standardizedFitness(individual2));
    } catch (final FitnessException exception) {
      throw new ComparisonException(
          "Failed to get the standardized fitness of an individual.", exception);
    }
  }

}
