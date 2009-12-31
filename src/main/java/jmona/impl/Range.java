/**
 * Range.java
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
package jmona.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A range over integers, similar in spirit to the Python <a
 * href="http://docs.python.org/library/functions.html#range">{@code range()}
 * </a> function.
 * 
 * For large numbers, this class uses less memory than a Vector containing each
 * integer in the interval.
 * 
 * @author Jeffrey Finkelstein
 */
// TODO negative increments
public class Range implements Iterable<Integer>, Iterator<Integer> {

  /** The default increment for this Iterator. */
  public static final int DEFAULT_INCREMENT = 1;

  /**
   * Get a new iterable Range object over the interval from 0 to {@code
   * terminalInteger-1}, inclusive.
   * 
   * @param terminalInteger
   *          The maximum + 1 of the range to get.
   * @return An iterable Range object over the interval from 0 to {@code
   *         terminalInteger-1}, inclusive.
   */
  public static Range range(final int terminalInteger) {
    return range(0, terminalInteger);
  }

  /**
   * Get a new iterable Range object over the interval from {@code
   * initialInteger} to {@code terminalInteger-1}, inclusive.
   * 
   * @param initialInteger
   *          The minimum of the range to get.
   * @param terminalInteger
   *          The maximum + 1 of the range to get.
   * @return An iterable Range object over the interval from 0 to {@code
   *         terminalInteger-1}, inclusive.
   */
  public static Range range(final int initialInteger, final int terminalInteger) {
    return range(initialInteger, terminalInteger, 1);
  }

  /**
   * Get a new iterable Range object over the interval from {@code
   * initialInteger} to {@code terminalInteger-1}, inclusive, with incrementing
   * steps of the specified value.
   * 
   * @param initialInteger
   *          The minimum of the range to get.
   * @param terminalInteger
   *          The maximum + 1 of the range to get.
   * @param incrementInteger
   *          The value by which to increment over the Range.
   * @return An iterable Range object over the interval from 0 to {@code
   *         terminalInteger-1}, inclusive.
   */
  public static Range range(final int initialInteger,
      final int terminalInteger, final int incrementInteger) {
    return new Range(initialInteger, terminalInteger, incrementInteger);
  }

  /** The current pointer. */
  private int current;
  /** The maximum + 1 integer of this range. */
  private final int end;
  /** The increment for this iterator. */
  private final int increment;
  /** The lower integer of this range. */
  private final int start;

  /**
   * Instantiate this range of integers from {@code 0} to {@code
   * terminalInteger-1} inclusive.
   * 
   * @param terminalInteger
   *          The maximum + 1 integer of this range.
   */
  public Range(final int terminalInteger) {
    this(0, terminalInteger);
  }

  /**
   * Instantiate this range of integers from {@code initialInteger} to {@code
   * terminalInteger-1} inclusive.
   * 
   * @param initialInteger
   *          The lower integer of this range.
   * @param terminalInteger
   *          The maximum + 1 integer of this range.
   */
  public Range(final int initialInteger, final int terminalInteger) {
    this(initialInteger, terminalInteger, DEFAULT_INCREMENT);
  }

  /**
   * Instantiate this range of integers from {@code initialInteger} to {@code
   * terminalInteger-1}, inclusive, with the specified increment.
   * 
   * @param initialInteger
   *          The lower integer of this range.
   * @param terminalInteger
   *          The maximum + 1 integer of this range.
   * @param incrementInteger
   *          The value by which to increment the current integer when iterating
   *          over this range.
   */
  public Range(final int initialInteger, final int terminalInteger,
      final int incrementInteger) {
    this.start = initialInteger;
    this.end = terminalInteger;
    this.increment = incrementInteger;

    this.current = this.start - this.increment;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return this.current + this.increment < this.end;
  }

  /**
   * Get an Iterator over this range of integers.
   * 
   * @return An Iterator over this range of integers.
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<Integer> iterator() {
    return this;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @throws NoSuchElementException
   *           {@inheritDoc}
   * @see java.util.Iterator#next()
   */
  @Override
  public Integer next() {
    if (this.current + this.increment >= this.end) {
      throw new NoSuchElementException("No more elements in this range.");
    }

    // increment the current integer
    this.current += this.increment;

    return this.current;
  }

  /**
   * This operation is unsupported.
   * 
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.Iterator#remove()
   */
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
