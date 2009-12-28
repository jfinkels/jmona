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
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Vector;

import jmona.EvolutionContext;
import jmona.MaxGenerationCompletionCondition;
import jmona.exceptions.CompletionException;
import jmona.ga.impl.GAEvolutionContext;
import jmona.impl.example.ExampleIndividual;

import org.junit.Test;

/**
 * Test class for the MaxGenerationCompletionCondition class.
 * 
 * @author jfinkels
 */
public class DefaultMaxGenerationCompletionConditionTester {

  /**
   * Test method for
   * {@link jmona.impl.DefaultMaxGenerationCompletionCondition#isSatisfied(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testIsSatisfied() {
    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(new ExampleIndividual());
    population.add(new ExampleIndividual());
    final EvolutionContext<ExampleIndividual> context = new GAEvolutionContext<ExampleIndividual>(
        population);

    final MaxGenerationCompletionCondition<ExampleIndividual> criteria = new DefaultMaxGenerationCompletionCondition<ExampleIndividual>();

    try {
      criteria.setMaxGenerations(0);
      assertTrue(criteria.isSatisfied(context));

      criteria.setMaxGenerations(2);
      assertFalse(criteria.isSatisfied(context));
    } catch (final CompletionException exception) {
      exception.printStackTrace(System.err);
      fail(exception.getMessage());
    }
  }

}
