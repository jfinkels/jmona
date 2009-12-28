/**
 * AbstractMetricTester.java
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
package jmona.impl.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jmona.Metric;
import jmona.exceptions.MetricException;
import jmona.test.Util;

import org.junit.Test;

/**
 * Base test class for Metric test classes.
 * 
 * @param <M>
 *          The type of Metric under test.
 * @param <E>
 *          The type of the domain of the Metric.
 * @author jfinkels
 */
public class AbstractMetricTester<E, M extends Metric<E>> {
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
   * Test method for the metric.
   */
  @Test
  public void testMeasure() {
    try {

      // property 1: non-negativity
      assertTrue(this.metric.measure(x, x) >= 0);
      assertTrue(this.metric.measure(x, y) >= 0);
      assertTrue(this.metric.measure(x, z) >= 0);
      assertTrue(this.metric.measure(y, x) >= 0);
      assertTrue(this.metric.measure(y, y) >= 0);
      assertTrue(this.metric.measure(y, z) >= 0);
      assertTrue(this.metric.measure(z, x) >= 0);
      assertTrue(this.metric.measure(z, y) >= 0);
      assertTrue(this.metric.measure(z, z) >= 0);

      // property 2: identity of indiscernibles
      assertEquals(0, this.metric.measure(x, x), 0);
      assertEquals(0, this.metric.measure(y, y), 0);
      assertEquals(0, this.metric.measure(z, z), 0);

      // property 3: symmetry
      assertEquals(this.metric.measure(x, x), this.metric.measure(x, x), 0);
      assertEquals(this.metric.measure(x, y), this.metric.measure(y, x), 0);
      assertEquals(this.metric.measure(x, z), this.metric.measure(z, x), 0);
      assertEquals(this.metric.measure(y, x), this.metric.measure(x, y), 0);
      assertEquals(this.metric.measure(y, y), this.metric.measure(y, y), 0);
      assertEquals(this.metric.measure(y, z), this.metric.measure(z, y), 0);
      assertEquals(this.metric.measure(z, x), this.metric.measure(x, z), 0);
      assertEquals(this.metric.measure(z, y), this.metric.measure(y, z), 0);
      assertEquals(this.metric.measure(z, z), this.metric.measure(z, z), 0);

      // property 4: triangle inequality
      assertTrue(this.metric.measure(x, y) <= this.metric.measure(x, z)
          + this.metric.measure(z, y));
      assertTrue(this.metric.measure(x, z) <= this.metric.measure(x, y)
          + this.metric.measure(y, z));
      assertTrue(this.metric.measure(y, z) <= this.metric.measure(y, x)
          + this.metric.measure(x, z));
    } catch (final MetricException exception) {
      Util.fail(exception);
    }
  }

}
