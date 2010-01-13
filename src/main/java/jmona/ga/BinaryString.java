/**
 * BinaryString.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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
package jmona.ga;

import java.util.List;

import jmona.DeepCopyable;

/**
 * A binary string.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public interface BinaryString extends List<Byte>, DeepCopyable<BinaryString> {
  /**
   * Flips the bit at the specified index in this binary string.
   * 
   * @param index
   *          The index of the bit to flip.
   */
  void flipBit(final int index);
}
