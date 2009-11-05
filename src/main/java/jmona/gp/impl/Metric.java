/**
 * Metric.java
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
package jmona.gp.impl;

/**
 * A metric on elements of type E.
 * 
 * A metric is a function which measures the distance between two elements of a
 * set. Formally, a metric on a set, <em>E</em> is a function,
 * <em>d: E x E &rarr; <strong>R</strong></em>, such that &forall;<em>x,y,z</em>
 * &isin;<em>E</em>:
 * <ol>
 * <li><em>d(x,y)</em> &ge; 0 (non-negativity)</li>
 * <li><em>d(x,y)</em> = 0 if and only if <em>x</em> = <em>y</em> (identity of
 * indiscernibles)</li>
 * <li><em>d(x,y)</em> = <em>d(y,x)</em> (symmetry)</li>
 * <li><em>d(x,y)</em> &le; <em>d(x,z)</em> + <em>d(z,y)</em> (triangle
 * inequality)</li>
 * </ol>
 * 
 * For more information, see the following Wikipedia articles:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Metric_(mathematics)">Metric</a></li>
 * <li><a href="http://en.wikipedia.org/wiki/Metric_space">Metric space</a></li>
 * </ul>
 * 
 * @param <E>
 *          The domain of this metric.
 * @author jfinkels
 */
public interface Metric<E> {
  /**
   * Measure the distance between the two specified elements.
   * 
   * Must always have the properties of a metric function, namely
   * non-negativity, identity of indiscernibles, symmetry, and the triangle
   * inequality.
   * 
   * @param element1
   *          An element.
   * @param element2
   *          Another element.
   * @return The distance between the two specified elements.
   */
  double measure(final E element1, final E element2);
}
