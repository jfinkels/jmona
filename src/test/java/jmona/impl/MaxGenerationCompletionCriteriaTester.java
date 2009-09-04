/**
 * MaxGenerationCompletionCriteriaTester.java
 */
package jmona.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jmona.EvolutionContext;
import jmona.Individual;
import jmona.Population;

import org.junit.Test;

/**
 * Test class for the MaxGenerationCompletionCriteria class.
 * 
 * @author jeff
 */
public class MaxGenerationCompletionCriteriaTester {

  /**
   * Test method for
   * {@link jmona.impl.MaxGenerationCompletionCriteria#isSatisfied(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testIsSatisfied() {
    final Population<Individual> population = new DefaultPopulation<Individual>();
    population.add(new Individual() {
    });
    population.add(new Individual() {
    });
    final EvolutionContext<Individual> context = new DefaultEvolutionContext<Individual>(
        population);
    final MaxGenerationCompletionCriteria<Individual> criteria = new MaxGenerationCompletionCriteria<Individual>();
    criteria.setMaxGenerations(0);
    assertTrue(criteria.isSatisfied(context));
    criteria.setMaxGenerations(2);
    assertFalse(criteria.isSatisfied(context));
  }

}
