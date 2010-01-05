/**
 * RankSelectionTester.java
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

import java.util.List;
import java.util.Vector;

import jmona.SelectionException;
import jmona.functional.Range;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Test;

/**
 * Test class for the RankSelection class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class RankSelectionTester {

  /** The number of selections to make. */
  public static final int NUM_SELECTIONS = 10000;

  /**
   * Test method for
   * {@link jmona.impl.selection.RankSelection#select(java.util.List, jmona.FitnessFunction)}
   * .
   */
  @Test
  public void testSelect() {
    final RankSelection<ExampleIndividual> function = new RankSelection<ExampleIndividual>();
    final ExampleFitnessFunction fitnessFunction = new ExampleFitnessFunction();
    fitnessFunction.setExtremum(0.0);

    final ExampleIndividual individual1 = new ExampleIndividual(1);
    final ExampleIndividual individual2 = new ExampleIndividual(2);
    final ExampleIndividual individual3 = new ExampleIndividual(3);

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    population.add(individual1);
    population.add(individual2);
    population.add(individual3);

    int selectionsOfIndividual1 = 0;
    int selectionsOfIndividual2 = 0;
    int selectionsOfIndividual3 = 0;

    try {
      ExampleIndividual selection = null;
      for (final int i : new Range(NUM_SELECTIONS)) {
        selection = function.select(population, fitnessFunction);

        if (selection.equals(individual1)) {
          selectionsOfIndividual1 += 1;
        } else if (selection.equals(individual2)) {
          selectionsOfIndividual2 += 1;
        } else {
          selectionsOfIndividual3 += 1;
        }
      }
    } catch (final SelectionException exception) {
      Util.fail(exception);
    }

    final double expected1 = NUM_SELECTIONS * (3.0 / 6.0);
    final double expected2 = NUM_SELECTIONS * (2.0 / 6.0);
    final double expected3 = NUM_SELECTIONS * (1.0 / 6.0);

    assertEquals(expected1, selectionsOfIndividual1, expected1 * 0.1);
    assertEquals(expected2, selectionsOfIndividual2, expected2 * 0.1);
    assertEquals(expected3, selectionsOfIndividual3, expected3 * 0.1);
  }

}
