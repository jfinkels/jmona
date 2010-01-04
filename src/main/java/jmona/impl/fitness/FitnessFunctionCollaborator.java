/**
 * FitnessFunctionCollaborator.java
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

import jmona.FitnessFunction;

/**
 * An object which has access to a FitnessFunction.
 * 
 * @param <T>
 *          The type of individual which is measured by the FitnessFunction with
 *          which this object collaborates.
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class FitnessFunctionCollaborator<T> {

  /**
   * The FitnessFunction used to determine the adjusted fitness of an
   * individual.
   */
  private FitnessFunction<T> fitnessFunction = null;

  /**
   * Gets the FitnessFunction used to determine the adjusted fitness of an
   * individual.
   * 
   * @return The FitnessFunction used to determine the adjusted fitness of an
   *         individual.
   */
  public FitnessFunction<T> fitnessFunction() {
    return this.fitnessFunction;
  }

  /**
   * Sets the FitnessFunction used to determine the adjusted fitness of an
   * individual.
   * 
   * @param newFitnessFunction
   *          The FitnessFunction used to determine the adjusted fitness of an
   *          individual.
   */
  public void setFitnessFunction(final FitnessFunction<T> newFitnessFunction) {
    this.fitnessFunction = newFitnessFunction;
  }
}
