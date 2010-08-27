/**
 * MutableRange.java
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
package jmona.impl.mutable.functional;

import jfcommon.functional.AbstractRange;
import jmona.impl.mutable.MutableInteger;

/**
 * An AbstractRange consisting of MutableIntegers.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableRange extends AbstractRange<MutableInteger> {

  /**
   * Instantiates this MutableRange by adding each integer between 0 (inclusive)
   * and {@code terminalInteger} (exclusive).
   * 
   * @param terminalInteger
   *          The (exclusive) upper bound on integers to add to this list.
   */
  public MutableRange(final int terminalInteger) {
    super(terminalInteger);
  }

  /**
   * Instantiates this MutableRange by adding each integer between
   * {@code initialInteger} (inclusive) and {@code terminalInteger} (exclusive).
   * 
   * @param initialInteger
   *          The (inclusive) lower bound on integers to add to this list.
   * @param terminalInteger
   *          The (exclusive) upper bound on integers to add to this list.
   */
  public MutableRange(final int initialInteger, final int terminalInteger) {
    super(initialInteger, terminalInteger);
  }

  /**
   * Instantiates this MutableRange by adding each integer between
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
  public MutableRange(final int initialInteger, final int terminalInteger,
      final int step) {
    super(initialInteger, terminalInteger, step);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.functional.AbstractRange#getValue()
   */
  @Override
  protected MutableInteger getValue() {
    return new MutableInteger(this.current());
  }

}
