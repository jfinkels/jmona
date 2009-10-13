/**
 * AbstractBreedingFunction.java
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
