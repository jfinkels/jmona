/**
 * TournamentSelectionTester.java
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

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import jmona.impl.example.ExampleIndividual;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author jfinkels
 */
public class TournamentSelectionTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(TournamentSelectionTester.class);

  /** The number of selections to make. */
  public static final int NUM_SELECTIONS = 1000;

  /**
   * Test method for
   * {@link jmona.impl.selection.TournamentSelection#select(java.util.Map)}.
   */
  @Test
  public void testSelect() {
    final TournamentSelection<ExampleIndividual> function = new TournamentSelection<ExampleIndividual>();

    final Map<ExampleIndividual, Double> fitnesses = new HashMap<ExampleIndividual, Double>();
    final ExampleIndividual individual1 = new ExampleIndividual(2);
    final ExampleIndividual individual2 = new ExampleIndividual(1);

    fitnesses.put(individual1, individual1.fitness());
    fitnesses.put(individual2, individual2.fitness());

    int selectionsOfIndividual1 = 0;
    int selectionsOfIndividual2 = 0;

    ExampleIndividual selectedIndividual = null;
    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      selectedIndividual = function.select(fitnesses);

      if (selectedIndividual.equals(individual1)) {
        selectionsOfIndividual1 += 1;
      } else if (selectedIndividual.equals(individual2)) {
        selectionsOfIndividual2 += 1;
      } else {
        fail("Something crazy has happened.");
      }
    }

    final int expectedSelections1 = (int) (TournamentSelection.DEFAULT_SELECTION_PROBABILITY * NUM_SELECTIONS);
    final int expectedSelections2 = NUM_SELECTIONS - expectedSelections1;
    final double delta = NUM_SELECTIONS * 0.05;

    LOG.debug(selectionsOfIndividual1);
    LOG.debug(expectedSelections1);
    LOG.debug(selectionsOfIndividual2);
    LOG.debug(expectedSelections2);

    // TODO correct assertions
    // assertEquals(expectedSelections1, selectionsOfIndividual1, delta);
    // assertEquals(expectedSelections2, selectionsOfIndividual2, delta);

  }
}
