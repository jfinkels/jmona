/**
 * MutableFloat.java
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
 * A deep-copyable, mutable float wrapper.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableFloat extends org.apache.commons.lang.mutable.MutableFloat
    implements DeepCopyable<MutableFloat> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -2646506100049786381L;

  /**
   * Instantiates this object by calling the default constructor of the
   * superclass.
   */
  public MutableFloat() {
    // intentionally unimplemented
  }

  /**
   * Instantiates this object with the specified initial value.
   * 
   * @param initialValue
   *          The initial value of this object.
   */
  public MutableFloat(final float initialValue) {
    super(initialValue);
  }

  /**
   * Instantiates this object with the specified initial value.
   * 
   * @param initialValue
   *          The initial value of this object.
   */
  public MutableFloat(final Number initialValue) {
    super(initialValue);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public MutableFloat deepCopy() {
    return new MutableFloat(this);
  }

}
