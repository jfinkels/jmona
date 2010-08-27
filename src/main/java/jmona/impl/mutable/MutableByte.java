/**
 * MutableByte.java
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
package jmona.impl.mutable;

import jmona.DeepCopyable;

/**
 * A deep-copyable, mutable byte wrapper.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableByte extends org.apache.commons.lang3.mutable.MutableByte
    implements DeepCopyable<MutableByte> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 7939210268935242142L;
  /** An instance of a zero byte. */
  public static final MutableByte ZERO = new MutableByte(0);
  /** An instance of a one byte. */
  public static final MutableByte ONE = new MutableByte(1);

  /**
   * Instantiates this object by calling the default constructor of the
   * superclass.
   */
  public MutableByte() {
    // intentionally unimplemented
  }

  /**
   * Instantiates this object with the specified initial value.
   * 
   * @param initialValue
   *          The initial value of this object.
   */
  public MutableByte(final byte initialValue) {
    super(initialValue);
  }

  /**
   * Instantiates this object with the specified initial value.
   * 
   * @param initialValue
   *          The initial value of this object.
   */
  public MutableByte(final Number initialValue) {
    super(initialValue);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public MutableByte deepCopy() {
    return new MutableByte(this);
  }

}
