/**
 * MaxGenerationCompletionCriteriaTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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

import java.util.Arrays;
import java.util.List;

import jfcommon.test.TestUtils;
import jmona.EvolutionException;
import jmona.FitnessException;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MaxGenerationCompletionCondition class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class MaxGenerationCompletionConditionTester {

  /** The CompletionCondition under test in this class. */
  private MaxGenerationCompletionCondition<ExampleIndividual, ExampleEvolutionContext> condition = null;
  /** The EvolutionContext on which to test the CompletionCondition. */
  private ExampleEvolutionContext context = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public void setUp() {
    this.condition = new MaxGenerationCompletionCondition<ExampleIndividual, ExampleEvolutionContext>(
        1);
    final List<ExampleIndividual> population = Arrays
        .asList(new ExampleIndividual());
    this.context = new ExampleEvolutionContext(population);
    try {
      this.context.setFitnessFunction(new ExampleFitnessFunction());
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.completion.MaxGenerationCompletionCondition#MaxGenerationCompletionCondition(int)}
   * .
   */
  @Test
  public void testMaxGenerationCompletionCondition() {
    assertFalse(this.condition.execute(this.context));

    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

    assertTrue(this.condition.execute(this.context));

  }

  /**
   * Test method for
   * {@link jmona.impl.completion.MaxGenerationCompletionCondition#execute(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testExecute() {
    this.condition.setMaxGenerations(0);
    assertTrue(this.condition.execute(this.context));

    this.condition.setMaxGenerations(2);
    assertFalse(this.condition.execute(this.context));
  }

}
