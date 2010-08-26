/**
 * MonaEvolutionTester.java
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
package jmona.example.monalisa.gp;

import java.awt.image.RenderedImage;
import java.io.IOException;

import jfcommon.test.TestUtils;
import jmona.CompletionCondition;
import jmona.CompletionException;
import jmona.EvolutionException;
import jmona.PopulationEvolutionContext;
import jmona.example.monalisa.Converter;
import jmona.example.monalisa.io.ImageWriter;
import jmona.gp.Tree;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for the Mona genetic programming example for matching an image
 * with random colored polygons.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MonaEvolutionTester {

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
  private CompletionCondition<Tree> completionCondition = null;
  /** Get the evolution context from the Spring XML configuration file. */
  @Autowired
  private PopulationEvolutionContext<Tree> context = null;

  /** The height of the image. */
  @Autowired
  private Integer height = null;

  /** The width of the image. */
  @Autowired
  private Integer width = null;

  /** Test the evolution. */
  @Test
  @DirtiesContext
  public final void testEvolution() {
    try {
      while (!this.completionCondition.execute(this.context)) {
        this.context.stepGeneration();
        LOG.debug(this.context.currentGeneration() + ": "
            + this.context.currentPopulation());
      }
    } catch (final CompletionException exception) {
      TestUtils.fail(exception);
    } catch (final EvolutionException exception) {
      TestUtils.fail(exception);
    }

    try {
      final Tree individual = this.context.currentPopulation().get(0);
      final ColoredPolygonNode root = (ColoredPolygonNode) individual.root();
      final RenderedImage image = Converter.toImage(root.evaluate(),
          this.width, this.height);
      ImageWriter.writeImage(image, FILENAME);
    } catch (final IOException exception) {
      TestUtils.fail(exception);
    }

  }
}
