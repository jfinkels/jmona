/**
 * DefaultMaxFitnessCompletionConditionTester.java
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

import jmona.CompletionException;
import jmona.EvolutionContext;
import jmona.FitnessException;
import jmona.ga.impl.GAEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DefaultMaxFitnessCompletionCondition class.
 * 
 * @author Jeffrey Finkelstein
 */
public class DefaultMaxFitnessCompletionConditionTester {

  /** The completion criteria under test. */
  private DefaultMaxFitnessCompletionCondition<ExampleIndividual> completionCriteria = null;
  /** An EvolutionContext with no functions set. */
  private EvolutionContext<ExampleIndividual> emptyContext = null;
  /** The evolution context on which to test the completion criteria. */
  private EvolutionContext<ExampleIndividual> evolutionContext = null;
  /** The population in the evolution context. */
  private List<ExampleIndividual> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.completionCriteria = new DefaultMaxFitnessCompletionCondition<ExampleIndividual>();

    this.population = new Vector<ExampleIndividual>();
    this.population.add(new ExampleIndividual(1));
    this.population.add(new ExampleIndividual(2));

    this.emptyContext = new GAEvolutionContext<ExampleIndividual>(
        this.population);

    this.evolutionContext = new GAEvolutionContext<ExampleIndividual>(
        this.population);

    try {
      this.evolutionContext.setFitnessFunction(new ExampleFitnessFunction());
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultMaxFitnessCompletionCondition#isSatisfied(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testIsSatisfied() {

    try {
      this.completionCriteria.isSatisfied(this.emptyContext);
      fail("Exception should have been thrown on previous line.");
    } catch (final CompletionException exception) {
      assertTrue(exception instanceof CompletionException);
    }

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
   * {@link jmona.impl.DefaultMaxFitnessCompletionCondition#setMaxFitness(double)}
   * .
   */
  @Test
  public void testSetMaxFitness() {
    try {
      assertFalse(this.completionCriteria.isSatisfied(this.evolutionContext));
      this.completionCriteria.setMaxFitness(1);
      assertTrue(this.completionCriteria.isSatisfied(this.evolutionContext));
    } catch (final CompletionException exception) {
      Util.fail(exception);
    }
  }

}
