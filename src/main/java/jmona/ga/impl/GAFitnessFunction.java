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

import jmona.Individual;
import jmona.impl.IdentityFunction;

/**
 * A FitnessFunction which measures the distance between a specified Individual
 * and a target Individual with a metric function.
 * 
 * @param <T>
 *          The type of Individual to measure.
 * @author jfinkels
 */
public class GAFitnessFunction<T extends Individual> extends
    MappingFitnessFunction<T, T> {

  public GAFitnessFunction() {
    this.setMapping(new IdentityFunction<T>());
  }
}
