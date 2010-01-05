/**
 * PresetFitnessFunctionTester.java
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
package jmona.impl.fitness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import jmona.functional.Range;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PresetFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.4
 */
public class PresetFitnessFunctionTester {

  /** The number of individuals in the preset fitnesses Map. */
  public static final int NUM_INDIVIDUALS = 1000;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The preset Map from individual to fitness. */
  private Map<ExampleIndividual, Double> fitnesses = null;

  /** The function under test. */
  private PresetFitnessFunction<ExampleIndividual> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.fitnesses = new HashMap<ExampleIndividual, Double>();

    ExampleIndividual individual = null;
    for (final int i : new Range(NUM_INDIVIDUALS)) {
      individual = new ExampleIndividual(i);
      this.fitnesses.put(individual, individual.fitness());
    }

    this.function = new PresetFitnessFunction<ExampleIndividual>(this.fitnesses);
  }

  /**
   * Test method for
   * {@link jmona.impl.fitness.PresetFitnessFunction#adjustedFitness(java.lang.Object)}
   * .
   */
  @Test
  public void testAdjustedFitness() {
    for (final ExampleIndividual individual : this.fitnesses.keySet()) {
      assertEquals(individual.fitness(), this.function
          .adjustedFitness(individual), ZERO_DELTA);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.fitness.PresetFitnessFunction#rawFitness(java.lang.Object)}
   * .
   */
  @Test
  public void testRawFitness() {
    try {
      this.function.rawFitness(null);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.fitness.PresetFitnessFunction#standardizedFitness(java.lang.Object)}
   * .
   */
  @Test
  public void testStandardizedFitness() {
    try {
      this.function.standardizedFitness(null);
      Util.shouldHaveThrownException();
    } catch (final UnsupportedOperationException exception) {
      assertTrue(exception instanceof UnsupportedOperationException);
    }
  }

}
