/**
 * KnownExtremumFitnessFunction.java
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

/**
 * A fitness function which determines the fitness of an individual with respect
 * to a known minimum or maximum raw fitness value.
 * 
 * @param <T>
 *          The type of individual whose fitness is to be determined.
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public abstract class KnownExtremumFitnessFunction<T> extends
    CachingFitnessFunction<T> {

  /** The default minimum raw fitness value. */
  public static final double DEFAULT_MINIMUM_FITNESS = 0;
  /**
   * The multiplier for determining standardized fitness given raw fitness and a
   * known maximum.
   */
  public static final int MAXIMUM = 1;
  /**
   * The multiplier for determining standardized fitness given raw fitness and a
   * known minimum.
   */
  public static final int MINIMUM = -1;
  /** The known extremum (maximum or minimum) of raw fitness values. */
  private Double extremum = null;

  /**
   * {@inheritDoc}
   * 
   * @param individual
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see jmona.FitnessFunction#adjustedFitness(java.lang.Object)
   */
  @Override
  public double adjustedFitness(final T individual) throws FitnessException {
    
    // get the cached adjusted fitness of the individual
    Double result = this.getCachedFitness(individual);
    
    // if the adjusted fitness of the individual is not in the cache
    if (result == null) {
      
      // compute the adjusted fitness of the individual
      result = 1.0 / (1.0 + this.standardizedFitness(individual));
      
      // add it to the cache
      this.putCachedFitness(individual, result);
    }
        
    return result;
  }

  /**
   * Gets the known extremum (maximum or minimum) raw fitness value.
   * 
   * @return The known extremum (maximum or minimum) raw fitness value.
   */
  public Double extremum() {
    return this.extremum;
  }

  /**
   * Sets the known extremum (maximum or minimum) raw fitness value.
   * 
   * @param newExtremum
   *          The known extremum (maximum or minimum) raw fitness value.
   */
  public void setExtremum(final Double newExtremum) {
    this.extremum = newExtremum;
  }

  /**
   * {@inheritDoc}
   * 
   * @param individual
   * @return {@inheritDoc}
   * @throws FitnessException
   *           If the maximum or minimum fitness value has not been set, or if
   *           the {@link #rawFitness(Object)} method throws a FitnessException.
   * @see jmona.FitnessFunction#standardizedFitness(java.lang.Object)
   */
  @Override
  public double standardizedFitness(final T individual) throws FitnessException {
    if (this.extremum == null) {
      throw new FitnessException(
          "Extremum (maximum or minimum) raw fitness value has not been set.");
    }

    return this.typeOfExtremum()
        * (this.extremum - this.rawFitness(individual));
  }

  /**
   * Returns {@link #MAXIMUM} if the known extremum is a maximum, or
   * {@link #MINIMUM} if the known extremum is a minimum.
   * 
   * @return {@link #MAXIMUM} if the known extremum is a maximum, or
   *         {@link #MINIMUM} if the known extremum is a minimum.
   */
  protected abstract int typeOfExtremum();

}
