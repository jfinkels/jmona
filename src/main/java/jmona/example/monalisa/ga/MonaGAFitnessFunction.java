/**
 * MonaGAFitnessFunction.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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
package jmona.example.monalisa.ga;

import java.awt.image.BufferedImage;
import java.util.List;

import jmona.FitnessException;
import jmona.MetricException;
import jmona.example.monalisa.ColoredPolygon;
import jmona.example.monalisa.Converter;
import jmona.example.monalisa.FixedImageMetric;
import jmona.impl.MinimizingFitnessFunction;

/**
 * A FitnessFunction which measures raw fitness as Euclidean distance between
 * two images in RGB color space.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class MonaGAFitnessFunction extends
    MinimizingFitnessFunction<List<ColoredPolygon>> {

  /** The height of the target image. */
  private final int height;
  /**
   * The metric with which to measure distance of individuals from a target
   * image.
   */
  private final FixedImageMetric metric;
  /** The width of the target image. */
  private final int width;

  /**
   * Instantiates this FitnessFunction with the specified target image against
   * which individuals will be compared.
   * 
   * @param targetImage
   *          The target image against which individuals will be compared.
   * @throws InterruptedException
   *           If there is a problem reading the pixels from the specified
   *           target image.
   */
  public MonaGAFitnessFunction(final BufferedImage targetImage)
      throws InterruptedException {
    this.metric = new FixedImageMetric(targetImage);
    this.width = targetImage.getWidth();
    this.height = targetImage.getHeight();
  }

  /**
   * Returns the Euclidean distance of the image represented by the specified
   * individual and the target image in RGB color space.
   * 
   * A smaller distance is better, 0 is a perfect match.
   * 
   * @param individual
   *          The individual whose distance from the target image will be
   *          determined.
   * @return The Euclidean distance between the image represented by the
   *         specified individual and the target image in RGB color space.
   * @throws FitnessException
   *           If there is a problem determining the distance of the specified
   *           individual from the target image.
   * @see jmona.FitnessFunction#rawFitness(java.lang.Object)
   */
  @Override
  public double rawFitness(final List<ColoredPolygon> individual)
      throws FitnessException {
    try {
      return this.metric.distanceFromTarget(Converter.toImage(individual,
          this.width, this.height));
    } catch (final MetricException exception) {
      throw new FitnessException(
          "Failed to determine the distance of this individual from the target image.",
          exception);
    }
  }

}
