/**
 * MappingFitnessFunctionTester.java
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
package jmona.ga.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmona.FitnessException;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMapping;
import jmona.impl.metrics.EuclideanMetric;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MappingFitnessFunction class.
 * 
 * @author jfinkels
 */
public class MappingFitnessFunctionTester {

  /** The minimum value to test. */
  public static final int MAX_VALUE = 100;
  /** The maximum value to test. */
  public static final int MIN_VALUE = -100;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The function under test. */
  private MappingFitnessFunction<ExampleIndividual, Double> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new MappingFitnessFunction<ExampleIndividual, Double>();
    this.function.setMapping(new ExampleMapping());
    this.function.setMetric(new EuclideanMetric<Double>());
    this.function.setTarget(0.0);
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.MappingFitnessFunction#fitness(jmona.Individual)}.
   */
  @Test
  public void testFitness() {
    try {
      double expectedFitness = 0.0;
      for (int i = MIN_VALUE; i < MAX_VALUE; ++i) {

        if (i == 0) {
          expectedFitness = Double.POSITIVE_INFINITY;
        } else {
          expectedFitness = Math.abs(1.0 / i);
        }

        assertEquals(expectedFitness, this.function
            .fitness(new ExampleIndividual(i)), ZERO_DELTA);
      }
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.ga.impl.MappingFitnessFunction#sanityCheck()}.
   */
  @Test
  public void testSanityCheck() {
    this.function = new MappingFitnessFunction<ExampleIndividual, Double>();
    try {
      this.function.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      assertTrue(exception instanceof NullPointerException);
    }

    this.function.setMetric(new EuclideanMetric<Double>());
    try {
      this.function.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      assertTrue(exception instanceof NullPointerException);
    }

    this.function.setTarget(0.0);
    try {
      this.function.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      assertTrue(exception instanceof NullPointerException);
    }

    this.function.setMapping(new ExampleMapping());
    try {
      this.function.sanityCheck();
    } catch (final NullPointerException exception) {
      Util.fail(exception);
    }
  }
}
