/**
 * Tour.java
 * 
 * Copyright 2010 "Jeffrey Finkelstein"
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
package jmona.example.tsp.bco;

import java.util.Iterator;

import jmona.CopyingException;
import jmona.DeepCopyableList;
import jmona.bco.Solution;
import jmona.impl.DeepCopyableVector;
import jmona.impl.mutable.MutableInteger;

/**
 * @author "Jeffrey Finkelstein"
 * @since 0.5
 */
public class Tour implements Solution<MutableInteger> {

  public Tour() {
    // intentionally unimplemented
  }

  private Tour(final DeepCopyableList<MutableInteger> initialCities)
      throws CopyingException {
    this.cities = initialCities.deepCopy();
  }

  private DeepCopyableList<MutableInteger> cities = new DeepCopyableVector<MutableInteger>();

  /*
   * (non-Javadoc)
   * 
   * @see jmona.bco.Solution#append(java.lang.Object)
   */
  @Override
  public void append(final MutableInteger solutionPart) {
    this.cities.add(solutionPart);
  }

  /*
   * (non-Javadoc)
   * 
   * @see jmona.DeepCopyable#deepCopy()
   */
  @Override
  public Tour deepCopy() throws CopyingException {
    return new Tour(this.cities);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<MutableInteger> iterator() {
    return this.cities.iterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see jmona.bco.Solution#last()
   */
  @Override
  public MutableInteger last() {
    if (this.cities.isEmpty()) {
      return null;
    }
    return this.cities.get(this.cities.size() - 1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see jmona.bco.Solution#size()
   */
  @Override
  public int size() {
    return this.cities.size();
  }

}
