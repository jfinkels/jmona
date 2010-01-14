/**
 * StandardizedFitnessGetter.java
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
package jmona.impl.fitness;

import jmona.FitnessException;
import jmona.Function;
import jmona.MappingException;

/**
 * A function which gets the standardized fitness of an individual using a
 * specified FitnessFunction.
 * 
 * @param <T>
 *          The type of individual for which to get the standardized fitness.
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class StandardizedFitnessGetter<T> extends
    FitnessFunctionCollaborator<T> implements Function<T, Double> {

  /**
   * Gets the standardized fitness of the specified individual using the
   * FitnessFunction set in the {@link #fitnessFunction} property.
   * 
   * @param input
   *          The individual whose standardized fitness is to be determined.
   * @return The standardized fitness of the specified individual determined by
   *         the FitnessFunction set in the {@code #fitnessFunction} property.
   * @throws MappingException
   *           If the FitnessFunction throws a FitnessException.
   * @see jmona.Function#execute(java.lang.Object)
   */
  @Override
  public Double execute(final T input) throws MappingException {
    try {
      return this.fitnessFunction().standardizedFitness(input);
    } catch (final FitnessException exception) {
      throw new MappingException(
          Messages.getString("StandardizedFitnessGetter.0"), exception); //$NON-NLS-1$
    }
  }
}
