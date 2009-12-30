/**
 * EuclideanVectorMetric.java
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

import java.util.List;

import jmona.Metric;
import jmona.MetricException;
import jmona.impl.Range;

/**
 * Get the Euclidean distance between two specified vectors in Euclidean
 * n-space.
 * 
 * @param <T>
 *          The type of Number on which to measure.
 * @author jfinkels
 */
public class EuclideanVectorMetric<T extends Number> implements Metric<List<T>> {

  /**
   * Get the Euclidean distance between the two specified vectors.
   * 
   * Let <em>v<sub>1</sub></em> be the vector
   * <em>(x<sub>1</sub>, x<sub>2</sub>, &hellip;, x<sub>n</sub>)</em> and
   * <em>v<sub>2</sub></em> be the vector
   * <em>(y<sub>1</sub>, y<sub>2</sub>, &hellip;, y<sub>n</sub>)</em>, and
   * <em>d:<strong>R</strong><sup>n</sup>&rarr;<strong>R</strong></em> be the
   * Euclidean metric metric. Then
   * 
   * <em>d(v<sub>1</sub>, v<sub>2</sub>)=&radic;((x<sub>1</sub>-y<sub>1</sub>)<sup>2</sup>+(x<sub>2</sub>-y<sub>2</sub>)<sup>2</sup>+&hellip;+(x<sub>n</sub>-y<sub>n</sub>)<sup>2</sup>)</em>
   * .
   * 
   * @param vector1
   *          A vector in Euclidean n-space.
   * @param vector2
   *          Another vector in Euclidean n-space.
   * @return The Euclidean distance between the two specified vectors.
   * @throws MetricException
   *           If the two specified vectors are not the same size.
   * @see jmona.Metric#measure(java.lang.Object, java.lang.Object)
   */
  @Override
  public double measure(final List<T> vector1, final List<T> vector2)
      throws MetricException {
    if (vector1.size() != vector2.size()) {
      throw new MetricException(
          "Sizes of input vectors must be equal (they were " + vector1.size()
              + " and " + vector2.size() + ").");
    }

    double difference = 0;
    double squaredDifference = 0;
    double sum = 0;
    for (final int i : new Range(vector1.size())) {
      difference = vector1.get(i).doubleValue() - vector2.get(i).doubleValue();
      squaredDifference = Math.pow(difference, 2);
      sum += squaredDifference;
    }

    return Math.sqrt(sum);
  }

}
