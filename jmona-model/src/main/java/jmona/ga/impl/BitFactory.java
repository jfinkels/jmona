/**
 * BitFactory.java
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
package jmona.ga.impl;

import jmona.Factory;
import jmona.impl.mutable.MutableByte;
import jmona.random.RandomUtils;

/**
 * A factory for bits (0s or 1s).
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class BitFactory implements Factory<MutableByte> {

  /**
   * Generate a random bit, with value 0 or 1, uniformly distributed.
   * 
   * @return A 0 or a 1.
   * @see jmona.Factory#createObject()
   */
  @Override
  public MutableByte createObject() {
    return new MutableByte(RandomUtils.randomData().nextInt(0, 1));
  }

}
