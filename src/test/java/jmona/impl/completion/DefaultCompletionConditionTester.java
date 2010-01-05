/**
 * DefaultCompletionConditionTester.java
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

import java.util.List;
import java.util.Vector;

import jmona.CompletionException;
import jmona.GeneticEvolutionContext;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultCompletionCondition class.
 * 
 * @author Jeffrey Finkelstein
 */
public class DefaultCompletionConditionTester {

  /** The completion criteria under test. */
  private DefaultCompletionCondition<ExampleIndividual> completionCriteria = null;
  /** The evolution context on which to test the completion criteria. */
  private GeneticEvolutionContext<ExampleIndividual> evolutionContext = null;
  /** The population in the evolution context. */
  private List<ExampleIndividual> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.completionCriteria = new DefaultCompletionCondition<ExampleIndividual>();

    this.population = new Vector<ExampleIndividual>();
    this.population.add(new ExampleIndividual(1));
    this.population.add(new ExampleIndividual(2));

    this.evolutionContext = new ExampleEvolutionContext(this.population);

    this.evolutionContext.setFitnessFunction(new ExampleFitnessFunction());
  }

  /**
   * Test method for
   * {@link jmona.impl.completion.DefaultCompletionCondition#isSatisfied(jmona.EvolutionContext)}
   * due to maximum fitness.
   */
  @Test
  public void testIsSatisfiedMaxFitness() {
    try {
      assertFalse(this.completionCriteria.isSatisfied(this.evolutionContext));

      this.population.add(new ExampleIndividual(
          DefaultMaxFitnessCompletionCondition.DEFAULT_MAX_FITNESS));
      assertTrue(this.completionCriteria.isSatisfied(this.evolutionContext));
    } catch (final CompletionException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.completion.DefaultCompletionCondition#isSatisfied(jmona.EvolutionContext)}
   * due to maximum generation met.
   */
  @Test
  public void testIsSatisfiedMaxGeneration() {
    try {
      this.completionCriteria.setMaxGenerations(0);
      assertTrue(this.completionCriteria.isSatisfied(this.evolutionContext));

      this.completionCriteria.setMaxGenerations(2);
      assertFalse(this.completionCriteria.isSatisfied(this.evolutionContext));
    } catch (final CompletionException exception) {
      Util.fail(exception);
    }
  }
}
