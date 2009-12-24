/**
 * Tour.java
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
package jmona.example.ga.tsp;

import java.util.Vector;

import jmona.Individual;

/**
 * A tour in the traveling salesman problem.
 * 
 * @author jfinkels
 */
// TODO abstract from Tour and BinaryString to make mutation function better?
public class Tour extends Vector<Integer> implements Individual {

  /** Default generated serial version UID. */
  private static final long serialVersionUID = 5079089965907994960L;

  /**
   * {@inheritDoc}
   * 
   * @return {@inheritDoc}
   * @see jmona.Individual#deepCopy()
   */
  @Override
  public Tour deepCopy() {
    final Tour clone = new Tour();
    
    for (final int city : this) {
      clone.add(city);
    }
    
    return clone;
  }

}