/**
 * ElitismSelectionFunctionTester.java
 * 
 * Copyright 2010 Jeffrey Finkelstein
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.FitnessFunction;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the ElitismSelectionFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.5
 */
public class ElitismSelectionFunctionTester {

  /**
   * Test method for
   * {@link jmona.impl.selection.ElitismSelectionFunction#select(java.util.List, jmona.FitnessFunction, int)}
   * .
   */
  @Test
  public void testSelect() {
    final ElitismSelectionFunction<ExampleIndividual> function = new ElitismSelectionFunction<ExampleIndividual>();

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();

    final ExampleIndividual individual1 = new ExampleIndividual(0);
    final ExampleIndividual individual2 = new ExampleIndividual(1);

    population.add(individual1);
    population.add(individual2);

    final FitnessFunction<ExampleIndividual> fitnessFunction = new ExampleFitnessFunction();

    int numberToSelect = 1;
    List<ExampleIndividual> selection = function.select(population,
        fitnessFunction, numberToSelect);

    assertEquals(numberToSelect, selection.size());

    assertSame(individual1, selection.get(0));

    numberToSelect = 2;

    selection = function.select(population, fitnessFunction, numberToSelect);

    assertEquals(numberToSelect, selection.size());

    assertSame(individual1, selection.get(0));
    assertSame(individual2, selection.get(1));

    numberToSelect = 3;

    try {
      selection = function.select(population, fitnessFunction, numberToSelect);
      Util.shouldHaveThrownException();
    } catch (final IllegalArgumentException exception) {
      assertTrue(population.size() < numberToSelect);
    }
  }

}
