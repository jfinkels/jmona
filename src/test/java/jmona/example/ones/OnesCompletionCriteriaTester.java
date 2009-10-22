/**
 * OnesCompletionCriteriaTester.java
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
