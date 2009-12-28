/**
 * PopulationLoggingPostProcessorTester.java
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

import jmona.EvolutionContext;
import jmona.exceptions.EvolutionException;
import jmona.exceptions.ProcessingException;
import jmona.test.Util;

import org.apache.log4j.Level;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test class for the PopulationLoggingPostProcessor class.
 * 
 * @author jfinkels
 */
@ContextConfiguration(locations = "classpath:/jmona/example/ones/OnesEvolutionTester-context.xml")
@SuppressWarnings("unchecked")
public class PopulationLoggingPostProcessorTester extends
    AbstractJUnit4SpringContextTests {

  /** The EvolutionContext from which to get a population for logging. */
  @Autowired
  private EvolutionContext evolutionContext = null;
  /** The PostProcessor under test. */
  private PopulationLoggingPostProcessor processor = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.processor = new PopulationLoggingPostProcessor();
  }

  /**
   * Test method for
   * {@link jmona.impl.PopulationLoggingPostProcessor#process(jmona.EvolutionContext)}
   * .
   */
  @DirtiesContext
  @Test
  public void testProcess() {
    try {
      processor.process(evolutionContext);
      this.evolutionContext.stepGeneration();
      processor.process(evolutionContext);
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    } catch (final ProcessingException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.PopulationLoggingPostProcessor#setLoggingLevel(org.apache.log4j.Level)}
   * .
   */
  @Test
  public void testSetLoggingLevel() {
    this.processor.setLoggingLevel(Level.FATAL);
    try {
      this.processor.process(this.evolutionContext);
    } catch (final ProcessingException exception) {
      Util.fail(exception);
    }
  }
}
