/**
 * ImageOutputPostProcessor.java
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

import java.awt.image.BufferedImage;
import java.io.IOException;

import jmona.EvolutionContext;
import jmona.ProcessingException;
import jmona.example.monalisa.MonaIndividual;
import jmona.impl.PeriodicPostProcessor;

import org.apache.log4j.Logger;

/**
 * A PostProcessor which writes an Individual out to an image on the filesystem.
 * 
 * @author jeff
 */
public class ImageOutputPostProcessor extends
    PeriodicPostProcessor<MonaIndividual> {
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(ImageOutputPostProcessor.class);
  /** The height of the image to output. */
  private int height = -1;
  /** The directory in which to write images. */
  private String outputDir = ".";

  /** The width of the image to output. */
  private int width = -1;

  /**
   * Generate the filename at which the image which an Individual represents
   * will be output.
   * 
   * @param currentGeneration
   *          The current generation of the EvolutionContext.
   * @return The filename at which the image represented by an Individual will
   *         be output.
   */
  private String generateFilename(final int currentGeneration) {
    if (!this.outputDir.endsWith("/")) {
      this.outputDir += "/";
    }

    return String.format(this.outputDir + "generation%1$d.png",
        currentGeneration);
  }

  /**
   * Write the first Individual in the current population of the specified
   * EvolutionContext out to an image.
   * 
   * The image will be written at the filename specified by the return value of
   * the {@link #generateFilename(int)} method with current generation number as
   * input.
   * 
   * @param evolutionContext
   *          The EvolutionContext containing the population from which to get
   *          an Individual.
   * @throws ProcessingException
   *           If the height or width of the output image has not been set, or
   *           if there is a problem writing the image to disk.
   * @see jmona.impl.PeriodicPostProcessor#processAtInterval(jmona.EvolutionContext)
   */
  @Override
  protected void processAtInterval(
      final EvolutionContext<MonaIndividual> evolutionContext)
      throws ProcessingException {
    if (this.height < 0) {
      throw new ProcessingException("Height is " + this.height
          + ". It has not been set, or is less than 0.");
    }

    if (this.width < 0) {
      throw new ProcessingException("Width is " + this.width
          + ". It has not been set, or is less than 0.");
    }

    // get the current generation number
    final int currentGeneration = evolutionContext.currentGeneration();

    // get an individual from the current population
    final MonaIndividual individual = evolutionContext.currentPopulation().get(
        0);

    // create an image from the specified individual
    final BufferedImage image = ImageWriter.toImage(individual, this.width,
        this.height);

    // generate a filename at which to write this image
    final String filename = this.generateFilename(currentGeneration);

    // write the image to the specified filename
    try {
      LOG.debug("Writing image to file " + filename);
      ImageWriter.writeImage(image, filename);
    } catch (final IOException exception) {
      throw new ProcessingException("Failed writing image to disk.", exception);
    }
  }

  /**
   * Set the height of the output image.
   * 
   * @param newHeight
   *          The height of the output image.
   */
  public void setHeight(final int newHeight) {
    this.height = newHeight;
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

  /**
   * Set the width of the output image.
   * 
   * @param newWidth
   *          The width of the output image.
   */
  public void setWidth(final int newWidth) {
    this.width = newWidth;
  }

}
