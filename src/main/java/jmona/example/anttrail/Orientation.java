/**
 * Orientation.java
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

import jmona.DeepCopyable;
import jmona.impl.Pair;

/**
 * An immutable two-tuple of integers (a vector in the Cartesian plane).
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class Orientation extends Pair<Integer, Integer> implements
    DeepCopyable<Orientation> {

  /**
   * Gets the eastward unit vector, that is (1, 0).
   * 
   * @return The eastward unit vector, that is (1, 0).
   */
  public static Orientation east() {
    return new Orientation(1, 0);
  }

  /**
   * Gets the northward unit vector, that is (0, -1).
   * 
   * @return The northward unit vector, that is (0, -1).
   */
  public static Orientation north() {
    return new Orientation(0, -1);
  }

  /**
   * Gets the southward unit vector, that is (0, 1).
   * 
   * @return The southward unit vector, that is (0, 1).
   */
  public static Orientation south() {
    return new Orientation(0, 1);
  }

  /**
   * Gets the westward unit vector, that is (-1, 0).
   * 
   * @return The westward unit vector, that is (-1, 0).
   */
  public static Orientation west() {
    return new Orientation(-1, 0);
  }

  /**
   * Instantiates this Orientation with the specified x and y components.
   * 
   * @param initialX
   *          The x component of this pair.
   * @param initialY
   *          The y component of this pair.
   */
  public Orientation(final Integer initialX, final Integer initialY) {
    super(initialX, initialY);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public Orientation deepCopy() {
    return new Orientation(this.left(), this.right());
  }

  /**
   * Returns true if and only if the specified object is an Orientation object
   * with the same x and y components.
   * 
   * @param object
   *          The object to test for equality.
   * @return True if and only if the specified object is an Orientation object
   *         with the same x and y components.
   */
  @Override
  public boolean equals(final Object object) {

    // if the other object is not an orientation, they are not equal
    if (!(object instanceof Orientation)) {
      return false;
    }

    // cast the object to a Orientation
    final Orientation otherPair = (Orientation) object;

    // return true if and only if both the x and y values are equal
    return otherPair.left() == this.left() && otherPair.right() == this.right();
  }

  /**
   * Gets the integer represented by a binary string comprised of the
   * concatenation of the lower order half of the bitstring representing the x
   * component of this coordinate pair and the lower order half of the bitstring
   * representing the y component of this coordinate pair.
   * 
   * We hope that users of this class won't need a coordinate pair with
   * component values greater than or equal to |2<sup>
   * <code>(Integer.SIZE / 2)</code></sup>|.
   * 
   * @return The concatenation of the lower order half of the binary string of
   *         the x component with the lower order half of the binary string of
   *         the y component.
   */
  // TODO many orientations are in the same eq. class under the hashcode
  // TODO extends Pair<Short, Short>?
  @Override
  public int hashCode() {
    return ((this.left() & CoordinatePair.HALF_MASK) << (Integer.SIZE / 2))
        | (this.right() & CoordinatePair.HALF_MASK);
  }

  /**
   * Gets a copy of this vector having been rotated left (counterclockwise)
   * 90&deg;.
   * 
   * @return A copy of this vector having been rotated left (counterclockwise)
   *         90&deg;.
   */
  public Orientation rotatedLeft() {
    final CoordinatePair pair = this.toCoordinatePair();
    pair.rotateLeft();

    return pair.toOrientation();
  }

  /**
   * Gets a copy of this vector having been rotated right (clockwise) 90&deg;.
   * 
   * @return A copy of this vector having been rotated right (clockwise)
   *         90&deg;.
   */
  public Orientation rotatedRight() {
    final CoordinatePair pair = this.toCoordinatePair();
    pair.rotateRight();

    return pair.toOrientation();
  }

  /**
   * This method always throws an UnsupportedOperationException.
   * 
   * @param newLeft
   *          This parameter is ignored.
   * @throws UnsupportedOperationException
   *           This Exception is always thrown.
   */
  @Override
  public void setLeft(final Integer newLeft) {
    throw new UnsupportedOperationException();
  }

  /**
   * This method always throws an UnsupportedOperationException.
   * 
   * @param newRight
   *          This parameter is ignored.
   * @throws UnsupportedOperationException
   *           This Exception is always thrown.
   */
  @Override
  public void setRight(final Integer newRight) {
    throw new UnsupportedOperationException();
  }

  /**
   * Gets a CoordinatePair with the same x and y components as this object.
   * 
   * @return A CoordinatePair with the same x and y components as this object.
   */
  public CoordinatePair toCoordinatePair() {
    return new CoordinatePair(this.left(), this.right());
  }
}
