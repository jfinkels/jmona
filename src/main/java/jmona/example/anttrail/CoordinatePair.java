/**
 * CoordinatePair.java
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

import org.apache.log4j.Logger;

/**
 * A coordinate pair on the Cartesian plane in which the upper left corner is
 * the origin (the point (0,0)), so positive values for the y component
 * represent points lower on the grid.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class CoordinatePair extends Pair<Integer, Integer> implements
    DeepCopyable<CoordinatePair> {

  /** A bitmask consisting of all ones on the right half of the bitstring. */
  public static final int HALF_MASK = -1 >>> (Integer.SIZE / 2);

  private static final transient Logger LOG = Logger
      .getLogger(CoordinatePair.class);

  /**
   * @return
   */
  public static CoordinatePair origin() {
    return new CoordinatePair(0, 0);
  }

  /**
   * Instantiate this class with the specified initial x and y components of
   * this coordinate pair.
   * 
   * @param initialX
   *          The initial x component of this coordinate pair.
   * @param initialY
   *          The initial y component of this coordinate pair.
   */
  public CoordinatePair(final Integer initialX, final Integer initialY) {
    super(initialX, initialY);
  }

  /**
   * Add the specified coordinate pair to this coordinate pair (setting this
   * coordinate pair to the value of the sum).
   * 
   * @param otherPair
   *          A coordinate pair to add to this one.
   */
  public void add(final Pair<Integer, Integer> otherPair) {
    this.setX(this.x() + otherPair.left());
    this.setY(this.y() + otherPair.right());
  }

  /**
   * Add the specified coordinate pair to this coordinate pair (setting this
   * coordinate pair to the value of the sum), and wrap around if the sum
   * exceeds the specified bounds.
   * 
   * The idea is that the bounds specify the number of units which divide a
   * toroidal grid, so when expressed as two-dimensional Cartesian plane,
   * movement off the left of the grid wraps around to the right, and vice
   * versa, and movement off the top of the grid wraps around to the bottom, and
   * vice versa.
   * 
   * For example, if the sum would yield an x component value of {@code
   * bounds.x()}, this method assigns 0 to the x component of this coordinate
   * pair. If the sum would yield a y component value of -1, this method assigns
   * {@code this.bounds.y()-1} to the y component of this coordinate pair.
   * 
   * @param otherPair
   *          A coordinate pair to add to this one.
   * @param bounds
   *          The bounds of the toroidal grid when expressed as a Cartesian
   *          plane.
   */
  public void add(final Pair<Integer, Integer> otherPair,
      final CoordinatePair bounds) {

    this.setX((this.x() + otherPair.left()) % bounds.x());
    this.setY((this.y() + otherPair.right()) % bounds.y());

    // check for negatives; the Java % operator keeps the sign of the dividend
    if (this.x() < 0) {
      this.setX(bounds.x() + this.x());
    }

    if (this.y() < 0) {
      this.setY(bounds.y() + this.y());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public CoordinatePair deepCopy() {
    return new CoordinatePair(this.x(), this.y());
  }

  /**
   * Returns true if and only if the specified object is a CoordinatePair with
   * the same x and y components.
   * 
   * @param object
   *          The object to test for equality.
   * @return True if and only if the specified object is a CoordinatePair with
   *         the same x and y components.
   */
  @Override
  public boolean equals(final Object object) {

    // if the other object is not a coordinate pair, they are not equal
    if (!(object instanceof CoordinatePair)) {
      return false;
    }

    // cast the object to a CoordinatePair
    final CoordinatePair otherPair = (CoordinatePair) object;

    // return true if and only if both the x and y values are equal
    return otherPair.x() == this.x() && otherPair.y() == this.y();
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
  // TODO many coordinate pairs are in the same eq. class under the hashcode
  // TODO extends Pair<Short, Short>?
  @Override
  public int hashCode() {
    return ((this.x() & HALF_MASK) << (Integer.SIZE / 2))
        | (this.y() & HALF_MASK);
  }

  /** Rotates this vector by 90&deg; (&Pi;/2 radians). */
  public void rotateLeft() {
    this.set(this.y(), -1 * this.x());
  }

  /** Rotates this vector by -90&deg; (-&Pi;/2 radians). */
  public void rotateRight() {
    this.set(-1 * this.y(), this.x());
  }

  /**
   * Set both the x and y components of this coordinate pair.
   * 
   * @param newX
   *          The x component of this coordinate pair.
   * @param newY
   *          The y component of this coordinate pair.
   */
  public void set(final int newX, final int newY) {
    this.setX(newX);
    this.setY(newY);
  }

  /**
   * Convenience method for {@link #setLeft(Integer)}.
   * 
   * @param newX
   *          The x component of this coordinate pair.
   */
  public void setX(final int newX) {
    this.setLeft(newX);
  }

  /**
   * Convenience method for {@link #setRight(Integer)}.
   * 
   * @param newY
   *          The y component of this coordinate pair.
   */
  public void setY(final int newY) {
    this.setRight(newY);
  }

  /**
   * Gets a new CoordinatePair representing the sum of this coordinate pair with
   * the specified other pair.
   * 
   * @param otherPair
   *          The pair to sum with this one.
   * @return A new CoordinatePair representing the sum of this coordinate pair
   *         with the specified other pair.
   */
  public CoordinatePair sumWith(final Pair<Integer, Integer> otherPair) {
    final CoordinatePair result = new CoordinatePair(this.x(), this.y());

    result.add(otherPair);

    return result;
  }

  public CoordinatePair sumWith(final Pair<Integer, Integer> otherPair,
      final CoordinatePair bounds) {
    final CoordinatePair result = new CoordinatePair(this.x(), this.y());

    result.add(otherPair, bounds);

    return result;
  }

  public Orientation toOrientation() {
    return new Orientation(this.x(), this.y());
  }

  /**
   * Convenience method for {@link #left()}.
   * 
   * @return The x component of this coordinate pair.
   */
  public int x() {
    return this.left();
  }

  /**
   * Convenience method for {@link #right()}.
   * 
   * @return The y component of this coordinate pair.
   */
  public int y() {
    return this.right();
  }
}
