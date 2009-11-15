/**
 * AbstractEvolutionContextTester.java
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import jmona.EvolutionException;
import jmona.Population;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractEvolutionContext class.
 * 
 * @author jfinkels
 */
public class AbstractEvolutionContextTester {

  /** The context under test in this class. */
  private AbstractEvolutionContext<ExampleIndividual> context = null;

  /** An example EvolutionContext. */
  private class ExampleEvolutionContext extends
      AbstractEvolutionContext<ExampleIndividual> {

    /** The Logger for this class. */
    private final transient Logger log = Logger
        .getLogger(ExampleEvolutionContext.class);

    /**
     * Instantiate this EvolutionContext with the specified initial population.
     * 
     * @param initialPopulation
     *          The initial population of the context.
     */
    public ExampleEvolutionContext(
        final Population<ExampleIndividual> initialPopulation) {
      super(initialPopulation);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws {@inheritDoc}
     * @see jmona.EvolutionContext#stepGeneration()
     */
    @Override
    public void stepGeneration() throws EvolutionException {
      this.log.debug("Stepping generation...");
    }

  }
  
  private Population<ExampleIndividual> population = null;
  
  @Before
  public final void setUp() {
    this.population = new DefaultPopulation<ExampleIndividual>();
    this.population.add(new ExampleIndividual());
    this.population.add(new ExampleIndividual());
    
    this.context = new ExampleEvolutionContext(this.population);
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#AbstractEvolutionContext(jmona.Population)}
   * .
   */
  @Test
  public void testAbstractEvolutionContext() {
    final Population<ExampleIndividual> initialPopulation = new DefaultPopulation<ExampleIndividual>();
    try {
      this.context = new ExampleEvolutionContext(initialPopulation);
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }

    initialPopulation.add(new ExampleIndividual());
    try {
      this.context = new ExampleEvolutionContext(initialPopulation);
    } catch (final IllegalArgumentException exception) {
      assertTrue(exception instanceof IllegalArgumentException);
    }

    initialPopulation.add(new ExampleIndividual());
    try {
      this.context = new ExampleEvolutionContext(initialPopulation);
    } catch (final IllegalArgumentException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.impl.AbstractEvolutionContext#sanityCheck()}.
   */
  @Test
  public void testSanityCheck() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#crossoverFunction()}.
   */
  @Test
  public void testCrossoverFunction() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#crossoverProbability()}.
   */
  @Test
  public void testCrossoverProbability() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#currentFitnesses()}.
   */
  @Test
  public void testCurrentFitnesses() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#currentGeneration()}.
   */
  @Test
  public void testCurrentGeneration() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#currentPopulation()}.
   */
  @Test
  public void testCurrentPopulation() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#fitnessFunction()}.
   */
  @Test
  public void testFitnessFunction() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#incrementGeneration()}.
   */
  @Test
  public void testIncrementGeneration() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#mutationFunction()}.
   */
  @Test
  public void testMutationFunction() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#recalculateFitnesses()}.
   */
  @Test
  public void testRecalculateFitnesses() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#selectionFunction()}.
   */
  @Test
  public void testSelectionFunction() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#setCrossoverFunction(jmona.CrossoverFunction)}
   * .
   */
  @Test
  public void testSetCrossoverFunction() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#setCrossoverProbability(double)}
   * .
   */
  @Test
  public void testSetCrossoverProbability() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#setCurrentPopulation(jmona.Population)}
   * .
   */
  @Test
  public void testSetCurrentPopulation() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#setFitnessFunction(jmona.FitnessFunction)}
   * .
   */
  @Test
  public void testSetFitnessFunction() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#setMutationProbability(double)}.
   */
  @Test
  public void testSetMutationProbability() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#mutationProbability()}.
   */
  @Test
  public void testMutationProbability() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#setMutationFunction(jmona.MutationFunction)}
   * .
   */
  @Test
  public void testSetMutationFunction() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link jmona.impl.AbstractEvolutionContext#setSelectionFunction(jmona.SelectionFunction)}
   * .
   */
  @Test
  public void testSetSelectionFunction() {
    fail("Not yet implemented");
  }

}
