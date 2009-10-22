/**
 * Pair.java
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
 * A pair of elements.
 * 
 * @param <S>
 *          The type of the left element.
 * @param <T>
 *          The type of the right element.
 * @author jfinke
 */
public class Pair<S, T> {
  /** The left element. */
  private final S left;
  /** The right element. */
  private final T right;

  /**
   * Instantiate this pair with the specified left and right elements.
   * 
   * @param initialLeft
   *          The left element.
   * @param initialRight
   *          The right element.
   */
  public Pair(final S initialLeft, final T initialRight) {
    this.left = initialLeft;
    this.right = initialRight;
  }

  /**
   * Get the left element.
   * 
   * @return The left element.
   */
  public S left() {
    return this.left;
  }

  /**
   * Get the right element.
   * 
   * @return The right element.
   */
  public T right() {
    return this.right;
  }
}
