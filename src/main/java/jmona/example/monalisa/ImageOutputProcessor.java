/**
 * ImageOutputProcessor.java
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

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.List;

import jmona.PopulationEvolutionContext;
import jmona.ProcessingException;
import jmona.example.monalisa.io.ImageWriter;
import jmona.impl.postprocessing.PeriodicProcessor;

/**
 * A Processor which writes an individual out to an image on the filesystem.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class ImageOutputProcessor<L extends List<ColoredPolygon>, E extends PopulationEvolutionContext<L>>
    extends PeriodicProcessor<L, E> {

  /** The default directory in which to write images. */
  public static final String DEFAULT_OUTPUT_DIR = "target";
  /** The format for the filename of the image to write. */
  public static final String FILENAME_FORMAT = "generation%d.png";
  /** The height of the image to output. */
  private final int height;
  /** The directory in which to write images. */
  private String outputDir = DEFAULT_OUTPUT_DIR;
  /** The key for the System property containing the file separator character. */
  public static final String FILE_SEPARATOR_KEY = "file.separator";
  /** The System dependent file separator character. */
  public static final String FILE_SEPARATOR = System
      .getProperty(FILE_SEPARATOR_KEY);
  /** The width of the image to output. */
  private final int width;

  /**
   * Instantiate this Processor with the specified width and height of the image
   * to write.
   * 
   * @param initialWidth
   *          The width of the image to write.
   * @param initialHeight
   *          The height of the image to write.
   */
  public ImageOutputProcessor(final int initialWidth, final int initialHeight) {
    this.width = initialWidth;
    this.height = initialHeight;
  }

  /**
   * Generate the filename at which the image which an Individual represents
   * will be output.
   * 
   * @param outputDirectory
   *          The directory in which to write the file.
   * @param currentGeneration
   *          The current generation of the EvolutionContext.
   * @return The filename at which the image represented by an Individual will
   *         be output.
   */
  protected static String generateFilename(final String outputDirectory,
      final int currentGeneration) {

    // add a file separator character if the specified output directory does not
    // already end with one
    String directory = outputDirectory;
    if (!directory.endsWith(FILE_SEPARATOR)) {
      directory += FILE_SEPARATOR;
    }

    return directory + String.format(FILENAME_FORMAT, currentGeneration);
  }

  /**
   * Write the first Individual in the current population of the specified
   * EvolutionContext out to an image.
   * 
   * The image will be written at the filename specified by the return value of
   * the {@link #generateFilename(String, int)} method with the output directory
   * name property and the current generation number as input.
   * 
   * @param evolutionContext
   *          The EvolutionContext containing the population from which to get
   *          an Individual.
   * @throws ProcessingException
   *           If the height or width of the output image has not been set, or
   *           if there is a problem writing the image to disk.
   * @see jmona.impl.postprocessing.PeriodicProcessor#processAtInterval(jmona.EvolutionContext)
   */
  @Override
  protected void processAtInterval(final E evolutionContext)
      throws ProcessingException {

    // get the current generation number
    final int currentGeneration = evolutionContext.currentGeneration();

    // get an individual from the current population
    final List<ColoredPolygon> individual = evolutionContext
        .currentPopulation().get(0);

    // create an image from the specified individual
    final RenderedImage image = Converter.toImage(individual, this.width,
        this.height);

    // generate a filename at which to write this image
    final String filename = generateFilename(this.outputDir, currentGeneration);

    // write the image to the specified filename
    try {
      ImageWriter.writeImage(image, filename);
    } catch (final IOException exception) {
      throw new ProcessingException("Failed writing image to disk.", exception);
    }
  }

  /**
   * Set the directory in which to write images.
   * 
   * @param newOutputDir
   *          The directory in which to write images.
   */
  public void setOutputDir(final String newOutputDir) {
    this.outputDir = newOutputDir;
  }
}
