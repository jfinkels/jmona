/**
 * TournamentSelectionTester.java
 * 
 * Copyright 2009, 2010 Jeffrey Finkelstein
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

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import jfcommon.functional.Range;
import jmona.impl.example.ExampleIndividual;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the TournamentSelection class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class TournamentSelectionTester {

  /** The number of individuals in the tournament. */
  public static final int NUM_INDIVIDUALS = 5;
  /** The number of selections to make. */
  public static final int NUM_SELECTIONS = 1000;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** All individuals in the tournament. */
  private Map<ExampleIndividual, Double> population = null;
  /** The SelectionFunction under test. */
  private TournamentSelection<ExampleIndividual> function = null;

  /** Establish a test fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new TournamentSelection<ExampleIndividual>();

    this.population = new HashMap<ExampleIndividual, Double>();
    for (final int i : new Range(NUM_INDIVIDUALS)) {
      this.population.put(new ExampleIndividual(i), (double) i);
    }

  }

  /**
   * Test method for
   * {@link jmona.impl.selection.TournamentSelection#select(Map)}.
   */
  @Test
  public void testSelect() {

    ExampleIndividual individual = null;
    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      individual = this.function.select(this.population);
      assertTrue(this.population.containsKey(individual));
    }

    // add some more individuals
    for (int i = NUM_INDIVIDUALS; i < 2 * NUM_INDIVIDUALS; ++i) {
      this.population.put(new ExampleIndividual(i), (double) i);
    }

    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      individual = this.function.select(this.population);
      assertTrue(this.population.containsKey(individual));
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.TournamentSelection#setTournamentSize(int)}.
   */
  @Test
  public void testSetTournamentSize() {
    final int newTournamentSize = 10;
    this.function.setTournamentSize(newTournamentSize);
    // TODO assertions to make here?
  }
}
