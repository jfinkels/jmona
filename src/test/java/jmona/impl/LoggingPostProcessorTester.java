/**
 * LoggingPostProcessorTester.java
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

import java.util.List;
import java.util.Vector;

import jmona.EvolutionContext;
import jmona.impl.example.ExampleEvolutionContext;
import jmona.impl.example.ExampleIndividual;

import org.apache.log4j.Level;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the LoggingPostProcessor class.
 * 
 * I don't know how to capture output from the Logger, so we must examine the
 * output manually.
 * 
 * @author jfinkels
 */
public class LoggingPostProcessorTester {

  /** A message to log. */
  public static final String TEST_MESSAGE = "Hello, world!";
  /** The context about which to log information. */
  private EvolutionContext<ExampleIndividual> context = null;

  /** The PostProcessor under test. */
  private LoggingPostProcessor<ExampleIndividual> processor = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    final ExampleIndividual individual1 = new ExampleIndividual();
    final ExampleIndividual individual2 = new ExampleIndividual();

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);

    this.context = new ExampleEvolutionContext(population);

    this.processor = new PopulationLoggingPostProcessor<ExampleIndividual>();
  }

  /**
   * Test method for
   * {@link jmona.impl.LoggingPostProcessor#log(java.lang.String)}.
   */
  @Test
  public void testLogString() {
    this.processor.log(TEST_MESSAGE);
  }

  /**
   * Test method for
   * {@link jmona.impl.LoggingPostProcessor#log(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public void testLogStringThrowable() {
    this.processor.log(TEST_MESSAGE, new Exception());
  }

  /**
   * Test method for
   * {@link jmona.impl.LoggingPostProcessor#processAtInterval(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testProcessAtInterval() {
    this.processor.processAtInterval(this.context);
  }

  /**
   * Test method for
   * {@link jmona.impl.LoggingPostProcessor#setLoggingLevel(org.apache.log4j.Level)}
   * .
   */
  @Test
  public void testSetLoggingLevel() {
    this.processor.setLoggingLevel(Level.FATAL);
    this.processor.processAtInterval(this.context);
  }

}
