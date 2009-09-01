/**
 * OnesCompletionCriteriaTester.java
 */
package jmona.example.ones;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jmona.EvolutionContext;
import jmona.Population;
import jmona.impl.DefaultEvolutionContext;
import jmona.impl.DefaultPopulation;
import jmona.impl.MaxGenerationCompletionCriteria;

import org.junit.Test;

/**
 * Test class for the OnesCompletionCriteria class.
 * 
 * @author jeff
 */
public class OnesCompletionCriteriaTester {

  /** The length of the genes of the individuals to test. */
  public static final int GENE_LENGTH = 4;

  /**
   * Test method for
   * {@link jmona.example.ones.OnesCompletionCriteria#isSatisfied(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testIsSatisfiedEvolutionContextOfOnesIndividual() {
    final MaxGenerationCompletionCriteria<OnesIndividual> criteria = new OnesCompletionCriteria(
        GENE_LENGTH);
    criteria.setMaxGenerations(2);
    final Population<OnesIndividual> population = new DefaultPopulation<OnesIndividual>();
    population.add(new OnesIndividual(new short[] { 0, 0, 0, 0 }));
    population.add(new OnesIndividual(new short[] { 1, 0, 0, 0 }));
    final EvolutionContext<OnesIndividual> context = new DefaultEvolutionContext<OnesIndividual>(
        population);

    assertFalse(criteria.isSatisfied(context));

    criteria.setMaxGenerations(0);
    assertTrue(criteria.isSatisfied(context));

    criteria.setMaxGenerations(1);
    population.add(new OnesIndividual(new short[] { 1, 1, 1, 1 }));
    assertTrue(criteria.isSatisfied(context));

    criteria.setMaxGenerations(0);
    assertTrue(criteria.isSatisfied(context));
  }

}
