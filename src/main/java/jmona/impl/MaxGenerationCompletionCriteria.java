/**
 * DefaultCompletionCriteria.java
 */
package jmona.impl;

import jmona.CompletionCriteria;
import jmona.EvolutionContext;
import jmona.Individual;

/**
 * An object which checks if the specified maximum number of generations has
 * been achieved in the specified evolution context.
 * 
 * @param <T>
 *          The type of Individual being evolved.
 * @author jeff
 */
public class MaxGenerationCompletionCriteria<T extends Individual> implements
    CompletionCriteria<T> {

  /** The default maximum number of generations in the evolution. */
  public static final int DEFAULT_MAX_GENERATIONS = Integer.MAX_VALUE;

  /**
   * The maximum number of generations for this evolution. If the number of
   * generations in the evolution meets or exceeds this value, then the
   * evolution stops.
   */
  private int maxGenerations = DEFAULT_MAX_GENERATIONS;

  /**
   * Whether the maximum number of generations has already occurred.
   * 
   * @param context
   *          {@inheritDoc}
   * @return Whether the maximum number of generations has already occurred.
   * @see jmona.CompletionCriteria#isSatisfied(jmona.EvolutionContext)
   */
  @Override
  public boolean isSatisfied(final EvolutionContext<T> context) {
    return context.currentGeneration() >= this.maxGenerations;
  }

  /**
   * Set the maximum number of generations for this evolution. If the number of
   * generations in the evolution meets or exceeds this value, then the
   * evolution stops.
   * 
   * @param newMaxGenerations
   *          Set the maximum number of generations for this evolution.
   */
  public void setMaxGenerations(final int newMaxGenerations) {
    this.maxGenerations = newMaxGenerations;
  }
}
