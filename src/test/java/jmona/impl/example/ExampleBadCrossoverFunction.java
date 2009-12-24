/**
 * ExampleBadCrossoverFunction.java
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
package jmona.impl.example;

import jmona.CrossoverException;
import jmona.CrossoverFunction;

/**
 * A CrossoverFunction which always throws an Exception.
 * 
 * @author jfinkels
 */
public class ExampleBadCrossoverFunction implements
    CrossoverFunction<ExampleIndividual> {

  /**
   * Always throws a CrossoverException.
   * 
   * @param individual1
   *          This parameter is ignored.
   * @param individual2
   *          This parameter is ignored.
   * @throws CrossoverException
   *           Always throws this Exception.
   * @see jmona.CrossoverFunction#crossover(jmona.Individual, jmona.Individual)
   */
  @Override
  public void crossover(final ExampleIndividual individual1,
      final ExampleIndividual individual2) throws CrossoverException {
    throw new CrossoverException();
  }

}
