/**
 * TournamentSelection.java
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
package jmona.impl.selection;

import java.util.Map;

import jmona.Individual;
import jmona.SelectionFunction;

/**
 * A tournament selection algorithm.
 * 
 * @param <T>
 *          The type of Individual to select.
 * @author jfinkels
 */
public class TournamentSelection<T extends Individual> implements
    SelectionFunction<T> {

  /**
   * 
   * @param fitnesses
   *          {@inheritDoc}
   * @see jmona.SelectionFunction#select(java.util.Map)
   */
  // TODO documentation for this method
  @Override
  public T select(final Map<T, Double> fitnesses) {
    // TODO Auto-generated method stub
    return null;
  }

}
