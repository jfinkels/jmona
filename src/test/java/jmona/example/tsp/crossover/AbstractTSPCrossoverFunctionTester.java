/**
 * AbstractTSPCrossoverFunctionTester.java
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
package jmona.example.tsp.crossover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jmona.CrossoverException;
import jmona.CrossoverFunction;
import jmona.InitializationException;
import jmona.example.tsp.TourFactory;
import jmona.functional.MutableRange;
import jmona.functional.Range;
import jmona.impl.mutable.MutableInteger;
import jmona.test.Util;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the traveling salesman problem CrossoverFunction classes.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public abstract class AbstractTSPCrossoverFunctionTester {

  /** The length of the Tours under test. */
  public static final int LENGTH = 30;
  /** The Logger for this class. */
  private static final transient Logger LOG = Logger
      .getLogger(AbstractTSPCrossoverFunctionTester.class);
  /** The number of independent crossovers to perform. */
  public static final int NUM_TESTS = 100;
  /** The function under test. */
  private CrossoverFunction<List<MutableInteger>> function = null;
  /**
   * A tour on which to perform crossover which contains an increasing sequence
   * of integers.
   */
  private List<MutableInteger> tour1 = null;

  /**
   * A tour on which to perform crossover which contains a decreasing sequence
   * of integers.
   */
  private List<MutableInteger> tour2 = null;
  /**
   * A tour on which to perform crossover which contains a random sequence of
   * integers.
   */
  private List<MutableInteger> tour3 = null;
  /**
   * A tour on which to perform crossover which contains a random sequence of
   * integers.
   */
  private List<MutableInteger> tour4 = null;

  /**
   * Instantiate this test class with the specified CrossoverFunction.
   * 
   * @param initialFunction
   *          The CrossoverFunction under test.
   */
  public AbstractTSPCrossoverFunctionTester(
      final CrossoverFunction<List<MutableInteger>> initialFunction) {
    this.function = initialFunction;
  }

  /**
   * Get the CrossoverFunction under test in this class.
   * 
   * @return The CrossoverFunction under test in this class.
   */
  public CrossoverFunction<List<MutableInteger>> function() {
    return this.function;
  }

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.tour1 = new Vector<MutableInteger>();
    this.tour2 = new Vector<MutableInteger>();

    final TourFactory factory = new TourFactory(LENGTH);

    for (final int i : new Range(LENGTH)) {
      this.tour1.add(new MutableInteger(i));
      this.tour2.add(new MutableInteger(LENGTH - 1 - i));
    }

    try {
      this.tour3 = factory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    try {
      this.tour4 = factory.createObject();
    } catch (final InitializationException exception) {
      Util.fail(exception);
    }

    assertEquals(LENGTH, this.tour1.size());
    assertEquals(LENGTH, this.tour2.size());
    assertEquals(LENGTH, this.tour3.size());
    assertEquals(LENGTH, this.tour4.size());

    for (final MutableInteger i : new MutableRange(LENGTH)) {
      assertTrue(this.tour1.contains(i));
      assertTrue(this.tour2.contains(i));
      assertTrue(this.tour3.contains(i));
      assertTrue(this.tour4.contains(i));
    }
  }

  /**
   * The test for the {@link jmona.CrossoverFunction#crossover(Object, Object)}
   * method in each traveling salesman problem CrossoverFunction test class.
   */
  @Test
  public void testCrossover() {
    LOG.debug("Tour 1 before: " + this.tour1);
    LOG.debug("Tour 2 before: " + this.tour2);

    try {
      this.function.crossover(this.tour1, this.tour2);
    } catch (final CrossoverException exception) {
      Util.fail(exception);
    }

    LOG.debug("Tour 1 after: " + this.tour1);
    LOG.debug("Tour 2 after: " + this.tour2);

    assertEquals(LENGTH, this.tour1.size());
    assertEquals(LENGTH, this.tour2.size());

    for (final MutableInteger i : new MutableRange(LENGTH)) {
      assertTrue(this.tour1.contains(i));
      assertTrue(this.tour2.contains(i));
    }

    LOG.debug("Tour 3 before: " + this.tour3);
    LOG.debug("Tour 4 before: " + this.tour4);

    try {
      this.function.crossover(this.tour3, this.tour4);
    } catch (final CrossoverException exception) {
      Util.fail(exception);
    }

    LOG.debug("Tour 3 after: " + this.tour3);
    LOG.debug("Tour 4 after: " + this.tour4);

    assertEquals(LENGTH, this.tour3.size());
    assertEquals(LENGTH, this.tour4.size());

    for (final MutableInteger i : new MutableRange(LENGTH)) {
      assertTrue(this.tour3.contains(i));
      assertTrue(this.tour4.contains(i));
    }

  }

  /**
   * Get the tour containing the increasing sequence.
   * 
   * @return The tour containing the increasing sequence.
   */
  protected List<MutableInteger> tour1() {
    return this.tour1;
  }

  /**
   * Get the tour containing the decreasing sequence.
   * 
   * @return The tour containing the decreasing sequence.
   */
  protected List<MutableInteger> tour2() {
    return this.tour2;
  }
}
