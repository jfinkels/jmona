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
package jmona.example.monalisa.ga;

import jmona.IndividualFactory;
import jmona.example.monalisa.ColoredPolygonFactory;

/**
 * @author jfinkels
 */
public class MonaIndividualFactory implements IndividualFactory<MonaIndividual> {

  public static final int DEFAULT_NUMBER_OF_POLYGONS = 10;

  private final ColoredPolygonFactory coloredPolygonFactory = new ColoredPolygonFactory();

  private int numberOfPolygons = DEFAULT_NUMBER_OF_POLYGONS;

  /*
   * (non-Javadoc)
   * 
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public MonaIndividual createIndividual() {
    final MonaIndividual result = new MonaIndividual();

    for (int i = 0; i < this.numberOfPolygons; ++i) {
      result.add(this.coloredPolygonFactory.createIndividual());
    }

    return result;
  }
  /**
   * Set the maximum number of points in a polygon.
   * 
   * @param newMaxPoints
   *          The maximum number of points in a polygon.
   */
  public void setMaxPoints(final int newMaxPoints) {
    this.coloredPolygonFactory.setMaxPoints(newMaxPoints);
  }

  /**
   * Set the maximum x value of a polygon.
   * 
   * @param newMaxX
   *          The maximum x value of a polygon.
   */
  public void setMaxX(final int newMaxX) {
    this.coloredPolygonFactory.setMaxX(newMaxX);
  }

  /**
   * Set the maximum y value of a polygon.
   * 
   * @param newMaxY
   *          The maximum y value of a polygon.
   */
  public void setMaxY(final int newMaxY) {
    this.coloredPolygonFactory.setMaxY(newMaxY);
  }

  /**
   * Set the minimum number of points in a polygon.
   * 
   * @param newMinPoints
   *          The minimum number of points in a polygon.
   */
  public void setMinPoints(final int newMinPoints) {
    this.coloredPolygonFactory.setMinPoints(newMinPoints);
  }

  public void setNumberOfPolygons(final int newNumberOfPolygons) {
    this.numberOfPolygons = newNumberOfPolygons;
  }

}
