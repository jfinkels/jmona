/**
 * MonaEvolutionTester.java
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
package jmona.example.monalisa.gp;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.List;

import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.EvolutionContext;
import jmona.EvolutionException;
import jmona.example.monalisa.ColoredPolygon;
import jmona.example.monalisa.Converter;
import jmona.example.monalisa.io.ImageWriter;
import jmona.gp.EvaluationException;
import jmona.gp.Tree;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Test class for the Mona genetic programming example for matching an image
 * with random colored polygons.
 * 
 * @author jfinkels
 */
@ContextConfiguration
public class MonaEvolutionTester extends AbstractJUnit4SpringContextTests {

  /**
   * The file to which to write the best individual at the end of the evolution.
   */
  public static final String FILENAME = "target/mona.png";
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(MonaEvolutionTester.class);
  /**
   * Get the completion criteria for this evolution from the Spring XML
   * configuration file.
   */
  @Autowired
  private CompletionCondition<Tree<List<ColoredPolygon>>> completionCondition = null;
  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private EvolutionContext<Tree<List<ColoredPolygon>>> context = null;

  /** Test the evolution. */
  @Test
  @DirtiesContext
  public final void testEvolution() {
    try {
      while (!this.completionCondition.isSatisfied(this.context)) {
        this.context.stepGeneration();
        LOG.debug(this.context.currentGeneration() + ": "
            + this.context.currentPopulation());
      }
    } catch (final CompletionException exception) {
      Util.fail(exception);
    } catch (final EvolutionException exception) {
      Util.fail(exception);
    } catch (final ArrayIndexOutOfBoundsException exception) {
      Util.fail(exception);
    } catch (final NullPointerException exception) {
      Util.fail(exception);
    }

    // could not autowire because spring could distinguish between these 2 beans
    final int width = (Integer) this.applicationContext.getBean("width");
    final int height = (Integer) this.applicationContext.getBean("height");

    try {
      final Tree<List<ColoredPolygon>> individual = this.context
          .currentPopulation().get(0);
      final RenderedImage image = Converter.toImage(individual.evaluate(),
          width, height);
      ImageWriter.writeImage(image, FILENAME);
    } catch (final EvaluationException exception) {
      Util.fail(exception);
    } catch (final IOException exception) {
      Util.fail(exception);
    }

  }
}
