/**
 * ImageOutputPostProcessorTester.java
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
package jmona.example.monalisa.output;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.ProcessingException;
import jmona.example.monalisa.MonaIndividual;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test class for the ImageOutputPostProcessor class.
 * 
 * @author jeff
 */
@ContextConfiguration(locations = "classpath:/jmona/example/monalisa/MonaEvolutionContextTester-context.xml")
public class ImageOutputPostProcessorTester extends
    AbstractJUnit4SpringContextTests {

  /**
   * Print the stack trace of the specified Throwable cause of the failure, then
   * fail the current test.
   * 
   * @param cause
   *          The cause for the test failure.
   */
  protected static final void fail(final Throwable cause) {
    cause.printStackTrace(System.err);
    org.junit.Assert.fail(cause.getMessage());
  }

  /** An EvolutionContext to process. */
  @Autowired
  private EvolutionContext<MonaIndividual> evolutionContext = null;

  /** The PostProcessor under test. */
  private ImageOutputPostProcessor processor = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.processor = new ImageOutputPostProcessor();
    this.processor.setOutputDir("target");
    this.processor.setWidth(1);
    this.processor.setHeight(1);
  }

  /** Test method for default period length of 1. */
  @DirtiesContext
  @Test
  public void testDefaultPeriod() {

    try {
      this.processor.process(this.evolutionContext);
      this.evolutionContext.stepGeneration();
      this.processor.process(this.evolutionContext);
    } catch (final ProcessingException exception) {
      fail(exception);
    } catch (final EvolutionException exception) {
      fail(exception);
    }

    final File outputFile1 = new File("target/generation0.png");
    final File outputFile2 = new File("target/generation1.png");

    assertTrue("file1 does not exist.", outputFile1.exists());
    assertTrue("file2 does not exist.", outputFile2.exists());

    assertTrue(outputFile1.delete());
    assertTrue(outputFile2.delete());
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.output.ImageOutputPostProcessor#processAtInterval(jmona.EvolutionContext)}
   * .
   */
  @DirtiesContext
  @Test
  public void testProcessAtInterval() {

    try {
      this.processor.processAtInterval(null);
    } catch (final ProcessingException exception) {
      fail(exception);
    } catch (final NullPointerException exception) {
      assertTrue(exception instanceof NullPointerException);
    }

    try {
      this.processor.processAtInterval(this.evolutionContext);
    } catch (final ProcessingException exception) {
      fail(exception);
    }

  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.output.ImageOutputPostProcessor#setPeriod(int)}
   * .
   */
  @DirtiesContext
  @Test
  public void testSetPeriod() {
    final int newPeriod = 5;
    this.processor.setPeriod(newPeriod);

    final File outputFile = new File("target/generation4.png");
    assertTrue(!outputFile.exists() || outputFile.delete());
    
    for (int i = 0; i < newPeriod - 1; ++i) {
      try {
        this.processor.process(this.evolutionContext);
        this.evolutionContext.stepGeneration();
        assertFalse("File should not exist, but does.", outputFile.exists());
      } catch (final ProcessingException exception) {
        fail(exception);
      } catch (final EvolutionException exception) {
        fail(exception);
      }
    }

    try {
      this.processor.process(this.evolutionContext);
    } catch (final ProcessingException exception) {
      fail(exception);
    }

    assertTrue(outputFile.exists());
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.output.ImageOutputPostProcessor#setHeight(int)}
   * and
   * {@link jmona.example.monalisa.output.ImageOutputPostProcessor#setWidth(int)}
   * .
   */
  @Test
  public void testSetWidthAndHeight() {
    this.processor.setHeight(-1);

    try {
      this.processor.processAtInterval(this.evolutionContext);
      org.junit.Assert
          .fail("An Exception should have been thrown on the previous line.");
    } catch (final ProcessingException exception) {
      // height has not been set
      this.processor.setHeight(1);
    }

    this.processor.setHeight(1);
    this.processor.setWidth(-1);

    try {
      this.processor.processAtInterval(this.evolutionContext);
      org.junit.Assert
          .fail("An Exception should have been thrown on the previous line.");
    } catch (final ProcessingException exception) {
      // width has not been set
      this.processor.setWidth(1);
    }

    try {
      this.processor.processAtInterval(this.evolutionContext);
    } catch (final ProcessingException exception) {
      fail(exception);
    }
  }

}
