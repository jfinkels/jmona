/**
 * MonaMutationFunction.java
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
package jmona.example.ga.monalisa;

import java.awt.Color;
import java.awt.Polygon;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import jmona.MutationException;
import jmona.MutationFunction;
import jmona.impl.Util;

/**
 * A mutation function which mutates with a certain probability the polygons
 * (and corresponding colors) in the gene of an individual.
 * 
 * @author jfinkels
 */
public class MonaMutationFunction implements MutationFunction<MonaIndividual> {

  /** The probability of a mutation of a polygon in any individual. */
  private static final double DEFAULT_MUTATION_PROBABILITY = 0.6;
  /** The maximum value of the red, green, blue, and alpha fields of a color. */
  public static final int MAX_COLOR_VALUE = 255;
  /** The maximum value to shift when mutating colors. */
  public static final int MAX_SHIFT = 50;
  /** The maximum height beyond which no polygon may have points. */
  private int height = -1;
  /** The probability of mutating a polygon in the gene of a MonaIndividual. */
  private double mutationProbability = DEFAULT_MUTATION_PROBABILITY;
  /** The maximum width beyond which no polygon may have points. */
  private int width = -1;

  /**
   * Mutate the specified color to a nearby color without changing the alpha
   * value.
   * 
   * @param color
   *          The input color.
   * @return The mutated copy of the input color.
   */
  protected Color mutate(final Color color) {
    // get the shifted colors
    int newRed = color.getRed()
        + (Util.RANDOM.nextInt(MAX_SHIFT * 2) - MAX_SHIFT);
    int newGreen = color.getGreen()
        + (Util.RANDOM.nextInt(MAX_SHIFT * 2) - MAX_SHIFT);
    int newBlue = color.getBlue()
        + (Util.RANDOM.nextInt(MAX_SHIFT * 2) - MAX_SHIFT);

    // ensure that the values are within the permissible range of values for
    // fields of a color, that is, between 0 and 255, inclusive
    newRed = Math.max(Math.min(newRed, MAX_COLOR_VALUE), 0);
    newGreen = Math.max(Math.min(newGreen, MAX_COLOR_VALUE), 0);
    newBlue = Math.max(Math.min(newBlue, MAX_COLOR_VALUE), 0);

    return new Color(newRed, newGreen, newBlue, color.getAlpha());
  }

  /**
   * Mutate (in-place) the polygons contained in the specified individual with a
   * certain probability.
   * 
   * @param individual
   *          The individual to mutate.
   * @throws MutationException
   *           {@inheritDoc}
   * @see jmona.MutationFunction#mutate(jmona.Individual)
   */
  @Override
  public void mutate(final MonaIndividual individual) throws MutationException {
    if (this.width <= 0) {
      throw new MutationException(
          "Width has not been set, or it is less than 0.");
    }
    if (this.height <= 0) {
      throw new MutationException(
          "Height has not been set, or it is less than 0.");
    }

    // get the map of polygons and colors in the individual
    final Map<Polygon, Color> polygons = individual.gene();

    // initialize a new map for all changes made to the original map
    final Map<Polygon, Color> changes = new HashMap<Polygon, Color>();

    // the list of polygons which were mutated, for later removal from the gene
    // of the individual
    final Collection<Polygon> changedPolygons = new Vector<Polygon>();

    // iterate over each polygon to color pair in the map
    // TODO check if polygons or colors need to be cloned
    Polygon currentPolygon = null, mutatedPolygon = null;
    Color currentColor = null, mutatedColor = null;
    for (final Entry<Polygon, Color> entry : polygons.entrySet()) {
      if (Util.RANDOM.nextDouble() <= this.mutationProbability) {
        // get the current polygon and color from the map
        currentPolygon = entry.getKey();
        currentColor = entry.getValue();

        // add the current polygon to the list of changed polygons
        changedPolygons.add(currentPolygon);

        // mutate the polygon and its color
        mutatedPolygon = this.mutate(currentPolygon);
        mutatedColor = this.mutate(currentColor);

        // add the new polygon and color to the list of changes
        changes.put(mutatedPolygon, mutatedColor);
      }
    }

    // remove all changed polygons from the gene of the individual
    for (final Polygon changedPolygon : changedPolygons) {
      polygons.remove(changedPolygon);
    }

    // put the new changed polygons into the gene of the individual
    polygons.putAll(changes);

  }

  /**
   * Mutate the specified polygon by transforming one of its points to a random
   * point within the bounds specified by this mutator function.
   * 
   * @param polygon
   *          The polygon to mutate
   * @return A mutated copy of the input polygon, with one of its points changed
   *         to a random point.
   */
  protected Polygon mutate(final Polygon polygon) {
    final Polygon result = new Polygon(polygon.xpoints.clone(), polygon.ypoints
        .clone(), polygon.npoints);

    // choose a point to mutate
    final int mutationPoint = Util.RANDOM.nextInt(result.npoints);

    // mutate the x and y values of the point
    result.xpoints[mutationPoint] = (int) (Util.RANDOM.nextDouble() * this.width);
    result.ypoints[mutationPoint] = (int) (Util.RANDOM.nextDouble() * this.height);

    return result;
  }

  /**
   * Set the maximum height beyond which no polygon may have a point.
   * 
   * @param newHeight
   *          The maximum height beyond which no polygon may have a point.
   */
  public void setHeight(final int newHeight) {
    this.height = newHeight;
  }

  /**
   * Set the probability of mutating a polygon in the gene of a MonaIndividual.
   * 
   * @param newMutationProbability
   *          The probability of mutating a polygon in the gene of a
   *          MonaIndividual.
   */
  public void setMutationProbability(final double newMutationProbability) {
    this.mutationProbability = newMutationProbability;
  }

  /**
   * Set the maximum width beyond which no polygon may have a point.
   * 
   * @param newWidth
   *          The maximum width beyond which no polygon may have a point.
   */
  public void setWidth(final int newWidth) {
    this.width = newWidth;
  }
}
