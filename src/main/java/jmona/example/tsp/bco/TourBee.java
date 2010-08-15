/**
 * TourBee.java
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

import jmona.CopyingException;
import jmona.bco.Bee;
import jmona.bco.Solution;
import jmona.impl.mutable.MutableInteger;

/**
 * @author "Jeffrey Finkelstein"
 * @since 0.5
 */
public class TourBee implements Bee<MutableInteger> {

  private Solution<MutableInteger> tour = new Tour();

  /*
   * (non-Javadoc)
   * 
   * @see jmona.bco.Bee#partialSolution()
   */
  @Override
  public Solution<MutableInteger> partialSolution() {
    return this.tour;
  }

  /*
   * (non-Javadoc)
   * 
   * @see jmona.bco.Bee#setPartialSolution(jmona.bco.Solution)
   */
  @Override
  public void setPartialSolution(
      final Solution<MutableInteger> newPartialSolution)
      throws CopyingException {
    this.tour = newPartialSolution.deepCopy();
  }

}
