/**
 * AbstractMetricTester.java
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
import static org.junit.Assert.assertTrue;
import jfcommon.test.TestUtils;
import jmona.Metric;
import jmona.MetricException;

import org.junit.Before;
import org.junit.Test;

/**
 * Base test class for Metric test classes.
 * 
 * @param <M>
 *          The type of Metric under test.
 * @param <E>
 *          The type of the domain of the Metric.
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public abstract class AbstractMetricTester<E, M extends Metric<E>> {
  /** The metric for this test. */
  private M metric = null;
  /** The x value for this test. */
  private E x = null;
  /** The y value for this test. */
  private E y = null;
  /** The z value for this test. */
  private E z = null;

  /**
   * Set the metric for this test.
   * 
   * @param newMetric
   *          The metric for this test.
   */
  protected void setMetric(final M newMetric) {
    this.metric = newMetric;
  }

  /**
   * Set the x value for this test.
   * 
   * @param newX
   *          The x value for this test.
   */
  protected void setX(final E newX) {
    this.x = newX;
  }

  /**
   * Set the y value for this test.
   * 
   * @param newY
   *          The y value for this test.
   */
  protected void setY(final E newY) {
    this.y = newY;
  }

  /**
   * Set the z value for this test.
   * 
   * @param newZ
   *          The z value for this test.
   */
  protected void setZ(final E newZ) {
    this.z = newZ;
  }

  /**
   * Establish a fixture for tests in this class.
   * 
   * Concrete implementing subclasses should use the {@link #setMetric(Metric)},
   * {@link #setX(Object)}, {@link #setY(Object)} and {@link #setZ(Object)}
   * methods to establish the fixture.
   */
  @Before
  public abstract void setUp();

  /**
   * Test method for the metric.
   */
  @Test
  public void testMeasure() {
    try {

      // property 1: non-negativity
      assertTrue(this.metric.measure(this.x, this.x) >= 0);
      assertTrue(this.metric.measure(this.x, this.y) >= 0);
      assertTrue(this.metric.measure(this.x, this.z) >= 0);
      assertTrue(this.metric.measure(this.y, this.x) >= 0);
      assertTrue(this.metric.measure(this.y, this.y) >= 0);
      assertTrue(this.metric.measure(this.y, this.z) >= 0);
      assertTrue(this.metric.measure(this.z, this.x) >= 0);
      assertTrue(this.metric.measure(this.z, this.y) >= 0);
      assertTrue(this.metric.measure(this.z, this.z) >= 0);

      // property 2: identity of indiscernibles
      assertEquals(0, this.metric.measure(this.x, this.x), 0);
      assertEquals(0, this.metric.measure(this.y, this.y), 0);
      assertEquals(0, this.metric.measure(this.z, this.z), 0);

      // property 3: symmetry
      assertEquals(this.metric.measure(this.x, this.x), this.metric.measure(
          this.x, this.x), 0);
      assertEquals(this.metric.measure(this.x, this.y), this.metric.measure(
          this.y, this.x), 0);
      assertEquals(this.metric.measure(this.x, this.z), this.metric.measure(
          this.z, this.x), 0);
      assertEquals(this.metric.measure(this.y, this.x), this.metric.measure(
          this.x, this.y), 0);
      assertEquals(this.metric.measure(this.y, this.y), this.metric.measure(
          this.y, this.y), 0);
      assertEquals(this.metric.measure(this.y, this.z), this.metric.measure(
          this.z, this.y), 0);
      assertEquals(this.metric.measure(this.z, this.x), this.metric.measure(
          this.x, this.z), 0);
      assertEquals(this.metric.measure(this.z, this.y), this.metric.measure(
          this.y, this.z), 0);
      assertEquals(this.metric.measure(this.z, this.z), this.metric.measure(
          this.z, this.z), 0);

      // property 4: triangle inequality
      assertTrue(this.metric.measure(this.x, this.y) <= this.metric.measure(
          this.x, this.z)
          + this.metric.measure(this.z, this.y));
      assertTrue(this.metric.measure(this.x, this.z) <= this.metric.measure(
          this.x, this.y)
          + this.metric.measure(this.y, this.z));
      assertTrue(this.metric.measure(this.y, this.z) <= this.metric.measure(
          this.y, this.x)
          + this.metric.measure(this.x, this.z));
    } catch (final MetricException exception) {
      TestUtils.fail(exception);
    }
  }

}
