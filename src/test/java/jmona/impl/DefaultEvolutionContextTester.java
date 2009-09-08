/**
 * DefaultEvolutionContextTester.java
 */
package jmona.impl;

import static org.junit.Assert.assertSame;
import jmona.EvolutionContext;
import jmona.Individual;
import jmona.Population;

import org.junit.Test;

/**
 * Test class for the DefaultEvolutionContext class.
 * 
 * @author jeff
 */
public class DefaultEvolutionContextTester {

  /**
   * Test method for
   * {@link jmona.impl.DefaultEvolutionContext#DefaultEvolutionContext(jmona.Population)}
   * .
   */
  @Test
  public void testDefaultEvolutionContext() {
    final Population<Individual> population = new DefaultPopulation<Individual>();
    population.add(new Individual() {

      @Override
      public Individual copy() {
        return this;
      }
    });
    population.add(new Individual() {
      @Override
      public Individual copy() {
        return this;
      }
    });

    EvolutionContext<Individual> context = new DefaultEvolutionContext<Individual>(
        population);

    assertSame(population, context.population());
  }

}
