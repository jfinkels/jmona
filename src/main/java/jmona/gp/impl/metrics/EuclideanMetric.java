/**
 * EuclideanMetric.java
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
package jmona.gp.impl.metrics;

import jmona.gp.impl.Metric;

/**
 * The one-dimensional Euclidean metric, which measures the distance between two
 * real numbers.
 * 
 * The Euclidean distance between two numbers is defined as
 * <em>|element1 - element2|</em>.
 * 
 * @author jfinkels
 */
public class EuclideanMetric implements Metric<Double> {

  /**
   * Get the Euclidean distance between the two specified numbers, defined as
   * <em>|element1 - element2|</em>.
   * 
   * @param element1
   *          A number.
   * @param element2
   *          Another number.
   * @return The absolute value of the difference of the two specified numbers,
   *         that is, <em>|element1 - element2|</em>.
   * @see jmona.gp.impl.Metric#measure(Object, Object)
   */
  @Override
  public double measure(final Double element1, final Double element2) {
    return Math.abs(element1 - element2);
  }

}
