/**
 * ImmutablePair.java
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
package jmona;

/**
 * An immutable 2-tuple of objects.
 * 
 * @param <S>
 *          The type of the left object.
 * @param <T>
 *          The type of the right object.
 * @author jfinkels
 */
public class ImmutablePair<S, T> {
  /** The left object. */
  private final S left;
  /** The right object. */
  private final T right;

  /**
   * Instantiate a 2-tuple with the specified initial objects.
   * 
   * @param initialLeft
   *          The initial left object.
   * @param initialRight
   *          The initial right object.
   */
  public ImmutablePair(final S initialLeft, final T initialRight) {
    this.left = initialLeft;
    this.right = initialRight;
  }

  /**
   * Get the left object.
   * 
   * @return The left object.
   */
  public S left() {
    return this.left;
  }

  /**
   * Get the right object.
   * 
   * @return The right object.
   */
  public T right() {
    return this.right;
  }
}
