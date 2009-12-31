/**
 * GAFitnessFunction.java
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
package jmona.ga.impl;

import jmona.impl.IdentityFunction;

/**
 * A FitnessFunction which measures the distance between a specified individual
 * and a target individual with a metric function.
 * 
 * @param <T>
 *          The type of Individual to measure.
 * @author Jeffrey Finkelstein
 */
public class GAFitnessFunction<T> extends MappingFitnessFunction<T, T> {

  /**
   * Instantiate this MappingFitnessFunction with the identity mapping, that is,
   * simply measure the fitness of individuals as they are.
   */
  public GAFitnessFunction() {
    this.setMapping(new IdentityFunction<T>());
  }
}
