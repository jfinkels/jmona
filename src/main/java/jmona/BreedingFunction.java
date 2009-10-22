/**
 * BreedingFunction.java
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
package jmona;

/**
 * A class which contains a single method for breeding two parent individuals to
 * produce a child individual.
 * 
 * @param <T>
 *          The type of Individual to breed.
 * @author jfinke
 */
public interface BreedingFunction<T extends Individual> {

  /**
   * Breed the two specified parents to produce two children. Implementing
   * classes should use a crossover function during execution of this method.
   * 
   * @param parents
   *          The parent individuals.
   * @throws BreedingException
   *           If there is a problem breeding the two specified parents.
   * @return The child resulting from breeding the two specified parents.
   */
  Pair<T, T> breed(final Pair<T, T> parents) throws BreedingException;

  /**
   * Get the crossover function used by this context.
   * 
   * @return The crossover function used by this context.
   */
  CrossoverFunction<T> crossoverFunction();

  /**
   * Get the probability of crossover occurring during a breeding, between 0
   * (inclusive) and 1 (inclusive).
   * 
   * @return The probability of crossover occurring during a breeding, between 0
   *         (inclusive) and 1 (inclusive).
   */
  double crossoverProbability();

  /**
   * Set the selection function used by this context.
   * 
   * @param function
   *          The selection function used by this context.
   */
  void setCrossoverFunction(final CrossoverFunction<T> function);

  /**
   * Set the probability of crossover occurring during a breeding, between 0
   * (inclusive) and 1 (inclusive).
   * 
   * @param probability
   *          The probability of crossover occurring during a breeding, between
   *          0 (inclusive) and 1 (inclusive).
   */
  void setCrossoverProbability(final double probability);

}
