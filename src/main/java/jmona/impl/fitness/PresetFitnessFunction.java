/**
 * PresetFitnessFunction.java
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

import java.util.Map;

import jmona.FitnessFunction;

/**
 * A FitnessFunction which uses a preset Map from individual to fitness to get
 * the adjusted fitness of an individual.
 * 
 * This FitnessFunction cannot determine the raw fitness or standardized fitness
 * of an individual.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual whose fitness is to be determined.
 * @since 0.3.1
 */
public class PresetFitnessFunction<T> implements FitnessFunction<T> {

  /** The Map from individual to its adjusted fitness. */
  private final Map<T, Double> adjustedFitnesses;

  /**
   * Instantiates this FitnessFunction with access to the specified Map from
   * individual to corresponding adjusted fitness, which is used to determine
   * the adjusted fitness of individuals in the {@link #adjustedFitness(Object)}
   * method.
   * 
   * @param initialFitnesses
   *          The Map from individual to its adjusted fitness.
   */
  public PresetFitnessFunction(final Map<T, Double> initialFitnesses) {
    this.adjustedFitnesses = initialFitnesses;
  }

  /**
   * Get the adjusted fitness of the specified individual from the Map specified
   * in the constructor of this class.
   * 
   * @param individual
   *          The individual whose fitness is to be determined from the Map
   *          specified in the constructor of this class.
   * @return The adjusted fitness of the specified individual.
   * @see jmona.FitnessFunction#adjustedFitness(java.lang.Object)
   */
  @Override
  public double adjustedFitness(final T individual) {
    return this.adjustedFitnesses.get(individual);
  }

  /**
   * Always throws an UnsupportedOperationException.
   * 
   * @param individual
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see jmona.FitnessFunction#rawFitness(java.lang.Object)
   */
  @Override
  public double rawFitness(final T individual) {
    throw new UnsupportedOperationException();
  }

  /**
   * Always throws an UnsupportedOperationException.
   * 
   * @param individual
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see jmona.FitnessFunction#standardizedFitness(java.lang.Object)
   */
  @Override
  public double standardizedFitness(final T individual) {
    throw new UnsupportedOperationException();
  }

}
