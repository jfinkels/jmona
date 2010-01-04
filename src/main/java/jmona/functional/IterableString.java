/**
 * IterableString.java
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A String which is Iterable over each of its characters, similar to <a
 * href="http://docs.python.org/library/stdtypes.html#typesseq">Python's
 * built-in <code>string</code> type</a>.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class IterableString implements Iterable<Character>, Iterator<Character> {
  /** The string over which this class iterates. */
  private final String string;
  /** The pointer representing the current character index in the string. */
  private int pointer = 0;

  /**
   * Use the specified String as the String over which to iterate.
   * 
   * @param initialString
   *          The String over which to iterate.
   */
  public IterableString(final String initialString) {
    this.string = initialString;
  }

  /**
   * Gets an Iterator over each of the characters of the String.
   * 
   * @return An Iterator over each of the characters of the String.
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<Character> iterator() {
    return new IterableString(this.string);
  }

  /**
   * Whether this String has any more characters over which to iterate.
   * 
   * @return Whether this String has any more characters over which to iterate.
   * @see java.util.Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return this.pointer < this.string.length();
  }

  /**
   * Gets the next character in the iteration.
   * 
   * @return The next character in the iteration.
   * @throws NoSuchElementException
   *           {@inheritDoc}
   * @see java.util.Iterator#next()
   */
  @Override
  public Character next() {
    if (this.pointer >= this.string.length()) {
      throw new NoSuchElementException(
          "There are no more characters in the string.");
    }
    return this.string.charAt(this.pointer++);
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
