/**
 * MutableBoolean.java
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
 * A deep-copyable, mutable boolean wrapper.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class MutableBoolean extends
    org.apache.commons.lang.mutable.MutableBoolean implements
    DeepCopyable<MutableBoolean> {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = -8838774305994993306L;

  /**
   * Instantiates this object by calling the default constructor of the
   * superclass.
   */
  public MutableBoolean() {
    // intentionally unimplemented
  }

  /**
   * Instantiates this object with the specified initial value.
   * 
   * @param initialValue
   *          The initial value of this object.
   */
  public MutableBoolean(final boolean initialValue) {
    super(initialValue);
  }

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public MutableBoolean deepCopy() {
    return new MutableBoolean(this.booleanValue());
  }

}
