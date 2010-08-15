/**
 * TourFactory.java
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
package jmona.example.tsp;

import java.util.Collections;

import jmona.CopyingException;
import jmona.DeepCopyableList;
import jmona.InitializationException;
import jmona.functional.MutableRange;
import jmona.impl.DeepCopyableListFactory;
import jmona.impl.DeepCopyableVector;
import jmona.impl.mutable.MutableInteger;

/**
 * A factory which generates random Tours.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class TourFactory extends DeepCopyableListFactory<MutableInteger> {

  /**
   * @param initialSize
   */
  public TourFactory(final int initialSize) {
    super(initialSize);
  }

  /**
   * Generate a random Tour of length specified in the constructor.
   * 
   * A Tour of length <em>l</em> will have cities numbered sequentially from
   * <em>0</em> to <em>l-1</em>, inclusive.
   * 
   * Unlike the corresponding method in the superclass, this method does not
   * require that the {@link DeepCopyableListFactory#elementFactory()} be
   * non-null (because it doesn't use that property at all).
   * 
   * @return A randomly generated Tour.
   * @throws InitializationException
   *           If there is a problem generating the sequence of integers.
   * @see jmona.Factory#createObject()
   */
  @Override
  public DeepCopyableList<MutableInteger> createObject()
      throws InitializationException {
    DeepCopyableList<MutableInteger> result;
    try {
      result = new DeepCopyableVector<MutableInteger>(new MutableRange(this
          .size()));
    } catch (final CopyingException exception) {
      throw new InitializationException(
          "Failed to create initial list of integers.", exception);
    }

    Collections.shuffle(result);

    return result;
  }

}
