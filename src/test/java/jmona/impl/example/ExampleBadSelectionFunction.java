/**
 * ExampleBadSelectionFunction.java
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

import java.util.List;

import jmona.FitnessFunction;
import jmona.IndependentSelectionFunction;
import jmona.SelectionException;

/**
 * A SelectionFunction which always throws a SelectionException.
 * 
 * @author Jeffrey Finkelstein
 */
public class ExampleBadSelectionFunction implements
    IndependentSelectionFunction<ExampleIndividual> {

  /**
   * Always throws a SelectionException.
   * 
   * @param population
   *          This parameter is ignored.
   * @param fitnessFunction
   *          This parameter is ignored.
   * @return Never returns.
   * @throws SelectionException
   *           Always throws this Exception.
   * @see jmona.IndependentSelectionFunction#select(List, FitnessFunction)
   */
  @Override
  public ExampleIndividual select(final List<ExampleIndividual> population,
      final FitnessFunction<ExampleIndividual> fitnessFunction)
      throws SelectionException {
    throw new SelectionException();
  }

}
