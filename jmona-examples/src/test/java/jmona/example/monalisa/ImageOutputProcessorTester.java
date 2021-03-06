/**
 * ImageOutputProcessorTester.java
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
package jmona.example.monalisa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.DeepCopyableList;
import jmona.GeneticEvolutionContext;
import jmona.InitializationException;
import jmona.ProcessingException;
import jmona.ga.impl.GAEvolutionContext;
import jmona.impl.DeepCopyableListFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ImageOutputProcessor class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ImageOutputProcessorTester {

  /** The Processor under test. */
  private ImageOutputProcessor<DeepCopyableList<ColoredPolygon>, GeneticEvolutionContext<DeepCopyableList<ColoredPolygon>>> processor = null;
  /** The width of the output image. */
  public static final int WIDTH = 5;
  /** The height of the output image. */
  public static final int HEIGHT = 5;
  /** The output directory for the image. */
  public static final String OUTPUT_DIRECTORY = "target/";

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.processor = new ImageOutputProcessor<DeepCopyableList<ColoredPolygon>, GeneticEvolutionContext<DeepCopyableList<ColoredPolygon>>>(
        WIDTH, HEIGHT);
    this.processor.setOutputDir(OUTPUT_DIRECTORY);
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ImageOutputProcessor#generateFilename(java.lang.String, int)}
   * .
   */
  @Test
  public void testGenerateFilename() {
    final String directory = "target";
    final int generation = 1;
    final String filename = ImageOutputProcessor.generateFilename(directory,
        generation);

    assertEquals(
        directory + ImageOutputProcessor.FILE_SEPARATOR
            + String.format(ImageOutputProcessor.FILENAME_FORMAT, generation),
        filename);

    final String filename2 = ImageOutputProcessor.generateFilename(directory
        + "/", generation);

    assertEquals(filename, filename2);
  }

  /**
   * Test method for
   * {@link jmona.example.monalisa.ImageOutputProcessor#processAtInterval(jmona.EvolutionContext)}
   * .
   */
  @Test
  public void testProcessAtInterval() {
    final ColoredPolygonFactory polygonFactory = new ColoredPolygonFactory();
    polygonFactory.setMaxX(WIDTH);
    polygonFactory.setMaxY(HEIGHT);

    final DeepCopyableListFactory<ColoredPolygon> factory = new DeepCopyableListFactory<ColoredPolygon>(
        0);
    factory.setElementFactory(new ColoredPolygonFactory());

    DeepCopyableList<ColoredPolygon> individual1 = null;
    DeepCopyableList<ColoredPolygon> individual2 = null;
    try {
      individual1 = factory.createObject();
      individual2 = factory.createObject();
    } catch (final InitializationException exception) {
      TestUtils.fail(exception);
    }

    final List<DeepCopyableList<ColoredPolygon>> population = new Vector<DeepCopyableList<ColoredPolygon>>();
    population.add(individual1);
    population.add(individual2);

    final GeneticEvolutionContext<DeepCopyableList<ColoredPolygon>> context = new GAEvolutionContext<DeepCopyableList<ColoredPolygon>>(
        population);

    try {
      this.processor.processAtInterval(context);
    } catch (final ProcessingException exception) {
      TestUtils.fail(exception);
    }

    final File file = new File(OUTPUT_DIRECTORY
        + ImageOutputProcessor.FILE_SEPARATOR
        + String.format(ImageOutputProcessor.FILENAME_FORMAT,
            context.currentGeneration()));

    assertTrue(file.exists());
  }

  /** Free external resources allocated during tests. */
  @After
  public final void tearDown() {
    final File file = new File(OUTPUT_DIRECTORY
        + ImageOutputProcessor.FILE_SEPARATOR
        + String.format(ImageOutputProcessor.FILENAME_FORMAT, 0));
    if (file.exists()) {
      assertTrue(file.delete());
    }
  }
}
