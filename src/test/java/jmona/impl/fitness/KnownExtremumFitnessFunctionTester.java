/**
 * KnownExtremumFitnessFunctionTester.java
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
import static org.junit.Assert.assertNull;
import jmona.FitnessException;
import jmona.impl.example.ExampleFitnessFunction;
import jmona.impl.example.ExampleIndividual;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the KnownExtremumFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.3
 */
public class KnownExtremumFitnessFunctionTester {
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The FitnessFunction under test. */
  private KnownExtremumFitnessFunction<ExampleIndividual> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new ExampleFitnessFunction();
  }

  /**
   * Test method for
   * {@link jmona.impl.fitness.KnownExtremumFitnessFunction#adjustedFitness(java.lang.Object)}
   * .
   */
  @Test
  public void testAdjustedFitness() {
    final int fitness = 10;
    final ExampleIndividual individual = new ExampleIndividual(fitness);
    try {
      assertEquals(1.0 / (1 + individual.fitness()), this.function
          .adjustedFitness(individual), ZERO_DELTA);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.fitness.KnownExtremumFitnessFunction#extremum()}.
   */
  @Test
  public void testExtremum() {
    final double newExtremum = 10;
    this.function.setExtremum(newExtremum);
    assertEquals(newExtremum, this.function.extremum().doubleValue(),
        ZERO_DELTA);
    
    this.function.setExtremum(null);
    try {
      this.function.standardizedFitness(new ExampleIndividual());
      Util.shouldHaveThrownException();
    } catch (final FitnessException exception) {
      assertNull(this.function.extremum());
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.fitness.KnownExtremumFitnessFunction#standardizedFitness(java.lang.Object)}
   * .
   */
  @Test
  public void testStandardizedFitness() {
    final double fitness = 10;
    final ExampleIndividual individual = new ExampleIndividual(fitness);
    try {
      assertEquals(individual.fitness(), this.function
          .standardizedFitness(individual), ZERO_DELTA);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for
   * {@link jmona.impl.fitness.KnownExtremumFitnessFunction#typeOfExtremum()}.
   */
  @Test
  public void testTypeOfExtremum() {
    assertEquals(KnownExtremumFitnessFunction.MINIMUM, this.function
        .typeOfExtremum());
  }

}
