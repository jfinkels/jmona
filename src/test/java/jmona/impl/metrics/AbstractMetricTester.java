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
 
import org.junit.Test;
 
/**
* Base test class for Metric test classes.
*
* @param <M>
* The type of Metric under test.
* @param <E>
* The type of the domain of the Metric.
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
* The metric for this test.
*/
  protected void setMetric(final M newMetric) {
    this.metric = newMetric;
  }
 
  /**
* Set the x value for this test.
*
* @param newX
* The x value for this test.
*/
  protected void setX(final E newX) {
    this.x = newX;
  }
 
  /**
* Set the y value for this test.
*
* @param newY
* The y value for this test.
*/
  protected void setY(final E newY) {
    this.y = newY;
  }
 
  /**
* Set the z value for this test.
*
* @param newZ
* The z value for this test.
*/
  protected void setZ(final E newZ) {
    this.z = newZ;
  }
 
  /**
* Test method for the metric.
*/
  @Test
  public void testMeasure() {
    // property 1: non-negativity
    assertTrue(metric.measure(x, x) >= 0);
    assertTrue(metric.measure(x, y) >= 0);
    assertTrue(metric.measure(x, z) >= 0);
    assertTrue(metric.measure(y, x) >= 0);
    assertTrue(metric.measure(y, y) >= 0);
    assertTrue(metric.measure(y, z) >= 0);
    assertTrue(metric.measure(z, x) >= 0);
    assertTrue(metric.measure(z, y) >= 0);
    assertTrue(metric.measure(z, z) >= 0);
 
    // property 2: identity of indiscernibles
    assertEquals(0, metric.measure(x, x), 0);
    assertEquals(0, metric.measure(y, y), 0);
    assertEquals(0, metric.measure(z, z), 0);
 
    // property 3: symmetry
    assertEquals(metric.measure(x, x), metric.measure(x, x), 0);
    assertEquals(metric.measure(x, y), metric.measure(y, x), 0);
    assertEquals(metric.measure(x, z), metric.measure(z, x), 0);
    assertEquals(metric.measure(y, x), metric.measure(x, y), 0);
    assertEquals(metric.measure(y, y), metric.measure(y, y), 0);
    assertEquals(metric.measure(y, z), metric.measure(z, y), 0);
    assertEquals(metric.measure(z, x), metric.measure(x, z), 0);
    assertEquals(metric.measure(z, y), metric.measure(y, z), 0);
    assertEquals(metric.measure(z, z), metric.measure(z, z), 0);
 
    // property 4: triangle inequality
    assertTrue(metric.measure(x, y) <= metric.measure(x, z)
        + metric.measure(z, y));
    assertTrue(metric.measure(x, z) <= metric.measure(x, y)
        + metric.measure(y, z));
    assertTrue(metric.measure(y, z) <= metric.measure(y, x)
        + metric.measure(x, z));
  }
 
}
