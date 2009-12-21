/**
 * BinaryStringFactory.java
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

import jmona.IndividualFactory;

/**
 * A factory for creating random binary strings.
 * 
 * @author jfinkels
 */
public class BinaryStringFactory implements IndividualFactory<CharArrayBinaryString> {

  /** The default length of the binary string to create. */
  public static final int DEFAULT_LENGTH = 20;
  /** The length of the binary string to create. */
  private int length = DEFAULT_LENGTH;

  /**
   * Create a new uniformly random binary string.
   * 
   * @return A uniformly random binary string.
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public CharArrayBinaryString createIndividual() {
    return new CharArrayBinaryString(this.length, true);
  }

  /**
   * Set the length of the binary string to create.
   * 
   * @param newLength
   *          The length of the binary string to create.
   */
  public void setLength(final int newLength) {
    this.length = newLength;
  }

}
