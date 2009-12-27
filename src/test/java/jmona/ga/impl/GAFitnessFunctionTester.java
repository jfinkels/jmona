/**
 * GAFitnessFunctionTester.java
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
import static org.junit.Assert.fail;
import jmona.FitnessException;
import jmona.impl.example.ExampleIndividual;
import jmona.impl.example.ExampleMetric;
import jmona.test.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the GAFitnessFunction class.
 * 
 * @author jfinkels
 */
public class GAFitnessFunctionTester {

  /** The target fitness for ExampleIndividuals. */
  private static final int TARGET_FITNESS = 100;
  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The function under test. */
  private GAFitnessFunction<ExampleIndividual> function = null;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.function = new GAFitnessFunction<ExampleIndividual>();
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.GAFitnessFunction#fitness(jmona.Individual)}.
   */
  @Test
  public void testFitness() {
    final ExampleIndividual target = new ExampleIndividual(TARGET_FITNESS);
    this.function.setMetric(new ExampleMetric());
    this.function.setTarget(target);

    try {
      assertEquals(2.0 / TARGET_FITNESS, this.function
          .fitness(new ExampleIndividual(TARGET_FITNESS / 2)), ZERO_DELTA);
      assertEquals(1.0 / TARGET_FITNESS, this.function
          .fitness(new ExampleIndividual(0)), ZERO_DELTA);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }

    try {
      assertEquals(Double.POSITIVE_INFINITY, this.function.fitness(target),
          ZERO_DELTA);
    } catch (final FitnessException exception) {
      Util.fail(exception);
    }
  }

  /**
   * Test method for {@link jmona.ga.impl.GAFitnessFunction#sanityCheck()}.
   */
  @Test
  public void testSanityCheck() {
    try {
      this.function.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      // metric has not been set
      this.function.setMetric(new ExampleMetric());
    }

    try {
      this.function.sanityCheck();
      Util.shouldHaveThrownException();
    } catch (final NullPointerException exception) {
      // target has not been set
      this.function.setTarget(new ExampleIndividual(TARGET_FITNESS));
    }

    try {
      this.function.sanityCheck();
    } catch (final NullPointerException exception) {
      Util.fail(exception);
    }
  }

  /** Test method for when Exceptions are thrown. */
  @Test
  public void testExceptions() {
    this.function.setMetric(null);
    try {
      this.function.fitness(new ExampleIndividual());
      Util.shouldHaveThrownException();
    } catch (final FitnessException exception) {
      assertTrue(exception.getCause() instanceof NullPointerException);
    }
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.GAFitnessFunction#setMetric(jmona.Metric)}.
   */
  @Test
  public void testSetMetric() {
    this.function.setMetric(new ExampleMetric());
  }

  /**
   * Test method for
   * {@link jmona.ga.impl.GAFitnessFunction#setTarget(jmona.Individual)}.
   */
  @Test
  public void testSetTarget() {
    this.function.setTarget(new ExampleIndividual(TARGET_FITNESS));
  }

}
