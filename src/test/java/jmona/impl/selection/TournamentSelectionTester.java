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
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the TournamentSelection class.
 * 
 * @author jfinkels
 */
public class TournamentSelectionTester {

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(TournamentSelectionTester.class);

  /** The number of selections to make. */
  public static final int NUM_SELECTIONS = 1000;
  /** The fitnesses from which to select. */
  private Map<ExampleIndividual, Double> fitnesses = null;
  /** The SelectionFunction under test. */
  private TournamentSelection<ExampleIndividual> function = null;
  /** An ExampleIndividual. */
  private ExampleIndividual individual1 = null;
  /** Another ExampleIndividual. */
  private ExampleIndividual individual2 = null;

  /** Establish a test fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new TournamentSelection<ExampleIndividual>();
    this.fitnesses = new HashMap<ExampleIndividual, Double>();
    this.individual1 = new ExampleIndividual(2);
    this.individual2 = new ExampleIndividual(1);
    this.fitnesses.put(this.individual1, this.individual1.fitness());
    this.fitnesses.put(this.individual2, this.individual1.fitness());
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.TournamentSelection#select(java.util.Map)}.
   */
  //@Test
  public void testSelect() {
    fail("Not yet implemented.");
/*    int selectionsOfIndividual1 = 0;
    int selectionsOfIndividual2 = 0;

    ExampleIndividual selectedIndividual = null;
    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      selectedIndividual = function.select(this.fitnesses);
      
      LOG.debug("Selected individual: " + selectedIndividual);
      
      if (selectedIndividual.equals(this.individual1)) {
        selectionsOfIndividual1 += 1;
      } else if (selectedIndividual.equals(this.individual2)) {
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
*/
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.TournamentSelection#setTournamentSize(int)}.
   */
  @Test
  public void testSetTournamentSize() {
    final int newTournamentSize = 10;
    this.function.setTournamentSize(newTournamentSize);
    // TODO assertions
  }
}
