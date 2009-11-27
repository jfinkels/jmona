/**
 * IdentityCrossoverFunction.java
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
package jmona.impl;

import jmona.CrossoverFunction;
import jmona.Individual;

/**
 * A crossover function which does nothing.
 * 
 * @param <T>
 *          The type of Individual on which to perform crossover.
 * @author jfinkels
 */
public class IdentityCrossoverFunction<T extends Individual> implements
    CrossoverFunction<T> {

  /**
   * This method does nothing.
   * 
   * @param parent1
   *          This parameter is ignored.
   * @param parent2
   *          This parameter is ignored.
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final T parent1, final T parent2) {
    // intentionally unimplemented
  }

}
