/**
 * MonaEvolutionContextTester.java
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
package jmona.example.monalisa;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import jmona.CompletionCriteria;
import jmona.CompletionException;
import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.Population;
import jmona.Util;
import jmona.example.monalisa.output.ImageWriter;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test class for an evolution of the Mona example.
 * 
 * @author jeff
 */
@ContextConfiguration
public class MonaEvolutionContextTester extends
    AbstractJUnit4SpringContextTests {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(MonaEvolutionContextTester.class);

  /** The filename at which to write a test image. */
  public static final String OUTPUT_FILENAME = "target/final.png";

  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCriteria<MonaIndividual> completionCriteria = null;

  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private EvolutionContext<MonaIndividual> context = null;

  /** Perform cleanup after each test. */
  @After
  public final void tearDown() {
    final File outputFile = new File(OUTPUT_FILENAME);
    if (outputFile.exists() && !outputFile.delete()) {
      LOG.debug("Failed to delete output file at " + OUTPUT_FILENAME);
    }
  }

  /** Test method for a Mona evolution. */
  @Test
  public final void testEvolution() {
    // could not autowire because spring could distinguish between these 2 beans
    final int height = (Integer) this.applicationContext.getBean("height");
    final int width = (Integer) this.applicationContext.getBean("width");

    try {
      while (!this.completionCriteria.isSatisfied(this.context)) {
        this.context.stepGeneration();
        LOG.debug("Current generation: " + this.context.currentGeneration());
      }
    } catch (final CompletionException exception) {
      Util.fail(exception);
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    } finally {
      // get the population at the last generation of the evolution
      final Population<MonaIndividual> currentPopulation = this.context
          .currentPopulation();

      // get one of the population
      final MonaIndividual individual = currentPopulation.get(0);

      // convert the individual into an image
      final BufferedImage image = ImageWriter
          .toImage(individual, width, height);

      // write the image to a file
      try {
        ImageWriter.writeImage(image, OUTPUT_FILENAME);
      } catch (final IOException exception) {
        Util.fail(exception);
      }
    }
  }
}
