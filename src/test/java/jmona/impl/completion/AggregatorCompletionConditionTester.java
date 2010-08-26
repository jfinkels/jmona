/**
 * AggregatorCompletionConditionTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.impl.completion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.CompletionException;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the AggregatorCompletionCondition class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class AggregatorCompletionConditionTester {

  /**
   * Test method for
   * {@link jmona.impl.completion.AggregatorCompletionCondition#AggregatorCompletionCondition(java.lang.Iterable)}
   * .
   */
  @Test
  public void testAggregatorCompletionCondition() {
    final MaxGenerationCompletionCondition<ExampleIndividual> condition1 = new MaxGenerationCompletionCondition<ExampleIndividual>();
    condition1.setMaxGenerations(2);
    final PerfectMatchCompletionCondition<ExampleIndividual> condition2 = new PerfectMatchCompletionCondition<ExampleIndividual>();
    @SuppressWarnings("unchecked")
    final AggregatorCompletionCondition<ExampleIndividual> condition = new AggregatorCompletionCondition<ExampleIndividual>(
        condition1, condition2);

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();

    try {
      final ExampleEvolutionContext context = new ExampleEvolutionContext(
          population);
      context.setFitnessFunction(new ExampleFitnessFunction());

      assertFalse(condition.execute(context));

      population.add(new ExampleIndividual(0));
      context.stepGeneration();

      assertTrue(condition.execute(context));

      population.clear();
      context.stepGeneration();

      assertTrue(condition.execute(context));
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    } catch (final CompletionException exception) {
      TestUtils.fail(exception);
    }
  }
}
