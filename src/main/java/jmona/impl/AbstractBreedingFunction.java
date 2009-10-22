/**
 * AbstractBreedingFunction.java
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
package jmona.impl;

import jmona.BreedingFunction;
import jmona.CrossoverFunction;
import jmona.Individual;

/**
 * A base class for breeding functions, so subclasses need only implement the
 * breeding algorithm.
 * 
 * @param <T>
 *          The type of the individual.
 * @author jfinke
 */
public abstract class AbstractBreedingFunction<T extends Individual> implements
    BreedingFunction<T> {
  /** The crossover function for this breeding function. */
  private CrossoverFunction<T> crossoverFunction = null;
  /** The probability that crossover will occur when breeding. */
  public static final double DEFAULT_CROSSOVER_PROB = 0.6;
  /**
   * The probability of crossover occuring during a breeding, between 0
   * (inclusive) and 1 (inclusive).
   */
  private double crossoverProbability = DEFAULT_CROSSOVER_PROB;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.BreedingFunction#crossoverFunction()
   */
  @Override
  public CrossoverFunction<T> crossoverFunction() {
    return this.crossoverFunction;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public double crossoverProbability() {
    return this.crossoverProbability;
  }

  /**
   * {@inheritDoc}
   * 
   * @param function
   *          {@inheritDoc}
   * @see jmona.BreedingFunction#setCrossoverFunction(jmona.CrossoverFunction)
   */
  @Override
  public void setCrossoverFunction(final CrossoverFunction<T> function) {
    this.crossoverFunction = function;
  }

  /**
   * {@inheritDoc}
   * 
   * @param probability
   *          {@inheritDoc}
   */
  @Override
  public void setCrossoverProbability(final double probability) {
    this.crossoverProbability = probability;
  }
}
