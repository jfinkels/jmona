/**
 * Range.java
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

/**
 * A range over integers, similar in spirit to the Python <a
 * href="http://docs.python.org/library/functions.html#range">{@code range()}
 * </a> function.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class Range extends AbstractRange<Integer> {

  /**
   * Instantiates this class by calling the corresponding constructor of the
   * superclass.
   * 
   * @param terminalInteger
   *          The (exclusive) upper bound on this range of integers.
   */
  public Range(final int terminalInteger) {
    super(terminalInteger);
  }

  /**
   * Instantiates this class by calling the corresponding constructor of the
   * superclass.
   * 
   * @param initialInteger
   *          The (inclusive) lower bound on this range of integers.
   * @param terminalInteger
   *          The (exclusive) upper bound on this range of integers.
   */
  public Range(final int initialInteger, final int terminalInteger) {
    super(initialInteger, terminalInteger);
  }

  /**
   * Instantiates this class by calling the corresponding constructor of the
   * superclass.
   * 
   * @param initialInteger
   *          The (inclusive) lower bound on this range of integers.
   * @param terminalInteger
   *          The (exclusive) upper bound on this range of integers.
   * @param incrementInteger
   *          The number by which to increment integers in this range.
   */
  public Range(final int initialInteger, final int terminalInteger,
      final int incrementInteger) {
    super(initialInteger, terminalInteger, incrementInteger);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.functional.AbstractRange#getValue()
   */
  @Override
  protected Integer getValue() {
    return this.current();
  }

}
