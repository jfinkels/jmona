/**
 * ColoredPolygon.java
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
package jmona.example.monalisa;

import java.awt.Color;
import java.awt.Polygon;

import jmona.DeepCopyable;
import jmona.impl.Range;

/**
 * A Polygon with a Color.
 * 
 * @author jfinkels
 */
public class ColoredPolygon extends Polygon implements
    DeepCopyable<ColoredPolygon> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 5912239970326362556L;

  /** The Color of this Polygon. */
  private Color color = null;

  /**
   * Get the Color of this Polygon.
   * 
   * @return The Color of this Polygon.
   */
  public Color color() {
    return this.color;
  }

  /**
   * Set the Color of this Polygon.
   * 
   * @param newColor
   *          The Color of this Polygon.
   */
  public void setColor(final Color newColor) {
    this.color = newColor;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.Individual#deepCopy()
   */
  @Override
  public ColoredPolygon deepCopy() {
    final ColoredPolygon clone = new ColoredPolygon();

    clone.xpoints = this.xpoints.clone();
    clone.ypoints = this.ypoints.clone();
    clone.npoints = this.npoints;

    final Color clonedColor = new Color(this.color.getRed(), this.color
        .getGreen(), this.color.getGreen(), this.color.getAlpha());
    clone.setColor(clonedColor);

    return clone;
  }

  /**
   * Get the String representation of this ColoredPolygon.
   * 
   * @return The String representation of this ColoredPolygon.
   */
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder();
    result.append("ColoredPolygon[");
    result.append(this.color);
    result.append("; ");
    for (final int i : new Range(this.npoints)) {
      result.append("(");
      result.append(this.xpoints[i]);
      result.append(",");
      result.append(this.ypoints[i]);
      result.append("),");
    }
    result.append("]");

    return result.toString();
  }
}
