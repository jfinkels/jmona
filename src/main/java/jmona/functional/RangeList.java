/**
 * RangeList.java
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
package jmona.functional;

import java.util.ArrayList;

/**
 * An ArrayList which can be initialized with a range of integers.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class RangeList extends ArrayList<Integer> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -6564604936956238872L;

  /**
   * Instantiates this RangeList by adding each integer between 0 (inclusive)
   * and {@code terminalInteger} (exclusive).
   * 
   * @param terminalInteger
   *          The (exclusive) upper bound on integers to add to this list.
   */
  public RangeList(final int terminalInteger) {
    this(0, terminalInteger);
  }

  /**
   * Instantiates this RangeList by adding each integer between
   * {@code initialInteger} (inclusive) and {@code terminalInteger} (exclusive).
   * 
   * @param initialInteger
   *          The (inclusive) lower bound on integers to add to this list.
   * @param terminalInteger
   *          The (exclusive) upper bound on integers to add to this list.
   */
  public RangeList(final int initialInteger, final int terminalInteger) {
    this(initialInteger, terminalInteger, 1);
  }

  /**
   * Instantiates this RangeList by adding each integer between
   * {@code initialInteger} (inclusive) and {@code terminalInteger} (exclusive),
   * incremented by {@code step} between each integer.
   * 
   * @param initialInteger
   *          The (inclusive) lower bound on integers to add to this list.
   * @param terminalInteger
   *          The (exclusive) upper bound on integers to add to this list.
   * @param step
   *          The increment between each integer initially added to this list.
   */
  public RangeList(final int initialInteger, final int terminalInteger,
      final int step) {
    for (final int i : new Range(initialInteger, terminalInteger, step)) {
      this.add(i);
    }
  }

}
