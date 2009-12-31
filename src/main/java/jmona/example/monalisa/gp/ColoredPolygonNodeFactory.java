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

import jmona.Factory;
import jmona.example.monalisa.ColoredPolygonFactory;

/**
 * Factory which generates a ColoredPolygonNode containing a random
 * ColoredPolygon.
 * 
 * @author Jeffrey Finkelstein
 */
public class ColoredPolygonNodeFactory implements Factory<ColoredPolygonNode> {

  /** A factory which creates ColoredPolygon objects. */
  private ColoredPolygonFactory coloredPolygonFactory = null;

  /**
   * Generate a ColoredPolygonNode containing a single randomly generated
   * ColoredPolygon object.
   * 
   * @return A ColoredPolygonNode containing a single randomly generated
   *         ColoredPolygon object.
   * @see jmona.Factory#createObject()
   */
  @Override
  public ColoredPolygonNode createObject() {
    final ColoredPolygonNode result = new ColoredPolygonNode();

    result.setColoredPolygon(this.coloredPolygonFactory.createObject());

    return result;
  }

  /**
   * Set the factory which creates ColoredPolygon objects.
   * 
   * @param newColoredPolygonFactory
   *          The factory which creates ColoredPolygon objects.
   */
  public void setColoredPolygonFactory(
      final ColoredPolygonFactory newColoredPolygonFactory) {
    this.coloredPolygonFactory = newColoredPolygonFactory;
  }
}
