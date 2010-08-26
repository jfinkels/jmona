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
import jfcommon.test.TestUtils;
import jmona.EvolutionException;
import jmona.impl.example.ExampleIndividual;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the AbstractEvolutionContext class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class AbstractEvolutionContextTester {

  /** The Logger for this class. */
  protected static final transient Logger LOG = Logger
      .getLogger(AbstractEvolutionContextTester.class);
  /** The EvolutionContext under test. */
  private AbstractEvolutionContext<ExampleIndividual> context = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {

    this.context = new AbstractEvolutionContext<ExampleIndividual>() {
      @Override
      protected void executeGenerationStep() throws EvolutionException {
        LOG.debug("Executing generation step.");
      }
    };

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
   * {@link jmona.impl.context.AbstractEvolutionContext#stepGeneration()}.
   */
  @Test
  public void testStepGeneration() {
    try {
      this.context.stepGeneration();
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }
  }

}
