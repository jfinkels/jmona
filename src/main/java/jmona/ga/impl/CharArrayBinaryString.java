/**
 * CharArrayBinaryString.java
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

import jmona.ga.BinaryString;
import jmona.impl.Util;

/**
 * A binary string with bits indexed from left to right, represented as an array
 * of {@code char}s.
 * 
 * The binary string is represented as a character array for convenience when
 * casting to integers where necessary. When casting from {@code char} to
 * {@code int}, the {@code char} value is zero-extended to fill the {@code int}.
 * For more information, see <a href=
 * "http://java.sun.com/docs/books/jls/third_edition/html/conversions.html#5.1.2"
 * >the conversions section of the Java Language Specification</a>.
 * 
 * @author jfinkels
 */
public class CharArrayBinaryString implements BinaryString {
  /** The block size for the underlying {@code char} array. */
  public static final int BLOCK_SIZE = Character.SIZE;

  /**
   * The format string for representing a single byte as a String with eight
   * characters.
   */
  public static final String CHAR_BINARY_FORMAT = "%" + Character.SIZE + "s";

  /**
   * Get the block which contains the bit at the specified index.
   * 
   * @param index
   *          The index of the bit whose containing block is to be determined.
   * @return The index of the block which contains the bit at the specified
   *         index.
   */
  private static int containingBlock(final int index) {
    return index / BLOCK_SIZE;
  }

  /**
   * Get the distance from the right of the specified block of the bit at the
   * specified index.
   * 
   * @param containingBlock
   *          The block containing this bit.
   * @param index
   *          The index of the bit whose distance from the right of the
   *          specified block will be determined.
   * @return The distance of the bit at the specified index from the right of
   *         the specified block.
   */
  private static int shiftWithinBlock(final int containingBlock, final int index) {
    return BLOCK_SIZE - 1 - (index - (containingBlock * BLOCK_SIZE));
  }

  /** The binary string which this class represents. */
  private final char[] bitstring;

  /** The number of bits in this binary string. */
  private final int length;

  /**
   * Instantiate this CharArrayBinaryString with the specified length (number of
   * bits), with all bits initially set to 0.
   * 
   * This is a convenience method for {@code
   * CharArrayBinaryString(initialLength, false)}.
   * 
   * @param initialLength
   *          The length of this binary string, in number of bits.
   */
  public CharArrayBinaryString(final int initialLength) {
    this(initialLength, false);
  }

  /**
   * Instantiate this CharArrayBinaryString with the specified length (number of
   * bits).
   * 
   * If the {@code random} parameter is {@code true}, then the binary string
   * will be filled randomly. Otherwise, it will be initially set to all 0s.
   * 
   * @param initialLength
   *          The length of this binary string, in number of bits.
   * @param fillWithRandomBits
   *          Whether to fill this binary string with random bits.
   */
  public CharArrayBinaryString(final int initialLength,
      final boolean fillWithRandomBits) {
    this.length = initialLength;
    this.bitstring = new char[(length / BLOCK_SIZE) + 1];

    if (fillWithRandomBits) {
      for (int i = 0; i < this.bitstring.length; ++i) {
        // TODO we are throwing away 3/4 of the randomness we produce here
        this.bitstring[i] = (char) Util.RANDOM.nextInt();
      }

    // get the last block in the array which contains bits for this bitstring
    int containingBlock = containingBlock(this.length - 1);
    
    // get the distance of the last bit from the right
    int shift = shiftWithinBlock(containingBlock, this.length - 1);
    
    this.bitstring[containingBlock] &= -1 << shift;  
    }
  }

  /**
   * Instantiate this CharArrayBinaryString with the specified length (number of
   * bits) and the specified initial byte array.
   * 
   * @param initialLength
   *          The length of this binary string, in number of bits.
   * @param initialBitstring
   *          The initial byte array containing the bits of this binary string.
   */
  protected CharArrayBinaryString(final int initialLength,
      final char[] initialBitstring) {
    this.length = initialLength;
    this.bitstring = initialBitstring.clone();
  }

  /**
   * Get the number of ones in this binary string.
   * 
   * @return The number of ones in this binary string.
   */
  @Override
  public int bitCount() {
    int sum = 0;

    // count the bits in each char
    for (final char aChar : this.bitstring) {
      sum += Integer.bitCount(aChar);
    }

    return sum;
  }

  /**
   * Check whether the specified index is valid with respect to the length of
   * this binary string.
   * 
   * @param index
   *          The index to check for validity.
   * @throws IllegalArgumentException
   *           If the specified index is greater than or equal to the length of
   *           this binary string.
   */
  private void checkIndex(final int index) {
    if (index >= this.length) {
      throw new IllegalArgumentException("Cannot get bit at index " + index
          + " because it is greater than or equal to the length, "
          + this.length + ".");
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  @Override
  public CharArrayBinaryString deepCopy() {
    return new CharArrayBinaryString(this.length, this.bitstring);
  }

  /**
   * {@inheritDoc}
   * 
   * @param index
   *          {@inheritDoc}
   * @throws IllegalArgumentException
   *           If the specified index is greater than or equal to the length of
   *           this binary string.
   */
  @Override
  public void flipBit(final int index) {
    this.checkIndex(index);

    final int containingBlock = containingBlock(index);
    final int shiftWithinBlock = shiftWithinBlock(containingBlock, index);

    this.bitstring[containingBlock] ^= (1 << shiftWithinBlock);
  }

  /**
   * {@inheritDoc}
   * 
   * @param index
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @throws IllegalArgumentException
   *           If the specified index is greater than or equal to the length of
   *           this binary string.
   */
  @Override
  public byte getBit(final int index) {
    this.checkIndex(index);

    final int containingBlock = containingBlock(index);
    final int shiftWithinBlock = shiftWithinBlock(containingBlock, index);

    final int bit = this.bitstring[containingBlock] & (1 << shiftWithinBlock);

    byte result = 0;
    if (bit != 0) {
      result = 1;
    }
    return result;
  }

  /**
   * Get an iterator over each bit in this binary string.
   * 
   * Each bit value is represented as a byte.
   * 
   * @return An iterator over each bit in this binary string.
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<Byte> iterator() {
    return new BinaryStringIterator(this);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   */
  public int length() {
    return this.length;
  }

  /**
   * Return the String representation of this binary string.
   * 
   * @return The String representation of this binary string.
   */
  public String toString() {
    final StringBuilder result = new StringBuilder();

    for (final char aChar : this.bitstring) {
      result.append(String.format(CHAR_BINARY_FORMAT, Integer
          .toBinaryString(aChar)));
    }

    return result.toString().substring(0, this.length).replace(' ', '0');
  }

}
