/**
 * MinimizingFitnessFunction.java
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

/**
 * A fitness function which determines the raw fitness of an individual with
 * respect to a known minimum raw fitness value.
 * 
 * @param <T>
 *          The type of individual whose fitness is to be determined.
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public abstract class MinimizingFitnessFunction<T> extends
    KnownExtremumFitnessFunction<T> {

  /**
   * Instantiates this FitnessFunction by setting the minimum raw fitness value
   * to {@value KnownExtremumFitnessFunction#DEFAULT_MINIMUM_FITNESS}.
   */
  public MinimizingFitnessFunction() {
    this(DEFAULT_MINIMUM_FITNESS);
  }

  /**
   * Instantiates this FitnessFunction by setting the minimum raw fitness value
   * to the specified value.
   * 
   * @param minimumRawFitness
   *          The minimum raw fitness value.
   */
  public MinimizingFitnessFunction(final double minimumRawFitness) {
    this.setExtremum(minimumRawFitness);
  }

  /**
   * Returns {@value KnownExtremumFitnessFunction#MINIMUM}.
   * 
   * @return {@value KnownExtremumFitnessFunction#MINIMUM}.
   * @see jmona.impl.fitness.KnownExtremumFitnessFunction#typeOfExtremum()
   */
  @Override
  protected int typeOfExtremum() {
    return MINIMUM;
  }

}
