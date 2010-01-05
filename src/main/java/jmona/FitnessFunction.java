/**
 * FitnessFunction.java
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
package jmona;

/**
 * A class which provides methods for determining the fitness of an individual.
 * 
 * The raw fitness of an individual is a measure of its fitness in the context
 * of the specified evolution in which it lives. The standardized fitness
 * converts the raw fitness into a form such that a standardized fitness closer
 * to 0 is better. The adjusted fitness converts the standardized fitness to a
 * value between 0 and 1 such that a value closer to 1 is better.
 * 
 * For more information, see John R. Koza's
 * <em>Genetic programming: on the programming of computers by means of natural selection</em>
 * , section 6.3.1, pages 95-98.
 * 
 * @param <T>
 *          The type of Individual for which to determine fitness.
 * @author Jeffrey Finkelstein
 */
public interface FitnessFunction<T> {
  /**
   * Determines the raw fitness of the specified individual in the context of
   * the specific evolution in which the individual lives.
   * 
   * @param individual
   *          The individual for which to determine raw fitness.
   * @return The raw fitness of the specified individual in the context of the
   *         specific evolution in which the individual lives.
   * @throws FitnessException
   *           If there is a problem determining the raw fitness of the
   *           specified individual.
   */
  double rawFitness(final T individual) throws FitnessException;

  /**
   * Determines the standardized fitness of the specified individual, so that a
   * standardized fitness closer to 0 implies a more fit individual.
   * 
   * If a smaller raw fitness of an individual is better, and the minimum raw
   * fitness is 0, then this method exactly equals the raw fitness.
   * 
   * If a larger raw fitness of an individual is better, and the maximum raw
   * fitness is known, then the standardized fitness must equal the maximum raw
   * fitness minus the raw fitness of the individual.
   * 
   * For more information, see John R. Koza's
   * <em>Genetic programming: on the programming of computers by means of natural selection</em>
   * , section 6.3.1, pages 95-98.
   * 
   * @param individual
   *          The individual for which to determine standardized fitness.
   * @return The standardized fitness of the specified individual.
   * @throws FitnessException
   *           If there is a problem determining the standardized fitness of the
   *           specified individual.
   */
  double standardizedFitness(final T individual) throws FitnessException;

  /**
   * Determines the adjusted fitness of the specified individual, so that the
   * adjusted fitness of an individual is always between 0 and 1, and a greater
   * adjusted fitness is better.
   * 
   * If the standardized fitness, <em>s(i)</em>, of individual <em>i</em> is
   * known, then the adjusted fitness of individual <em>i</em> must equal
   * <em>1/(1+s(i))</em>.
   * 
   * If a smaller raw fitness of an individual is better and the minimum raw
   * fitness is unknown, or if a larger raw fitness of an individual is better
   * and the maximum raw fitness is unknown, the adjusted fitness may be
   * calculated in a similar way directly from the raw fitness.
   * 
   * @param individual
   *          The individual for which to determine adjusted fitness.
   * @return The adjusted fitness of the specified individual.
   * @throws FitnessException
   *           If there is a problem determining the adjusted fitness of the
   *           specified individual.
   */
  // TODO how to calculate adjustedFitness if raw max or min is unknown
  double adjustedFitness(final T individual) throws FitnessException;
}
