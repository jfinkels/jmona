/**
 * CharArrayBinaryString.java
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
package jmona.ga.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import jmona.ga.BinaryString;
import jmona.random.RandomUtils;

/**
 * A binary string with bits indexed from left to right, represented as an array
 * of {@code char}s.
 * 
 * The binary string is represented as an array of {@code Character}s instead of
 * an array of {@code Byte}s for convenience when casting to integers where
 * necessary. When casting from {@code char} to {@code int}, the {@code char}
 * value is zero-extended to fill the {@code int}. For more information, see <a
 * href=
 * "http://java.sun.com/docs/books/jls/third_edition/html/conversions.html#5.1.2"
 * >the conversions section of the Java Language Specification</a>.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
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
   * Check whether the specified value is 0 or 1.
   * 
   * @param value
   *          The value to check.
   * @throws IllegalArgumentException
   *           If the specified value is not 0 or 1.
   */
  private static void checkValue(final byte value) {
    if (value != 0 && value != 1) {
      throw new IllegalArgumentException("Value must be 0 or 1, not " + value);
    }
  }

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
  CharArrayBinaryString(final int initialLength,
      final boolean fillWithRandomBits) {
    this.length = initialLength;
    this.bitstring = new char[(this.length / BLOCK_SIZE) + 1];

    if (fillWithRandomBits) {
      for (int i = 0; i < this.bitstring.length; ++i) {
        // TODO we are throwing away 3/4 of the randomness we produce here
        this.bitstring[i] = (char) RandomUtils.randomData().nextInt(
            Integer.MIN_VALUE, Integer.MAX_VALUE);
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
   * This operation is unsupported.
   * 
   * @param bit
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.Collection#add(java.lang.Object)
   */
  @Override
  public boolean add(final Byte bit) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
  }

  /**
   * This operation is unsupported.
   * 
   * @param index
   *          This parameter is ignored.
   * @param value
   *          This parameter is ignored.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.List#add(int, java.lang.Object)
   */
  @Override
  public void add(final int index, final Byte value) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");

  }

  /**
   * This operation is unsupported.
   * 
   * @param c
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.Collection#addAll(java.util.Collection)
   */
  @Override
  public boolean addAll(final Collection<? extends Byte> c) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
  }

  /**
   * This operation is unsupported.
   * 
   * @param index
   *          This parameter is ignored.
   * @param c
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.List#addAll(int, java.util.Collection)
   */
  @Override
  public boolean addAll(final int index, final Collection<? extends Byte> c) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
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
   * This operation is unsupported.
   * 
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.Collection#clear()
   */
  @Override
  public void clear() {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
  }

  /**
   * {@inheritDoc}
   * 
   * @param o
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see java.util.Collection#contains(java.lang.Object)
   */
  @Override
  public boolean contains(final Object o) {
    final byte value = (Byte) o;
    for (final Byte bit : this) {
      if (value == bit.intValue()) {
        return true;
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   * 
   * @param c
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see java.util.Collection#containsAll(java.util.Collection)
   */
  @Override
  public boolean containsAll(final Collection<?> c) {
    for (final Object o : c) {
      if (!this.contains(o)) {
        return false;
      }
    }
    return true;
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
   * Get the value of the bit at the specified index.
   * 
   * @param index
   *          The index of the bit to get.
   * @return The value of the bit at the specified index.
   * @see java.util.List#get(int)
   */
  @Override
  public Byte get(final int index) {
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
   * {@inheritDoc}
   * 
   * @param o
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see java.util.List#indexOf(java.lang.Object)
   */
  @Override
  public int indexOf(final Object o) {
    final Byte value = (Byte) o;

    int i = 0;
    while (i < this.length) {
      if (this.get(i).equals(value)) {
        return i;
      }

      i += 1;
    }

    return -1;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.Collection#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    return this.length == 0;
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
   * @param o
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see java.util.List#lastIndexOf(java.lang.Object)
   */
  @Override
  public int lastIndexOf(final Object o) {

    final Byte value = (Byte) o;

    int i = this.length - 1;
    while (i >= 0) {
      if (this.get(i).equals(value)) {
        return i;
      }

      i -= 1;
    }

    return i;

  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.List#listIterator()
   */
  @Override
  public ListIterator<Byte> listIterator() {
    return new BinaryStringIterator(this);
  }

  /**
   * {@inheritDoc}
   * 
   * @param index
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see java.util.List#listIterator(int)
   */
  @Override
  public ListIterator<Byte> listIterator(final int index) {
    return new BinaryStringIterator(this, index);
  }

  /**
   * This operation is unsupported.
   * 
   * @param index
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.List#remove(int)
   */
  @Override
  public Byte remove(final int index) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
  }

  /**
   * This operation is unsupported.
   * 
   * @param object
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.List#remove(Object)
   */
  @Override
  public boolean remove(final Object object) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
  }

  /**
   * This operation is unsupported.
   * 
   * @param collection
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.Collection#removeAll(java.util.Collection)
   */
  @Override
  public boolean removeAll(final Collection<?> collection) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
  }

  /**
   * This operation is unsupported.
   * 
   * @param collection
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.Collection#retainAll(java.util.Collection)
   */
  @Override
  public boolean retainAll(final Collection<?> collection) {
    throw new UnsupportedOperationException(
        "The length of this binary string cannot be changed.");
  }

  /**
   * Set the bit at the specified index to the specified value (which must be 0
   * or 1).
   * 
   * @param index
   *          The index of the bit to set.
   * @param value
   *          The value to set the bit.
   * @return The previous value of the bit at the specified index.
   * @throws IllegalArgumentException
   *           If the specified value is not 0 or 1, or if the specified index
   *           is greater than or equal to the length of the binary string.
   * @see java.util.List#set(int, java.lang.Object)
   */
  @Override
  public Byte set(final int index, final Byte value) {
    this.checkIndex(index);
    checkValue(value);

    byte previousValue = this.get(index);

    if ((value == 1 && previousValue == 0)
        || (value == 0 && previousValue == 1)) {
      this.flipBit(index);
    }

    return previousValue;
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.Collection#size()
   */
  @Override
  public int size() {
    return this.length;
  }

  /**
   * This operation is unsupported.
   * 
   * @param fromIndex
   *          This parameter is ignored.
   * @param toIndex
   *          This parameter is ignored.
   * @return Never returns.
   * @throws UnsupportedOperationException
   *           Always throws this Exception.
   * @see java.util.List#subList(int, int)
   */
  @Override
  public List<Byte> subList(final int fromIndex, final int toIndex) {
    throw new UnsupportedOperationException(
        "This operation is unsupported on binary strings.");
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see java.util.Collection#toArray()
   */
  @Override
  public Byte[] toArray() {
    final Byte[] result = new Byte[this.length];

    for (int i = 0; i < this.length; ++i) {
      result[i] = this.get(i);
    }

    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @param <T>
   *          The type of Object in the array.
   * @param array
   *          {@inheritDoc}
   * @return {@inheritDoc}
   * @see java.util.Collection#toArray(Object[])
   */
  @SuppressWarnings("unchecked")
  @Override
  public <T> T[] toArray(final T[] array) {

    if (array.length < this.length) {
      final Byte[] result = new Byte[this.length];

      for (int i = 0; i < this.length; ++i) {
        result[i] = this.get(i);
      }

      return (T[]) result;
    } else {
      if (array.length > this.length) {
        array[this.length] = null;
      }

      for (int i = 0; i < this.length; ++i) {
        array[i] = (T) this.get(i);
      }

      return array;
    }

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
