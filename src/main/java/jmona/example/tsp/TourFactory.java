/**
 * TourFactory.java
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
package jmona.example.tsp;

import java.util.Collections;

import jmona.DeepCopyableList;
import jmona.impl.PartialDeepCopyableListFactory;
import jmona.impl.PartialDeepCopyableVector;

/**
 * A factory which generates random Tours.
 * 
 * @author jfinkels
 */
public class TourFactory extends PartialDeepCopyableListFactory<Integer> {

  /**
   * Generate a random Tour of length specified in the constructor.
   * 
   * A Tour of length <em>l</em> will have cities numbered sequentially from
   * <em>0</em> to <em>l-1</em>, inclusive.
   * 
   * @return A randomly generated Tour.
   * @see jmona.IndividualFactory#createIndividual()
   */
  @Override
  public DeepCopyableList<Integer> createObject() {
    final DeepCopyableList<Integer> result = new PartialDeepCopyableVector<Integer>();

    for (int i = 0; i < this.size(); ++i) {
      result.add(i);
    }

    Collections.shuffle(result);

    return result;
  }

}
