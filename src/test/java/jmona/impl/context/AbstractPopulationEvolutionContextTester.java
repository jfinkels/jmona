/**
 * AbstractPopulationEvolutionContextTester.java
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
package jmona.impl.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.Vector;

import jmona.EvolutionException;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractEvolutionContext class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AbstractPopulationEvolutionContextTester {

  /** The Logger for this class. */
  protected static final transient Logger LOG = Logger
      .getLogger(AbstractPopulationEvolutionContextTester.class);
  /** The EvolutionContext under test. */
  private AbstractPopulationEvolutionContext<ExampleIndividual> context = null;
  /** The population in the AbstractEvolutionContext. */
  private List<ExampleIndividual> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.population = new Vector<ExampleIndividual>();

    this.population.add(new ExampleIndividual(0));
    this.population.add(new ExampleIndividual(1));
    this.context = new AbstractPopulationEvolutionContext<ExampleIndividual>(
        this.population) {
      @Override
      protected void executeGenerationStep() throws EvolutionException {
        LOG.debug("Executing generation step.");
      }
    };

  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#AbstractEvolutionContext(java.util.List)}
   * .
   */
  @Test
  public void testAbstractEvolutionContext() {
    assertSame(this.population, this.context.currentPopulation());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#currentGeneration()}.
   */
  @Test
  public void testCurrentGeneration() {
    assertEquals(0, this.context.currentGeneration());
    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
    assertEquals(1, this.context.currentGeneration());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#currentPopulation()}.
   */
  @Test
  public void testCurrentPopulation() {
    assertSame(this.population, this.context.currentPopulation());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#executeGenerationStep()}
   * .
   */
  @Test
  public void testExecuteGenerationStep() {
    try {
      this.context.executeGenerationStep();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#setCurrentPopulation(java.util.List)}
   * .
   */
  @Test
  public void testSetCurrentPopulation() {
    final List<ExampleIndividual> newPopulation = new Vector<ExampleIndividual>();
    this.context.setCurrentPopulation(newPopulation);

    assertSame(newPopulation, this.context.currentPopulation());
  }

  /**
   * Test method for
   * {@link jmona.impl.context.AbstractEvolutionContext#stepGeneration()}.
   */
  @Test
  public void testStepGeneration() {
    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

    assertSame(this.population.get(0), this.context.currentPopulation().get(0));
    assertSame(this.population.get(1), this.context.currentPopulation().get(1));
  }

}
