/**
 * MaxGenerationCompletionCriteriaTester.java
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
    population.add(new Individual() {        @Override
      public Individual copy() {
      return this;
    }
    });
    population.add(new Individual() {        @Override
      public Individual copy() {
      return this;
    }
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
