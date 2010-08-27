/**
 * BinaryStringFactory.java
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

import jfcommon.functional.Range;
import jmona.DeepCopyableList;
import jmona.impl.DeepCopyableVector;
import jmona.impl.SizedFactory;
import jmona.impl.mutable.MutableByte;
import jmona.random.RandomUtils;

/**
 * A factory which creates random BinaryString objects.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class BinaryStringFactory extends SizedFactory<DeepCopyableList<MutableByte>> {

  /**
   * Instantiates this Factory with the specified number of bits in the binary
   * strings to create.
   * 
   * @param initialSize
   *          The number of bits in the binary strings to create.
   */
  public BinaryStringFactory(final int initialSize) {
    super(initialSize);
  }

  /**
   * Creates a random BinaryString.
   * 
   * @return A random BinaryString.
   * @see jmona.Factory#createObject()
   */
  @Override
  public DeepCopyableList<MutableByte> createObject() {
    final DeepCopyableList<MutableByte> result = new DeepCopyableVector<MutableByte>();

    for (final int i : new Range(this.size())) {
      final MutableByte bit;
      
      if (RandomUtils.nextBoolean()) {
        bit = new MutableByte(1);
      } else {
        bit = new MutableByte(0);
      }
      
      result.add(bit);
    }
    
    return result;
  }

}
