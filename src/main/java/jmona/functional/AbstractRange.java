/**
 * Range.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A range over numbers, similar in spirit to the Python <a
 * href="http://docs.python.org/library/functions.html#range">{@code range()}
 * </a> function.
 * 
 * For large ranges, this class uses less memory than a Vector containing each
 * integer in the interval.
 * 
 * @param <N>
 *          The type of number which over which this range iterates.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
// TODO negative increments
public abstract class AbstractRange<N extends Number> implements Iterator<N>,
    Iterable<N> {

  /** The default increment for this Iterator. */
  public static final int DEFAULT_INCREMENT = 1;

  /** The current pointer. */
  private int current;
  /** The maximum + 1 integer of this range. */
  private final int end;
  /** The increment for this iterator. */
  private final int increment;
  /** The lower integer of this range. */
  private final int start;

  /**
   * Instantiate this range of integers from {@code 0} to
   * {@code terminalInteger-1} inclusive.
   * 
   * @param terminalInteger
   *          The maximum + 1 integer of this range.
   */
  public AbstractRange(final int terminalInteger) {
    this(0, terminalInteger);
  }

  /**
   * Instantiate this range of integers from {@code initialInteger} to
   * {@code terminalInteger-1} inclusive.
   * 
   * @param initialInteger
   *          The lower integer of this range.
   * @param terminalInteger
   *          The maximum + 1 integer of this range.
   */
  public AbstractRange(final int initialInteger, final int terminalInteger) {
    this(initialInteger, terminalInteger, DEFAULT_INCREMENT);
  }

  /**
   * Instantiate this range of integers from {@code initialInteger} to
   * {@code terminalInteger-1}, inclusive, with the specified increment.
   * 
   * @param initialInteger
   *          The lower integer of this range.
   * @param terminalInteger
   *          The maximum + 1 integer of this range.
   * @param incrementInteger
   *          The value by which to increment the current integer when iterating
   *          over this range.
   */
  public AbstractRange(final int initialInteger, final int terminalInteger,
      final int incrementInteger) {
    this.start = initialInteger;
    this.end = terminalInteger;
    this.increment = incrementInteger;

    this.current = this.start - this.increment;
  }

  /**
   * Gets the current value of this iterator.
   * 
   * @return The current value of this iterator.
   */
  protected int current() {
    return this.current;
  }

  /**
   * Gets the terminal number in this range.
   * 
   * @return The terminal number in this range.
   */
  protected int end() {
    return this.end;
  }

  /**
   * Gets the current value of this range.
   * 
   * @return The current value of this range.
   */
  protected abstract N getValue();

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
   * Gets the increment between numbers in this range.
   * 
   * @return The increment between numbers in this range.
   */
  protected int increment() {
    return this.increment;
  }

  /**
   * Get an Iterator over this range of integers.
   * 
   * @return An Iterator over this range of integers.
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<N> iterator() {
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
  public N next() {
    if (this.current + this.increment >= this.end) {
      throw new NoSuchElementException("No more elements in this range.");
    }

    // increment the current integer
    this.current += this.increment;

    return this.getValue();
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

  /**
   * Sets the current value of this iterator.
   * 
   * @param newCurrent
   *          The current value of this iterator.
   */
  protected void setCurrent(final int newCurrent) {
    this.current = newCurrent;
  }

  /**
   * Gets the initial number in this range.
   * 
   * @return The initial number in this range.
   */
  protected int start() {
    return this.start;
  }
}
