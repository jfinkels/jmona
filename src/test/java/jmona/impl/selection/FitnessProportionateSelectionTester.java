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
import java.util.Vector;

import jmona.FitnessFunction;
import jmona.SelectionException;
import jmona.functional.Range;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

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

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new FitnessProportionateSelection<ExampleIndividual>();
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(List, FitnessFunction)}
   * on individuals of equal weights.
   */
  @Test
  public void testEqualWeightSelect() {
    final FitnessFunction<ExampleIndividual> fitnessFunction = new ExampleFitnessFunction();
    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();

    try {
      assertNull(this.function.select(population, fitnessFunction));
    } catch (final SelectionException exception) {
      Util.fail(exception);
    }

    ExampleIndividual individual = new ExampleIndividual();
    population.add(individual);

    try {
      for (final int i : new Range(NUM_SELECTIONS)) {
        assertSame(individual, this.function
            .select(population, fitnessFunction));
      }
    } catch (final SelectionException exception) {
      Util.fail(exception);
    }

    // initialize some individuals all with equal fitness
    for (final int i : new Range(NUM_INDIVIDUALS)) {
      population.add(new ExampleIndividual());
    }

    // initialize the number of selections of each individual
    final Map<ExampleIndividual, Integer> numberOfSelections = new HashMap<ExampleIndividual, Integer>();
    for (final ExampleIndividual i : population) {
      numberOfSelections.put(i, 0);
    }

    try {
      for (final int i : new Range(NUM_SELECTIONS)) {
        individual = this.function.select(population, fitnessFunction);
        numberOfSelections.put(individual,
            numberOfSelections.get(individual) + 1);
      }
    } catch (final SelectionException exception) {
      Util.fail(exception);
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
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(List, FitnessFunction)}
   * with a fitnesses map with no elements.
   */
  @Test
  public void testSelectionEmptyMap() {
    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    try {
      assertNull(this.function.select(population, new ExampleFitnessFunction()));
    } catch (final SelectionException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.selection.FitnessProportionateSelection#select(List, FitnessFunction)}
   * on individuals of unequal weight.
   */
  @Test
  public void testUnequalWeightSelect() {

    final List<ExampleIndividual> population = new Vector<ExampleIndividual>();
    ExampleIndividual individual = new ExampleIndividual(1);
    population.add(individual);

    try {
      for (final int i : new Range(NUM_SELECTIONS)) {
        assertSame(individual, this.function.select(population,
            new ExampleFitnessFunction()));
      }
    } catch (final SelectionException exception) {
      Util.fail(exception);
    }
    population.clear();

    // initialize some individuals all with equal fitness
    individual = null;
    for (final int i : new Range(NUM_INDIVIDUALS / 2)) {
      population.add(new ExampleIndividual(1.0));
    }
    for (final int i : new Range(NUM_INDIVIDUALS / 2, NUM_INDIVIDUALS)) {
      population.add(new ExampleIndividual(2.0));
    }

    final Map<ExampleIndividual, Integer> numberOfSelections = new HashMap<ExampleIndividual, Integer>();
    for (final ExampleIndividual i : population) {
      numberOfSelections.put(i, 0);
    }

    int lowerSelections = 0;
    int upperSelections = 0;

    try {
      for (final int i : new Range(NUM_SELECTIONS)) {
        individual = this.function.select(population,
            new ExampleFitnessFunction());

        if (individual.fitness() == 1.0) {
          lowerSelections += 1;
        } else {
          upperSelections += 1;
        }
      }
    } catch (final SelectionException exception) {
      Util.fail(exception);
    }

    // the total number of selections must equal NUM_SELECTIONS
    assertEquals(NUM_SELECTIONS, lowerSelections + upperSelections);

    // assert that the correct number of selections were made
    final double epsilon = NUM_SELECTIONS * 0.1;
    assertEquals(NUM_SELECTIONS / 3.0, upperSelections, epsilon);
    assertEquals(2 * NUM_SELECTIONS / 3.0, lowerSelections, epsilon);

  }

  /** If no individual has any fitness, the selection should be random. */
  @Test
  public void testNoFitnesses() {
    final ExampleIndividual individual1 = new ExampleIndividual(
        Double.POSITIVE_INFINITY);
    final ExampleIndividual individual2 = new ExampleIndividual(
        Double.POSITIVE_INFINITY);
    final ExampleIndividual individual3 = new ExampleIndividual(
        Double.POSITIVE_INFINITY);

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
        selection = this.function.select(population,
            new ExampleFitnessFunction());

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

    final double expected = NUM_SELECTIONS / 3.0;
    final double delta = expected * 0.1;

    assertEquals(expected, selectionsOfIndividual1, delta);
    assertEquals(expected, selectionsOfIndividual2, delta);
    assertEquals(expected, selectionsOfIndividual3, delta);
  }

}
