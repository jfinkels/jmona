/**
 * FitnessProportionateSelectionTester.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmona.impl.example.ExampleIndividual;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the FitnessProportionateSelection class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
// TODO I don't know much about statistics; a statistician should make tests
public class FitnessProportionateSelectionTester {

  /** The number of individuals over which to make a selection. */
  public static final int NUM_INDIVIDUALS = 20;
  /** The number of selections to make. */
  public static final int NUM_SELECTIONS = 100000;
  /** The SelectionFunction under test. */
  private FitnessProportionateSelection<ExampleIndividual> function = null;
  /** The map of fitnesses from which to select individuals. */
  private Map<ExampleIndividual, Double> fitnesses = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new FitnessProportionateSelection<ExampleIndividual>();
    this.fitnesses = new HashMap<ExampleIndividual, Double>();
  }

  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(FitnessProportionateSelectionTester.class);

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(Map)} on
   * individuals of equal weights.
   */
  @Test
  public void testEqualWeightSelect() {
    assertNull(this.function.select(this.fitnesses));

    ExampleIndividual individual = new ExampleIndividual(1);
    this.fitnesses.put(individual, individual.fitness());

    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      assertSame(individual, this.function.select(this.fitnesses));
    }

    // initialize some individuals all with equal fitness
    for (int i = 0; i < NUM_INDIVIDUALS; ++i) {
      individual = new ExampleIndividual(1);
      this.fitnesses.put(individual, individual.fitness());
    }

    // initialize the number of selections of each individual
    final Map<ExampleIndividual, Integer> numberOfSelections = new HashMap<ExampleIndividual, Integer>();
    for (final ExampleIndividual i : this.fitnesses.keySet()) {
      numberOfSelections.put(i, 0);
    }

    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      individual = this.function.select(this.fitnesses);
      numberOfSelections.put(individual,
          numberOfSelections.get(individual) + 1);
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

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(List)}
   * with a fitnesses map with no elements.
   */
  @Test
  public void testSelectionEmptyMap() {
    assertNull(this.function.select(this.fitnesses));
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(List)} on
   * individuals of unequal weight.
   */
  @Test
  public void testUnequalWeightSelect() {
    // initialize some individuals all with equal fitness
    ExampleIndividual individual = null;
    for (int i = 0; i < NUM_INDIVIDUALS / 2; ++i) {
      individual = new ExampleIndividual(1);
      this.fitnesses.put(individual, individual.fitness());
    }

    for (int i = 0; i < NUM_INDIVIDUALS / 2; ++i) {
      individual = new ExampleIndividual(2);
      this.fitnesses.put(individual, individual.fitness());
    }

    int lowerSelections = 0;
    int upperSelections = 0;
    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      individual = this.function.select(this.fitnesses);

      if (individual.fitness() == 1.0) {
        lowerSelections += 1;
      } else {
        upperSelections += 1;
      }
    }

    // the total number of selections must equal NUM_SELECTIONS
    assertEquals(NUM_SELECTIONS, lowerSelections + upperSelections);

    // assert that the correct number of selections were made
    final double epsilon = NUM_SELECTIONS * 0.1;
    assertEquals(2 * NUM_SELECTIONS / 3.0, upperSelections, epsilon);
    assertEquals(NUM_SELECTIONS / 3.0, lowerSelections, epsilon);

  }

  /** If no individual has any fitness, the selection should be random. */
  @Test
  public void testNoFitnesses() {
    final ExampleIndividual individual1 = new ExampleIndividual(0);
    final ExampleIndividual individual2 = new ExampleIndividual(0);
    final ExampleIndividual individual3 = new ExampleIndividual(0);

    this.fitnesses.put(individual1, individual1.fitness());
    this.fitnesses.put(individual2, individual2.fitness());
    this.fitnesses.put(individual3, individual3.fitness());

    int selectionsOfIndividual1 = 0;
    int selectionsOfIndividual2 = 0;
    int selectionsOfIndividual3 = 0;

    ExampleIndividual selection = null;
    for (int i = 0; i < NUM_SELECTIONS; ++i) {
      selection = this.function.select(this.fitnesses);

      if (selection.equals(individual1)) {
        selectionsOfIndividual1 += 1;
      } else if (selection.equals(individual2)) {
        selectionsOfIndividual2 += 1;
      } else {
        selectionsOfIndividual3 += 1;
      }
    }

    final double expected = NUM_SELECTIONS / 3.0;
    final double delta = expected * 0.1;
    LOG.debug(selectionsOfIndividual1);
    LOG.debug(selectionsOfIndividual2);
    LOG.debug(selectionsOfIndividual3);
    assertEquals(expected, selectionsOfIndividual1, delta);
    assertEquals(expected, selectionsOfIndividual2, delta);
    assertEquals(expected, selectionsOfIndividual3, delta);
  }

}
