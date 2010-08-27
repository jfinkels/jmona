/**
 * PerfectMatchCompletionConditionTester.java
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

import jfcommon.functional.MappingException;
import jfcommon.test.TestUtils;
import jmona.FitnessException;
import jmona.GeneticEvolutionContext;
import jmona.ga.impl.GAEvolutionContext;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PerfectMatchCompletionCondition class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class PerfectMatchCompletionConditionTester {

  /** The completion criteria under test. */
  private PerfectMatchCompletionCondition<ExampleIndividual> completionCriteria = null;
  /** The evolution context on which to test the completion criteria. */
  private GeneticEvolutionContext<ExampleIndividual> evolutionContext = null;
  /** The population in the evolution context. */
  private List<ExampleIndividual> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.completionCriteria = new PerfectMatchCompletionCondition<ExampleIndividual>();

    this.population = new Vector<ExampleIndividual>();
    this.population.add(new ExampleIndividual(1));
    this.population.add(new ExampleIndividual(2));

    this.evolutionContext = new GAEvolutionContext<ExampleIndividual>(
        this.population);

    try {
      this.evolutionContext.setFitnessFunction(new ExampleFitnessFunction());
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.completion.PerfectMatchCompletionCondition#execute(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testExecute() {

    try {
      assertFalse(this.completionCriteria.execute(this.evolutionContext));
      this.population.add(new ExampleIndividual(0));
      this.evolutionContext.setFitnessFunction(new ExampleFitnessFunction());
      assertTrue(this.completionCriteria.execute(this.evolutionContext));
    } catch (final MappingException exception) {
      TestUtils.fail(exception);
    } catch (final FitnessException exception) {
      TestUtils.fail(exception);
    }
  }

}
