/**
 * Trail.java
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
package jmona.example.anttrail;

/**
 * A bounded grid on a Cartesian plane containing food at some coordinates.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class Trail {
  /** The bounds of this Trail on the Cartesian plane. */
  private final CoordinatePair bounds;
  /** The locations on the Trail where there is food. */
  private final boolean[][] foodLocations;

  /**
   * Instantiate this Trail with the specified locations containing food.
   * 
   * @param initialFoodLocations
   *          Locations on the grid where there is food.
   */
  public Trail(final boolean[][] initialFoodLocations) {

    this.foodLocations = initialFoodLocations.clone();

    // get the width of the foodLocations 2d array
    final int height = this.foodLocations.length;
    final int width = this.foodLocations[0].length;

    // assume this trail is a square when determining its bounds
    this.bounds = new CoordinatePair(width, height);
  }

  /**
   * Gets the bounds of this Trail on the Cartesian plane.
   * 
   * This CoordinatePair must be non-negative.
   * 
   * @return The bounds of this Trail on the Cartesian plane.
   */
  public CoordinatePair bounds() {
    return this.bounds;
  }

  /**
   * Whether there is food at the specified location on the Trail.
   * 
   * @param coordinates
   *          The coordinates to check for food.
   * @return Whether there is food at the specified location on the Trail.
   */
  public boolean foodAt(final CoordinatePair coordinates) {
    return this.foodLocations[coordinates.y()][coordinates.x()];
  }

}
