/**
 * LoggingPostProcessorTester.java
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
package jmona.impl.postprocessing;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.LoggingException;
import jmona.ProcessingException;
import jmona.impl.context.AbstractEvolutionContext;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.apache.log4j.Level;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the LoggingPostProcessor class.
 * 
 * I don't know how to capture output from the Logger, so we must examine the
 * output manually.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class LoggingPostProcessorTester {

  /** A message to log. */
  public static final String TEST_MESSAGE = "Hello, world!";
  /** The context about which to log information. */
  private EvolutionContext<ExampleIndividual> context = null;

  /** The PostProcessor under test. */
  private LoggingPostProcessor<ExampleIndividual> processor = null;
  /** The population in the EvolutionContext. */
  private List<ExampleIndividual> population = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    final ExampleIndividual individual1 = new ExampleIndividual();
    final ExampleIndividual individual2 = new ExampleIndividual();

    this.population = new Vector<ExampleIndividual>();
    this.population.add(individual1);
    this.population.add(individual2);

    this.context = new ExampleEvolutionContext(this.population);

    this.processor = new PopulationLoggingPostProcessor<ExampleIndividual>();
  }

  /**
   * Test method for
   * {@link jmona.impl.postprocessing.LoggingPostProcessor#log(java.lang.String)}
   * .
   */
  @Test
  public void testLogString() {
    this.processor.log(TEST_MESSAGE);
  }

  /**
   * Test method for
   * {@link jmona.impl.postprocessing.LoggingPostProcessor#log(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testLogStringThrowable() {
    this.processor.log(TEST_MESSAGE, new Exception());
  }

  /**
   * Test method for
   * {@link jmona.impl.postprocessing.LoggingPostProcessor#processAtInterval(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testProcessAtInterval() {
    try {
      this.processor.processAtInterval(this.context);
    } catch (final ProcessingException exception) {
      Util.fail(exception);
    }
    
    this.processor = new FitnessLoggingPostProcessor<ExampleIndividual>();
    
    final EvolutionContext<ExampleIndividual> badContext = new AbstractEvolutionContext<ExampleIndividual>() {
      @Override
      protected void executeGenerationStep() throws EvolutionException {
        // intentionally unimplemented
      }
    };
        
    try {
      this.processor.processAtInterval(badContext);
      Util.shouldHaveThrownException();
    } catch (final ProcessingException exception) {
      assertTrue(exception.getCause() instanceof LoggingException);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.postprocessing.LoggingPostProcessor#setLoggingLevel(org.apache.log4j.Level)}
   * .
   */
  @Test
  public void testSetLoggingLevel() {
    this.processor.setLoggingLevel(Level.FATAL);
    try {
      this.processor.processAtInterval(this.context);
    } catch (final ProcessingException exception) {
      Util.fail(exception);
    }
  }

}
