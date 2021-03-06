/**
 * EuclideanVectorMetric.java
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
package jmona.impl.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import jfcommon.test.TestUtils;
import jmona.MetricException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the EuclideanVectorMetric class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class EuclideanVectorMetricTester extends
    AbstractMetricTester<List<Double>, EuclideanVectorMetric<Double>> {

  /** Zero. */
  public static final double ZERO_DELTA = 0.0;
  /** The u vector for this test. */
  private List<Double> u = null;
  /** The v vector for this test. */
  private List<Double> v = null;
  /** The w vector for this test. */
  private List<Double> w = null;

  /** Establish a fixture for tests in this class. */
  @Before
  @Override
  public final void setUp() {
    this.v = new Vector<Double>();
    this.u = new Vector<Double>();
    this.w = new Vector<Double>();

    this.v.add(0.0);
    this.v.add(0.0);
    this.v.add(0.0);

    this.u.add(1.0);
    this.u.add(0.0);
    this.u.add(0.0);

    this.w.add(1.0);
    this.w.add(-1.0);
    this.w.add(0.5);

    this.setMetric(new EuclideanVectorMetric<Double>());
    this.setX(this.v);
    this.setY(this.u);
    this.setZ(this.w);
  }

  /** Test for throwing Exceptions. */
  @Test
  public void testException() {
    final EuclideanVectorMetric<Double> metric = new EuclideanVectorMetric<Double>();

    final List<Double> vector1 = new Vector<Double>();
    final List<Double> vector2 = new Vector<Double>();

    vector1.add(0.0);

    try {
      metric.measure(vector1, vector2);
      TestUtils.shouldHaveThrownException();
    } catch (final MetricException exception) {
      // the two vectors have different sizes
      assertFalse(vector1.size() == vector2.size());
      assertTrue(exception instanceof MetricException);
    }
  }

  /** Test the metric with known values. */
  @Test
  public void testKnownValues() {
    final EuclideanVectorMetric<Double> metric = new EuclideanVectorMetric<Double>();

    final List<Double> vector1 = new Vector<Double>();
    final List<Double> vector2 = new Vector<Double>();

    vector1.add(1.0);
    vector1.add(0.0);
    vector1.add(0.0);

    vector2.add(0.0);
    vector2.add(1.0);
    vector2.add(0.0);

    try {
      assertEquals(Math.sqrt(2), metric.measure(vector1, vector2), ZERO_DELTA);
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    }

    vector1.clear();
    vector2.clear();

    vector1.add(0.0);
    vector1.add(3.0);
    vector1.add(0.0);

    vector2.add(0.0);
    vector2.add(0.0);
    vector2.add(4.0);

    try {
      assertEquals(5.0, metric.measure(vector1, vector2), ZERO_DELTA);
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    }
  }
}
