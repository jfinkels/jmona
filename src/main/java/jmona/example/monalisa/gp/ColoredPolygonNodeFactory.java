/**
 * ColoredPolygonNodeFactory.java
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

import java.util.List;

import jmona.example.monalisa.ColoredPolygon;
import jmona.example.monalisa.ColoredPolygonFactory;
import jmona.gp.FunctionNodeFactory;
import jmona.gp.TerminalNodeFactory;

/**
 * Factory which generates a ColoredPolygonNode containing a random
 * ColoredPolygon.
 * 
 * @author jfinkels
 */
public class ColoredPolygonNodeFactory implements
    TerminalNodeFactory<List<ColoredPolygon>>,
    FunctionNodeFactory<List<ColoredPolygon>> {

  /** A factory which creates ColoredPolygon objects. */
  private final ColoredPolygonFactory coloredPolygonFactory = new ColoredPolygonFactory();

  /**
   * Generate a ColoredPolygonNode containing a single randomly generated
   * ColoredPolygon object.
   * 
   * @return A ColoredPolygonNode containing a single randomly generated
   *         ColoredPolygon object.
   * @see jmona.gp.NodeFactory#createNode()
   */
  @Override
  public ColoredPolygonNode createNode() {
    final ColoredPolygonNode result = new ColoredPolygonNode();

    result.setColoredPolygon(coloredPolygonFactory.createIndividual());

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
}
