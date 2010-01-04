/**
 * Ant.java
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
 * An Ant which can move forward, turn left and right, and eats food.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public interface Ant {

  /**
   * Move this Ant forward in the direction of its orientation, consume food if
   * it exists at that location, and increment the total number of moves made.
   */
  void advance();

  /**
   * Gets the amount of food eaten by this Ant.
   * 
   * @return The amount of food eaten by this Ant.
   */
  int foodEaten();

  /**
   * Whether there is food at the location directly ahead of the Ant's current
   * location with respect to its current orientation.
   * 
   * @return Whether there is food at the location directly ahead of the Ant's
   *         current location with respect to its current orientation.
   */
  boolean isFoodAhead();

  /**
   * Gets the location of this Ant on the Trail.
   * 
   * @return The location of this Ant on the Trail.
   */
  CoordinatePair location();

  /**
   * Gets the maximum number of steps allowed for this Ant.
   * 
   * @return The maximum number of steps allowed for this Ant.
   */
  int maxSteps();

  /**
   * Gets the total number of moves made by this Ant (including moves forward
   * and turns left and right).
   * 
   * @return The total number of moves made by this Ant.
   */
  int movesMade();

  /**
   * Gets the orientation of this Ant as a vector in the Cartesian plane.
   * 
   * @return The orientation of this Ant as a vector in the Cartesian plane.
   */
  Orientation orientation();

  /**
   * Resets the state of the Ant to its initial state before making any moves or
   * consuming any food, and returns it to its initial location and orientation.
   */
  void reset();

  /**
   * Sets the maximum number of steps allowed for this Ant.
   * 
   * @param newMaxSteps
   *          The maximum number of steps allowed for this Ant.
   */
  void setMaxSteps(final int newMaxSteps);

  /**
   * Rotate the Ant counterclockwise 90&deg; (changing its orientation but
   * maintaining its location).
   */
  void turnLeft();

  /**
   * Rotate the Ant clockwise 90&deg; (changing its orientation but maintaining
   * its location).
   */
  void turnRight();
}
