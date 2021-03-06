/**
 * PeriodicProcessorTester.java
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
package jmona.impl.processing;

import static org.junit.Assert.assertEquals;
import jfcommon.test.TestUtils;
import jmona.ProcessingException;
import jmona.impl.example.ExampleProcessor;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PeriodicProcessor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class PeriodicProcessorTester {

  /** The processor under test. */
  private ExampleProcessor processor = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.processor = new ExampleProcessor();
  }

  /**
   * Test method for
   * {@link jmona.impl.processing.PeriodicProcessor#process(jmona.EvolutionContext)}.
   */
  @Test
  public void testProcess() {
    try {
      assertEquals(0, this.processor.count());
      this.processor.process(null);
      assertEquals(1, this.processor.count());
      this.processor.process(null);
      assertEquals(2, this.processor.count());
    } catch (final ProcessingException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.processing.PeriodicProcessor#processAtInterval(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testProcessAtInterval() {
    try {
      assertEquals(0, this.processor.count());
      this.processor.process(null);
      assertEquals(1, this.processor.count());
      this.processor.process(null);
      assertEquals(2, this.processor.count());
    } catch (final ProcessingException exception) {
      TestUtils.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.impl.processing.PeriodicProcessor#setPeriod(int)}.
   */
  @Test
  public void testSetPeriod() {
    this.processor.setPeriod(2);
    try {
      assertEquals(0, this.processor.count());
      this.processor.process(null);
      assertEquals(0, this.processor.count());
      this.processor.process(null);
      assertEquals(1, this.processor.count());
      this.processor.process(null);
      assertEquals(1, this.processor.count());
      this.processor.process(null);
      assertEquals(2, this.processor.count());
      this.processor.process(null);
      assertEquals(2, this.processor.count());
    } catch (final ProcessingException exception) {
      TestUtils.fail(exception);
    }
  }

}
