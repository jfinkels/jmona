/**
 * MonaIndividualFactory.java
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

import jmona.IndividualFactory;
import jmona.impl.Util;

/**
 * A factory for creating MonaIndividuals with random polygons.
 * 
 * @author jfinkels
 */
public class MonaIndividualFactory implements IndividualFactory<MonaIndividual> {

  /** The maximum value for a red, green, blue, or alpha color value. */
  public static final int MAX_VALUE = 256;
  /**
   * The default alpha value is the quotient of {@value #MAX_VALUE} and this (
   * {@value #DEFAULT_ALPHA_VALUE_SCALE}).
   */
  public static final int DEFAULT_ALPHA_VALUE_SCALE = 5;
  /** The default alpha values for colors in the individuals. */
  public static final int DEFAULT_ALPHA_VALUE = MAX_VALUE
      / DEFAULT_ALPHA_VALUE_SCALE;
  /** The default initial number of polygons in a created individual. */
  public static final int DEFAULT_INITIAL_POLYGONS = 128;
  /** The default maximum number of points in a polygon. */
  public static final int DEFAULT_MAX_POINTS = 10;
  /** The default maximum x value of a polygon. */
  public static final int DEFAULT_MAX_X = 256;
  /** The default maximum y value of a polygon. */
  public static final int DEFAULT_MAX_Y = 256;
  /** The default minimum number of points in a polygon. */
  public static final int DEFAULT_MIN_POINTS = 3;

  /** The fixed alpha value for all colors in the individuals. */
  private int alphaValue = DEFAULT_ALPHA_VALUE;
  /** The number of initial polygons in a created individual. */
  private int initialPolygons = DEFAULT_INITIAL_POLYGONS;
  /** The maximum number of points in a polygon. */
  private int maxPoints = DEFAULT_MAX_POINTS;
  /** The maximum x value of a polygon. */
  private int maxX = DEFAULT_MAX_X;
  /** The maximum y value of a polygon. */
  private int maxY = DEFAULT_MAX_Y;
  /** The minimum number of points in a polygon. */
  private int minPoints = DEFAULT_MIN_POINTS;

  /**
   * Create a new individual with a gene consisting of a random number of random
   * polygons with random colors.
   * 
   * @return A new individual with a gene consisting of a random number of
   *         random polygons with random colors.
   * 
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public MonaIndividual createIndividual() {
    final MonaIndividual result = new MonaIndividual();

    // iterate over the specified number of initial polygons
    Polygon polygon = null;
    Color color = null;
    int numPoints = 0;
    int[] xpoints = null;
    int[] ypoints = null;
    for (int i = 0; i < this.initialPolygons; ++i) {

      // randomly select the number of points in the polygon between min and max
      numPoints = this.minPoints
          + Util.RANDOM.nextInt(this.maxPoints - this.minPoints);

      // create new arrays to contain the x and y coordinates
      xpoints = new int[numPoints];
      ypoints = new int[numPoints];

      // iterate over the number of points in the array
      for (int j = 0; j < numPoints; ++j) {
        // randomly select a new x and y less than the max width and height
        xpoints[j] = Util.RANDOM.nextInt(this.maxX);
        ypoints[j] = Util.RANDOM.nextInt(this.maxY);
      }

      // create a new polygon with the randomly generated points
      polygon = new Polygon(xpoints, ypoints, numPoints);

      // create a new color with randomly generated red, green, blue, and alpha
      color = new Color(Util.RANDOM.nextInt(MAX_VALUE), Util.RANDOM
          .nextInt(MAX_VALUE), Util.RANDOM.nextInt(MAX_VALUE), this.alphaValue);

      // add this (polygon, color) pair to the resulting individual
      result.gene().put(polygon, color);
    }

    return result;
  }

  /**
   * Set the fixed alpha value for all colors in created individuals.
   * 
   * @param newAlphaValue
   *          The fixed alpha value for all colors in created individuals.
   */
  public void setAlphaValue(final int newAlphaValue) {
    this.alphaValue = newAlphaValue;
  }

  /**
   * Set the number of initial polygons in a created individual.
   * 
   * @param newInitialPolygons
   *          The number of initial polygons in a created individual.
   */
  public void setInitialPolygons(final int newInitialPolygons) {
    this.initialPolygons = newInitialPolygons;
  }

  /**
   * Set the maximum number of points in a polygon.
   * 
   * @param newMaxPoints
   *          The maximum number of points in a polygon.
   */
  public void setMaxPoints(final int newMaxPoints) {
    this.maxPoints = newMaxPoints;
  }

  /**
   * Set the maximum x value of a polygon.
   * 
   * @param newMaxX
   *          The maximum x value of a polygon.
   */
  public void setMaxX(final int newMaxX) {
    this.maxX = newMaxX;
  }

  /**
   * Set the maximum y value of a polygon.
   * 
   * @param newMaxY
   *          The maximum y value of a polygon.
   */
  public void setMaxY(final int newMaxY) {
    this.maxY = newMaxY;
  }

  /**
   * Set the minimum number of points in a polygon.
   * 
   * @param newMinPoints
   *          The minimum number of points in a polygon.
   */
  public void setMinPoints(final int newMinPoints) {
    this.minPoints = newMinPoints;
  }

}
