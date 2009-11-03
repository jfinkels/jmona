/**
 * FitnessProportionateSelectionTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.Map;

import jmona.Individual;
import jmona.SelectionFunction;
import jmona.impl.ExampleIndividual;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the FitnessProportionateSelection class.
 * 
 * @author jfinkels
 */
public class FitnessProportionateSelectionTester {

  /** The number of individuals over which to make a selection. */
  public static final int NUM_INDIVIDUALS = 10;
  /** The number of selections to make. */
  public static final int NUM_SELECTIONS = 1000;

  /** The SelectionFunction under test. */
  private SelectionFunction<ExampleIndividual> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new FitnessProportionateSelection<ExampleIndividual>();
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(java.util.Map)}
   * .
   */
  // TODO I don't know much about statistics; a statistician should make tests
  @Test
  public void testSelect() {
    final Map<ExampleIndividual, Double> fitnesses = new HashMap<ExampleIndividual, Double>();

    ExampleIndividual individual = this.function.select(fitnesses);
    assertNull(individual);

    individual = new ExampleIndividual();
    fitnesses.put(individual, 1.0);
    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      assertSame(individual, this.function.select(fitnesses));
    }
    fitnesses.remove(individual);

    // initialize some individuals all with equal fitness
    individual = null;
    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      individual = new ExampleIndividual(NUM_INDIVIDUALS);
      fitnesses.put(individual, individual.fitness());
    }

    final Map<Individual, Integer> numberOfSelections = new HashMap<Individual, Integer>();
    Individual selectedIndividual = null;
    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      selectedIndividual = this.function.select(fitnesses);
      Integer selections = numberOfSelections.get(selectedIndividual);
      if (selections == null) {
        numberOfSelections.put(selectedIndividual, 1);
      } else {
        numberOfSelections.put(selectedIndividual, (Integer) (selections + 1));
      }
    }

    int sum = 0;
    for (final Integer selections : numberOfSelections.values()) {
      sum += selections;
    }
    double meanSelections = (double) sum / numberOfSelections.size();

    double expectedSelectionsPerIndividual = (double) NUM_SELECTIONS
        / NUM_INDIVIDUALS;

    double epsilon = expectedSelectionsPerIndividual * 0.1;

    assertEquals(expectedSelectionsPerIndividual, meanSelections, epsilon);

  }

}
