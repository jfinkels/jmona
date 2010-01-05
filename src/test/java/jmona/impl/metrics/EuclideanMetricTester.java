/**
 * EuclideanMetricTester.java
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

import org.junit.Before;

/**
 * Test class for the EuclideanMetric class.
 * 
 * @author Jeffrey Finkelstein
 * @since 0.1
 */
public class EuclideanMetricTester extends
    AbstractMetricTester<Double, EuclideanMetric<Double>> {

  /** The x value for this test. */
  public static final double X = 1.0;
  /** The y value for this test. */
  public static final double Y = 2.0;
  /** The z value for this test. */
  public static final double Z = -3.0;

  /** Establish a fixture for tests in this class. */
  @Before
  public final void setUp() {
    this.setMetric(new EuclideanMetric<Double>());
    this.setX(X);
    this.setY(Y);
    this.setZ(Z);
  }
}
