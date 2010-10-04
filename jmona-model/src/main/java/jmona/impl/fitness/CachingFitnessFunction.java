/**
 * CachingFitnessFunction.java
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

import java.util.HashMap;
import java.util.Map;

import jmona.FitnessFunction;

/**
 * A fitness function which has access to a cache of adjusted fitness values.
 * 
 * Implementing classes can use the cache provided by this class to store
 * adjusted fitness values so that fitness values for individuals which have not
 * changed do not need to be computed.
 * 
 * Since fitnesses can be calculated for arbitrary objects, it is possible that
 * such an object may have changed its state. This class is specifically for use
 * in GeneticEvolutionContext classes, in which individuals are deep copied
 * between generations.
 * 
 * @author Jeffrey Finkelstein
 * @param <T>
 *          The type of individual whose fitness is to be determined.
 * @since 0.5
 */
public abstract class CachingFitnessFunction<T> implements FitnessFunction<T> {
  /**
   * The cache for this class, which contains a map from object to the fitness
   * value of that object.
   */
  private Map<T, Double> cache = new HashMap<T, Double>();

  /**
   * Returns the adjusted fitness of the specified individual if its raw fitness
   * has already been cached, or {@code null} if the adjusted fitness of this
   * individual has not been cached.
   * 
   * @param individual
   *          The individual whose fitness is to be fetched from the cache.
   * @return The adjusted fitness of the specified individual if its fitness has
   *         already been cached, or {@code null} if its adjusted fitness has
   *         not already been cached.
   */
  protected Double getCachedFitness(final T individual) {
    Double result = null;

    if (this.cache.containsKey(individual)) {
      result = this.cache.get(individual);
    }

    return result;
  }

  /**
   * Puts the specified adjusted fitness of the specified individual in the
   * cache.
   * 
   * @param individual
   *          The individual whose adjusted fitness value will be cached.
   * @param fitness
   *          The adjusted fitness value to add to the cache.
   * @return The previous adjusted fitness value associated with the specified
   *         individual, or {@code null} if no fitness was previously cached.
   */
  protected Double putCachedFitness(final T individual, final Double fitness) {
    return this.cache.put(individual, fitness);
  }
}
