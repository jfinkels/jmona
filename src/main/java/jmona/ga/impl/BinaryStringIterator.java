/**
 * BinaryStringIterator.java
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
package jmona.ga.impl;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import jmona.ga.BinaryString;

/**
 * An iterator over bits in a binary string.
 * 
 * @author jfinkels
 */
class BinaryStringIterator implements ListIterator<Byte> {

  /** The binary string over which this object iterates. */
  private final BinaryString binaryString;
  /** The pointer to the current bit in the binary string. */
  private int pointer = -1;

  /**
   * Instantiate this Iterator with access to the specified binary string.
   * 
   * @param initialBinaryString
   *          The binary string over which to iterate.
   */
  public BinaryStringIterator(final BinaryString initialBinaryString) {
    this.binaryString = initialBinaryString;
  }

  /**
   * Instantiate this Iterator with access to the specified binary string, and
   * with the specified starting index into the binary string.
   * 
   * @param initialBinaryString
   *          The binary string over which to iterate.
   * @param initialIndex
   *          The starting index of this iterator in the specified binary
   *          string.
   */
  public BinaryStringIterator(final BinaryString initialBinaryString,
      final int initialIndex) {
    this(initialBinaryString);
    this.pointer = initialIndex;
  }

  /**
   * This operation is unsupported.
   * 
   * @param bit
   *          This parameter is ignored.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.ListIterator#add(java.lang.Object)
   */
  @Override
  public void add(final Byte bit) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");

  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return this.pointer < (this.binaryString.size() - 1);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.ListIterator#hasPrevious()
   */
  @Override
  public boolean hasPrevious() {
    return (this.pointer > 0 && this.pointer <= this.binaryString.size());
  }

  /**
   * Get the value of the next bit in the binary string.
   * 
   * @return The value of the next bit in the binary string.
   * @throws NoSuchElementException
   *           If the binary string has no more bits over which to iterate.
   * @see java.util.Iterator#next()
   */
  @Override
  public Byte next() {
    this.pointer += 1;

    if (this.pointer >= this.binaryString.size()) {
      this.pointer -= 1;
      throw new NoSuchElementException(
          "This binary string contains no more elements.");
    }

    final Byte result = this.binaryString.get(this.pointer);
    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.ListIterator#nextIndex()
   */
  @Override
  public int nextIndex() {
    return Math.min(this.pointer + 1, this.binaryString.size());
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.ListIterator#previous()
   */
  @Override
  public Byte previous() {
    if (this.pointer == 0) {
      throw new NoSuchElementException("There is no previous element.");
    }

    this.pointer -= 1;
    return this.binaryString.get(this.pointer);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.ListIterator#previousIndex()
   */
  @Override
  public int previousIndex() {
    return this.pointer - 1;
  }

  /**
   * This operation is not supported and always throws an {@code
   * UnsupportedOperationException}.
   * 
   * @throws UnsupportedOperationException
   *           Always throws this exception.
   * @see java.util.Iterator#remove()
   */
  @Override
  public void remove() {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
  }

  /**
   * {@inheritDoc}
   * 
   * @param value
   *          {@inheritDoc}
   * @see java.util.ListIterator#set(java.lang.Object)
   */
  @Override
  public void set(final Byte value) {
    this.binaryString.set(this.pointer, value);
  }

}
