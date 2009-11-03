/**
 * DefaultMaxFitnessCompletionCriteriaTester.java
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

import org.junit.Before;
import org.junit.Test;

/**
 * @author jfinkels
 */
public class DefaultMaxFitnessCompletionCriteriaTester {

  /**
   * Print the stack trace of the specified exception and fail the test.
   * 
   * @param exception
   *          The exception which caused the test failure.
   */
  protected static void fail(final Throwable exception) {
    exception.printStackTrace(System.err);
    org.junit.Assert.fail(exception.getMessage());
  }

  /** The completion criteria under test. */
  private DefaultMaxFitnessCompletionCriteria<ExampleIndividual> completionCriteria = null;
  /** The evolution context on which to test the completion criteria. */
  private EvolutionContext<ExampleIndividual> evolutionContext = null;
  /** The population in the evolution context. */
  private Population<ExampleIndividual> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.completionCriteria = new DefaultMaxFitnessCompletionCriteria<ExampleIndividual>();
    
    this.population = new DefaultPopulation<ExampleIndividual>();
    this.population.add(new ExampleIndividual(1));
    this.population.add(new ExampleIndividual(2));
    
    this.evolutionContext = new DefaultEvolutionContext<ExampleIndividual>(
        this.population);

    try {
      this.evolutionContext.setFitnessFunction(new ExampleFitnessFunction());
    } catch (final FitnessException exception) {
      fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultMaxFitnessCompletionCriteria#isSatisfied(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testIsSatisfied() {
    try {
      assertFalse(this.completionCriteria.isSatisfied(this.evolutionContext));
      this.population.add(new ExampleIndividual(
          DefaultMaxFitnessCompletionCriteria.DEFAULT_MAX_FITNESS));
      assertTrue(this.completionCriteria.isSatisfied(this.evolutionContext));
    } catch (final CompletionException exception) {
      fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.DefaultMaxFitnessCompletionCriteria#setMaxFitness(double)}
   * .
   */
  @Test
  public void testSetMaxFitness() {
    try {
      assertFalse(this.completionCriteria.isSatisfied(this.evolutionContext));
      this.completionCriteria.setMaxFitness(1);
      assertTrue(this.completionCriteria.isSatisfied(this.evolutionContext));
    } catch (final CompletionException exception) {
      fail(exception);
    }
  }

}
