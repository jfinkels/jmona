/**
 * DefaultCompletionConditionTester.java
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
import jmona.CompletionException;
import jmona.EvolutionContext;
import jmona.FitnessException;
import jmona.Population;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultCompletionCondition class.
 * 
 * @author jfinkels
 */
public class DefaultCompletionConditionTester {

  /** The completion criteria under test. */
  private DefaultCompletionCondition<ExampleIndividual> completionCondition = null;
  /** The evolution context on which to test the completion criteria. */
  private EvolutionContext<ExampleIndividual> evolutionContext = null;
  /** The population in the evolution context. */
  private Population<ExampleIndividual> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.completionCondition = new DefaultCompletionCondition<ExampleIndividual>();

    this.population = new DefaultPopulation<ExampleIndividual>();
    this.population.add(new ExampleIndividual(1));
    this.population.add(new ExampleIndividual(2));

    this.evolutionContext = new ExampleEvolutionContext(this.population);

    try {
      this.evolutionContext.setFitnessFunction(new ExampleFitnessFunction());
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultCompletionCondition#isSatisfied(jmona.EvolutionContext)}
   * due to maximum fitness.
   */
  @Test
  public void testIsSatisfiedMaxFitness() {
    try {
      assertFalse(this.completionCondition.isSatisfied(this.evolutionContext));

      this.population.add(new ExampleIndividual(
          DefaultMaxFitnessCompletionCondition.DEFAULT_MAX_FITNESS));
      assertTrue(this.completionCondition.isSatisfied(this.evolutionContext));
    } catch (final CompletionException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultCompletionCondition#isSatisfied(jmona.EvolutionContext)}
   * due to maximum generation met.
   */
  @Test
  public void testIsSatisfiedMaxGeneration() {
    try {
      this.completionCondition.setMaxGenerations(0);
      assertTrue(this.completionCondition.isSatisfied(this.evolutionContext));

      this.completionCondition.setMaxGenerations(2);
      assertFalse(this.completionCondition.isSatisfied(this.evolutionContext));
    } catch (final CompletionException exception) {
      Util.fail(exception);
    }
  }
}
