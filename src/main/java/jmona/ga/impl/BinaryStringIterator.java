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

import java.util.Iterator;
import java.util.NoSuchElementException;

import jmona.ga.BinaryString;

/**
 * An iterator over bits in a binary string.
 * 
 * @author jfinkels
 */
class BinaryStringIterator implements Iterator<Byte> {

  /** The binary string over which this object iterates. */
  private final BinaryString charArrayBinaryString;
  /** The pointer to the current bit in the binary string. */
  private int pointer = -1;

  /**
   * Instantiate this Iterator with access to the specified binary string.
   * 
   * @param initialBinaryString
   *          The binary string over which to iterate.
   */
  public BinaryStringIterator(final BinaryString initialBinaryString) {
    this.charArrayBinaryString = initialBinaryString;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return this.pointer < (this.charArrayBinaryString.length() - 1);
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

    if (this.pointer >= this.charArrayBinaryString.length()) {
      throw new NoSuchElementException(
          "This binary string contains no more elements.");
    }

    final Byte result = this.charArrayBinaryString.getBit(this.pointer);
    return result;
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
        "This operation is not supported on binary strings.");
  }

}
