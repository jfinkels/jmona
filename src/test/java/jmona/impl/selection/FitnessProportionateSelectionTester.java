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
import java.util.Map.Entry;

import jmona.functional.Range;
import jmona.impl.example.ExampleIndividual;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the FitnessProportionateSelection class.
 * 
 * @author Jeffrey Finkelstein
 */
// TODO I don't know much about statistics; a statistician should make tests
public class FitnessProportionateSelectionTester {

  /** The number of individuals over which to make a selection. */
  public static final int NUM_INDIVIDUALS = 10;
  /** The number of selections to make. */
  public static final int NUM_SELECTIONS = 100000;

  /** The SelectionFunction under test. */
  private FitnessProportionateSelection<ExampleIndividual> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new FitnessProportionateSelection<ExampleIndividual>();
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(java.util.Map)}
   * on Individuals of equal weights.
   */
  @Test
  public void testEqualWeightSelect() {
    final Map<ExampleIndividual, Double> fitnesses = new HashMap<ExampleIndividual, Double>();

    assertNull(this.function.select(fitnesses));

    ExampleIndividual individual = new ExampleIndividual();
    fitnesses.put(individual, 1.0);
    for (final int i : new Range(NUM_SELECTIONS)) {
      assertSame(individual, this.function.select(fitnesses));
    }
    fitnesses.remove(individual);

    // initialize some individuals all with equal fitness
    individual = null;
    for (final int i : new Range(NUM_INDIVIDUALS)) {
      individual = new ExampleIndividual(NUM_INDIVIDUALS);
      fitnesses.put(individual, individual.fitness());
    }

    final Map<ExampleIndividual, Integer> numberOfSelections = new HashMap<ExampleIndividual, Integer>();
    ExampleIndividual selectedIndividual = null;
    for (final int i : new Range(NUM_SELECTIONS)) {
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

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(java.util.Map)}
   * with a fitnesses map with no elements.
   */
  @Test
  public void testSelectionEmptyMap() {
    final Map<ExampleIndividual, Double> fitnesses = new HashMap<ExampleIndividual, Double>();
    final ExampleIndividual individual = this.function.select(fitnesses);
    assertNull(individual);
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(java.util.Map)}
   * on Individual of unequal weight.
   */
  @Test
  public void testUnequalWeightSelect() {
    final Map<ExampleIndividual, Double> fitnesses = new HashMap<ExampleIndividual, Double>();

    ExampleIndividual individual = new ExampleIndividual();

    fitnesses.put(individual, 1.0);
    for (final int i : new Range(NUM_SELECTIONS)) {
      assertSame(individual, this.function.select(fitnesses));
    }
    fitnesses.remove(individual);

    // initialize some individuals all with equal fitness
    individual = null;
    for (final int i : new Range(NUM_INDIVIDUALS / 2)) {
      individual = new ExampleIndividual(1.0);
      fitnesses.put(individual, individual.fitness());
    }
    for (final int i : new Range(NUM_INDIVIDUALS / 2, NUM_INDIVIDUALS)) {
      individual = new ExampleIndividual(2.0);
      fitnesses.put(individual, individual.fitness());
    }

    final Map<ExampleIndividual, Integer> numberOfSelections = new HashMap<ExampleIndividual, Integer>();
    ExampleIndividual selectedIndividual = null;
    for (final int i : new Range(NUM_SELECTIONS)) {
      selectedIndividual = this.function.select(fitnesses);
      Integer selections = numberOfSelections.get(selectedIndividual);
      if (selections == null) {
        numberOfSelections.put(selectedIndividual, 1);
      } else {
        numberOfSelections.put(selectedIndividual, (Integer) (selections + 1));
      }
    }

    // get the total number of selections made over all individuals
    int lowerSum = 0;
    int upperSum = 0;
    for (final Entry<ExampleIndividual, Integer> entry : numberOfSelections
        .entrySet()) {
      if (entry.getKey().fitness() == 2.0) {
        upperSum += entry.getValue();
      } else {
        lowerSum += entry.getValue();
      }
    }
    final int sum = upperSum + lowerSum;

    // assert that the correct number of selections were made
    final double epsilon = NUM_SELECTIONS * 0.1;
    assertEquals(NUM_SELECTIONS * 0.333, lowerSum, epsilon);
    assertEquals(NUM_SELECTIONS * 0.666, upperSum, epsilon);
    assertEquals(NUM_SELECTIONS, sum);

    // get the arithmetic mean number of selections
    double meanLowerSelections = lowerSum / (NUM_INDIVIDUALS / 2.0);
    double meanUpperSelections = upperSum / (NUM_INDIVIDUALS / 2.0);

    final double lowerEpsilon = meanLowerSelections * 0.1;
    final double upperEpsilon = meanUpperSelections * 0.1;

    for (final Entry<ExampleIndividual, Integer> entry : numberOfSelections
        .entrySet()) {
      if (entry.getKey().fitness() == 1.0) {
        assertEquals(entry.getValue(), meanLowerSelections, lowerEpsilon);
      } else {
        assertEquals(entry.getValue(), meanUpperSelections, upperEpsilon);
      }
    }
  }

}
