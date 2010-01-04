/**
 * TargetedFitnessFunctionTester.java
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
package jmona.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmona.FitnessException;
import jmona.Function;
import jmona.MappingException;
import jmona.Metric;
import jmona.MetricException;
import jmona.functional.Range;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMapping;
import jmona.impl.metrics.EuclideanMetric;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the TargetedFitnessFunction class.
 * 
 * @author Jeffrey Finkelstein
 */
public class TargetedFitnessFunctionTester {

  /** The minimum value to test. */
  public static final int MAX_VALUE = 100;
  /** The maximum value to test. */
  public static final int MIN_VALUE = -100;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The function under test. */
  private TargetedFitnessFunction<ExampleIndividual, Double> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new TargetedFitnessFunction<ExampleIndividual, Double>();
    this.function.setMapping(new ExampleMapping());
    this.function.setMetric(new EuclideanMetric<Double>());
    this.function.setTarget(0.0);
  }

  /**
   * Test method for {@link jmona.impl.TargetedFitnessFunction#fitness(Object)}.
   */
  @Test
  public void testFitness() {

    try {
      double expectedFitness = 0.0;
      for (final int i : new Range(MIN_VALUE, MAX_VALUE)) {

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
   * Test method for {@link jmona.impl.TargetedFitnessFunction#sanityCheck()}.
   */
  @Test
  public void testSanityCheck() {
    this.function = new TargetedFitnessFunction<ExampleIndividual, Double>();
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

    this.function.setMapping(null);
    try {
      this.function.fitness(new ExampleIndividual());
      Util.shouldHaveThrownException();
    } catch (final FitnessException exception) {
      assertTrue(exception.getCause() instanceof NullPointerException);
    }

    this.function.setMapping(new Function<ExampleIndividual, Double>() {

      @Override
      public Double execute(final ExampleIndividual input)
          throws MappingException {
        throw new MappingException();
      }
    });

    try {
      this.function.fitness(new ExampleIndividual());
      Util.shouldHaveThrownException();
    } catch (final FitnessException exception) {
      assertTrue(exception.getCause() instanceof MappingException);
      this.function.setMapping(new ExampleMapping());
    }

    this.function.setMetric(new Metric<Double>() {

      @Override
      public double measure(final Double element1, final Double element2)
          throws MetricException {
        throw new MetricException();
      }
    });

    try {
      this.function.fitness(new ExampleIndividual());
      Util.shouldHaveThrownException();
    } catch (final FitnessException exception) {
      assertTrue(exception.getCause() instanceof MetricException);
    }

  }
}
